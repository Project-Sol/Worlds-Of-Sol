package projectsol.worldsofsol.common.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import projectsol.worldsofsol.WorldsOfSol;
import projectsol.worldsofsol.common.armor.ExoboneArmor;
import projectsol.worldsofsol.common.block.CocoonBlock;
import projectsol.worldsofsol.common.block.CustomStairsBlock;
import projectsol.worldsofsol.common.tools.SolSword;
import projectsol.worldsofsol.common.tools.SolAxe;
import projectsol.worldsofsol.common.tools.SolHoe;
import projectsol.worldsofsol.common.tools.SolPickaxe;

import static net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.copyOf;

import java.util.LinkedHashMap;
import java.util.Map;

public class SolObjects {
    private static final Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();
    private static final Map<Item, Identifier> ITEMS = new LinkedHashMap<>();


    //Blocks
    public static final Block LUNAR_REGOLITH = create("lunar_regolith", new Block(copyOf(Blocks.DIRT).breakByTool(FabricToolTags.SHOVELS).breakByHand(true)), true);
    public static final Block MOON_ROCK = create("moon_rock", new Block(FabricBlockSettings.of(Material.STONE).strength(2.5F, 4.0F).breakByTool(FabricToolTags.PICKAXES, 0)), true);
    public static final Block COBBLED_MOON_ROCK = create("cobbled_moon_rock", new Block(copyOf(SolObjects.MOON_ROCK)), true);
    public static final Block MOON_BRICKS = create("moon_bricks", new Block(copyOf(SolObjects.MOON_ROCK)), true);

    public static final Block MOON_ROCK_STAIRS = create("moon_rock_stairs", new CustomStairsBlock(MOON_BRICKS.getDefaultState(), copyOf(SolObjects.MOON_ROCK)), true);
    public static final Block COBBLED_MOON_ROCK_STAIRS = create("cobbled_moon_rock_stairs", new CustomStairsBlock(MOON_BRICKS.getDefaultState(), copyOf(SolObjects.COBBLED_MOON_ROCK)), true);
    public static final Block MOON_BRICK_STAIRS = create("moon_brick_stairs", new CustomStairsBlock(MOON_BRICKS.getDefaultState(), copyOf(SolObjects.MOON_BRICKS)), true);

    public static final Block MOON_ROCK_SLAB = create("moon_rock_slab", new SlabBlock(copyOf(SolObjects.MOON_ROCK)), true);
    public static final Block COBBLED_MOON_ROCK_SLAB = create("cobbled_moon_rock_slab", new SlabBlock(copyOf(SolObjects.COBBLED_MOON_ROCK)), true);
    public static final Block MOON_BRICK_SLAB = create("moon_brick_slab", new SlabBlock(copyOf(SolObjects.MOON_BRICKS)), true);

    public static final Block MOON_ROCK_WALL = create("moon_rock_wall", new WallBlock(copyOf(SolObjects.MOON_ROCK)), true);
    public static final Block COBBLED_MOON_ROCK_WALL = create("cobbled_moon_rock_wall", new WallBlock(copyOf(SolObjects.COBBLED_MOON_ROCK)), true);
    public static final Block MOON_BRICK_WALL = create("moon_brick_wall", new WallBlock(copyOf(SolObjects.MOON_BRICKS)), true);

    public static final Block METEORITE = create("meteorite", new Block(copyOf(Blocks.IRON_BLOCK)), true);
    public static final Block ALIEN_GOO = create("alien_goo", new Block(copyOf(Blocks.MAGMA_BLOCK)), true);

