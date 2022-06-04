package com.epherical.croptopia.dyntreesaddon.genfeature;

import com.epherical.croptopia.blocks.LeafCropBlock;
import com.epherical.croptopia.register.Content;
import com.ferreusveritas.dynamictrees.api.registry.RegistryEvent;
import com.ferreusveritas.dynamictrees.systems.genfeatures.GenFeature;

import static com.epherical.croptopia.dyntreesaddon.CroptopiaDTAddon.reg;

public class GenFeatures {

    public static final GenFeature FRUIT_CROP = new FruitBlockGenFeature(reg("fruit_crop_apple"), () -> (LeafCropBlock) Content.APPLE.asBlock());



    public static void registerGenFeatures(RegistryEvent<GenFeature> event) {
        event.getRegistry().register(FRUIT_CROP);
    }

}
