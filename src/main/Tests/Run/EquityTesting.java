package main.Tests.Run;

import mi.poker.calculation.EquityCalculation;
import mi.poker.calculation.Result;
import org.junit.Test;

import java.text.DecimalFormat;

import static java.lang.Math.round;

public class EquityTesting
{
    @Test
    public void calculatePotOdds() throws Exception
    {
        int oppoenetBetInt = 100;
        double oppoenetBet = oppoenetBetInt;
        int tablePotInt = 300;
        double tablePot = tablePotInt;
        double potOdds;
        int ourEquity = 50;

        //divide the call by the pot plus the call (size of pot after call is made)
         //   bet / pot + bet


        //The "round"ing down means we need to take the > majority in the next part.
        //potOdds = round((1/((tablePot + oppoenetBet)/oppoenetBet)*100));

        potOdds = (oppoenetBet/(tablePot+oppoenetBet)*100);

        System.out.println("pot odds "+potOdds);

        if (potOdds > ourEquity)
        {
            System.out.println(" Call.Eq " + potOdds + " > Our.Eq " + ourEquity + " : FOLD");
            //return eq:false
        } else {
            System.out.println(" Call.Eq " + potOdds + " > Our.Eq " + ourEquity + " : CALL");
            //return eq:true
        }

        //IF (cE %50 OF oE) {Consider and excess equity for a reraise - or to trap/detect trap/detect draw?}
    }

    @Test
    public void genEqCorrectly() throws Exception
    {
        String hand = "Ad2d";
        String x = "";
        int opponents = 2;

        for (int i = 0; i < opponents; i++)
        {
            x = x + ",XxXx";
        }
        Result eq1 = EquityCalculation.calculateMonteCarlo(hand+x,"8c5c5sQs","");
/*        String abc = (eq1.toString());
        String i = abc.substring(0,2);*/
        System.out.println(eq1);
    }

    @Test
    public void gencorrectly2() throws Exception
    {
        String omega = "KhKd";
        int opponenets = 2;
        String ftr = "Jh9h2s";

            String opponents = "";
            int convert = 0;

            if ( opponenets < 6 )
            {
                for (int i = 0; i < opponenets; i++)
                {
                    opponents = opponents + ",XxXx";
                }
                String arg1 = omega + opponents;
                String arg2 = ftr;
                Result res = EquityCalculation.calculateMonteCarlo(arg1, arg2, "");
                String resToStr = String.valueOf(res);

                System.out.println("DEBUG RES: " +res);

                if (resToStr.contains("."))
                {
                    String[] splitResToStr = resToStr.split("[.]");
                    resToStr = splitResToStr[0];
                }

                convert = Integer.parseInt(resToStr);
                main.Switchboard.setChen(0);
                System.out.println(convert);

            } else {
                int chen = (int) main.Classes.ExternalClasses.Chen.Chen.ChenFormula(omega);
                System.out.println("Too many players. Switching to Chen score: " + chen);
                main.Switchboard.setEquity(0);
                main.Switchboard.setChen(chen);
            }
    }

    @Test
    public void EQvsChen() throws Exception
    {
        String hand = "KhKd";
        System.out.println("Chen :" + main.Classes.ExternalClasses.Chen.Chen.ChenFormula(hand));

        String x = "";
        int opponents = 3;

        for (int i = 0; i < opponents; i++)
        {
            x = x + ",XxXx";
        }
        Result eq1 = EquityCalculation.calculateMonteCarlo(hand + x, "", "");
        String abc = (eq1.toString());
        String i = abc.substring(0, 2);
        System.out.println("EQ: " + i);
    }

    @Test
    public void PotOdds() throws Exception
    {
        int oppoenetBetInt = 30;
        double oppoenetBet = oppoenetBetInt;
        int tablePotInt = 90;
        double tablePot = tablePotInt;
        double callEquity;
        int ourEquity =25;

        //The "round"ing down means we need to take the > majority in the next part.
        callEquity = round((1 / ((tablePot + oppoenetBet) / oppoenetBet) * 100));

        if ( callEquity > ourEquity )
        {
            System.out.println(" Call.Eq " + callEquity + " > Our.Eq " + ourEquity + " : FOLD");
            //return eq:false
        }
        else
        {
            System.out.println(" Call.Eq " + callEquity + " > Our.Eq " + ourEquity + " : CALL");
        }
    }

