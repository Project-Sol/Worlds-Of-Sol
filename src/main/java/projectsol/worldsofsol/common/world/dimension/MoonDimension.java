package projectsol.worldsofsol.common.world.dimension;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import projectsol.worldsofsol.WorldsOfSol;
import projectsol.worldsofsol.common.world.gen.MoonSurfaceBuilder;
import projectsol.worldsofsol.common.world.gen.MoonSurfaceBuilderConfig;


public class MoonDimension {
    private static final RegistryKey<DimensionOptions> DIMENSION_KEY = RegistryKey.of(
            Registry.DIMENSION_KEY, new Identifier(WorldsOfSol.MODID, "moon")
    );

    private static RegistryKey<World> WORLD_KEY = RegistryKey.of(
            Registry.WORLD_KEY, DIMENSION_KEY.getValue()
    );

    public static void init(){
        SurfaceBuilder<MoonSurfaceBuilderConfig> MOON_SURFACE_BUILDER = Registry.register(Registry.SURFACE_BUILDER, WorldsOfSol.locate("surface_builder"), new MoonSurfaceBuilder());
        WORLD_KEY = RegistryKey.of(Registry.WORLD_KEY, new Identifier(WorldsOfSol.MODID, "moon"));
    }
}
