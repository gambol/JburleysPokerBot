package main.Tools.Generate.TTA;

import main.Tools.Generate.Locations;
import main.Tools.Scanners.ScanPixelColour;

public class Dealer
{
    public static void generate()
    {
        int position = 0;

        for (int i = 0; i < 3; i++)
        {
            String button = ScanPixelColour.scanPixelColour(Locations.dealerPos(i).x,Locations.dealerPos(i).y);
            //System.out.println(button);
            if (button.contentEquals("FireBrick")||button.contentEquals("Red"))
            {
                position = i+1;
            }
        }
        main.Switchboard.setDealer(position);
    }
}
