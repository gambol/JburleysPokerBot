package main;

public class Switchboard
{
    private static int chen;
    private static int actionScore = 100;
    private static int equity;
    private static double raise;
    private static String omega;
    private static String flop;
    private static String turn;
    private static String river;
    private static int opponents;
    private static int stage;

    public static double getPot()
    {
        return pot;
    }

    public static void setPot(double pot)
    {
        Switchboard.pot = pot;
    }

    private static double pot;

    public static void setRaiseRange(Double raiseRange)
    {
        Switchboard.raiseRange = raiseRange;
    }

    private static Double raiseRange;
    public static int dealer;



    public static int getDealer(){return dealer;}
    public static void setDealer(int dealer){Switchboard.dealer = dealer;}
    public static Double getRaiseRange(){return raiseRange;}
    public static String getOmega() {return omega;}
    //public static int getChen(){return chen;}
    public static void setChen(int chen){Switchboard.chen = chen;}
    public static double getRaise() {return raise;}
    public static void setRaise(double raise)
    {
        Switchboard.raise = raise;
    }
    public static void setPot(int pot){Switchboard.pot = pot;}
    public static int getEquity() {return equity;}
    public static void setEquity(int equity) {Switchboard.equity = equity;}
    public static int getOpponents(){return opponents;}
    public static void setOpponents(int opponents){Switchboard.opponents = opponents;}
    public static void setOmega(String omega){Switchboard.omega = omega;}
    public static String getFlop()
    {
        return flop;
    }
    public static void setFlop(String flop)
    {
        Switchboard.flop = flop;
    }
    public static String getTurn()
    {
        return turn;
    }
    public static void setTurn(String turn)
    {
        Switchboard.turn = turn;
    }
    public static String getRiver()
    {
        return river;
    }
    public static void setRiver(String river)
    {
        Switchboard.river = river;
    }
    public static int getStage(){return stage;}
    public static void setStage(int stage)
    {
        Switchboard.stage = stage;
    }
    public static int getActionScore(){return actionScore;}
    public static void setActionScore(int action){Switchboard.actionScore = action;}
}