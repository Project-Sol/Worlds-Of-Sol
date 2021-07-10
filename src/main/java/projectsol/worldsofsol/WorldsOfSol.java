package projectsol.worldsofsol;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import projectsol.worldsofsol.common.registry.*;

public class WorldsOfSol implements ModInitializer {

	public static final String MODID = "worldsofsol";
	public static final ItemGroup WORLDS_OF_SOL_GROUP = FabricItemGroupBuilder.build(new Identifier(MODID, MODID), () -> new ItemStack(BlockRegistry.COBBLED_MOON_ROCK));


	@Override
	public void onInitialize() {
		System.out.println("Worlds of Sol Loading");

		BlockRegistry.init();
		ItemRegistry.init();
		ArmorRegistry.init();
		StatusEffectRegistry.init();
		ToolRegistry.init();
		DimensionRegistry.init();
		
	}
}
