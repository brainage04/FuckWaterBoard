package com.github.brainage04.fuckwaterboard.mixin;

import com.github.brainage04.fuckwaterboard.events.ModClientChatEvent;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP extends AbstractClientPlayer {
    public MixinEntityPlayerSP(World worldIn, GameProfile playerProfile) {
        super(worldIn, playerProfile);
    }

    @Inject(method = "sendChatMessage", at = @At("TAIL"))
    public void onChatSendMessage(String message, CallbackInfo ci) {
        new ModClientChatEvent().setLastMessage(message);

        // ChatUtils.messageToChat(message);
    }
}
