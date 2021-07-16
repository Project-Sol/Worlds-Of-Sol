package projectsol.worldsofsol.common.tools;

import net.minecraft.item.HoeItem;
import net.minecraft.item.ToolMaterial;

public class SolHoe extends HoeItem {
    public SolHoe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}