package io.github.redrain0o0.artistry.networking.packet;

import io.github.redrain0o0.artistry.Artistry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record AttackKeybindC2SPayload(int entityId) implements CustomPacketPayload {
    public static final ResourceLocation ATTACK_KEYBIND_ID = Artistry.createID("attack_keybind");
    public static final CustomPacketPayload.Type<AttackKeybindC2SPayload> ID = new CustomPacketPayload.Type<>(ATTACK_KEYBIND_ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, AttackKeybindC2SPayload> CODEC = StreamCodec.composite(ByteBufCodecs.INT, AttackKeybindC2SPayload::entityId, AttackKeybindC2SPayload::new);

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
