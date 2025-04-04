package io.github.redrain0o0.artistry.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.network.chat.Component;

public class ModMenuSupport implements ModMenuApi {

    public Boolean myBooleanOption = true;

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parentScreen -> YetAnotherConfigLib.createBuilder()
                .title(Component.translatable("artistry.config.narrator"))
                .category(ConfigCategory.createBuilder() // Termites
                        .name(Component.translatable("artistry.config.termites"))
                        .tooltip(Component.translatable("This text will appear as a tooltip when you hover or focus the button with Tab. There is no need to add \n to wrap as YACL will do it for you."))
                        .group(OptionGroup.createBuilder()
                                .name(Component.translatable("Name of the group"))
                                .description(OptionDescription.of(Component.translatable("This text will appear when you hover over the name or focus on the collapse button with Tab.")))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.translatable("artistry.config.termites.splinter_shield_splinter_count"))
                                        .description(OptionDescription.of(Component.translatable("This text will appear as a tooltip when you hover over the option.")))
                                        .binding(true, () -> this.myBooleanOption, newVal -> this.myBooleanOption = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .build())
                        .build())
                .build()
                .generateScreen(parentScreen);
    }
}