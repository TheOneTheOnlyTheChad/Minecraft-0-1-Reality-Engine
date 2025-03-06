package com.zeroonelogicmod.entities;

import com.zeroonelogicmod.ai.NPCMemoryAI;
import com.zeroonelogicmod.ai.NPCEvolutionAI;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;

public class NPCArchivist extends Mob {
    private final NPCMemoryAI memoryAI;
    private final NPCEvolutionAI evolutionAI;

    public NPCArchivist(EntityType<? extends Mob> entityType, Level level) {
        super(entityType, level);
        this.memoryAI = new NPCMemoryAI();
        this.evolutionAI = new NPCEvolutionAI();
    }

    // React based on player interactions
    public void interactWithPlayer(String playerAction) {
        memoryAI.updateInteraction(playerAction);
        evolutionAI.learnFromExperience(playerAction);
        System.out.println("Archivist behavior: " + memoryAI.decideBehavior());
        System.out.println("Archivist evolution: " + evolutionAI.decideNextAction());
    }

    // Custom AI goal for behavior tree
    static class ArchivistBehaviorGoal extends Goal {
        private final NPCArchivist npc;

        public ArchivistBehaviorGoal(NPCArchivist npc) {
            this.npc = npc;
        }

        @Override
        public boolean canUse() {
            return true; // Always running
        }

        @Override
        public void tick() {
            npc.interactWithPlayer("ignored"); // Default idle reaction
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new ArchivistBehaviorGoal(this));
    }
}

