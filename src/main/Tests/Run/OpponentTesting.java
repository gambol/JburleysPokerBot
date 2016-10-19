package main.Tests.Run;

import main.Tools.Generate.Locations;
import main.Tools.Scanners.ScanPixelColour;
import org.junit.Test;

public class OpponentTesting
{
    @Test
    public void countOpponents() throws Exception
    {
        int opponents = 0;
        for (int i = 0; i < 8; i++)
        {
            String colour = ScanPixelColour.scanPixelColour(Locations.pRekter(i).x,Locations.pRekter(i).y);

            if (colour.contentEquals("HSienna"))
            {
                opponents++;
            }
        }

        System.out.println(opponents);
    }

    @Test
    public void Dealer() throws Exception
    {
        main.Tests.setObserver.staticImage();

        int position = 0;

        for (int i = 0; i < 3; i++)
        {
            String button = ScanPixelColour.scanPixelColour(Locations.dealerPos(i).x,Locations.dealerPos(i).y);
            System.out.println(button);
            if (button.contentEquals("Red"))
            {
                position = i;
            }
        }

        System.out.println(position);
    }
}
