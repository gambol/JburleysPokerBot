package main.Tools.Generate.TTA;
import main.Tools.Generate.Locations;
import main.Tools.Scanners.ScanPixelColour;

public class Opponents
{
    public static void generate()
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
        main.Switchboard.setOpponents(opponents);
    }
}