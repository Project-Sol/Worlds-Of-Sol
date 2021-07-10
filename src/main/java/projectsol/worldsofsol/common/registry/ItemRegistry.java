package projectsol.worldsofsol.common.registry;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import projectsol.worldsofsol.WorldsOfSol;

import java.util.LinkedHashMap;
import java.util.Map;

public class ItemRegistry {

    private static final Map<Item, Identifier> ITEMS = new LinkedHashMap<>();

    public static final Item CHITIN = create("chitin", new Item(gen()));
    public static final Item ILMENITE_RAW = create("ilmenite_raw", new Item(gen()));
    public static final Item ILMENITE_INGOT = create("ilmenite_ingot", new Item(gen()));

    public static final Item OLIVINE = create("olivine", new Item(gen()));
    public static final Item OXIFARIBACTE = create("oxifaribacte", new Item(gen()));


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
