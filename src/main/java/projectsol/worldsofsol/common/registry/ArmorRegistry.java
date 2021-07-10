package projectsol.worldsofsol.common.registry;


import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import projectsol.worldsofsol.WorldsOfSol;
import projectsol.worldsofsol.common.armor.ExoboneArmor;

import java.util.LinkedHashMap;
import java.util.Map;

public class ArmorRegistry {
    private static final Map<Item, Identifier> ITEMS = new LinkedHashMap<>();

    public static final ArmorMaterial EXOBONE_ARMOR_MATERIAL = new ExoboneArmor();

    public static final Item EXOBONE_HELMET = create("exobone_helmet", new ArmorItem(EXOBONE_ARMOR_MATERIAL, EquipmentSlot.HEAD, gen()));
    public static final Item EXOBONE_CHESTPLATE = create("exobone_chestplate", new ArmorItem(EXOBONE_ARMOR_MATERIAL, EquipmentSlot.CHEST, gen()));
    public static final Item EXOBONE_LEGGINGS = create("exobone_leggings", new ArmorItem(EXOBONE_ARMOR_MATERIAL, EquipmentSlot.LEGS, gen()));
    public static final Item EXOBONE_BOOTS = create("exobone_boots", new ArmorItem(EXOBONE_ARMOR_MATERIAL, EquipmentSlot.FEET, gen()));

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
