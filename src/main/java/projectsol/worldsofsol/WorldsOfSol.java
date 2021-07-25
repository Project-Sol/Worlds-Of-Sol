package projectsol.worldsofsol;

import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.command.EntitySelector;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Identifier;
import projectsol.worldsofsol.common.registry.*;

import projectsol.worldsofsol.common.world.dimension.MoonDimension;
import projectsol.worldsofsol.common.world.gen.feature.SolFeature;
import projectsol.worldsofsol.common.world.structures.SolStructures;

import static net.minecraft.server.command.CommandManager.argument;
import static projectsol.worldsofsol.common.registry.SolCommands.moon;


public class WorldsOfSol implements ModInitializer {

	public static final String MODID = "worldsofsol";
	public static final ItemGroup WORLDS_OF_SOL_GROUP = FabricItemGroupBuilder.build(new Identifier(WorldsOfSol.MODID, WorldsOfSol.MODID), () -> new ItemStack(SolObjects.COBBLED_MOON_ROCK));


	@Override
	public void onInitialize() {
		SolObjects.init();
		SolStatusEffects.init();
		MoonDimension.init();
		SolFeature.init();
		SolEntities.init();
		SolStructures.init();

		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
			LiteralCommandNode<ServerCommandSource> worldsofsolNode = CommandManager
					.literal("moon").requires(source -> source.hasPermissionLevel(2)).build();
			ArgumentCommandNode<ServerCommandSource, EntitySelector> moonNode =
					argument("player", EntityArgumentType.player())
							.executes(context -> moon(context, EntityArgumentType.getPlayer(context,"player")))
							.build();
			dispatcher.getRoot().addChild(worldsofsolNode);
			worldsofsolNode.addChild(moonNode);

		});
	}
}
