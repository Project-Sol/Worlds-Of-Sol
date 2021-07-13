package projectsol.worldsofsol;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import projectsol.worldsofsol.common.registry.*;

import projectsol.worldsofsol.common.world.carver.CraterCarver;
import projectsol.worldsofsol.common.world.carver.CraterConfig;
import projectsol.worldsofsol.common.world.gen.MoonSurfaceBuilder;
import projectsol.worldsofsol.common.world.gen.MoonSurfaceBuilderConfig;


public class WorldsOfSol implements ModInitializer {

	public static final String MODID = "worldsofsol";
	public static final ItemGroup WORLDS_OF_SOL_GROUP = FabricItemGroupBuilder.build(new Identifier(WorldsOfSol.MODID, WorldsOfSol.MODID), () -> new ItemStack(BlockRegistry.COBBLED_MOON_ROCK));
	public static Identifier locate(String location) {
		return new Identifier(MODID, location);
	}

	private static ConfiguredFeature<?, ?> ORE_OXIFARIBACTE_OVERWORLD = Feature.ORE
			.configure(new OreFeatureConfig(
					OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
					BlockRegistry.OXIFARIBACTE_ORE.getDefaultState(),
					13)) // Vein size
			.range(new RangeDecoratorConfig(
					UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(32))))
			.spreadHorizontally()
			.repeat(2); // Number of veins per chunk

	private static final RegistryKey<DimensionOptions> DIMENSION_KEY = RegistryKey.of(
			Registry.DIMENSION_KEY, new Identifier(WorldsOfSol.MODID, "moon")
	);
	private static RegistryKey<World> MOON_WORLD_KEY = RegistryKey.of(
			Registry.WORLD_KEY, DIMENSION_KEY.getValue()
	);


	@Override
	public void onInitialize() {

		BlockRegistry.init();
		ItemRegistry.init();
		ArmorRegistry.init();
		StatusEffectRegistry.init();

		SurfaceBuilder<MoonSurfaceBuilderConfig> MOON_SURFACE_BUILDER = Registry.register(Registry.SURFACE_BUILDER, WorldsOfSol.locate("surface_builder"), new MoonSurfaceBuilder());
		MOON_WORLD_KEY = RegistryKey.of(Registry.WORLD_KEY, new Identifier(WorldsOfSol.MODID, "moon"));


		RegistryKey<ConfiguredFeature<?, ?>> oreOxifaribacteOverworldOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
				new Identifier(WorldsOfSol.MODID, "ore_oxifaribacte_overworld"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreOxifaribacteOverworldOverworld.getValue(), ORE_OXIFARIBACTE_OVERWORLD);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreOxifaribacteOverworldOverworld);

		Registry.register(Registry.CARVER, new Identifier(WorldsOfSol.MODID, "moon_crater"), new CraterCarver(CraterConfig.CRATER_CODEC));


	}
}
