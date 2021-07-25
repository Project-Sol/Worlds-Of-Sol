package projectsol.worldsofsol.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class CometFeature extends Feature<DefaultFeatureConfig> {

    private int spawnX;
    private int spawnY;
    private int spawnZ;
    private int tailX;
    private int tailY;
    private int tailZ;
    private int size;

    public CometFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        Random rand = context.getRandom();
        spawnX = context.getOrigin().getX();
        spawnY = context.getOrigin().getY();
        spawnZ = context.getOrigin().getZ();
        double rotation = rand.nextInt();
        double xmod = Math.sin(rotation);
        double zmod = Math.cos(rotation);
        int distMod = 150 + rand.nextInt(50);
        tailX = context.getOrigin().getX() + (int) (xmod * distMod);
        tailY = context.getOrigin().getY() + 40 + rand.nextInt(40);
        tailZ = context.getOrigin().getZ() + (int) (zmod * distMod);
        size = 2 + rand.nextInt(8);
        int xDiff = tailX - spawnX;
        int yDiff = tailY - spawnY;
        int zDiff = tailZ - spawnZ;

        genCore(world, rand, size);

        for (int p = 0; p < 100; p += 2) {
            int cX = spawnX + (int) (((float) p / 100F) * xDiff);
            int cY = spawnY + (int) (((float) p / 100F) * yDiff);
            int cZ = spawnZ + (int) (((float) p / 100F) * zDiff);
            float pc = (float) p / 100F;

            int density = 500 - (int) (pc * 550);
            if (density < 20) density = 20;
            trailSphere(world, cX, cY, cZ, (size + 3) - (int) (pc * (size - 2)), density, world.getRandom());

            density = 1000 - (int) (pc * 10000);
            trailSphere(world, cX, cY, cZ, (size + 3) - (int) (pc * (size - 2)), density, world.getRandom());

        }
        return true;
    }

    private void genCore(StructureWorldAccess world, Random rand, int r) {
        for (int x = spawnX - r; x <= spawnX + r; x++) {
            for (int z = spawnZ - r; z <= spawnZ + r; z++) {
                for (int y = spawnY - r; y <= spawnY + r; y++) {
                    if ((int) (getDistance(x, y, z, spawnX, spawnY, spawnZ)) <= r) {
                        float genP = rand.nextFloat();
                        BlockPos pos = new BlockPos(x, y, z);
                        if (0.1F > genP) {
                            world.setBlockState(pos, Blocks.DIAMOND_BLOCK.getDefaultState(), 3);
                        }
                        else if (0.4F > genP) {
                            world.setBlockState(pos, Blocks.DIAMOND_BLOCK.getDefaultState(), 3);
                        }
                        else {
                            world.setBlockState(pos, Blocks.DIAMOND_BLOCK.getDefaultState(), 3);
                        }
                    }
                }
            }
        }
    }

    public void trailSphere(StructureWorldAccess world, int xi, int yi, int zi, int r, int density, Random rand) {
        if (density <= 0) return;
        if (density > 10000) density = 10000;
        for (int x = xi - r; x <= xi + r; x++) {
            for (int z = zi - r; z <= zi + r; z++) {
                for (int y = yi - r; y <= yi + r; y++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    if ((density >= rand.nextInt(10000)) && world.isAir(pos) && (int) (getDistance(x, y, z, xi, yi, zi)) == r) {
                        if (0.9F >= rand.nextFloat()) {
                            world.setBlockState(pos, Blocks.DIAMOND_BLOCK.getDefaultState(), 3);
                        }
                        else if (rand.nextBoolean()) {
                            world.setBlockState(pos, Blocks.DIAMOND_BLOCK.getDefaultState(), 3);
                        }
                        else {
                            world.setBlockState(pos, Blocks.DIAMOND_BLOCK.getDefaultState(), 3);
                        }
                    }
                }
            }
        }
    }

    public static double getDistance(int x1, int y1, int z1, int x2, int y2, int z2) {
        int dx = x1 - x2;
        int dy = y1 - y2;
        int dz = z1 - z2;
        return Math.sqrt((dx * dx + dy * dy + dz * dz));
    }
}
