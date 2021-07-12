package projectsol.worldsofsol.common.world.gen;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.carver.Carver;
import projectsol.worldsofsol.common.world.gen.carver.CraterCarver;
import projectsol.worldsofsol.common.world.gen.carver.CraterConfig;

public class MoonRegistry {

    public static final Carver<CraterConfig> MOON_CRATERS = Registry.register(Registry.CARVER, new Identifier("worldsofsol", "moon_craters"), new CraterCarver(CraterConfig.CRATER_CODEC));


    public static void init() {

    }
}
