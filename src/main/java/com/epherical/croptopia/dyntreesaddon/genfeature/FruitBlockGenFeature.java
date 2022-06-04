package com.epherical.croptopia.dyntreesaddon.genfeature;

import com.epherical.croptopia.blocks.LeafCropBlock;
import com.ferreusveritas.dynamictrees.api.GeneratesFruit;
import com.ferreusveritas.dynamictrees.api.TreeHelper;
import com.ferreusveritas.dynamictrees.api.network.MapSignal;
import com.ferreusveritas.dynamictrees.blocks.branches.BranchBlock;
import com.ferreusveritas.dynamictrees.compat.seasons.SeasonHelper;
import com.ferreusveritas.dynamictrees.systems.genfeatures.FruitGenFeature;
import com.ferreusveritas.dynamictrees.systems.genfeatures.GenFeatureConfiguration;
import com.ferreusveritas.dynamictrees.systems.genfeatures.context.PostGenerationContext;
import com.ferreusveritas.dynamictrees.systems.genfeatures.context.PostGrowContext;
import com.ferreusveritas.dynamictrees.systems.nodemappers.FindEndsNode;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.util.CoordUtils;
import com.ferreusveritas.dynamictrees.util.SafeChunkBounds;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.function.Supplier;

/**
 * Most of this class is copied from here
 * https://github.com/DynamicTreesTeam/DynamicTrees/blob/develop/1.18.2/src/main/java/com/ferreusveritas/dynamictrees/systems/genfeatures/FruitGenFeature.java
 */
@GeneratesFruit
public class FruitBlockGenFeature extends FruitGenFeature {

    private final Supplier<LeafCropBlock> fruitBlock;


    public FruitBlockGenFeature(ResourceLocation registryName, Supplier<LeafCropBlock> block) {
        super(registryName);
        this.fruitBlock = block;
    }

    @Override
    protected void registerProperties() {
        this.register(VERTICAL_SPREAD, QUANTITY, RAY_DISTANCE, FRUITING_RADIUS, PLACE_CHANCE);
    }


    @Override
    public GenFeatureConfiguration createDefaultConfiguration() {
        return new GenFeatureConfiguration(this)
                .with(VERTICAL_SPREAD, 30f)
                .with(QUANTITY, 4)
                .with(FRUITING_RADIUS, 8)
                .with(PLACE_CHANCE, 1f);
    }

    protected boolean postGenerate(GenFeatureConfiguration configuration, PostGenerationContext context) {
        if (context.endPoints().isEmpty()) {
            return false;
        } else {
            int qty = configuration.get(QUANTITY);
            qty = (int)((float)qty * context.fruitProductionFactor());

            for(int i = 0; i < qty; ++i) {
                BlockPos endPoint = context.endPoints().get(context.random().nextInt(context.endPoints().size()));
                this.addFruit(configuration, context.world(), context.species(), context.pos().above(), endPoint, true, false, context.bounds(), context.seasonValue());
            }

            return true;
        }
    }

    protected boolean postGrow(GenFeatureConfiguration configuration, PostGrowContext context) {
        Level world = context.world();
        BlockState blockState = world.getBlockState(context.treePos());
        BranchBlock branch = TreeHelper.getBranch(blockState);
        if (branch != null && branch.getRadius(blockState) >= configuration.get(FRUITING_RADIUS) && context.natural()) {
            BlockPos rootPos = context.pos();
            float fruitingFactor = context.species().seasonalFruitProductionFactor(world, rootPos);
            // todo: maybe re-implement a configurable fruiting factor?
            if (fruitingFactor > 0.3 && fruitingFactor > world.random.nextFloat()) {
                FindEndsNode endFinder = new FindEndsNode();
                TreeHelper.startAnalysisFromRoot(world, rootPos, new MapSignal(endFinder));
                List<BlockPos> endPoints = endFinder.getEnds();
                int qty = configuration.get(QUANTITY);
                if (!endPoints.isEmpty()) {
                    for(int i = 0; i < qty; ++i) {
                        BlockPos endPoint = endPoints.get(world.random.nextInt(endPoints.size()));
                        this.addFruit(configuration, world, context.species(), rootPos.above(), endPoint, false, true,
                                SafeChunkBounds.ANY, SeasonHelper.getSeasonValue(world, rootPos));
                    }
                }
            }
        }

        return true;
    }

    protected void addFruit(GenFeatureConfiguration configuration, LevelAccessor world, Species species, BlockPos treePos, BlockPos branchPos, boolean worldGen, boolean enableHash, SafeChunkBounds safeBounds, Float seasonValue) {
        BlockPos fruitPos = CoordUtils.getRayTraceFruitPos(world, species, treePos, branchPos, safeBounds);
        if (fruitPos != BlockPos.ZERO &&
                (!enableHash || ((CoordUtils.coordHashCode(fruitPos, 0) & 3) == 0)) &&
                world.getRandom().nextFloat() <= configuration.get(PLACE_CHANCE)) {
            LeafCropBlock fruitBlock = this.fruitBlock.get();
            BlockState setState = fruitBlock.getStateForAge(worldGen ? 3 : 0);
            world.setBlock(fruitPos, setState, 3);
        }

    }
}
