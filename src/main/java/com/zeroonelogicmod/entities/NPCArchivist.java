package com.zeroonelogicmod.entities;

import com.zeroonelogicmod.ai.NPCMemorySystem;
import com.zeroonelogicmod.ai.NPCEvolutionAI;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;

import java.util.Random;

public class NPCArchivist extends Mob {
    private final NPCMemorySystem memorySystem;
    private final NPCEvolutionAI evolutionAI;
    private final Random random = new Random();

    public NPCArchivist(EntityType<? extends Mob> entityType, Level level) {
        super(entityType, level);
        this.memorySystem = new NPCMemorySystem();
        this.evolutionAI = new NPCEvolutionAI();
    }

    // Player interaction logic
    public void interactWithPlayer(String playerAction) {
        memorySystem.rememberAction(playerAction);
        evolutionAI.learnFromExperience(playerAction);

        String memoryState = memorySystem.decideBasedOnMemory();
        String evolutionState = evolutionAI.decideNextAction();

        System.out.println("Archivist Memory: " + memoryState);
        System.out.println("Archivist Evolution: " + evolutionState);
    }

    // Custom AI goal - NPC reacts dynamically
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
            String state = npc.memorySystem.decideBasedOnMemory();
            switch (state) {
                case "attacked":
                    System.out.println("Archivist is defensive!");
                    break;
                case "helped":
                    System.out.println("Archivist is friendly!");
                    break;
                default:
                    System.out.println("Archivist is observing...");
                    break;
            }
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new ArchivistBehaviorGoal(this));
    }
}