    // Special Blocks
    public static final Block COCOON_BLOCK = create("cocoon", new CocoonBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOL).strength(1.0F, 0.5F)), true);

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

    public static final Block ILMENITE_RAW_BLOCK = create("ilmenite_raw_block", new Block(copyOf(Blocks.RAW_IRON_BLOCK)), true);
    public static final Block FERROTITANIUM_BLOCK = create("ferrotitanium_block", new Block(copyOf(Blocks.IRON_BLOCK)), true);
    public static final Block OLIVINE_BLOCK = create("olivine_block", new Block(copyOf(Blocks.IRON_BLOCK)), true);
    public static final Block OXIFARIBACTE_BLOCK = create("oxifaribacte_block", new Block(copyOf(Blocks.IRON_BLOCK)), true);

    //Armor
    public static final ArmorMaterial EXOBONE_ARMOR_MATERIAL = new ExoboneArmor();

    public static final Item EXOBONE_HELMET = create("exobone_helmet", new ArmorItem(EXOBONE_ARMOR_MATERIAL, EquipmentSlot.HEAD, gen()));
    public static final Item EXOBONE_CHESTPLATE = create("exobone_chestplate", new ArmorItem(EXOBONE_ARMOR_MATERIAL, EquipmentSlot.CHEST, gen()));
    public static final Item EXOBONE_LEGGINGS = create("exobone_leggings", new ArmorItem(EXOBONE_ARMOR_MATERIAL, EquipmentSlot.LEGS, gen()));
    public static final Item EXOBONE_BOOTS = create("exobone_boots", new ArmorItem(EXOBONE_ARMOR_MATERIAL, EquipmentSlot.FEET, gen()));

    //Items
    public static final Item CHITIN = create("chitin", new Item(gen()));

    //Materials
    public static final Item ILMENITE_RAW = create("ilmenite_raw", new Item(gen()));
    public static final Item ILMENITE_INGOT = create("ilmenite_ingot", new Item(gen()));
    public static final Item OLIVINE = create("olivine", new Item(gen()));
    public static final Item OXIFARIBACTE = create("oxifaribacte", new Item(gen()));

    //Tools
    public static final Item EXOBONE_SWORD = create("exobone_sword", new SolSword(ToolMaterials.STONE, 2, -2.4F, gen()));

    public static final ToolItem FERROTITANIUM_SWORD = create("ferrotitanium_sword", new SwordItem(SolMaterials.FERROTITANIUM_TOOL,3,-2.4F, gen()));
    public static final ToolItem FERROTITANIUM_PICKAXE = create("ferrotitanium_pickaxe", new SolPickaxe(SolMaterials.FERROTITANIUM_TOOL,1,-2.8F, gen()));
    public static final ToolItem FERROTITANIUM_SHOVEL = create("ferrotitanium_shovel", new ShovelItem(SolMaterials.FERROTITANIUM_TOOL,1.5F,-3, gen()));
    public static final ToolItem FERROTITANIUM_AXE = create("ferrotitanium_axe", new SolAxe(SolMaterials.FERROTITANIUM_TOOL,6,-3.1F, gen()));
    public static final ToolItem FERROTITANIUM_HOE = create("ferrotitanium_hoe", new SolHoe(SolMaterials.FERROTITANIUM_TOOL,-2,-1, gen()));



    private static Item.Settings gen() {
        return new Item.Settings().group(WorldsOfSol.WORLDS_OF_SOL_GROUP);
    }
    private static <T extends Item> T create(String name, T item) {
        ITEMS.put(item, new Identifier(WorldsOfSol.MODID, name));
        return item;
    }

    private static <T extends Block> T create(String name, T block, boolean createItem) {
        BLOCKS.put(block, new Identifier(WorldsOfSol.MODID, name));
        if (createItem) {
            ITEMS.put(new BlockItem(block, gen()), BLOCKS.get(block));
        }
        return block;
    }
    //Painting
    private static PaintingMotive create(String id, int width, int height) {
        final PaintingMotive type = new PaintingMotive(width, height);
        Registry.register(Registry.PAINTING_MOTIVE, new Identifier(WorldsOfSol.MODID, id), type);
        return type;
    }


    public static void init() {
        BLOCKS.keySet().forEach(block -> Registry.register(Registry.BLOCK, BLOCKS.get(block), block));
        ITEMS.keySet().forEach(item -> Registry.register(Registry.ITEM, ITEMS.get(item), item));
        //Paintings
        create("black_hole", 16, 16);
    }
}
