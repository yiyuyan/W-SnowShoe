package cn.ksmcbrigade.wss;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod("w_ss")
@Mod.EventBusSubscriber(value = Dist.CLIENT,modid = "w_ss")
public class WSnowShoe {
    public static boolean enabled = false;

    public static KeyMapping key = new KeyMapping("key.w_ss", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_I,"key.w_ss");

    @SubscribeEvent
    public static void registerKey(RegisterKeyMappingsEvent event){
        event.register(key);
    }

    @SubscribeEvent
    public static void onInputKey(InputEvent.Key event){
        if(key.isDown()){
            enabled = !enabled;
            if(Minecraft.getInstance().player!=null) Minecraft.getInstance().player.sendSystemMessage(Component.literal("SnowShoe: "+enabled));
        }
    }

    @SubscribeEvent
    public static void registerCommand(RegisterClientCommandsEvent event){
        event.getDispatcher().register(Commands.literal("wss").executes(context -> {
            enabled = !enabled;
            context.getSource().sendSystemMessage(Component.literal("SnowShoe: "+enabled));
            return 0;
        }));
    }
}
