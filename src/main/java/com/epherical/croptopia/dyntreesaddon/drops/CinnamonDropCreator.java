package com.epherical.croptopia.dyntreesaddon.drops;

import com.epherical.croptopia.register.Content;
import com.ferreusveritas.dynamictrees.api.configurations.ConfigurationProperty;
import com.ferreusveritas.dynamictrees.systems.dropcreators.DropCreator;
import com.ferreusveritas.dynamictrees.systems.dropcreators.DropCreatorConfiguration;
import com.ferreusveritas.dynamictrees.systems.dropcreators.context.DropContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class CinnamonDropCreator extends DropCreator {
    public static final ConfigurationProperty<Integer> MAX_COUNT = ConfigurationProperty.integer("max_count");

    public CinnamonDropCreator(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected void registerProperties() {
        this.register(MAX_COUNT, RARITY);
    }

    @Override
    protected DropCreatorConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration()
                .with(RARITY, 1f)
                .with(MAX_COUNT, 2);
    }

    @Override
    protected void appendHarvestDrops(DropCreatorConfiguration configuration, DropContext context) {
        // mostly the same code from StickDropCreator in DynamicTrees
        // https://github.com/DynamicTreesTeam/DynamicTrees/blob/555dff1d188bd5a034e08eb834cb050c3f43fbdc/src/main/java/com/ferreusveritas/dynamictrees/systems/dropcreators/StickDropCreator.java#L49
        int chance = 50;
        if (context.fortune() > 0) {
            chance -= 2 << context.fortune();
            if (chance < 10) {
                chance = 10;
            }
        }
        if (context.random().nextInt((int) (chance / configuration.get(RARITY))) == 0) {
            int num = 1 + context.random().nextInt(configuration.get(MAX_COUNT));
            final ItemStack stack = new ItemStack(Content.CINNAMON);
            while (num > 0) {
                ItemStack drop = stack.copy();
                drop.setCount(Math.min(num, stack.getMaxStackSize()));
                context.drops().add(drop);
                num -= stack.getMaxStackSize();
            }
        }
    }
}
