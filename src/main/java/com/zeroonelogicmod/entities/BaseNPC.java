package com.zeroonelogicmod.entities;

import com.zeroonelogicmod.ai.NPCMemorySystem;
import com.zeroonelogicmod.ai.NPCEvolutionAI;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;

public abstract class BaseNPC extends Mob {
    protected final NPCMemorySystem memorySystem;
    protected final NPCEvolutionAI evolutionAI;

    public BaseNPC(EntityType<? extends Mob> entityType, Level level) {
        super(entityType, level);
        this.memorySystem = new NPCMemorySystem();
        this.evolutionAI = new NPCEvolutionAI();
    }

    // Handles interactions with the player
    public void interactWithPlayer(String playerAction) {
        memorySystem.rememberAction(playerAction);
        evolutionAI.learnFromExperience(playerAction);
        System.out.println(this.getName().getString() + " Memory: " + memorySystem.decideBasedOnMemory());
        System.out.println(this.getName().getString() + " Evolution: " + evolutionAI.decideNextAction());
    }

    // Abstract method - Each NPC defines its own behavior
    protected abstract void defineBehavior();
}

