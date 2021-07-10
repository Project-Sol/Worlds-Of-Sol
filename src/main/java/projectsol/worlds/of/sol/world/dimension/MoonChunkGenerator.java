package projectsol.worlds.of.sol.world.dimension;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.*;
import projectsol.worlds.of.sol.registry.BlockRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;


public class MoonChunkGenerator extends ChunkGenerator {

    protected final boolean moonBool;

    public static final Codec<MoonChunkGenerator> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    BiomeSource.CODEC.fieldOf("biome_source")
                            .forGetter((generator) -> generator.biomeSource),
                    Codec.BOOL.fieldOf("custom_bool")
                            .forGetter((generator) -> generator.moonBool)
            )
                    .apply(instance, instance.stable(MoonChunkGenerator::new))
    );

    public MoonChunkGenerator(BiomeSource biomeSource, boolean customBool) {
        super(biomeSource, new StructuresConfig(false));
        this.moonBool = customBool;
    }

    @Override
    protected Codec<? extends ChunkGenerator> getCodec() {
        return CODEC;
    }

    @Override
    public ChunkGenerator withSeed(long seed) {
        return this;
    }

    @Override
    public void buildSurface(ChunkRegion region, Chunk chunk) {
    }

    @Override
    public CompletableFuture<Chunk> populateNoise(Executor executor, StructureAccessor accessor, Chunk chunk) {
        List<BlockState> list = new ArrayList<BlockState>();
        list.add(Blocks.BEDROCK.getDefaultState());
        list.add(BlockRegistry.MOON_ROCK.getDefaultState());
        list.add(BlockRegistry.MOON_ROCK.getDefaultState());
        list.add(BlockRegistry.MOON_ROCK.getDefaultState());
        list.add(BlockRegistry.MOON_ROCK.getDefaultState());
        list.add(BlockRegistry.MOON_ROCK.getDefaultState());
        list.add(BlockRegistry.MOON_ROCK.getDefaultState());
        list.add(BlockRegistry.MOON_ROCK.getDefaultState());
        list.add(BlockRegistry.MOON_ROCK.getDefaultState());
        list.add(BlockRegistry.MOON_ROCK.getDefaultState());
        list.add(BlockRegistry.MOON_ROCK.getDefaultState());
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        Heightmap heightmap = chunk.getHeightmap(Heightmap.Type.OCEAN_FLOOR_WG);
        Heightmap heightmap2 = chunk.getHeightmap(Heightmap.Type.WORLD_SURFACE_WG);

        for(int i = 0; i < Math.min(chunk.getHeight(), list.size()); ++i) {
            BlockState blockState = (BlockState)list.get(i);
            if (blockState != null) {
                int j = chunk.getBottomY() + i;

                for(int k = 0; k < 16; ++k) {
                    for(int l = 0; l < 16; ++l) {
                        chunk.setBlockState(mutable.set(k, j, l), blockState, false);
                        heightmap.trackUpdate(k, j, l, blockState);
                        heightmap2.trackUpdate(k, j, l, blockState);
                    }
                }
            }
        }
        return CompletableFuture.completedFuture(chunk);
    }

    @Override
    public int getHeight(int x, int z, Heightmap.Type heightmapType, HeightLimitView heightLimitView) {
        return 70;
    }

    @Override
    public VerticalBlockSample getColumnSample(int x, int z, HeightLimitView heightLimitView) {
        return new VerticalBlockSample(0, new BlockState[0]);
    }
}