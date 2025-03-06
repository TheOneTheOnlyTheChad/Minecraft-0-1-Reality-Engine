package com.zeroonelogicmod.ai;

import java.util.HashMap;
import java.util.Map;

public class NPCLearningAI {
    private final Map<String, Integer> actionRewards = new HashMap<>();

    public NPCLearningAI() {
        actionRewards.put("attacked", -10);
        actionRewards.put("helped", 10);
        actionRewards.put("ignored", -1);
    }

    public void updateRewards(String action) {
        actionRewards.put(action, actionRewards.getOrDefault(action, 0) + 1);
    }

    public String decideAction() {
        return actionRewards.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("idle");
    }

    public void printAIState() {
        System.out.println("NPC AI Decision: " + decideAction());
    }
}

