package com.zeroonelogicmod.ai;

import java.util.Random;

public class NPCBehaviorTree {
    private String state;
    private final Random random = new Random();

    public NPCBehaviorTree() {
        this.state = "Idle"; // Default state
    }

    public void updateState(String playerAction) {
        switch (playerAction) {
            case "attacked":
                this.state = "Aggressive";
                break;
            case "helped":
                this.state = "Friendly";
                break;
            case "ignored":
                this.state = random.nextBoolean() ? "Neutral" : "Wandering";
                break;
            default:
                this.state = "Idle";
                break;
        }
        System.out.println("NPC state updated to: " + state);
    }

    public String getState() {
        return state;
    }
}