    @Test
    public void ChenVsEq_PotOdds() throws Exception
    {
        int oppoenetBetInt = 300;
        int tablePotInt = 600;
        int opponents = 2;
        String hand = "AdKd";

        double oppoenetBet = oppoenetBetInt;
        double tablePot = tablePotInt;
        double callEquity;
        String x = "";
        for (int i = 0; i < opponents; i++){x = x + ",XxXx";}
        int chen = (int) main.Classes.ExternalClasses.Chen.Chen.ChenFormula(hand);
        Result eq1 = EquityCalculation.calculateMonteCarlo(hand + x, "", "");
        String abc = (eq1.toString()); String i = abc.substring(0, 2);
        int ourEquity = Integer.parseInt(i);

        System.out.println("Chen :" + chen); System.out.println("EQ: " + i);

        callEquity = round((1 / ((tablePot + oppoenetBet) / oppoenetBet) * 100));
        if ( callEquity > ourEquity ) {
            System.out.println("Call.Eq " + callEquity + " > Our.Eq " + ourEquity + " : FOLD");}
        else {
            System.out.println("Call.Eq " + callEquity + " > Our.Eq " + ourEquity + " : CALL");
        }
        if (chen>10){
            System.out.println("Chen would  call : " + chen);}
        else {
            System.out.println("Chen would fold : " + chen);
        }
    }

    @Test
    public void TestTheEquityCalculationForRealsYo()
    {
        for(int i =0;i<100;i++)
        {
            int equity          = 80;
            int stage           = 1;
            double tableRaise   = main.Tools.Generate.RandomRange.run(0,80)/10;
            double pot          = main.Tools.Generate.RandomRange.run(30,80)/10;
            double potOdds      = round(tableRaise / (pot + tableRaise) * 100);
            int dealer          = main.Tools.Generate.RandomRange.run(1,3);
            int opponents       = main.Tools.Generate.RandomRange.run(1,3);
            int chen            = main.Tools.Generate.RandomRange.run(30,66);

            System.out.println("equity : "+equity);
            System.out.println("potOdds : "+potOdds);
            System.out.println("stage : "+stage);
            System.out.println("tableRaise : "+tableRaise);
            System.out.println("pot : "+pot);
            System.out.println("dealer : "+dealer);
            System.out.println("opponents : "+opponents);
            System.out.println("chen : "+chen);

            main.DecisionSystem.runEquity(equity,potOdds,stage,tableRaise,pot,dealer,opponents,chen);
            System.out.println("\n\n");
        }
    }

    @Test
    public void betSizing() throws Exception
    {
        double equity = 84;
        double pot = 0.14;


        double betSize = pot*(equity/100);


        if (betSize<0.04){betSize=0.04;}

        DecimalFormat df = new DecimalFormat("#0.00");
        System.out.print(df.format(betSize));

        /////fold to reraises
        //stage 1 = small pot
        //0.10 = bet 0.04 (stage x 0.02)
        //stage 2 = small pot
        //0.18 = bet 0.06 (stage + 0.02)
        //stage 3 = small pot
        //0.30 = bet 0.08 (stage ^ 0.02)
        //stage 4 = small pot
        //0.40 = bet 0.10 (stage + 0.02)
        //0.50 pot total // 0.28 spent

        //be willing to call if they double so EQ needs to be high:
        //stage 1 = big pot
        //0.20 = bet 0.10 (+ 1/2 of pot)
        //stage 2 = big pot
        //0.40 = bet 0.20 (+ 1/2 of pot)
        //stage 3 = big pot
        //0.60 = bet 0.30 (+ 1/2 of pot)
        //stage 4 = big pot
        //1.20 = bet 0.60 (+ 1/2 of pot)

        //so betting the 90% pot can win huge with +90 eq
        //stage 1
        //0.20 = bet 0.18 (+ 90% of pot)
        //stage 2
        //0.40 = bet 0.32 (+ 90% of pot)
        //stage 3
        //0.60 = bet 0.48 (+ 1/2 of pot)
        //stage 4
        //1.20 = bet 0.96 (+ 1/2 of pot)

        //So...betting over the pot is crazy. Scary even.
    }

}