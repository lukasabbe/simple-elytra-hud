package me.lukasabbe.simpleelytrahud.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.gui.controllers.cycling.EnumController;
import net.minecraft.text.Text;

public class ModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            final Config config = Config.HANDLER.instance();
            return YetAnotherConfigLib.createBuilder()
                    .title(Text.translatable("simpleelytrahud.config.title"))
                    .category(ConfigCategory.createBuilder()
                            .name(Text.translatable("simpleelytrahud.config.cat"))
                            .option(Option.<Boolean>createBuilder()
                                    .name(Text.translatable("simpleelytrahud.option.hud_toggle.info"))
                                    .description(OptionDescription.of(Text.translatable("simpleelytrahud.option.hud_toggle.description")))
                                    .binding(true, ()-> config.isHudOn, newVal -> config.isHudOn = newVal)
                                    .controller(TickBoxControllerBuilder::create)
                                    .build())
                            .option(Option.<Integer>createBuilder()
                                    .name(Text.translatable("simpleelytrahud.option.delay.info"))
                                    .description(OptionDescription.of(Text.translatable("simpleelytrahud.option.delay.description")))
                                    .binding(2, ()-> config.hudDelay, newVal -> config.hudDelay = newVal)
                                    .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                                            .range(0,30)
                                            .step(1))
                                    .build())
                            .option(Option.<Boolean>createBuilder()
                                    .name(Text.translatable("simpleelytrahud.option.elytra_damage_level_toggle.info"))
                                    .description(OptionDescription.of(Text.translatable("simpleelytrahud.option.elytra_damage_level.description")))
                                    .binding(true, ()-> config.isElytraDmgStatusOn, newVal -> config.isElytraDmgStatusOn = newVal)
                                    .controller(TickBoxControllerBuilder::create)
                                    .build())
                            .option(Option.<Boolean>createBuilder()
                                    .name(Text.translatable("simpleelytrahud.option.coordinates_toggle.info"))
                                    .description(OptionDescription.of(Text.translatable("simpleelytrahud.option.coordinates_toggle.description")))
                                    .binding(true, ()-> config.hudCords, newVal -> config.hudCords = newVal)
                                    .controller(TickBoxControllerBuilder::create)
                                    .build())
                            .option(Option.<SpeedEnum>createBuilder()
                                    .name(Text.translatable("simpleelytrahud.option.speed.info"))
                                    .description(OptionDescription.of(Text.translatable("simpleelytrahud.option.speed.description")))
                                    .binding(SpeedEnum.km, ()-> config.speedEnum, newVal -> config.speedEnum = newVal)
                                    .customController(opt -> new EnumController<>(opt,SpeedEnum.class))
                                    .build())
                            .option(Option.<HudPosition>createBuilder()
                                    .name(Text.translatable("simpleelytrahud.option.position.info"))
                                    .description(OptionDescription.of(Text.translatable("simpleelytrahud.option.position.description")))
                                    .binding(HudPosition.CENTER, ()-> config.hudPosition, newVal -> config.hudPosition = newVal)
                                    .customController(opt -> new EnumController<>(opt, HudPosition.class))
                                    .build())
                            .option(Option.<Boolean>createBuilder()
                                    .name(Text.translatable("simpleelytrahud.option.ignore_safe_area.info"))
                                    .description(OptionDescription.of(Text.translatable("simpleelytrahud.option.ignore_safe_area.description")))
                                    .binding(false, ()-> config.ignoreSafeArea, newVal -> config.ignoreSafeArea = newVal)
                                    .controller(TickBoxControllerBuilder::create)
                                    .build())
                            .build())
                    .save(()-> Config.HANDLER.save())
                    .build().generateScreen(parent);
        };
    }
}