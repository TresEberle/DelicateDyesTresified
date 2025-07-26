package tres.delicate_dyes_tresified.common.entity;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.neoforge.registries.DeferredHolder;
import tres.delicate_dyes_tresified.core.init.BlockInit;
import tres.delicate_dyes_tresified.core.init.EntityInit;
import tres.delicate_dyes_tresified.core.util.DyeColorUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ModSheep extends Sheep {
	private static final EntityDataAccessor<Byte> DATA_WOOL_ID;
	protected static final Map<DyeColorUtil, ItemLike> WOOL_BY_COLOR;
	private static final Map<DyeColor, ItemLike> VANILLA_WOOL_BY_COLOR;

	public ModSheep(EntityType<? extends ModSheep> type, Level level) {
		super(type, level);
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(2, new BreedGoal(this, (double)1.0F, Sheep.class));
	}

	protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
		super.defineSynchedData(pBuilder);
		pBuilder.define(DATA_WOOL_ID, (byte)0);
	}

	public static void convertToVanilla(ModSheep oldSheep, DyeColor color) {
		Level level = oldSheep.level();
		if (!level.isClientSide) {
			Sheep sheep = new Sheep(EntityType.SHEEP, level);
			sheep.load(sheep.saveWithoutId(new CompoundTag()));
			sheep.setColor(color);
			convertSheep(oldSheep, sheep);
		}

	}

	public static void convertToDyenamics(Sheep oldSheep, DyeColorUtil color) {
		Level level = oldSheep.level();
		if (!level.isClientSide) {
			ModSheep sheep = new ModSheep((EntityType) EntityInit.SHEEP.get(), level);
			sheep.load(sheep.saveWithoutId(new CompoundTag()));
			sheep.setColor(color);
			convertSheep(oldSheep, sheep);
		}

	}

	private static void convertSheep(Sheep oldSheep, Sheep sheep) {
		Level level = oldSheep.level();
		if (!level.isClientSide) {
			sheep.setAge(oldSheep.getAge());
			sheep.copyPosition(oldSheep);
			level.addFreshEntity(sheep);
			oldSheep.remove(RemovalReason.DISCARDED);
		}

	}

	public ResourceKey<LootTable> getDefaultLootTable() {
		return this.isSheared() ? this.getType().getDefaultLootTable() : (ResourceKey)EntityInit.SHEEP_LOOT.get(this.getDyenamicColor().getTranslationKey());
	}

	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (itemstack.getItem() instanceof DyeItem) {
			if (!this.level().isClientSide) {
				convertToVanilla(this, ((DyeItem)itemstack.getItem()).getDyeColor());
				itemstack.shrink(1);
				return InteractionResult.SUCCESS;
			} else {
				return InteractionResult.CONSUME;
			}
		} else if (itemstack.getItem().equals(Items.SHEEP_SPAWN_EGG)) {
			if (!this.level().isClientSide) {
				ModSheep sheep = new ModSheep((EntityType)EntityInit.SHEEP.get(), this.level());
				sheep.copyPosition(this);
				sheep.setBaby(true);
				sheep.setColor(this.getDyenamicColor());
				this.level().addFreshEntity(sheep);
				itemstack.shrink(1);
				return InteractionResult.SUCCESS;
			} else {
				return InteractionResult.CONSUME;
			}
		} else {
			return super.mobInteract(player, hand);
		}
	}

	public void shear(SoundSource category) {
		this.level().playSound((Player)null, this, SoundEvents.SHEEP_SHEAR, category, 1.0F, 1.0F);
		this.setSheared(true);
		int i = 1 + this.random.nextInt(3);
		DyeColorUtil color = this.getDyenamicColor();

		for(int j = 0; j < i; ++j) {
			ItemEntity itementity;
			if (color.getId() > 15) {
				itementity = this.spawnAtLocation((ItemLike)WOOL_BY_COLOR.get(this.getDyenamicColor()), 1);
			} else {
				itementity = this.spawnAtLocation((ItemLike)VANILLA_WOOL_BY_COLOR.get(color.getVanillaColor()), 1);
			}

			if (itementity != null) {
				itementity.setDeltaMovement(itementity.getDeltaMovement().add((double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F), (double)(this.random.nextFloat() * 0.05F), (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F)));
			}
		}

	}

	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putByte("Color", (byte)this.getDyenamicColor().getId());
	}

	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setColor(DyeColorUtil.byId(compound.getByte("Color")));
	}

	public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity entity) {
		return new ClientboundAddEntityPacket(this, entity);
	}

	public DyeColorUtil getDyenamicColor() {
		return DyeColorUtil.byId((Byte)this.entityData.get(DATA_WOOL_ID) & 127);
	}

	public void setColor(DyeColor color) {
		byte b0 = (Byte)this.entityData.get(DATA_WOOL_ID);
		this.entityData.set(DATA_WOOL_ID, (byte)(b0 & -128 | color.getId() & 15));
	}

	public void setColor(DyeColorUtil color) {
		byte b0 = (Byte)this.entityData.get(DATA_WOOL_ID);
		this.entityData.set(DATA_WOOL_ID, (byte)(b0 & -128 | color.getId() & 127));
	}

	public boolean isSheared() {
		return (Byte)this.entityData.get(DATA_WOOL_ID) < 0;
	}

	public void setSheared(boolean sheared) {
		byte b0 = (Byte)this.entityData.get(DATA_WOOL_ID);
		if (sheared) {
			this.entityData.set(DATA_WOOL_ID, (byte)(b0 | -128));
		} else {
			this.entityData.set(DATA_WOOL_ID, (byte)(b0 & 127));
		}

	}

	public Sheep getBreedOffspring(ServerLevel world, AgeableMob mate) {
		if (mate instanceof ModSheep parent) {
			ModSheep child = (ModSheep)((EntityType)EntityInit.SHEEP.get()).create(world);
			child.setColor(this.getDyeColorMixFromParents(this, parent));
			return child;
		} else {
			Sheep parent = (Sheep)mate;
			DyeColorUtil color = this.getDyeColorMixFromParents(this, parent);
			if (color.getId() < 16) {
				Sheep child = (Sheep)EntityType.SHEEP.create(world);
				child.setColor(color.getAnalogue());
				return child;
			} else {
				ModSheep child = (ModSheep)((EntityType)EntityInit.SHEEP.get()).create(world);
				child.setColor(color);
				return child;
			}
		}
	}

	public boolean canMate(Animal otherAnimal) {
		if (otherAnimal == this) {
			return false;
		} else if (otherAnimal instanceof Sheep) {
			return this.isInLove() && otherAnimal.isInLove();
		} else if (otherAnimal.getClass() != this.getClass()) {
			return false;
		} else {
			return this.isInLove() && otherAnimal.isInLove();
		}
	}

	protected DyeColorUtil getDyeColorMixFromParents(Sheep father, ModSheep mother) {
		return this.level().random.nextBoolean() ? DyeColorUtil.byId(father.getColor().getId()) : mother.getDyenamicColor();
	}

	protected DyeColorUtil getDyeColorMixFromParents(ModSheep mother, Sheep father) {
		return this.getDyeColorMixFromParents(father, mother);
	}

	protected DyeColorUtil getDyeColorMixFromParents(ModSheep father, ModSheep mother) {
		return this.level().random.nextBoolean() ? father.getDyenamicColor() : mother.getDyenamicColor();
	}

	public List<ItemStack> onSheared(@Nullable Player player, ItemStack item, Level level, BlockPos pos) {
		level.playSound((Player)null, this, SoundEvents.SHEEP_SHEAR, player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
		if (this.level().isClientSide) {
			return Collections.emptyList();
		} else {
			this.setSheared(true);
			int i = 1 + this.random.nextInt(3);
			DyeColorUtil color = this.getDyenamicColor();
			ItemStack stack;
			if (color.getId() > 15) {
				stack = new ItemStack((ItemLike)WOOL_BY_COLOR.get(color));
			} else {
				stack = new ItemStack((ItemLike)VANILLA_WOOL_BY_COLOR.get(color.getVanillaColor()));
			}

			List<ItemStack> items = new ArrayList();

			for(int j = 0; j < i; ++j) {
				items.add(stack);
			}

			return items;
		}
	}

	public ItemStack getPickResult() {
		SpawnEggItem spawneggitem = SpawnEggItem.byId(EntityType.SHEEP);
		return spawneggitem == null ? null : new ItemStack(spawneggitem);
	}

	static {
		DATA_WOOL_ID = SynchedEntityData.defineId(ModSheep.class, EntityDataSerializers.BYTE);
		WOOL_BY_COLOR = (Map) Util.make(Maps.newEnumMap(DyeColorUtil.class), (map) -> {
			for(DyeColorUtil color : DyeColorUtil.dyenamicValues()) {
				map.put(color, (ItemLike)((DeferredHolder)((Map) BlockInit.DYED_BLOCKS.get(color.getSerializedName())).get("wool")).get());
			}

		});
		VANILLA_WOOL_BY_COLOR = (Map)Util.make(Maps.newEnumMap(DyeColor.class), (map) -> {
			map.put(DyeColor.WHITE, Blocks.WHITE_WOOL);
			map.put(DyeColor.ORANGE, Blocks.ORANGE_WOOL);
			map.put(DyeColor.MAGENTA, Blocks.MAGENTA_WOOL);
			map.put(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_WOOL);
			map.put(DyeColor.YELLOW, Blocks.YELLOW_WOOL);
			map.put(DyeColor.LIME, Blocks.LIME_WOOL);
			map.put(DyeColor.PINK, Blocks.PINK_WOOL);
			map.put(DyeColor.GRAY, Blocks.GRAY_WOOL);
			map.put(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_WOOL);
			map.put(DyeColor.CYAN, Blocks.CYAN_WOOL);
			map.put(DyeColor.PURPLE, Blocks.PURPLE_WOOL);
			map.put(DyeColor.BLUE, Blocks.BLUE_WOOL);
			map.put(DyeColor.BROWN, Blocks.BROWN_WOOL);
			map.put(DyeColor.GREEN, Blocks.GREEN_WOOL);
			map.put(DyeColor.RED, Blocks.RED_WOOL);
			map.put(DyeColor.BLACK, Blocks.BLACK_WOOL);
		});
	}
}
