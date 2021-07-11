package projectsol.worldsofsol.common.registry;

import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import projectsol.worldsofsol.WorldsOfSol;
import projectsol.worldsofsol.common.item.BasicSwordItem;
import projectsol.worldsofsol.common.tools.WoSAxe;
import projectsol.worldsofsol.common.tools.WoSHoe;
import projectsol.worldsofsol.common.tools.WoSPickaxe;

import java.util.LinkedHashMap;
import java.util.Map;

public class ItemRegistry {

    private static final Map<Item, Identifier> ITEMS = new LinkedHashMap<>();

    public static final Item CHITIN = create("chitin", new Item(gen()));

    //Materials
    public static final Item ILMENITE_RAW = create("ilmenite_raw", new Item(gen()));
    public static final Item ILMENITE_INGOT = create("ilmenite_ingot", new Item(gen()));
    public static final Item OLIVINE = create("olivine", new Item(gen()));
    public static final Item OXIFARIBACTE = create("oxifaribacte", new Item(gen()));

    //Tools
    public static final Item EXOBONE_SWORD = create("exobone_sword", new BasicSwordItem(ToolMaterials.STONE, 2, -2.4F, gen()));

    public static final ToolItem FERROTITANIUM_SWORD = create("ferrotitanium_sword", new SwordItem(MaterialRegistry.FERROTITANIUM_TOOL,3,-2.4F, gen()));
    public static final ToolItem FERROTITANIUM_PICKAXE = create("ferrotitanium_pickaxe", new WoSPickaxe(MaterialRegistry.FERROTITANIUM_TOOL,1,-2.8F, gen()));
    public static final ToolItem FERROTITANIUM_SHOVEL = create("ferrotitanium_shovel", new ShovelItem(MaterialRegistry.FERROTITANIUM_TOOL,1.5F,-3, gen()));
    public static final ToolItem FERROTITANIUM_AXE = create("ferrotitanium_axe", new WoSAxe(MaterialRegistry.FERROTITANIUM_TOOL,6,-3.1F, gen()));
    public static final ToolItem FERROTITANIUM_HOE = create("ferrotitanium_hoe", new WoSHoe(MaterialRegistry.FERROTITANIUM_TOOL,-2,-1, gen()));

    private static Item.Settings gen() {
        return new Item.Settings().group(WorldsOfSol.WORLDS_OF_SOL_GROUP);
    }
    private static <T extends Item> T create(String name, T item) {
        ITEMS.put(item, new Identifier(WorldsOfSol.MODID, name));
        return item;
    }

    public static void init() {
        ITEMS.keySet().forEach(item -> Registry.register(Registry.ITEM, ITEMS.get(item), item));


    }
}
