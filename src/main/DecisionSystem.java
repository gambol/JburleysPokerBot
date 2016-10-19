package main;

import java.text.DecimalFormat;

import static java.lang.Math.round;
import static main.Classes.sysUpdate.msg;

public class DecisionSystem
{
    public DecisionSystem(double tableRaise, double pot, int equity, int stage, String omega, int opponents)
    {
        main.Tools.Generate.TTA.Dealer.generate();
        int dealer = main.Switchboard.getDealer();
        int chen = (int) main.Classes.ExternalClasses.Chen.Chen.ChenFormula(omega);
        double blind = 0.04;
        double potOdds = round(tableRaise / (pot + tableRaise) * 100);
        pot = ((int) round(pot));

        if ( stage == 1 )
        {
            runChen(chen, tableRaise, blind, dealer, opponents, pot);
        }
        else if ( chen > 0 )
        {
            runEquity(equity, potOdds, stage, tableRaise, pot, dealer, opponents, chen);
        }
        else
        {
            checkFold();
        }
    }

    public static void runChen(int chen, double tableRaise, double blind, int dealer, int opponents, double pot)
    {
        System.out.println("Chen :" + chen + " Dealer :" + dealer);

        if ( chen < 0 ) // Junk
        {
            if ( dealer > 0 && tableRaise < 0.04 )
            {
                raise(0.04);
            }
            else
            {
                checkFold();
            }
        }

        else if ( chen > 0 && chen <= 50) // Draws
        {
            if ( tableRaise < 0.04 )
            {
                raise(0.04);
            }
            else if ( tableRaise <= 0.08 )
            {
                checkCall();
            }
            else
            {
                checkFold();
            }
        }

        else if ( chen == 55 ) // PP
        {
            if ( tableRaise < 0.04 )
            {
                raise(0.04);
            }
            else if ( tableRaise <= 0.20 )
            {
                checkCall();
            }
            else
            {
                checkFold();
            }
        }

        else if ( chen > 60 ) // AA
        {
            if ( tableRaise == 0.02 )
            {
                checkCall();
            }
            else if ( tableRaise > 0.02 )
            {
                raiseHell(100);
            }
        }
        else
        {
            checkFold();
        }
    }

    public static void runEquity(int equity, double potOdds, int stage, double tableRaise, double pot, int dealer, int opponents, int chen)
    {
        //double bet = betSize(pot, equity, chen);
        msg("Equity: " + equity);
        msg("Pot odds: " + potOdds);
        msg("\n");

        if ( equity < 80 )
        {
            //Tatic for hihg draws.
            if ( chen > 0 && chen <= 50 )
            {
                if ( stage == 2 )
                {
                    if ( tableRaise == 0 )
                    {
                        raise(0.06);
                    }
                    else
                    {
                        if ( equity > potOdds )
                        {
                            checkCall();
                        }
                    }
                }
                if ( stage == 3 )
                {
                    if ( tableRaise == 0 )
                    {
                        raise(0.08);
                    }
                    else
                    {
                        if ( equity > potOdds )
                        {
                            checkCall();
                        }
                    }
                }
                if ( stage == 4 )
                {
                    if ( tableRaise == 0 )
                    {
                        raise(0.08);
                    }
                    else
                    {
                        if ( equity > 65 && tableRaise < 0.30 )
                        {
                            checkCall();
                        }
                    }
                }
            }

            //Tatic for set mining
            else if ( chen == 55 )
            {
                checkFold();
            }
        }

        //Don't scare the fish.
        if ( equity >= 80 )
        {

            if ( chen > 0 && chen <= 50 )
            {
                main.Tools.Generate.TTA.Pot.run();
                DecimalFormat df = new DecimalFormat("#0.00");
                double fraction = Double.parseDouble(df.format(main.Switchboard.getPot() * 0.45));
                double bet = main.Switchboard.getPot() - fraction;
                msg("Our BetSize: " + bet);
                raise(bet);
            }
            else if ( chen == 55 )
            {
                main.Tools.Generate.TTA.Pot.run();
                DecimalFormat df = new DecimalFormat("#0.00");
                double fraction = Double.parseDouble(df.format(main.Switchboard.getPot() * 0.65));
                double bet = main.Switchboard.getPot() - fraction;
                msg("Our BetSize: " + bet);
                raise(bet);
            }

        }
    }

/*    private static double betSize(double pot, double equity, int chen)
    {
        double betSize = main.Switchboard.getPot()*0.7; // bet size if we hit aset

        if ( chen > 0 && chen <= 50 )
        {
            betSize = pot * (0.45); // bet size if we hit straight
        }

        if ( betSize <= 0.04 )
        {
            betSize = 0.04;
        }

        DecimalFormat df = new DecimalFormat("#0.00");
        betSize = Double.parseDouble(df.format(betSize));

        return betSize;
    }*/

    private static void raise(double raise)
    {
        System.out.print(">>> Raising: " + raise);
        main.Switchboard.setRaiseRange(raise);
        main.Switchboard.setActionScore(2);
    }

    private static void checkCall()
    {
        System.out.print(">>> Check / Call. ");
        main.Switchboard.setActionScore(1);
    }

    private static void checkFold()
    {
        System.out.print(">>> Check / Fold. ");
        main.Switchboard.setActionScore(0);
    }

    private static void raiseHell(double raise)
    {
        System.out.print("/////////////////////// Raising Hell: " + raise);
        main.Switchboard.setRaiseRange(raise);
        main.Switchboard.setActionScore(3);
    }
}