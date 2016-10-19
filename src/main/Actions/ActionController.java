package main.Actions;

import static main.Classes.sysUpdate.msg;

public class ActionController
{
    public static boolean findTheButton()
    {
        try {Thread.sleep(50);} catch (InterruptedException e){e.printStackTrace();}
        int a = main.Switchboard.getActionScore();
        //msg("Scanning for button...");

        try {Thread.sleep(500);} catch (InterruptedException e){e.printStackTrace();}

        if (a==0) // Check / Fold
        {
            if (main.Tools.Scanners.scanButton.scanCheck())
            {
                System.out.println("Running script. CHECK.");
                runCheckScript();
                return true;
            }
            else
            {
                System.out.println("Running script: FOLD.");
                runFoldScript();
                return true;
            }
        }

        if (a==1) // Call / Check
        {
            if (main.Tools.Scanners.scanButton.scanCheck())
            {
                System.out.println("Running script. CHECK.");
                runCheckScript();
                return true;
            }
            else if (main.Tools.Scanners.scanButton.scanCall())
            {
                System.out.println("Running script: CALL.");
                runCallScript();
                return true;
            }
            else if (main.Tools.Scanners.scanButton.scanForPush())
            {
                //TODO:::
                System.out.println("DEBUG: SFP: PUSH DETECTED. TEMPORARY FOLD.");
                //scan the raise button
                //change the raise value in the switchboard
                //recalculate and rerun the raise manager run();
                //carry out the necessary script
                runFoldScript();
            }
        }

        if (a==2)
        {
            if (main.Tools.Scanners.scanButton.scanRaise())
            {
                System.out.println("Running script. RAISE");
                runRaiseScript();
                return true;
            }
            else
            {
                if (main.Tools.Scanners.scanButton.scanForPush())
                {
                    System.out.println("Looks like they raised all in. We're not raising hell today.");
                    runFoldScript();
                }
                else {
                    runRaiseScript();
                }
            }
        }

        if(a==3) // raise hell
        {
            System.out.println("Raising hell.");
            runRaiseScript();
        }

        //If nothing is found...
        return false;
    }

    private static void runRaiseScript() {Scripts.RaiseScript();}

    private static void runCallScript() {Scripts.script(1);}

    private static void runFoldScript() {Scripts.script(0);}

    private static void runCheckScript() {Scripts.script(1);}

    public static void resetActionScore() {main.Switchboard.setActionScore(100);}
}
