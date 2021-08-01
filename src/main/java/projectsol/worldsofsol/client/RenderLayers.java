package projectsol.worldsofsol.client;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import projectsol.worldsofsol.common.registry.SolObjects;

public class RenderLayers {

    public static void init() {

        BlockRenderLayerMap.INSTANCE.putBlock(SolObjects.VULCAN_ROOTS, RenderLayer.getCutout());
    }
}
