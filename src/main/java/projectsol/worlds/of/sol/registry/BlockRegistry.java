package projectsol.worlds.of.sol.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import projectsol.worlds.of.sol.block.CustomStairsBlock;

public class BlockRegistry {

    public static final Block LUNAR_REGOLITH = new Block(FabricBlockSettings.of(Material.STONE).strength(1.0F, 2.0F));
    public static final Block MOON_ROCK = new Block(FabricBlockSettings.of(Material.STONE).strength(2.5F, 4.0F));
    public static final Block COBBLED_MOON_ROCK = new Block(FabricBlockSettings.of(Material.STONE).strength(2.5F, 4.0F));
    public static final Block MOON_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).strength(2.5F, 4.0F));
    public static final Block MOON_BRICK_STAIRS = new CustomStairsBlock(MOON_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).strength(2.5F, 4.0F));
    public static final Block MOON_BRICK_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(2.5F, 4.0F));

    public static void init() {

        Registry.register(Registry.BLOCK, new Identifier("worldsofsol", "lunar_regolith"), LUNAR_REGOLITH);
        Registry.register(Registry.ITEM, new Identifier("worldsofsol", "lunar_regolith"), new BlockItem(LUNAR_REGOLITH, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier("worldsofsol", "moon_rock"), MOON_ROCK);
        Registry.register(Registry.ITEM, new Identifier("worldsofsol", "moon_rock"), new BlockItem(MOON_ROCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier("worldsofsol", "cobbled_moon_rock"), COBBLED_MOON_ROCK);
        Registry.register(Registry.ITEM, new Identifier("worldsofsol", "cobbled_moon_rock"), new BlockItem(COBBLED_MOON_ROCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier("worldsofsol", "moon_bricks"), MOON_BRICKS);
        Registry.register(Registry.ITEM, new Identifier("worldsofsol", "moon_bricks"), new BlockItem(MOON_BRICKS, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier("worldsofsol", "moon_brick_slab"), MOON_BRICK_SLAB);
        Registry.register(Registry.ITEM, new Identifier("worldsofsol", "moon_brick_slab"), new BlockItem(MOON_BRICK_SLAB, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier("worldsofsol", "moon_brick_stairs"), MOON_BRICK_STAIRS);
        Registry.register(Registry.ITEM, new Identifier("worldsofsol", "moon_brick_stairs"), new BlockItem(MOON_BRICK_STAIRS, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
    }
}
