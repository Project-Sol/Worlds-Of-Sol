package projectsol.worldsofsol.common.item;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class BasicSwordItem extends SwordItem {

    public BasicSwordItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}
