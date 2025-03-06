package com.zeroonelogicmod.ai;

import java.util.Random;

public class ChallengeSystem {
    private final Random random = new Random();
    private String currentChallenge;
    private boolean solved = false;

    public ChallengeSystem() {
        generateNewChallenge();
    }

    private void generateNewChallenge() {
        String[] challenges = {
                "Solve this binary logic puzzle",
                "Stabilize the quantum state",
                "Find the shortest path",
                "Optimize recursive thinking"
        };
        currentChallenge = challenges[random.nextInt(challenges.length)];
        solved = false;
    }

    public String getCurrentChallenge() {
        return currentChallenge;
    }

    public boolean attemptSolution(String solution) {
        if (solution.equalsIgnoreCase("correct")) {
            solved = true;
            return true;
        }
        return false;
    }

    public boolean isSolved() {
        return solved;
    }

    public void resetChallenge() {
        generateNewChallenge();
    }
}

