package me.lukasabbe.simpleelytrahud.data;

import me.lukasabbe.simpleelytrahud.SimpleElytraHudMod;
import me.lukasabbe.simpleelytrahud.config.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

/**
 * All data the HUD is using
 */
public class ElytraData {
    private MinecraftClient client;
    public boolean isFlying;
    private int counter;
    public float speed;
    public Vec3d postion;
    public float pitch;
    public double yaw;
    public float elytraStatus;
    public float maxElytraStatus;
    public int elytraDmgColor;

    public ElytraData(MinecraftClient client){
        if(client == null)
            SimpleElytraHudMod.LOGGER.error("There were no client");
        this.client = client;
    }

    /**
     * Update elytra data
     */
    public void updateData(){
        ClientPlayerEntity player = client.player;
        if(player == null) return;

        final ItemStack chestItem = player.getEquippedStack(EquipmentSlot.CHEST);

        if(!player.isGliding() || !chestItem.isOf(Items.ELYTRA)){
            counter = 0;
            isFlying=false;
            return;
        }
        //Hud delay is in sec times ticks. 20 ticks in 1 sec
        if(counter > Config.HANDLER.instance().hudDelay * 20){
            isFlying = true;
            speed = (float) (player.getVelocity().length() * 20d);
            postion = player.getPos();
            pitch = player.getPitch();
            elytraStatus = chestItem.getDamage();
            maxElytraStatus = chestItem.getMaxDamage();
            elytraDmgColor = chestItem.getItemBarColor();
            yaw = MathHelper.wrapDegrees(player.getYaw()) + 90;
        }else counter++;
    }
}
