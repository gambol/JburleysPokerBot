package main.Tools.Validators;

public class cleanSuit
{
    public static String run(String flopSuit)
    {
        String newFlopSuit = flopSuit;
        newFlopSuit = newFlopSuit.trim();
        newFlopSuit = newFlopSuit.replaceAll("[^DCHS]", "");
        if (!(newFlopSuit.isEmpty())){
            newFlopSuit = String.valueOf(newFlopSuit.charAt(0));
            newFlopSuit = newFlopSuit.toLowerCase();
            return newFlopSuit;
        } else {
            return "";
        }
    }
}
