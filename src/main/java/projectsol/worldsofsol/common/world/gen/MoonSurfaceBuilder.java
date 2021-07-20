package projectsol.worldsofsol.common.world.gen;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import java.util.Random;

public class MoonSurfaceBuilder extends SurfaceBuilder<MoonSurfaceBuilderConfig> {
    public MoonSurfaceBuilder() {
        super(MoonSurfaceBuilderConfig.CODEC);
    }


    @Override
    public void generate(Random random, Chunk chunk, Biome biome, int x, int z, int height, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, int i, long l, MoonSurfaceBuilderConfig surfaceConfig) {
            BlockState topState = surfaceConfig.getTopMaterial();
            BlockState underState = surfaceConfig.getUnderMaterial();
            BlockPos.Mutable mut = new BlockPos.Mutable();
            int maxDepth = -1;
            int depth = (int)(noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
            int cX = x & 15;
            int cZ = z & 15;

            for(int m = height; m >= 0; --m) {
                mut.set(cX, m, cZ);
                BlockState blockState3 = chunk.getBlockState(mut);
                if (blockState3.isAir()) {
                    maxDepth = -1;
                } else if (blockState3.isOf(defaultBlock.getBlock())) {
                    if (maxDepth == -1) {
                        if (depth <= 0) {
                            topState = Blocks.AIR.getDefaultState();
                            underState = defaultBlock;
                        } else if (m >= seaLevel - 4 && m <= seaLevel + 1) {
                            topState = surfaceConfig.getTopMaterial();
                            underState = surfaceConfig.getUnderMaterial();
                        }

                        if (m < seaLevel && (topState == null || topState.isAir())) {
                            topState = defaultFluid;
                            mut.set(cX, m, cZ);
                        }

                        maxDepth = depth;
                        if (m >= seaLevel - 1) {
                            chunk.setBlockState(mut, topState, false);
                        } else if (m < seaLevel - 7 - depth) {
                            topState = Blocks.AIR.getDefaultState();
                            underState = defaultBlock;
                            chunk.setBlockState(mut, surfaceConfig.getUnderwaterMaterial(), false);
                        } else {
                            chunk.setBlockState(mut, underState, false);
                        }
                    } else if (maxDepth > 0) {
                        --maxDepth;
                        chunk.setBlockState(mut, underState, false);

                    }
                }
            }
        }


    }
