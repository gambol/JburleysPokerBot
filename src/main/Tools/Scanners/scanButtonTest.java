package main.Tools.Scanners;

import org.junit.Test;

public class scanButtonTest
{
    @Test
    public void scanFold() throws Exception
    {
        //main.Tests.setObserver.generate();

        //F
        String checkWhite1;
        checkWhite1 = ScanPixelColour.scanPixelColour(256,341);
        checkWhite1 = String.valueOf(checkWhite1.charAt(0));

        //O
        String checkWhite2;
        checkWhite2 = ScanPixelColour.scanPixelColour(265,341);
        checkWhite2 = String.valueOf(checkWhite2.charAt(0));

        //L
        String checkWhite3;
        checkWhite3 = ScanPixelColour.scanPixelColour(271,341);
        checkWhite3 = String.valueOf(checkWhite3.charAt(0));

        //D
        String checkWhite4;
        checkWhite4 = ScanPixelColour.scanPixelColour(276,341);
        checkWhite4 = String.valueOf(checkWhite4.charAt(0));

        String result = checkWhite1 + checkWhite2 + checkWhite3 + checkWhite4;

        System.out.println("RESULT :" + result);
    }

    @Test
    public void scanCheck() throws Exception
    {
        //C
        String p1;
        p1 = ScanPixelColour.scanPixelColour(332,341);
        p1 = String.valueOf(p1.charAt(0));

        //H
        String p2;
        p2 = ScanPixelColour.scanPixelColour(343,341);
        p2 = String.valueOf(p2.charAt(0));

        //E
        String p3;
        p3 = ScanPixelColour.scanPixelColour(349,341);
        p3 = String.valueOf(p3.charAt(0));

        //C
        String p4;
        p4 = ScanPixelColour.scanPixelColour(354,341);
        p4 = String.valueOf(p4.charAt(0));

        //K
        String p5;
        p5 = ScanPixelColour.scanPixelColour(360,341);
        p5 = String.valueOf(p5.charAt(0));

        String result = p1 + p2 + p3 + p4 + p5;

        System.out.println("RESULT :" + result);
    }

    @Test
    public void scanCall() throws Exception
    {
        //C
        String p1;
        p1 = ScanPixelColour.scanPixelColour(341,336);
        p1 = String.valueOf(p1.charAt(0));

        //A
        String p2;
        p2 = ScanPixelColour.scanPixelColour(350,336);
        p2 = String.valueOf(p2.charAt(0));

        //L
        String p3;
        p3 = ScanPixelColour.scanPixelColour(355,336);
        p3 = String.valueOf(p3.charAt(0));

        //L
        String p4;
        p4 = ScanPixelColour.scanPixelColour(357,336);
        p4 = String.valueOf(p4.charAt(0));

        String result = p1 + p2 + p3 + p4;
        System.out.println("RESULT :" + result);

    }

    @Test
    public void scanRaise() throws Exception
    {
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
        System.out.println("RESULT :" + result);
    }

    @Test
    public void scanPixel()
    {
        //main.Tests.setObserver.generate();

        //C
        String checkRed1;
        checkRed1 = ScanPixelColour.scanPixelColour(336,341);
        checkRed1 = String.valueOf(checkRed1.charAt(0));
        String checkWhite1;
        checkWhite1 = ScanPixelColour.scanPixelColour(336,342);
        checkWhite1 = String.valueOf(checkWhite1.charAt(0));
        //H
        String checkRed2;
        checkRed2 = ScanPixelColour.scanPixelColour(343,339);
        checkRed2 = String.valueOf(checkRed2.charAt(0));
        String checkWhite2;
        checkWhite2 = ScanPixelColour.scanPixelColour(344,339);
        checkWhite2 = String.valueOf(checkWhite2.charAt(0));
        //E
        String checkRed3;
        checkRed3 = ScanPixelColour.scanPixelColour(350,341);
        checkRed3 = String.valueOf(checkRed3.charAt(0));
        String checkWhite3;
        checkWhite3 = ScanPixelColour.scanPixelColour(350,342);
        checkWhite3 = String.valueOf(checkWhite3.charAt(0));
        //implement "CK" if necessary

        String result = checkRed1 + checkRed2 + checkRed3 + checkWhite1 + checkWhite2 + checkWhite3;

        System.out.println("RESULT :" + result);
    }
}