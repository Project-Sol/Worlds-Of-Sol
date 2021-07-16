package projectsol.worldsofsol.common.registry;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import projectsol.worldsofsol.common.world.dimension.MoonDimension;

public class SolCommands {
    public static int moon(CommandContext<ServerCommandSource> context, ServerPlayerEntity player) throws CommandSyntaxException {
        ServerWorld serverWorld = context.getSource().getWorld();
        MinecraftServer minecraftServer = serverWorld.getServer();
        RegistryKey<World> registryKey = MoonDimension.MOON_WORLD_KEY;
        ServerWorld serverWorld2 = minecraftServer.getWorld(registryKey);
        FabricDimensions.teleport(player, serverWorld2, (new TeleportTarget(new Vec3d(
                (double)player.getX() + 0.5D,
                   (double)player.getY(),
                (double)player.getZ() + 0.5D),
                player.getVelocity(), player.getYaw(), player.getPitch())));
        return 0;
    }
}
