package projectsol.worldsofsol.common.world.dimension;

import net.minecraft.util.Identifier;
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
    public static RegistryKey<World> MOON_WORLD_KEY = RegistryKey.of(Registry.WORLD_KEY, new Identifier(WorldsOfSol.MODID, "moon"));
    private static final RegistryKey<DimensionOptions> DIMENSION_KEY = RegistryKey.of(Registry.DIMENSION_KEY, new Identifier(WorldsOfSol.MODID, "moon"));

    //biomes
    //public static RegistryKey<Biome> LUNAR_HIGHLANDS = RegistryKey.of(Registry.BIOME_KEY, new Identifier(WorldsOfSol.MODID, "lunar_highlands"));
    //public static RegistryKey<Biome> COMET_TUNDRA = RegistryKey.of(Registry.BIOME_KEY, new Identifier(WorldsOfSol.MODID,"comet_tundra"));
    //public static RegistryKey<Biome> LUNAR_MARE = RegistryKey.of(Registry.BIOME_KEY, new Identifier(WorldsOfSol.MODID,"lunar_mare"));

    public static void init(){
        SurfaceBuilder<MoonSurfaceBuilderConfig> MOON_SURFACE_BUILDER = Registry.register(Registry.SURFACE_BUILDER, new Identifier(WorldsOfSol.MODID,"surface_builder"), new MoonSurfaceBuilder());
        MOON_WORLD_KEY = RegistryKey.of(Registry.WORLD_KEY, new Identifier(WorldsOfSol.MODID, "moon"));

        // biomes
        //LUNAR_HIGHLANDS = RegistryKey.of(Registry.BIOME_KEY, new Identifier(WorldsOfSol.MODID,"lunar_highlands"));
        //COMET_TUNDRA = RegistryKey.of(Registry.BIOME_KEY, new Identifier(WorldsOfSol.MODID,"comet_tundra"));
        //LUNAR_MARE = RegistryKey.of(Registry.BIOME_KEY, new Identifier(WorldsOfSol.MODID, "lunar_mare"));

        RegistryKey<DimensionOptions> DIMENSION_KEY = RegistryKey.of(
                Registry.DIMENSION_KEY, new Identifier(WorldsOfSol.MODID, "moon")
        );
        Registry.register(Registry.CARVER, new Identifier(WorldsOfSol.MODID, "moon_crater"), new CraterCarver(CraterConfig.CRATER_CODEC));
    }
}
