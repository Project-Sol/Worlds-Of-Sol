package projectsol.worldsofsol.common.registry;

import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import projectsol.worldsofsol.WorldsOfSol;
import projectsol.worldsofsol.common.world.structures.MeteoriteStructure;

public class SolStructures {

    public static final StructureFeature<DefaultFeatureConfig> METEORITE_STRUCTURE = new MeteoriteStructure(DefaultFeatureConfig.CODEC);

    public static void init() {

        FabricStructureBuilder.create(new Identifier(WorldsOfSol.MODID, "meteorite_structure"), METEORITE_STRUCTURE)
                .defaultConfig(10 , 5 , 584262839).superflatFeature(METEORITE_STRUCTURE.configure(FeatureConfig.DEFAULT))
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .adjustsSurface()
                .register();
    }
}
