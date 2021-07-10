package projectsol.worldsofsol.common.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import projectsol.worldsofsol.WorldsOfSol;
import projectsol.worldsofsol.common.block.CustomStairsBlock;
import static net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.copyOf;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlockRegistry {
    private static final Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();
    private static final Map<Item, Identifier> ITEMS = new LinkedHashMap<>();

    //Blocks
    public static final Block LUNAR_REGOLITH = create("lunar_regolith", new Block(copyOf(Blocks.DIRT).breakByTool(FabricToolTags.SHOVELS).breakByHand(true)), true);
    public static final Block MOON_ROCK = create("moon_rock", new Block(FabricBlockSettings.of(Material.STONE).strength(2.5F, 4.0F).breakByTool(FabricToolTags.PICKAXES, 0)), true);
    public static final Block COBBLED_MOON_ROCK = create("cobbled_moon_rock", new Block(copyOf(BlockRegistry.MOON_ROCK)), true);
    public static final Block MOON_BRICKS = create("moon_bricks", new Block(copyOf(BlockRegistry.MOON_ROCK)), true);

    public static final Block MOON_ROCK_STAIRS = create("moon_rock_stairs", new CustomStairsBlock(MOON_BRICKS.getDefaultState(), copyOf(BlockRegistry.MOON_ROCK)), true);
    public static final Block COBBLED_MOON_ROCK_STAIRS = create("cobbled_moon_rock_stairs", new CustomStairsBlock(MOON_BRICKS.getDefaultState(), copyOf(BlockRegistry.COBBLED_MOON_ROCK)), true);
    public static final Block MOON_BRICK_STAIRS = create("moon_brick_stairs", new CustomStairsBlock(MOON_BRICKS.getDefaultState(), copyOf(BlockRegistry.MOON_BRICKS)), true);

    public static final Block MOON_ROCK_SLAB = create("moon_rock_slab", new SlabBlock(copyOf(BlockRegistry.MOON_ROCK)), true);
    public static final Block COBBLED_MOON_ROCK_SLAB = create("cobbled_moon_rock_slab", new SlabBlock(copyOf(BlockRegistry.COBBLED_MOON_ROCK)), true);
    public static final Block MOON_BRICK_SLAB = create("moon_brick_slab", new SlabBlock(copyOf(BlockRegistry.MOON_BRICKS)), true);

    public static final Block MOON_ROCK_WALL = create("moon_rock_wall", new WallBlock(copyOf(BlockRegistry.MOON_ROCK)), true);
    public static final Block COBBLED_MOON_ROCK_WALL = create("cobbled_moon_rock_wall", new WallBlock(copyOf(BlockRegistry.COBBLED_MOON_ROCK)), true);
    public static final Block MOON_BRICK_WALL = create("moon_brick_wall", new WallBlock(copyOf(BlockRegistry.MOON_BRICKS)), true);



    //Ores
    public static final Block ILMENITE_ORE = create("ilmenite_ore", new Block(FabricBlockSettings.of(Material.STONE)
            .strength(3F, 4.5F)
            .breakByTool(FabricToolTags.PICKAXES, 1)), true);
    public static final Block OLIVINE_ORE = create("olivine_ore", new Block(FabricBlockSettings.of(Material.STONE)
            .strength(3F, 4.5F)
            .breakByTool(FabricToolTags.PICKAXES, 1)), true);
    public static final Block OXIFARIBACTE_ORE = create("oxifaribacte_ore", new Block(FabricBlockSettings.of(Material.STONE)
            .strength(3F, 4.5F)
            .breakByTool(FabricToolTags.PICKAXES, 1)), true);

    public static final Block ILMENITE_RAW_BLOCK = create("ilmenite_raw_block", new Block(copyOf(BlockRegistry.ILMENITE_ORE)), true);
    public static final Block FERROTITANIUM_BLOCK = create("ferrotitanium_block", new Block(copyOf(Blocks.IRON_BLOCK)), true);
    public static final Block OLIVINE_BLOCK = create("olivine_block", new Block(copyOf(Blocks.IRON_BLOCK)), true);
    public static final Block OXIFARIBACTE_BLOCK = create("oxifaribacte_block", new Block(copyOf(Blocks.IRON_BLOCK)), true);

    private static <T extends Block> T create(String name, T block, boolean createItem) {
        BLOCKS.put(block, new Identifier(WorldsOfSol.MODID, name));
        if (createItem) {
            ITEMS.put(new BlockItem(block, gen()), BLOCKS.get(block));
        }
        return block;
    }

    private static Item.Settings gen() {
        return new Item.Settings().group(WorldsOfSol.WORLDS_OF_SOL_GROUP);
    }

    public static void init() {

        BLOCKS.keySet().forEach(block -> Registry.register(Registry.BLOCK, BLOCKS.get(block), block));
        ITEMS.keySet().forEach(item -> Registry.register(Registry.ITEM, ITEMS.get(item), item));

    }
}
