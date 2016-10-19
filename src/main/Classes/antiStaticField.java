package main.Classes;

public class antiStaticField
{
    static private Object action_obj = new Object();
    static private Object stage_obj = new Object();
    static private Object EQ_obj = new Object();
    static private Object OS_obj = new Object();

    public static void stopStageReader() {
        synchronized (stage_obj) {
            try {
                stage_obj.wait();
            } catch (Exception e) {}
        }
    }

    public static void startStageReader() {
        synchronized (stage_obj) {
            stage_obj.notify();
        }
    }

    public static void stopACTION() {
        synchronized (action_obj) {
            try {
                action_obj.wait();
            } catch (Exception e) {}
        }
    }

    public static void startACTION() {
        synchronized (action_obj) {
            action_obj.notify();
        }
    }
    public static void stopEQ() {
        synchronized (EQ_obj) {
            try {
                EQ_obj.wait();
            } catch (Exception e) {}
        }
    }

    public static void startEQ() {
        synchronized (EQ_obj) {
            EQ_obj.notify();
        }
    }
    public static void stopOS() {
        synchronized (OS_obj) {
            try {
                OS_obj.wait();
            } catch (Exception e) {}
        }
    }

    public static void startOS() {
        synchronized (OS_obj) {
            OS_obj.notify();
        }
    }
}