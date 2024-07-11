package me.lukasabbe.transporthud;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;

public class ModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Text.translatable("transporthud.config.title"));
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();
            builder.getOrCreateCategory(Text.translatable("transporthud.config.cat"))
                    .addEntry(entryBuilder.startBooleanToggle(Text.translatable("transporthud.option.hud.enabled"),Config.isHudOn)
                            .setDefaultValue(true).setSaveConsumer(newVal->Config.isHudOn = newVal).build());
            builder.setSavingRunnable(Config::save);
            return builder.build();
        };
    }
}