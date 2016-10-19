package main.Tests.Run;

import org.junit.Test;


public class ChenTesting
{
    //System.out.println(c+"h"+h+"h : "+ main.Classes.ExternalClasses.Chen.Chen.ChenFormula(c+"h"+h+"h"));
    //System.out.println(c+"c"+h+"d : "+ main.Classes.ExternalClasses.Chen.Chen.ChenFormula(c+"c"+h+"d"));

    @Test
    public void myCheneration() throws Exception
    {
        //System.out.println("WHAT EQ? "+ EquityCalculation.calculateMonteCarlo("AhJc,XxXx","",""));

        //set grade:
        int low = 0;
        int high = 100;

        System.out.println("Suited: ");

        for(int i=0;i<13;i++){
            for(int x=0;x<13;x++)
            {
                String h = String.valueOf(main.Classes.ExternalClasses.Chen.zCard.getRankChar(x));
                String c = String.valueOf(main.Classes.ExternalClasses.Chen.zCard.getRankChar(i));

                //View Chen Group
                if((main.Classes.ExternalClasses.Chen.Chen.ChenFormula(c+"d"+h+"d")>=low)
                        &&(main.Classes.ExternalClasses.Chen.Chen.ChenFormula(c+"d"+h+"d")<high))
                {
                    System.out.println(c+"d"+h+"d : "+ main.Classes.ExternalClasses.Chen.Chen.ChenFormula(c+"d"+h+"d"));
                    //System.out.println(c+"d"+h+"d : "+ main.Classes.ExternalClasses.Chen.Chen.ChenFormula(c+"d"+h+"d"));
                    //System.out.println(EquityCalculation.calculateMonteCarlo(c+"h"+h+"d,XxXx","",""));
                }
            }
        }

        System.out.println("OffSuit: ");

        for(int i=0;i<13;i++){
            for(int x=0;x<13;x++)
            {
                String h = String.valueOf(main.Classes.ExternalClasses.Chen.zCard.getRankChar(x));
                String c = String.valueOf(main.Classes.ExternalClasses.Chen.zCard.getRankChar(i));

                //View Chen Group
                if((main.Classes.ExternalClasses.Chen.Chen.ChenFormula(c+"s"+h+"c")>=low)
                        &&(main.Classes.ExternalClasses.Chen.Chen.ChenFormula(c+"s"+h+"c")<high))
                {
                    System.out.println(c+"s"+h+"c : "+ main.Classes.ExternalClasses.Chen.Chen.ChenFormula(c+"s"+h+"c"));
                    //System.out.println(c+"d"+h+"d : "+ main.Classes.ExternalClasses.Chen.Chen.ChenFormula(c+"d"+h+"d"));
                    //System.out.println(EquityCalculation.calculateMonteCarlo(c+"h"+h+"d,XxXx","",""));
                }
            }
        }
    }

    @Test
    public void myChen() throws Exception
    {System.out.println("Chen : "+ main.Classes.ExternalClasses.Chen.Chen.ChenFormula("AhKh"));}

    @Test
    public void TestRange() throws Exception
    {
       for (int i=0;i<90;i++ )
       {
           int chen=i;
           if (chen<21) // Grade A
           {
               System.out.println("Chen: "+chen+" Number: "+i+" 20");
           }

       }
    }

}
