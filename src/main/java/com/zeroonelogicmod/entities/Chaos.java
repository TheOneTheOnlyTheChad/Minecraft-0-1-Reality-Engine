package com.zeroonelogicmod.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import java.util.Random;

public class Chaos extends BaseNPC {
    private String achillesHeel;
    private final Random random = new Random();

    public Chaos(EntityType<? extends BaseNPC> entityType, Level level) {
        super(entityType, level);
        assignNewWeakness();
    }

    private void assignNewWeakness() {
        String[] weaknesses = {"Fire", "Water", "Light", "Darkness", "Silence"};
        achillesHeel = weaknesses[random.nextInt(weaknesses.length)];
        System.out.println("Chaos' new weakness is a mystery!");
    }

    @Override
    protected void defineBehavior() {
        System.out.println("Chaos disrupts everything...");

        if (memorySystem.decideBasedOnMemory().equals(achillesHeel)) {
            System.out.println("Player figured out Chaos' weakness! Resetting...");
            assignNewWeakness();
            evolutionAI.learnFromExperience("resist_" + achillesHeel);
        }
    }
}

