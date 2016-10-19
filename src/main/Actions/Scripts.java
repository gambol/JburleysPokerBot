package main.Actions;
import main.Switchboard;
import main.Tools.Generate.Locations;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Scripts
{
    public static void script(int point)
    {
        Robot fobot = null;

        try{fobot = new Robot();} catch (AWTException e1){e1.printStackTrace();}

        int screenx = main.Tools.Generate.Screen.getScreenX();
        int screeny = main.Tools.Generate.Screen.getScreenY();

        int manualInputx = (screenx+ Locations.crfp(point).x);
        int manualInputy = (screeny+Locations.crfp(point).y);

        int originalMousePositionx = MouseInfo.getPointerInfo().getLocation().x;
        int originalMousePositiony = MouseInfo.getPointerInfo().getLocation().y;
        int rrx = main.Tools.Generate.RandomRange.run(manualInputx-23,manualInputx+23);
        int rry = main.Tools.Generate.RandomRange.run(manualInputy-8,manualInputy+8);

        //Note there is a "hotkeys" feature available on some clients, it can make life easier, but
        // I wanted to throw off any possible detection. The following code will create "realistic" mouse movement:

        /*
        for (int i = 0; i < 100; i++)
        {
            int mov_x = ((rrx * i) / 100) + (originalMousePositionx * (100 - i) / 100);
            int mov_y = ((rry * i) / 100) + (originalMousePositiony * (100 - i) / 100);
            fobot.mouseMove(mov_x, mov_y);
            fobot.delay(2);
        }
        */

        int wait = main.Tools.Generate.RandomRange.run(100,600);
        try {Thread.sleep(wait);} catch (InterruptedException e) {e.printStackTrace();}
        fobot.mouseMove(rrx, rry);

        fobot.delay(50);
        fobot.mousePress(MouseEvent.BUTTON1_MASK);
        fobot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
        fobot.delay(50);
        fobot.mousePress(MouseEvent.BUTTON1_MASK);
        fobot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
        fobot.delay(50);
        fobot.mouseMove(originalMousePositionx, originalMousePositiony);

        //Return the mouse to the old position and click to refocus:
        /*
        fobot.delay(50);
        fobot.mousePress(MouseEvent.BUTTON1_MASK);
        fobot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
        */
        try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
    }

    public static void RaiseScript()
    {
        int raiseBox = 3;
        Robot fobot = null;

        try
        {
            fobot = new Robot();
        } catch (AWTException e1)
        {
            e1.printStackTrace();
        }

        int screenx = main.Tools.Generate.Screen.getScreenX();
        int screeny = main.Tools.Generate.Screen.getScreenY();

        int manualInputx = (screenx+ Locations.crfp(raiseBox).x);
        int manualInputy = (screeny+Locations.crfp(raiseBox).y);

        int originalMousePositionx = MouseInfo.getPointerInfo().getLocation().x;
        int originalMousePositiony = MouseInfo.getPointerInfo().getLocation().y;

        int rrx = main.Tools.Generate.RandomRange.run(manualInputx-7,manualInputx+7);
        int rry = main.Tools.Generate.RandomRange.run(manualInputy-5,manualInputy+5);

        /*
        for (int i = 0; i < 100; i++)
        {
            int mov_x = ((rrx * i) / 100) + (originalMousePositionx * (100 - i) / 100);
            int mov_y = ((rry * i) / 100) + (originalMousePositiony * (100 - i) / 100);
            fobot.mouseMove(mov_x, mov_y);
            fobot.delay(2);
        }
        */

        int wait = main.Tools.Generate.RandomRange.run(100,650);
        try {Thread.sleep(wait);} catch (InterruptedException e) {e.printStackTrace();}
        fobot.mouseMove(rrx, rry);

        fobot.delay(150);
        fobot.mousePress(MouseEvent.BUTTON1_MASK);
        fobot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
        fobot.delay(100);
        fobot.mousePress(MouseEvent.BUTTON1_MASK);
        fobot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);

        fobot.keyPress(KeyEvent.VK_DELETE);
        fobot.keyPress(KeyEvent.VK_DELETE);
        fobot.keyPress(KeyEvent.VK_BACK_SPACE);
        fobot.keyPress(KeyEvent.VK_BACK_SPACE);

        int[] anArray = new int[11];
        anArray[0] = KeyEvent.VK_0;
        anArray[1] = KeyEvent.VK_1;
        anArray[2] = KeyEvent.VK_2;
        anArray[3] = KeyEvent.VK_3;
        anArray[4] = KeyEvent.VK_4;
        anArray[5] = KeyEvent.VK_5;
        anArray[6] = KeyEvent.VK_6;
        anArray[7] = KeyEvent.VK_7;
        anArray[8] = KeyEvent.VK_8;
        anArray[9] = KeyEvent.VK_9;
        anArray[10] = KeyEvent.VK_DECIMAL;

        Double amount = Switchboard.getRaiseRange();
        char[] chars = ("" + amount).toCharArray();

        System.out.println("Raising: ");
        for(int i =0; i<chars.length; i++)
        {
            int  x = Character.getNumericValue(chars[i]);
            if(x==-1){
                System.out.print(".");
                fobot.keyPress(anArray[10]);
            } else {
                System.out.print(x);
                fobot.keyPress(anArray[x]);

            }
            fobot.delay(main.Tools.Generate.RandomRange.run(200,400));
        }
        //System.out.println("");

        //fobot.keyPress(KeyEvent.VK_ENTER);
        script(2);

        //fobot.mouseMove(1069, 752);
    }
}
