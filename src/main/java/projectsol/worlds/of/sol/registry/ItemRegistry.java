package projectsol.worlds.of.sol.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import projectsol.worlds.of.sol.WorldsOfSol;

public class ItemRegistry {

        public static final Item CHITIN = new Item(new FabricItemSettings().group(WorldsOfSol.ITEM_GROUP));


    public static void init() {

        Registry.register(Registry.ITEM, new Identifier("worldsofsol", "chitin"), CHITIN);
    }
}
