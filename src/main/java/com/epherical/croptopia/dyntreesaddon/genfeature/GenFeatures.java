package com.epherical.croptopia.dyntreesaddon.genfeature;

import com.epherical.croptopia.blocks.LeafCropBlock;
import com.epherical.croptopia.register.Content;
import com.ferreusveritas.dynamictrees.api.registry.RegistryEvent;
import com.ferreusveritas.dynamictrees.systems.genfeatures.GenFeature;

import static com.epherical.croptopia.dyntreesaddon.CroptopiaDTAddon.reg;

public class GenFeatures {

    public static final GenFeature ALMOND_TREE = new FruitBlockGenFeature(reg("fruit_crop_almond"), () -> (LeafCropBlock) Content.ALMOND.asBlock());
    public static final GenFeature APPLE_TREE = new FruitBlockGenFeature(reg("fruit_crop_apple"), () -> (LeafCropBlock) Content.APPLE.asBlock());
    public static final GenFeature APRICOT_TREE = new FruitBlockGenFeature(reg("fruit_crop_apricot"), () -> (LeafCropBlock) Content.APRICOT.asBlock());
    public static final GenFeature AVOCADO_TREE = new FruitBlockGenFeature(reg("fruit_crop_avocado"), () -> (LeafCropBlock) Content.AVOCADO.asBlock());
    public static final GenFeature BANANA_TREE = new FruitBlockGenFeature(reg("fruit_crop_banana"), () -> (LeafCropBlock) Content.BANANA.asBlock());
    public static final GenFeature CASHEW_TREE = new FruitBlockGenFeature(reg("fruit_crop_cashew"), () -> (LeafCropBlock) Content.CASHEW.asBlock());
    public static final GenFeature CHERRY_TREE = new FruitBlockGenFeature(reg("fruit_crop_cherry"), () -> (LeafCropBlock) Content.CHERRY.asBlock());
    public static final GenFeature COCONUT_TREE = new FruitBlockGenFeature(reg("fruit_crop_coconut"), () -> (LeafCropBlock) Content.COCONUT.asBlock());
    public static final GenFeature DATE_TREE = new FruitBlockGenFeature(reg("fruit_crop_date"), () -> (LeafCropBlock) Content.DATE.asBlock());
    public static final GenFeature DRAGONFRUIT_TREE = new FruitBlockGenFeature(reg("fruit_crop_dragonfruit"), () -> (LeafCropBlock) Content.DRAGONFRUIT.asBlock());
    public static final GenFeature FIG_TREE = new FruitBlockGenFeature(reg("fruit_crop_fig"), () -> (LeafCropBlock) Content.FIG.asBlock());
    public static final GenFeature GRAPEFRUIT_TREE = new FruitBlockGenFeature(reg("fruit_crop_grapefruit"), () -> (LeafCropBlock) Content.GRAPEFRUIT.asBlock());
    public static final GenFeature KUMQUAT_TREE = new FruitBlockGenFeature(reg("fruit_crop_kumquat"), () -> (LeafCropBlock) Content.KUMQUAT.asBlock());
    public static final GenFeature LEMON_TREE = new FruitBlockGenFeature(reg("fruit_crop_lemon"), () -> (LeafCropBlock) Content.LEMON.asBlock());
    public static final GenFeature LIME_TREE = new FruitBlockGenFeature(reg("fruit_crop_lime"), () -> (LeafCropBlock) Content.LIME.asBlock());
    public static final GenFeature MANGO_TREE = new FruitBlockGenFeature(reg("fruit_crop_mango"), () -> (LeafCropBlock) Content.MANGO.asBlock());
    public static final GenFeature NECTARINE_TREE = new FruitBlockGenFeature(reg("fruit_crop_nectarine"), () -> (LeafCropBlock) Content.NECTARINE.asBlock());
    public static final GenFeature NUTMEG_TREE = new FruitBlockGenFeature(reg("fruit_crop_nutmeg"), () -> (LeafCropBlock) Content.NUTMEG.asBlock());
    public static final GenFeature ORANGE_TREE = new FruitBlockGenFeature(reg("fruit_crop_orange"), () -> (LeafCropBlock) Content.ORANGE.asBlock());
    public static final GenFeature PEACH_TREE = new FruitBlockGenFeature(reg("fruit_crop_peach"), () -> (LeafCropBlock) Content.PEACH.asBlock());
    public static final GenFeature PEAR_TREE = new FruitBlockGenFeature(reg("fruit_crop_pear"), () -> (LeafCropBlock) Content.PEAR.asBlock());
    public static final GenFeature PECAN_TREE = new FruitBlockGenFeature(reg("fruit_crop_pecan"), () -> (LeafCropBlock) Content.PECAN.asBlock());
    public static final GenFeature PERSIMMON_TREE = new FruitBlockGenFeature(reg("fruit_crop_persimmon"), () -> (LeafCropBlock) Content.PERSIMMON.asBlock());
    public static final GenFeature PLUM_TREE = new FruitBlockGenFeature(reg("fruit_crop_plum"), () -> (LeafCropBlock) Content.PLUM.asBlock());
    public static final GenFeature STARFRUIT_TREE = new FruitBlockGenFeature(reg("fruit_crop_starfruit"), () -> (LeafCropBlock) Content.STARFRUIT.asBlock());
    public static final GenFeature WALNUT_TREE = new FruitBlockGenFeature(reg("fruit_crop_walnut"), () -> (LeafCropBlock) Content.WALNUT.asBlock());


    public static void registerGenFeatures(RegistryEvent<GenFeature> event) {
        event.getRegistry().registerAll(ALMOND_TREE, APPLE_TREE, APRICOT_TREE, AVOCADO_TREE, BANANA_TREE, CASHEW_TREE, CHERRY_TREE, COCONUT_TREE,
                DATE_TREE, DRAGONFRUIT_TREE, FIG_TREE, GRAPEFRUIT_TREE, KUMQUAT_TREE, LEMON_TREE, LIME_TREE, MANGO_TREE, NECTARINE_TREE, NUTMEG_TREE,
                ORANGE_TREE, PEACH_TREE, PEAR_TREE, PECAN_TREE, PERSIMMON_TREE, PLUM_TREE, STARFRUIT_TREE, WALNUT_TREE);
    }

}
