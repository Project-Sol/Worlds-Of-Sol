package projectsol.worldsofsol.common.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.SlabBlock;
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
    public static final Block MOON_BRICK_STAIRS = create("moon_brick_stairs", new CustomStairsBlock(MOON_BRICKS.getDefaultState(), copyOf(BlockRegistry.MOON_ROCK)), true);
    public static final Block MOON_BRICK_SLAB = create("moon_brick_slab", new SlabBlock(copyOf(BlockRegistry.MOON_ROCK)), true);

    //Ores
    public static final Block ILMENITE_ORE = create("ilmenite_ore", new Block(FabricBlockSettings.of(Material.STONE)
            .strength(2.5F, 4.0F)
            .breakByTool(FabricToolTags.PICKAXES, 1)), true);
    public static final Block OLIVINE_ORE = create("olivine_ore", new Block(FabricBlockSettings.of(Material.STONE)
            .strength(2.5F, 4.0F)
            .breakByTool(FabricToolTags.PICKAXES, 1)), true);

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
