package projectsol.worldsofsol.common.world.structures;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.FeatureConfig;
import projectsol.worldsofsol.WorldsOfSol;

public class SolStructureConfig {

    public static ConfiguredStructureFeature<?, ?> CONFIGURED_METEORITE_STRUCTURE = SolStructures.METEORITE_STRUCTURE.configure(FeatureConfig.DEFAULT);

    public static void init() {

        Registry<ConfiguredStructureFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new Identifier(WorldsOfSol.MODID, "configured_meteorite"), CONFIGURED_METEORITE_STRUCTURE);
    }
}
