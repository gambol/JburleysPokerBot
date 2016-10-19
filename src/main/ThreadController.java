package main;

import main.Tools.Generate.TTA.Dealer;
import main.Tools.Generate.TTA.Equity;
import main.Tools.Generate.TTA.Opponents;
import main.Tools.Generate.TTA.RaiseBtnDataMkII;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;

import static main.Actions.ActionController.resetActionScore;
import static main.Classes.sysUpdate.msg;
import static main.Switchboard.getOmega;
import static main.Tools.Generate.Board.compileBoard;
import static main.Tools.Generate.Screen.setBimage;

/*

If you found your way here via the ReadMe then you've came to the right place. Thanks for taking a look.
Okay. Scroll down a bit.
                           |
                           V
*/

public class ThreadController
{

    public static class THREADS implements Runnable
    {
        public void run()
        {
            Runnable observer0 = new OBSERVE_Screen();
            Thread Observe_Screen = new Thread(observer0);
            Runnable RM_T = new ROUND_MANAGER();
            Thread Switchboard_reader = new Thread(RM_T);
            Runnable OS_T = new OS();
            Thread OmegaStageObserver = new Thread(OS_T);

//Here we create the threads. Notice the observer here, it takes the screenshots in real time.
//We will use them later
            Observe_Screen.start();
            try{Thread.sleep(800);} catch (InterruptedException e) {e.printStackTrace();}
            OmegaStageObserver.start();
            try{Thread.sleep(800);} catch (InterruptedException e) {e.printStackTrace();}
            Switchboard_reader.start();
        }
    }

    public static class OBSERVE_Screen implements Runnable
    {
        public void run()
        {
            while (!Thread.currentThread().isInterrupted())
            {
                Robot robot = null;

                try{robot = new Robot();} catch (AWTException e){e.printStackTrace();}

                Rectangle screenCaptureSize = new Rectangle(main.Tools.Generate.Screen.getScreenX(), main.Tools.Generate.Screen.getScreenY(), 477, 359);
                BufferedImage screenShot = robot.createScreenCapture(screenCaptureSize);
                setBimage(screenShot);
                try{Thread.sleep(50);} catch (InterruptedException e) {e.printStackTrace();}

            }
        }
    }

    static class OS implements Runnable
    {
        public void run()
        {
            while (true)
            {
//As mentioned in the ReadMe, you need to generate a variable in order to use it.
//This code is generating the Omega in its own thread and sending the data to
//the switchboard class so my stage manager can use the data.
                main.Tools.Generate.Board.omegaGen();//< CTRL + CLICK omegaGen and we can take a look.
                try{Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
            }
        }
    }

    static class STAGE_MANAGER implements Runnable
    {
        @Override
        public void run()
        {
            //System.out.print("\nEQ: Scanning FOR TTA. \n");

            labelE:
            while (!(main.Switchboard.getOmega().isEmpty()))
            {
                fastfold();// optional

                //TTA:
                if ( main.Tools.Scanners.scanButton.scanFold() )
                {
                    stage_plan();
                    stage_action();
                }
                try
                {
                    Thread.sleep(150);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            try
            {
                Thread.sleep(50);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        private void fastfold()
        {
            String omega = main.Switchboard.getOmega();
            int chen = (int) main.Classes.ExternalClasses.Chen.Chen.ChenFormula(omega);
            main.Tools.Generate.TTA.Dealer.generate();
            int dealer = main.Switchboard.getDealer();
            main.Tools.Generate.TTA.Pot.run();
            double pot = main.Switchboard.getPot();

            //System.out.println(pot);

            try{Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}

            if (chen < 0 && dealer==0&& pot<0.08 )
            {
                msg("Fast Fold.");
                if (main.Tools.Scanners.scanButton.scanFastFold())
                {
                    main.Actions.Scripts.script(4);
                }
            }
        }

        private void stage_plan()
        {
            //Generate:
            main.Tools.Generate.TTA.Pot.run();
            RaiseBtnDataMkII.run();
            Opponents.generate();
            Dealer.generate();

            //Getters:
            String omega = getOmega();
            int opponents = main.Switchboard.getOpponents();
            if (opponents==1){opponents=2;}
            double tblRaise = Switchboard.getRaise();
            double pot = main.Switchboard.getPot();
            main.Tools.Generate.Board.stageGen();
            int stage = main.Switchboard.getStage();

            //Debug:
            System.out.println("\n"+omega+" Opponents : " + opponents + " Stage : " + main.Tools.Generate.Board.stageGen());
            System.out.println("Pot : " + pot + " Opponent raise : " + tblRaise);

            //Process Data:
            main.Tools.Generate.Board.stageGen();
            String board = compileBoard(stage);
            if(stage>1){System.out.println("Board :" + board);}
            Equity.run(omega, opponents, board);
            int equity = Switchboard.getEquity();

            //Make Decision
            new DecisionSystem(tblRaise, pot, equity, stage, omega, opponents);

            //TODO: Debug:
            //System.out.println("\nDEBUG: Taking screenshot.");
            //sysUpdate.takeScreenshot();

            main.Tools.Generate.Board.stageGen();
        }
    }

    private static void stage_action()
    {
        try
        {
            Thread.sleep(100);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        while (main.Actions.ActionController.findTheButton())
        {
            try
            {
                Thread.sleep(100);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            break;
        }

        try
        {
            Thread.sleep(250);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        resetActionScore();

        try
        {
            Thread.sleep(250);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }

    public static class ROUND_MANAGER implements Runnable
    {
        public void run()
        {
            while (true)
            {
                while (getOmega() == null)
                {
                    try
                    {
                        Thread.sleep(100);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                while (getOmega().isEmpty())
                {
                    msg("\nRM: Waiting for Omega _______________________________\n");
                    try
                    {
                        Thread.sleep(100);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }

                //System.out.println("RM: DEBUG: Stage: "+main.Tools.Generate.Board.stageGen());

                String time = new SimpleDateFormat("hh").format(new Date());
                String newToken = time + getOmega();

                while (roundTokenChecker(newToken) == true)
                {
                    Runnable stageMan = new STAGE_MANAGER();
                    Thread stageManager = new Thread(stageMan);
                    stageManager.start();
                    try
                    {
                        Thread.sleep(100);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                    try
                    {
                        stageManager.join();
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    break;
                }

                try
                {
                    Thread.sleep(100);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }

        static String oldToken;
        public static boolean roundTokenChecker(String newToken)
        {
            if ( !(newToken.equals(oldToken)) )
            {
                oldToken = newToken;
                return true;
            }
            else
            {
                //msg("No new token.");
                return false;
            }
        }
    }
}