package projectsol.worldsofsol.common.registry;

import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;

public class SolMaterials {
    public static final ToolMaterial FERROTITANIUM_TOOL = new ToolMaterial() {
        @Override
        public int getDurability() {
            return 2927;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return ToolMaterials.STONE.getMiningSpeedMultiplier();
        }

        @Override
        public float getAttackDamage() {
            return ToolMaterials.IRON.getAttackDamage();
        }

        @Override
        public int getMiningLevel() {
            return ToolMaterials.IRON.getMiningLevel();
        }

        @Override
        public int getEnchantability() {
            return ToolMaterials.GOLD.getEnchantability();
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.ofItems(SolObjects.ILMENITE_INGOT);
        }
    };
}
