package projectsol.worldsofsol.common.registry;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import projectsol.worldsofsol.WorldsOfSol;

public class SolTags {
    public static final Tag<EntityType<?>> SPACE_ENTITY = TagRegistry.entityType(new Identifier(WorldsOfSol.MODID, "space_type"));
}
