package main.Tools.Scanners;

public class scanButton
{
    public static boolean scanFold()
    {
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

        if (result.equals("WBWB"))
        {
            return true;
        } else {
            return false;
        }
    }

    public static boolean scanFastFold()
    {
        String checkWhite1;
        checkWhite1 = ScanPixelColour.scanPixelColour(258,330);
        checkWhite1 = String.valueOf(checkWhite1.charAt(0));
        String checkWhite2;
        checkWhite2 = ScanPixelColour.scanPixelColour(258,334);
        checkWhite2 = String.valueOf(checkWhite2.charAt(0));
        String checkWhite3;
        checkWhite3 = ScanPixelColour.scanPixelColour(258,344);
        checkWhite3 = String.valueOf(checkWhite3.charAt(0));
        String checkWhite4;
        checkWhite4 = ScanPixelColour.scanPixelColour(258,348);
        checkWhite4 = String.valueOf(checkWhite4.charAt(0));

        String result = checkWhite1 + checkWhite2 + checkWhite3 + checkWhite4;
        //System.out.println(result);
        if (result.equals("WWWW"))
        {
            return true;
        } else {
            return false;
        }
    }

    public static boolean scanCheck()
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

       if (result.equals("WBBWW"))
       {
            return true;
        } else {
            return false;
        }
    }

    public static boolean scanCall()
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

        if (result.equals("WBWB"))
        {
            return true;
        } else {
            return false;
        }
    }

    public static boolean scanRaise()
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

        if (result.equals("WBGBW"))
        {
            return true;
        } else {
            return false;
        }
    }

    public static boolean scanForPush()
    {
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

            if (result.equals("BBBBB"))
            {
                return true;
            } else {
                return false;
            }
        }
    }
}