package projectsol.worldsofsol.common.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import projectsol.worldsofsol.WorldsOfSol;
import projectsol.worldsofsol.common.entity.GreylinEntity;
import projectsol.worldsofsol.common.entity.MeteorHeadEntity;

public class SolEntities {
    public static final EntityType<MeteorHeadEntity> METEOR_HEAD_ENTITY = FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, MeteorHeadEntity::new).fireImmune()
            .dimensions(EntityDimensions.fixed(1.1F, 2.2F)).build();

    public static final EntityType<GreylinEntity> GREYLIN_ENTITY = FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, GreylinEntity::new)
            .dimensions(EntityDimensions.fixed(1.1F, 2.2F)).build();



    public static void init(){
        Registry.register(Registry.ENTITY_TYPE, new Identifier(WorldsOfSol.MODID, "meteor_head"), METEOR_HEAD_ENTITY);
        FabricDefaultAttributeRegistry.register(METEOR_HEAD_ENTITY, MeteorHeadEntity.cre());

        Registry.register(Registry.ENTITY_TYPE, new Identifier(WorldsOfSol.MODID, "greylin"), GREYLIN_ENTITY);
        FabricDefaultAttributeRegistry.register(GREYLIN_ENTITY, GreylinEntity.createGreylinAttributes());
    }
}
