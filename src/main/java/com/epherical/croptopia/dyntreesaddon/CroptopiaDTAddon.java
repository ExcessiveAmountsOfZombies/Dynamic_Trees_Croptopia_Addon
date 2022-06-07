package com.epherical.croptopia.dyntreesaddon;

import com.epherical.croptopia.blocks.LeafCropBlock;
import com.epherical.croptopia.events.HarvestEvent;
import com.ferreusveritas.dynamictrees.api.GatherDataHelper;
import com.ferreusveritas.dynamictrees.api.registry.RegistryEvent;
import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;
import com.ferreusveritas.dynamictrees.blocks.leaves.LeavesProperties;
import com.ferreusveritas.dynamictrees.blocks.rootyblocks.SoilProperties;
import com.ferreusveritas.dynamictrees.resources.Resources;
import com.ferreusveritas.dynamictrees.systems.dropcreators.DropCreator;
import com.ferreusveritas.dynamictrees.systems.genfeatures.GenFeature;
import com.ferreusveritas.dynamictrees.trees.Family;
import com.ferreusveritas.dynamictrees.trees.Species;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CroptopiaDTAddon.ID)
public class CroptopiaDTAddon {
    public static final String ID = "dtcroptopia";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();



    public CroptopiaDTAddon() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::gatherData);
        MinecraftForge.EVENT_BUS.register(this);

        RegistryHandler.setup(ID);
    }

    public void gatherData(GatherDataEvent event) {
        Resources.MANAGER.gatherData();
        GatherDataHelper.gatherAllData(
                ID,
                event,
                SoilProperties.REGISTRY,
                Family.REGISTRY,
                Species.REGISTRY,
                LeavesProperties.REGISTRY
        );
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

    /**
     * Set the block to air, so it acts like the fruit block that is in dyntrees. The fruits will regrow.
     */
    @SubscribeEvent
    public void onHarvest(HarvestEvent event) {
        if (event.getCurrentState().getBlock() instanceof LeafCropBlock) {
            event.setTurnedState(Blocks.AIR.defaultBlockState());
        }
    }

    @SubscribeEvent
    public void onInteractionWithTool(BlockEvent.BlockToolModificationEvent event) {
        // this is never fired.
        /*if (!event.isSimulated() && event.getPlayer() != null) {
            BlockState state = event.getState();
            if (state.getBlock() instanceof BranchBlock branchBlock) {
                branchBlock.getFamily().getPrimitiveLog().ifPresent(block -> {
                    if (state.is(Content.CINNAMON.getLog()) || state.is(Content.CINNAMON.getWood())) {
                        Block.popResource(event.getPlayer().level, event.getPos(), new ItemStack(Content.CINNAMON));
                    }
                });
            }
        }*/
    }

    public static ResourceLocation reg(String key) {
        return new ResourceLocation(ID, key);
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void registerGenFeatures(RegistryEvent<GenFeature> event) {
            TreeRegistry.registerGenFeatures(event);
        }

        @SubscribeEvent
        public static void registerDrops(RegistryEvent<DropCreator> event) {
            TreeRegistry.registerDropCreator(event);
        }

    }
}
