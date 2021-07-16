package projectsol.worldsofsol.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Iterator;

public class MoonDiskFeature extends Feature<MoonDiskFeatureConfig> {

    public MoonDiskFeature(Codec<MoonDiskFeatureConfig> codec) {
        super(codec);
    }

    public boolean generate(FeatureContext<MoonDiskFeatureConfig> context) {
        MoonDiskFeatureConfig diskFeatureConfig = (MoonDiskFeatureConfig)context.getConfig();
        BlockPos blockPos = context.getOrigin();
        StructureWorldAccess structureWorldAccess = context.getWorld();
        boolean bl = false;
        int i = blockPos.getY();
        int j = i + diskFeatureConfig.halfHeight;
        int k = i - diskFeatureConfig.halfHeight - 1;
        boolean bl2 = diskFeatureConfig.state.getBlock() instanceof FallingBlock;
        int l = diskFeatureConfig.radius.get(context.getRandom());

        for(int m = blockPos.getX() - l; m <= blockPos.getX() + l; ++m) {
            for(int n = blockPos.getZ() - l; n <= blockPos.getZ() + l; ++n) {
                int o = m - blockPos.getX();
                int p = n - blockPos.getZ();
                if (o * o + p * p <= l * l) {
                    boolean bl3 = false;

                    for(int q = j; q >= k; --q) {
                        BlockPos blockPos2 = new BlockPos(m, q, n);
                        BlockState blockState = structureWorldAccess.getBlockState(blockPos2);
                        Block block = blockState.getBlock();
                        boolean bl4 = false;
                        if (q > k) {
                            Iterator var21 = diskFeatureConfig.targets.iterator();

                            while(var21.hasNext()) {
                                BlockState blockState2 = (BlockState)var21.next();
                                if (blockState2.isOf(block)) {
                                    structureWorldAccess.setBlockState(blockPos2, diskFeatureConfig.state, 2);
                                    this.markBlocksAboveForPostProcessing(structureWorldAccess, blockPos2);
                                    bl = true;
                                    bl4 = true;
                                    break;
                                }
                            }
                        }

                        if (bl2 && bl3 && blockState.isAir()) {
                            BlockState blockState3 = diskFeatureConfig.state.isOf(Blocks.RED_SAND) ? Blocks.RED_SANDSTONE.getDefaultState() : Blocks.SANDSTONE.getDefaultState();
                            structureWorldAccess.setBlockState(new BlockPos(m, q + 1, n), blockState3, 2);
                        }

                        bl3 = bl4;
                    }
                }
            }
        }

        return bl;
    }
}
