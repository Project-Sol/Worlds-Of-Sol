package projectsol.worldsofsol.common.world.gen.feature;

import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.ConfiguredDecorator;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.HeightmapDecoratorConfig;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DiskFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import projectsol.worldsofsol.WorldsOfSol;
import projectsol.worldsofsol.common.registry.SolObjects;

public class SolFeature {
    private static ConfiguredFeature<?, ?> ORE_OXIFARIBACTE_OVERWORLD = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                    SolObjects.OXIFARIBACTE_ORE.getDefaultState(),
                    13)) // Vein size
            .range(new RangeDecoratorConfig(
                    UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(32))))
            .spreadHorizontally()
            .repeat(2); // Number of veins per chunk

    private static ConfiguredFeature<?, ?> MOON_BASALT_DISK = Feature.DISK.configure(new DiskFeatureConfig(Blocks.BASALT.getDefaultState(), UniformIntProvider.create(2, 5), 2, ImmutableList.of(SolObjects.LUNAR_REGOLITH.getDefaultState(), SolObjects.MOON_ROCK.getDefaultState()))).decorate((ConfiguredDecorator) Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.WORLD_SURFACE)).spreadHorizontally());

    public static void init(){
        RegistryKey<ConfiguredFeature<?, ?>> oreOxifaribacteOverworldOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier(WorldsOfSol.MODID, "ore_oxifaribacte_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreOxifaribacteOverworldOverworld.getValue(), ORE_OXIFARIBACTE_OVERWORLD);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreOxifaribacteOverworldOverworld);

        RegistryKey<ConfiguredFeature<?, ?>> moonBasaltDisk = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier("worldsofsol", "moon_basalt_disk"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, moonBasaltDisk.getValue(), MOON_BASALT_DISK);
    }
}
