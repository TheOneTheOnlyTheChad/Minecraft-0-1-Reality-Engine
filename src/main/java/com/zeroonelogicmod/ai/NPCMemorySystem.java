package com.zeroonelogicmod.ai;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class NPCMemorySystem {
    private final Map<String, Integer> memory = new HashMap<>();
    private static final String MEMORY_FILE = "npc_memory.dat";

    public NPCMemorySystem() {
        loadMemory();
    }

    // Track player interactions and save memory
    public void rememberAction(String action) {
        memory.put(action, memory.getOrDefault(action, 0) + 1);
        saveMemory();
    }

    // Save memory to a file (Persistent Learning)
    private void saveMemory() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(MEMORY_FILE))) {
            out.writeObject(memory);
        } catch (IOException e) {
            System.err.println("Error saving NPC memory: " + e.getMessage());
        }
    }

    // Load memory from a file (Persistent Learning)
    private void loadMemory() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(MEMORY_FILE))) {
            memory.putAll((Map<String, Integer>) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No existing NPC memory found. Starting fresh.");
        }
    }

    // Get NPC decision based on long-term memory
    public String decideBasedOnMemory() {
        return memory.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("neutral");
    }

    public void printMemoryState() {
        System.out.println("NPC Memory State: " + memory);
    }
}

