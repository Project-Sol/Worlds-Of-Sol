package projectsol.worldsofsol.common.registry;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import projectsol.worldsofsol.WorldsOfSol;
import projectsol.worldsofsol.common.item.BasicSwordItem;

public class ToolRegistry {

    public static final Item EXOBONE_SWORD = new BasicSwordItem(ToolMaterials.STONE, 2, -2.4F, new Item.Settings().group(WorldsOfSol.WORLDS_OF_SOL_GROUP).maxCount(1));


    public static void init() {

        Registry.register(Registry.ITEM, new Identifier("worldsofsol", "exobone_sword"), EXOBONE_SWORD);

    }
}
