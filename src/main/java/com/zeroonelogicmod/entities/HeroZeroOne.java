package com.zeroonelogicmod.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class HeroZeroOne extends BaseNPC {
    private boolean observing = true;

    public HeroZeroOne(EntityType<? extends BaseNPC> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineBehavior() {
        if (observing) {
            System.out.println("Hero 0:1 watches over the world...");
        }

        String memoryState = memorySystem.decideBasedOnMemory();

        if (memoryState.equals("chaos")) {
            System.out.println("Hero 0:1 steps in to fight chaos!");
            evolutionAI.learnFromExperience("defend");
            observing = false;
        } else if (memoryState.equals("stable")) {
            System.out.println("Hero 0:1 returns to observing.");
            observing = true;
        }
    }
}

