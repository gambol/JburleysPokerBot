package main.Tools.Generate.TTA;

import mi.poker.calculation.EquityCalculation;
import mi.poker.calculation.Result;

import static main.Classes.sysUpdate.msg;

public class Equity
{
    public static void run(String omega, int opp, String ftr)
    {
        int opponents = opp;
        if (opponents>=6){opponents = 5;}
        String opponent = "";
        int finalEquityFromCalc;

        for (int i = 0; i < opponents; i++){opponent = opponent + ",XxXx";}

        String arg1 = omega + opponent;
        String arg2 = ftr;


        Result res = EquityCalculation.calculateMonteCarlo(arg1, arg2, "");
        String resToStr = String.valueOf(res);

        // Check opponent score
        //System.out.println("The unaltered EQ :>>: " + res);

        if (resToStr.contains(".")) {
        String[] splitResToStr = resToStr.split("[.]");
        resToStr = splitResToStr[0];
        }

        if (resToStr.length()>2) {
            resToStr = resToStr.substring(0,2);
        }

        finalEquityFromCalc = Integer.parseInt(resToStr);
        main.Switchboard.setEquity(finalEquityFromCalc);
    }
}

