package com.zeroonelogicmod.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class PureEquilibrium extends BaseNPC {
    private boolean active = false; // Hidden until chaos is too high

    public PureEquilibrium(EntityType<? extends BaseNPC> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineBehavior() {
        if (memorySystem.decideBasedOnMemory().equals("chaos")) {
            active = true;
            System.out.println("Pure Equilibrium has emerged!");
        }

        if (active) {
            System.out.println("Pure Equilibrium is restoring balance...");
            evolutionAI.learnFromExperience("restore_balance");
        }

        if (memorySystem.decideBasedOnMemory().equals("stable")) {
            System.out.println("Equilibrium restored. Disappearing...");
            active = false;
        }
    }
}

