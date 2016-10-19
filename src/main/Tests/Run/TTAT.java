package main.Tests.Run;

import main.Tools.Scanners.ScanPixelColour;
import org.junit.Test;

public class TTAT
{
    @Test
    public void run() throws Exception
    {

        main.Tests.setObserver.staticImage();

        //F
        String checkWhite1F;
        checkWhite1F = ScanPixelColour.scanPixelColour(257,349);
        checkWhite1F = String.valueOf(checkWhite1F.charAt(0));
        //O
        String checkWhite2F;
        checkWhite2F = ScanPixelColour.scanPixelColour(266,349);
        checkWhite2F = String.valueOf(checkWhite2F.charAt(0));
        //L
        String checkWhite3F;
        checkWhite3F = ScanPixelColour.scanPixelColour(272,349);
        checkWhite3F = String.valueOf(checkWhite3F.charAt(0));
        //D
        String checkWhite4F;
        checkWhite4F = ScanPixelColour.scanPixelColour(277,349);
        checkWhite4F = String.valueOf(checkWhite4F.charAt(0));

        String result2 = checkWhite1F + checkWhite2F + checkWhite3F + checkWhite4F;
        System.out.println(result2);
    }

    @Test
    public void scanRaise()
    {
        main.Tests.setObserver.staticImage();

        //>R<asiseto
        String p1;
        p1 = ScanPixelColour.scanPixelColour(408,334);
        p1 = String.valueOf(p1.charAt(0));
        //>R<asiseto
        String p2;
        p2 = ScanPixelColour.scanPixelColour(408,336);
        p2 = String.valueOf(p2.charAt(0));
        //r>A<iseto
        String p3;
        p3 = ScanPixelColour.scanPixelColour(417,335);
        p3 = String.valueOf(p3.charAt(0));
        //r>A<iseto
        String p4;
        p4 = ScanPixelColour.scanPixelColour(417,336);
        p4 = String.valueOf(p4.charAt(0));
        //raise>T<o
        String p5;
        p5 = ScanPixelColour.scanPixelColour(444,336);
        p5 = String.valueOf(p5.charAt(0));

        String result = p1 + p2 + p3 + p4 +p5;

        System.out.println(result);
    }
}