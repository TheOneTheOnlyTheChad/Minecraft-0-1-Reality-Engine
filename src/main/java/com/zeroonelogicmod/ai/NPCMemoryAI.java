nano src/main/java/com/zeroonelogicmod/ai/NPCMemoryAI.java
package com.zeroonelogicmod.ai;

import java.util.HashMap;
import java.util.Map;

public class NPCMemoryAI {
    private final Map<String, Integer> actionRewards = new HashMap<>();
    private int trustLevel = 0;

    public NPCMemoryAI() {
        actionRewards.put("attacked", -10);
        actionRewards.put("helped", 10);
        actionRewards.put("ignored", -1);
    }

    // Track player interactions over time
    public void updateInteraction(String action) {
        actionRewards.put(action, actionRewards.getOrDefault(action, 0) + 1);

        // Trust system: Positive actions increase trust, negative actions decrease it
        if (action.equals("helped")) {
            trustLevel += 5;
        } else if (action.equals("attacked")) {
            trustLevel -= 10;
        } else {
            trustLevel -= 1;
        }

        // Cap trust between -100 (hostile) and 100 (friendly)
        trustLevel = Math.max(-100, Math.min(100, trustLevel));
    }

    // NPC decides behavior based on trust
    public String decideBehavior() {
        if (trustLevel >= 50) {
            return "Friendly"; // Helps the player
        } else if (trustLevel <= -50) {
            return "Hostile"; // Attacks the player
        } else {
            return "Neutral"; // Observes but does not engage

