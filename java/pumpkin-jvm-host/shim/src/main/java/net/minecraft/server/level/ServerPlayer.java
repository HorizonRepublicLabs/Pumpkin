package net.minecraft.server.level;

import com.mojang.authlib.GameProfile;
import com.mojang.datafixers.util.Either;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dialog.Dialog;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.permissions.PermissionSet;
import net.minecraft.stats.Stat;
import net.minecraft.util.Unit;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import net.minecraft.world.entity.animal.nautilus.AbstractNautilus;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.warden.WardenSpawnTracker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.CommandBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class ServerPlayer extends Player {

    public ServerGamePacketListenerImpl connection;

    public final ServerPlayerGameMode gameMode = null;

    public ServerPlayer(MinecraftServer server, ServerLevel level, GameProfile gameProfile, ClientInformation clientInformation) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.<init>:(Lnet/minecraft/server/MinecraftServer;Lnet/minecraft/server/level/ServerLevel;Lcom/mojang/authlib/GameProfile;Lnet/minecraft/server/level/ClientInformation;)V");
    }

    public BlockPos adjustSpawnLocation(ServerLevel level, BlockPos spawnSuggestion) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.adjustSpawnLocation:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/core/BlockPos;");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    public void giveExperienceLevels(int amount) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.giveExperienceLevels:(I)V");
    }

    public void onEnchantmentPerformed(ItemStack itemStack, int enchantmentCost) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.onEnchantmentPerformed:(Lnet/minecraft/world/item/ItemStack;I)V");
    }

    public void onEnterCombat() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.onEnterCombat:()V");
    }

    public void onLeaveCombat() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.onLeaveCombat:()V");
    }

    public void onInsideBlock(BlockState state) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.onInsideBlock:(Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    protected ItemCooldowns createItemCooldowns() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.createItemCooldowns:()Lnet/minecraft/world/item/ItemCooldowns;");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.tick:()V");
    }

    protected void tickRegeneration() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.tickRegeneration:()V");
    }

    public void handleShoulderEntities() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.handleShoulderEntities:()V");
    }

    protected void removeEntitiesOnShoulder() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.removeEntitiesOnShoulder:()V");
    }

    public void resetFallDistance() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.resetFallDistance:()V");
    }

    public void die(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.die:(Lnet/minecraft/world/damagesource/DamageSource;)V");
    }

    public void awardKillScore(Entity victim, DamageSource killingBlow) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.awardKillScore:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;)V");
    }

    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.hurtServer:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z");
    }

    public boolean canHarmPlayer(Player target) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.canHarmPlayer:(Lnet/minecraft/world/entity/player/Player;)Z");
    }

    protected void onAttributeUpdated(Holder<Attribute> attribute) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.onAttributeUpdated:(Lnet/minecraft/core/Holder;)V");
    }

    public void forceSetRotation(float yRot, boolean relativeY, float xRot, boolean relativeX) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.forceSetRotation:(FZFZ)V");
    }

    public boolean broadcastToPlayer(ServerPlayer player) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.broadcastToPlayer:(Lnet/minecraft/server/level/ServerPlayer;)Z");
    }

    public void take(Entity entity, int orgCount) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.take:(Lnet/minecraft/world/entity/Entity;I)V");
    }

    public Either<Player.BedSleepingProblem, Unit> startSleepInBed(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.startSleepInBed:(Lnet/minecraft/core/BlockPos;)Lcom/mojang/datafixers/util/Either;");
    }

    public void startSleeping(BlockPos bedPosition) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.startSleeping:(Lnet/minecraft/core/BlockPos;)V");
    }

    public void stopSleepInBed(boolean forcefulWakeUp, boolean updateLevelList) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.stopSleepInBed:(ZZ)V");
    }

    public boolean isInvulnerableTo(ServerLevel level, DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.isInvulnerableTo:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;)Z");
    }

    protected void onChangedBlock(ServerLevel level, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.onChangedBlock:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;)V");
    }

    protected void checkFallDamage(double ya, boolean onGround, BlockState onState, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.checkFallDamage:(DZLnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;)V");
    }

    public void onExplosionHit(Entity explosionCausedBy) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.onExplosionHit:(Lnet/minecraft/world/entity/Entity;)V");
    }

    protected void pushEntities() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.pushEntities:()V");
    }

    public void openTextEdit(SignBlockEntity sign, boolean isFrontText) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.openTextEdit:(Lnet/minecraft/world/level/block/entity/SignBlockEntity;Z)V");
    }

    public void openDialog(Holder<Dialog> dialog) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.openDialog:(Lnet/minecraft/core/Holder;)V");
    }

    public OptionalInt openMenu(MenuProvider provider) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.openMenu:(Lnet/minecraft/world/MenuProvider;)Ljava/util/OptionalInt;");
    }

    public OptionalInt openMenu(MenuProvider provider, java.util.function.Consumer<net.minecraft.network.RegistryFriendlyByteBuf> extraDataWriter) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.openMenu:(Lnet/minecraft/world/MenuProvider;Ljava/util/function/Consumer;)Ljava/util/OptionalInt;");
    }

    public void sendMerchantOffers(int containerId, MerchantOffers offers, int merchantLevel, int merchantXp, boolean showProgressBar, boolean canRestock) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.sendMerchantOffers:(ILnet/minecraft/world/item/trading/MerchantOffers;IIZZ)V");
    }

    public void openHorseInventory(AbstractHorse horse, Container container) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.openHorseInventory:(Lnet/minecraft/world/entity/animal/equine/AbstractHorse;Lnet/minecraft/world/Container;)V");
    }

    public void openNautilusInventory(AbstractNautilus nautilus, Container container) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.openNautilusInventory:(Lnet/minecraft/world/entity/animal/nautilus/AbstractNautilus;Lnet/minecraft/world/Container;)V");
    }

    public void openItemGui(ItemStack itemStack, InteractionHand hand) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.openItemGui:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/InteractionHand;)V");
    }

    public void openCommandBlock(CommandBlockEntity commandBlock) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.openCommandBlock:(Lnet/minecraft/world/level/block/entity/CommandBlockEntity;)V");
    }

    public void closeContainer() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.closeContainer:()V");
    }

    public void doCloseContainer() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.doCloseContainer:()V");
    }

    public void rideTick() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.rideTick:()V");
    }

    public void awardStat(Stat<?> stat, int count) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.awardStat:(Lnet/minecraft/stats/Stat;I)V");
    }

    public void resetStat(Stat<?> stat) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.resetStat:(Lnet/minecraft/stats/Stat;)V");
    }

    public int awardRecipes(Collection<RecipeHolder<?>> recipes) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.awardRecipes:(Ljava/util/Collection;)I");
    }

    public void triggerRecipeCrafted(RecipeHolder<?> recipe, List<ItemStack> itemStacks) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.triggerRecipeCrafted:(Lnet/minecraft/world/item/crafting/RecipeHolder;Ljava/util/List;)V");
    }

    public void awardRecipesByKey(List<ResourceKey<Recipe<?>>> recipeIds) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.awardRecipesByKey:(Ljava/util/List;)V");
    }

    public int resetRecipes(Collection<RecipeHolder<?>> recipe) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.resetRecipes:(Ljava/util/Collection;)I");
    }

    public void jumpFromGround() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.jumpFromGround:()V");
    }

    public void giveExperiencePoints(int i) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.giveExperiencePoints:(I)V");
    }

    public void disconnect() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.disconnect:()V");
    }

    protected void completeUsingItem() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.completeUsingItem:()V");
    }

    public void lookAt(EntityAnchorArgument.Anchor anchor, Vec3 pos) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.lookAt:(Lnet/minecraft/commands/arguments/EntityAnchorArgument$Anchor;Lnet/minecraft/world/phys/Vec3;)V");
    }

    protected void onEffectAdded(MobEffectInstance effect, Entity source) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.onEffectAdded:(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)V");
    }

    protected void onEffectUpdated(MobEffectInstance effect, boolean doRefreshAttributes, Entity source) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.onEffectUpdated:(Lnet/minecraft/world/effect/MobEffectInstance;ZLnet/minecraft/world/entity/Entity;)V");
    }

    protected void onEffectsRemoved(Collection<MobEffectInstance> effects) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.onEffectsRemoved:(Ljava/util/Collection;)V");
    }

    public void teleportTo(double x, double y, double z) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.teleportTo:(DDD)V");
    }

    public void teleportRelative(double dx, double dy, double dz) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.teleportRelative:(DDD)V");
    }

    public boolean teleportTo(ServerLevel level, double x, double y, double z, Set<Relative> relatives, float newYRot, float newXRot, boolean resetCamera) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.teleportTo:(Lnet/minecraft/server/level/ServerLevel;DDDLjava/util/Set;FFZ)Z");
    }

    public void snapTo(double x, double y, double z) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.snapTo:(DDD)V");
    }

    public void crit(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.crit:(Lnet/minecraft/world/entity/Entity;)V");
    }

    public void magicCrit(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.magicCrit:(Lnet/minecraft/world/entity/Entity;)V");
    }

    public void onUpdateAbilities() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.onUpdateAbilities:()V");
    }

    public ServerLevel level() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.level:()Lnet/minecraft/server/level/ServerLevel;");
    }

    public GameType gameMode() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.gameMode:()Lnet/minecraft/world/level/GameType;");
    }

    public void sendSystemMessage(Component message) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.sendSystemMessage:(Lnet/minecraft/network/chat/Component;)V");
    }

    public void sendOverlayMessage(Component message) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.sendOverlayMessage:(Lnet/minecraft/network/chat/Component;)V");
    }

    public PermissionSet permissions() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.permissions:()Lnet/minecraft/server/permissions/PermissionSet;");
    }

    protected void updateInvisibilityStatus() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.updateInvisibilityStatus:()V");
    }

    protected void processPortalCooldown() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.processPortalCooldown:()V");
    }

    public void swing(InteractionHand hand) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.swing:(Lnet/minecraft/world/InteractionHand;)V");
    }

    public ItemEntity drop(ItemStack itemStack, boolean randomly, boolean thrownFromHand) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.drop:(Lnet/minecraft/world/item/ItemStack;ZZ)Lnet/minecraft/world/entity/item/ItemEntity;");
    }

    public boolean isTextFilteringEnabled() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.isTextFilteringEnabled:()Z");
    }

    public boolean mayInteract(ServerLevel level, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.mayInteract:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;)Z");
    }

    protected void updateUsingItem(ItemStack useItem) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.updateUsingItem:(Lnet/minecraft/world/item/ItemStack;)V");
    }

    public void handleExtraItemsCreatedOnUse(ItemStack extraItems) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.handleExtraItemsCreatedOnUse:(Lnet/minecraft/world/item/ItemStack;)V");
    }

    public Optional<WardenSpawnTracker> getWardenSpawnTracker() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.getWardenSpawnTracker:()Ljava/util/Optional;");
    }

    public void onItemPickup(ItemEntity entity) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.onItemPickup:(Lnet/minecraft/world/entity/item/ItemEntity;)V");
    }

    public void indicateDamage(double xd, double zd) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.indicateDamage:(DD)V");
    }

    public boolean startRiding(Entity entityToRide, boolean force, boolean sendEventAndTriggers) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.startRiding:(Lnet/minecraft/world/entity/Entity;ZZ)Z");
    }

    public void removeVehicle() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.removeVehicle:()V");
    }

    public Vec3 getKnownMovement() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.getKnownMovement:()Lnet/minecraft/world/phys/Vec3;");
    }

    public Vec3 getKnownSpeed() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.getKnownSpeed:()Lnet/minecraft/world/phys/Vec3;");
    }

    protected float getEnchantedDamage(Entity entity, float dmg, DamageSource damageSource) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.getEnchantedDamage:(Lnet/minecraft/world/entity/Entity;FLnet/minecraft/world/damagesource/DamageSource;)F");
    }

    public void onEquippedItemBroken(Item brokenItem, EquipmentSlot inSlot) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayer.onEquippedItemBroken:(Lnet/minecraft/world/item/Item;Lnet/minecraft/world/entity/EquipmentSlot;)V");
    }

    public record RespawnConfig(LevelData.RespawnData respawnData, boolean forced) {
    }

    public record RespawnPosAngle(Vec3 position, float yaw, float pitch) {
    }

    public record SavedPosition(Optional<ResourceKey<Level>> dimension, Optional<Vec3> position, Optional<Vec2> rotation) {
    }

    public ServerPlayer() {
    }
}
