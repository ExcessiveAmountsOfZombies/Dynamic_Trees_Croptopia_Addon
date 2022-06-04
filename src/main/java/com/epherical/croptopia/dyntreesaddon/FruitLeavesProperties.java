package com.epherical.croptopia.dyntreesaddon;

import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;
import com.ferreusveritas.dynamictrees.blocks.leaves.LeavesProperties;
import net.minecraft.resources.ResourceLocation;


public class FruitLeavesProperties extends LeavesProperties {

    public static final TypedRegistry.EntryType<LeavesProperties> TYPE = TypedRegistry.newType(FruitLeavesProperties::new);

    public FruitLeavesProperties(ResourceLocation registryName) {
        super(registryName);
    }

}
