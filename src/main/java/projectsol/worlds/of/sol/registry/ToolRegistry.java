package projectsol.worlds.of.sol.registry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import projectsol.worlds.of.sol.WorldsOfSol;
import projectsol.worlds.of.sol.item.BasicSwordItem;

public class ToolRegistry {

    public static final Item EXOBONE_SWORD = new BasicSwordItem(ToolMaterials.STONE, 2, -2.4F, new Item.Settings().group(WorldsOfSol.ITEM_GROUP).maxCount(1));


    public static void init() {

        Registry.register(Registry.ITEM, new Identifier("worldsofsol", "exobone_sword"), EXOBONE_SWORD);

    }
}
