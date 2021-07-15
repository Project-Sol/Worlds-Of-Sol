package projectsol.worldsofsol;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import projectsol.worldsofsol.common.registry.*;

import projectsol.worldsofsol.common.world.dimension.MoonDimension;
import projectsol.worldsofsol.common.world.gen.feature.SolFeature;


public class WorldsOfSol implements ModInitializer {

	public static final String MODID = "worldsofsol";
	public static final ItemGroup WORLDS_OF_SOL_GROUP = FabricItemGroupBuilder.build(new Identifier(WorldsOfSol.MODID, WorldsOfSol.MODID), () -> new ItemStack(SolObjects.COBBLED_MOON_ROCK));
	public static Identifier locate(String location) {
		return new Identifier(MODID, location);
	}


	@Override
	public void onInitialize() {
		SolObjects.init();
		SolStatusEffects.init();
		MoonDimension.init();
		SolFeature.init();
		SolEntities.init();
	}
}
