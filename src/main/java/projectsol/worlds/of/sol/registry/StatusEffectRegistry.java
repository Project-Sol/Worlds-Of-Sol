package projectsol.worlds.of.sol.registry;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import projectsol.worlds.of.sol.effect.RadiationStatusEffect;

public class StatusEffectRegistry {

    public static final StatusEffect RADIATION = new RadiationStatusEffect();

    public static void init() {

        Registry.register(Registry.STATUS_EFFECT, new Identifier("worldsofsol", "radiation"), RADIATION);
    }
}
