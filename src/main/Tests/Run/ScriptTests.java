package main.Tests.Run;
import main.Tools.Generate.Locations;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
public class ScriptTests
{
    @Test
    public void FoldScriptTest() throws Exception
    {
        int e = 0;
        int p = e;
        Robot fobot;
        fobot = new Robot();
        int screenx = main.Tools.Generate.Screen.getScreenX();
        int screeny = main.Tools.Generate.Screen.getScreenY();
        int manualInputx = (screenx+ Locations.crfp(p).x);
        int manualInputy = (screeny+Locations.crfp(p).y);
        int originalMousePositionx = MouseInfo.getPointerInfo().getLocation().x;
        int originalMousePositiony = MouseInfo.getPointerInfo().getLocation().y;
        int rrx = main.Tools.Generate.RandomRange.run(manualInputx-23,manualInputx+23);
        int rry = main.Tools.Generate.RandomRange.run(manualInputy-10,manualInputy+10);
        for (int i = 0; i < 100; i++)
        {
            int mov_x = ((rrx * i) / 100) + (originalMousePositionx * (100 - i) / 100);
            int mov_y = ((rry * i) / 100) + (originalMousePositiony * (100 - i) / 100);
            fobot.mouseMove(mov_x, mov_y);
            fobot.delay(5);
        }
        fobot.mousePress(MouseEvent.BUTTON1_MASK);
        fobot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
        fobot.delay(30);
        fobot.mouseMove(originalMousePositionx, originalMousePositiony);
        fobot.delay(15);
        fobot.mousePress(MouseEvent.BUTTON1_MASK);
        fobot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
    }


    @Test
    public void RaiseScriptTest() throws Exception
    {
        int e = 3;
        int p = e;
        Robot fobot;
        fobot = new Robot();
        int screenx = main.Tools.Generate.Screen.getScreenX();
        int screeny = main.Tools.Generate.Screen.getScreenY();
        int manualInputx = (screenx+ Locations.crfp(p).x);
        int manualInputy = (screeny+Locations.crfp(p).y);
        int originalMousePositionx = MouseInfo.getPointerInfo().getLocation().x;
        int originalMousePositiony = MouseInfo.getPointerInfo().getLocation().y;
        int rrx = main.Tools.Generate.RandomRange.run(manualInputx-14,manualInputx+12);
        int rry = main.Tools.Generate.RandomRange.run(manualInputy-6,manualInputy+6);
        for (int i = 0; i < 100; i++)
        {
            int mov_x = ((rrx * i) / 100) + (originalMousePositionx * (100 - i) / 100);
            int mov_y = ((rry * i) / 100) + (originalMousePositiony * (100 - i) / 100);
            fobot.mouseMove(mov_x, mov_y);
            fobot.delay(5);
        }
        fobot.delay(50);
        fobot.mousePress(MouseEvent.BUTTON1_MASK);
        fobot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
        fobot.delay(20);
        fobot.mousePress(MouseEvent.BUTTON1_MASK);
        fobot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
        //amount


        int number = 123;
        int reverse = 0;

        int[] anArray = new int[10];
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

        while (number > 0)
        {
            reverse = reverse * 10;
            reverse = reverse + number%10;
            number = number/10;
            reverse = reverse % 10;

            //System.out.println(reverse);
            fobot.keyPress(anArray[reverse]);
            fobot.delay(main.Tools.Generate.RandomRange.run(200,400));
        }

        fobot.keyPress(KeyEvent.VK_ENTER);
        //MOVE TO SAFE SPOT
        fobot.mouseMove(1069, 752);

/*
        fobot.keyPress(KeyEvent.VK_1);
        fobot.delay(main.Tools.Generate.RandomRange.convertImage(150,250));
        fobot.keyPress(KeyEvent.VK_5);
        fobot.delay(main.Tools.Generate.RandomRange.convertImage(150,250));
        fobot.keyPress(KeyEvent.VK_0);
        fobot.delay(main.Tools.Generate.RandomRange.convertImage(150,250));
*/
    }
}