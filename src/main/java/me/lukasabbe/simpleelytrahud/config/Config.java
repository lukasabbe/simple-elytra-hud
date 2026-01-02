package me.lukasabbe.simpleelytrahud.config;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.platform.YACLPlatform;
import me.lukasabbe.simpleelytrahud.SimpleElytraHudMod;
import net.minecraft.util.Identifier;

public class Config{
    public static ConfigClassHandler<Config> HANDLER = ConfigClassHandler.createBuilder(Config.class)
            .id(Identifier.of(SimpleElytraHudMod.MOD_ID, "my_config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(YACLPlatform.getConfigDir().resolve("simple_elytra_hud_config.json5"))
                    .setJson5(true)
                    .build())
            .build();

    @SerialEntry
    public boolean isHudOn = true;

    @SerialEntry
    public boolean isElytraDmgStatusOn = true;

    @SerialEntry
    public int hudDelay = 2;

    @SerialEntry
    public boolean hudCords = true;

    @SerialEntry
    public SpeedEnum speedEnum = SpeedEnum.km;

    @SerialEntry
    public HudPosition hudPosition = HudPosition.CENTER;

    @SerialEntry
    public boolean ignoreSafeArea = false;
}
