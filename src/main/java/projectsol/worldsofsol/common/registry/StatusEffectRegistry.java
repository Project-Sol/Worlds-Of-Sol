package projectsol.worldsofsol.common.registry;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import projectsol.worldsofsol.WorldsOfSol;
import projectsol.worldsofsol.common.effect.CosmicBreathingStatusEffect;
import projectsol.worldsofsol.common.effect.RadiationStatusEffect;

import java.util.LinkedHashMap;
import java.util.Map;

public class StatusEffectRegistry {
    private static final Map<StatusEffect, Identifier> STATUS_EFFECTS = new LinkedHashMap<>();


    public static final StatusEffect RADIATION = create("radiation", new RadiationStatusEffect());
    public static final StatusEffect COSMIC_BREATHING = create("cosmic_breathing", new CosmicBreathingStatusEffect());


    public static final Potion COSMIC_BREATHING_POTION = new Potion("cosmic_breathing", new StatusEffectInstance(StatusEffectRegistry.COSMIC_BREATHING, 18000));
    public static final Potion COSMIC_BREATHING_POTION_LONG = new Potion("long_cosmic_breathing", new StatusEffectInstance(StatusEffectRegistry.COSMIC_BREATHING, 31800));


    private static <T extends StatusEffect> T create(String name, T effect) {
        STATUS_EFFECTS.put(effect, new Identifier(WorldsOfSol.MODID, name));
        return effect;
    }



    public static void init() {
        STATUS_EFFECTS.keySet().forEach(effect -> Registry.register(Registry.STATUS_EFFECT, STATUS_EFFECTS.get(effect), effect));

       // Registry.register(Registry.STATUS_EFFECT, new Identifier("worldsofsol", "radiation"), RADIATION);
       // Registry.register(Registry.STATUS_EFFECT, new Identifier("worldsofsol", "cosmic_breathing"), COSMIC_BREATHING);

        Registry.register(Registry.POTION, new Identifier("worldsofsol", "cosmic_breathing_potion"), COSMIC_BREATHING_POTION);
        Registry.register(Registry.POTION, new Identifier("worldsofsol", "long_cosmic_breathing_potion"), COSMIC_BREATHING_POTION_LONG);
    }
}
