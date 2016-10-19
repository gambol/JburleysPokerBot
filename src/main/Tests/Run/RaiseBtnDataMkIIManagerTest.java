package main.Tests.Run;
import org.junit.Test;

import static java.lang.Math.round;

public class RaiseBtnDataMkIIManagerTest
{
    @Test
    public void equityFormula() throws Exception
    {
        int oppoenetBetInt = 1400;
        double oppoenetBet = oppoenetBetInt;
        int tablePotInt = 3373;
        double tablePot = tablePotInt;
        double callEquity;
        int ourEquity = 16;

        //The "round"ing down means we need to take the > majority in the next part.
        callEquity = round((1/((tablePot + oppoenetBet)/oppoenetBet)*100));

        if (callEquity > ourEquity)
        {
            System.out.println(" Call.Eq " + callEquity + " > Our.Eq " + ourEquity + " : FOLD");
            //return eq:false
        } else {
            System.out.println(" Call.Eq " + callEquity + " > Our.Eq " + ourEquity + " : CALL");
            //return eq:true
        }
        //IF (cE %50 OF oE) {Consider and excess equity for a reraise - or to trap/detect trap/detect draw?}
    }
}