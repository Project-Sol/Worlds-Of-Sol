package projectsol.worlds.of.sol.registry;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import projectsol.worlds.of.sol.effect.CosmicBreathingStatusEffect;
import projectsol.worlds.of.sol.effect.RadiationStatusEffect;

public class StatusEffectRegistry {

    public static final StatusEffect RADIATION = new RadiationStatusEffect();
    public static final StatusEffect COSMIC_BREATHING = new CosmicBreathingStatusEffect();

    public static final Potion COSMIC_BREATHING_POTION = new Potion("cosmic_breathing", new StatusEffectInstance(StatusEffectRegistry.COSMIC_BREATHING, 18000));
    public static final Potion COSMIC_BREATHING_POTION_LONG = new Potion("long_cosmic_breathing", new StatusEffectInstance(StatusEffectRegistry.COSMIC_BREATHING, 31800));


    public static void init() {

        Registry.register(Registry.STATUS_EFFECT, new Identifier("worldsofsol", "radiation"), RADIATION);
        Registry.register(Registry.STATUS_EFFECT, new Identifier("worldsofsol", "cosmic_breathing"), COSMIC_BREATHING);

        Registry.register(Registry.POTION, new Identifier("worldsofsol", "cosmic_breathing_potion"), COSMIC_BREATHING_POTION);
        Registry.register(Registry.POTION, new Identifier("worldsofsol", "long_cosmic_breathing_potion"), COSMIC_BREATHING_POTION_LONG);
    }
}
