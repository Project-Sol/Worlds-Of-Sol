package projectsol.worldsofsol.common.world.structures;

import com.mojang.serialization.Decoder;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.fabricmc.fabric.impl.biome.modification.BiomeModificationContextImpl;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import projectsol.worldsofsol.WorldsOfSol;


public class SolStructures {

    public static final StructureFeature<DefaultFeatureConfig> METEORITE_STRUCTURE = new MeteoriteStructure(DefaultFeatureConfig.CODEC);


    public static void init() {

        FabricStructureBuilder.create(new Identifier(WorldsOfSol.MODID, "meteorite"), METEORITE_STRUCTURE)
                .defaultConfig(10 , 5 , 584262839).superflatFeature(METEORITE_STRUCTURE.configure(FeatureConfig.DEFAULT))
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .adjustsSurface()
                .register();
    }

}
