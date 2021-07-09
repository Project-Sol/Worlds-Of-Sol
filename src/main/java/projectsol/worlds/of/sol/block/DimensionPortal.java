package projectsol.worlds.of.sol.block;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.apache.logging.log4j.core.jmx.Server;
import projectsol.worlds.of.sol.registry.DimensionRegistry;

public class DimensionPortal extends Block {

    public DimensionPortal(Settings settings) {
        super(settings);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world instanceof ServerWorld) {
            RegistryKey<World> registryKey = world.getRegistryKey() == World.END ? World.OVERWORLD : World.END;
            ServerWorld serverWorld = ((ServerWorld) world).getServer().getWorld(registryKey);
            player.moveToWorld(serverWorld);
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }
}
