package me.lukasabbe.simpleelytrahud.config;

import dev.isxander.yacl3.api.NameableEnum;
import net.minecraft.text.Text;

public enum HudPosition implements NameableEnum {
    CENTER,
    TOP_LEFT,
    TOP_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_RIGHT;

    @Override
    public Text getDisplayName() {
        return Text.translatable("simpleelytrahud.option.position.options." + name().toLowerCase());
    }
}
