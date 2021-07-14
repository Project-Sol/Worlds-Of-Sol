package projectsol.worldsofsol.common.world.gen.feature;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
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
    public static void init(){
        RegistryKey<ConfiguredFeature<?, ?>> oreOxifaribacteOverworldOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier(WorldsOfSol.MODID, "ore_oxifaribacte_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreOxifaribacteOverworldOverworld.getValue(), ORE_OXIFARIBACTE_OVERWORLD);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreOxifaribacteOverworldOverworld);
    }
}
