package main.Tests.Run;

import main.Tools.Generate.Locations;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class ScriptsTest
{
    @Test
    public void RaiseScript()
    {
        int e = 3;
        int p = e;
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
        int manualInputx = (screenx+ Locations.crfp(p).x);
        int manualInputy = (screeny+Locations.crfp(p).y);
        int originalMousePositionx = MouseInfo.getPointerInfo().getLocation().x;
        int originalMousePositiony = MouseInfo.getPointerInfo().getLocation().y;
        int rrx = main.Tools.Generate.RandomRange.run(manualInputx-7,manualInputx+7);
        int rry = main.Tools.Generate.RandomRange.run(manualInputy-5,manualInputy+5);
/*        for (int i = 0; i < 100; i++)
        {
            int mov_x = ((rrx * i) / 100) + (originalMousePositionx * (100 - i) / 100);
            int mov_y = ((rry * i) / 100) + (originalMousePositiony * (100 - i) / 100);
            fobot.mouseMove(mov_x, mov_y);
            fobot.delay(2);
        }
        fobot.delay(80);
        fobot.mousePress(MouseEvent.BUTTON1_MASK);
        fobot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
        fobot.delay(40);
        fobot.mousePress(MouseEvent.BUTTON1_MASK);
        fobot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);*/

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

        double amount = 1.23;

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
        System.out.println("");

        fobot.keyPress(KeyEvent.VK_ENTER);
        fobot.mouseMove(1069, 752);
    }
}