package com.example.suzuki_r_finalproject;

public class SessionManager {
    private static long startTime;
    private static long endTime;

    public static void startSession() {
        startTime = System.currentTimeMillis();
    }

    public static void endSession() {
        endTime = System.currentTimeMillis();
    }

    public static long getSessionDuration() {
        return endTime - startTime;
    }
}
