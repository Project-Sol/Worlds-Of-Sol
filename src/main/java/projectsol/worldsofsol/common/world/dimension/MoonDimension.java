package projectsol.worldsofsol.common.world.dimension;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import projectsol.worldsofsol.WorldsOfSol;
import projectsol.worldsofsol.common.world.gen.MoonSurfaceBuilder;
import projectsol.worldsofsol.common.world.gen.MoonSurfaceBuilderConfig;
import projectsol.worldsofsol.common.world.gen.carver.CraterCarver;
import projectsol.worldsofsol.common.world.gen.carver.CraterConfig;


public class MoonDimension {
    public static RegistryKey<World> MOON_WORLD_KEY = RegistryKey.of(Registry.WORLD_KEY, WorldsOfSol.locate("moon"));

    private static final RegistryKey<DimensionOptions> DIMENSION_KEY = RegistryKey.of(Registry.DIMENSION_KEY, new Identifier(WorldsOfSol.MODID, "moon"));

    public static void init(){
        SurfaceBuilder<MoonSurfaceBuilderConfig> MOON_SURFACE_BUILDER = Registry.register(Registry.SURFACE_BUILDER, WorldsOfSol.locate("surface_builder"), new MoonSurfaceBuilder());
        MOON_WORLD_KEY = RegistryKey.of(Registry.WORLD_KEY, new Identifier(WorldsOfSol.MODID, "moon"));
        RegistryKey<DimensionOptions> DIMENSION_KEY = RegistryKey.of(
                Registry.DIMENSION_KEY, new Identifier(WorldsOfSol.MODID, "moon")
        );
        Registry.register(Registry.CARVER, new Identifier(WorldsOfSol.MODID, "moon_crater"), new CraterCarver(CraterConfig.CRATER_CODEC));
    }
}
