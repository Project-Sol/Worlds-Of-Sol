package projectsol.worlds.of.sol.registry;


import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import projectsol.worlds.of.sol.WorldsOfSol;
import projectsol.worlds.of.sol.armor.ExoboneArmor;

public class ArmorRegistry {

    public static final ArmorMaterial EXOBONE_ARMOR_MATERIAL = new ExoboneArmor();
    public static final Item EXOBONE_HELMET = new ArmorItem(EXOBONE_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(WorldsOfSol.ITEM_GROUP));
    public static final Item EXOBONE_CHESTPLATE = new ArmorItem(EXOBONE_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(WorldsOfSol.ITEM_GROUP));
    public static final Item EXOBONE_LEGGINGS = new ArmorItem(EXOBONE_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(WorldsOfSol.ITEM_GROUP));
    public static final Item EXOBONE_BOOTS = new ArmorItem(EXOBONE_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Settings().group(WorldsOfSol.ITEM_GROUP));

    public static void init() {

        Registry.register(Registry.ITEM, new Identifier("worldsofsol", "exobone_helmet"), EXOBONE_HELMET);
        Registry.register(Registry.ITEM, new Identifier("worldsofsol", "exobone_chestplate"), EXOBONE_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("worldsofsol", "exobone_leggings"), EXOBONE_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("worldsofsol", "exobone_boots"), EXOBONE_BOOTS);
    }
}
