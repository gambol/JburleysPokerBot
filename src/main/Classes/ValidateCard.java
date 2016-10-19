package main.Classes;
import org.apache.commons.lang3.StringUtils;

public class ValidateCard
{
    public static boolean run(String card, String suit)
    {
        //TODO: Run if removing the '1' from this list has consiquences ->
        char[] cardArray = new char[]
                { 'T', 'J', 'Q', 'K', 'A', '2', '3', '4', '5', '6', '7', '8', '9' };
        char[] suitArray = new char[]
                { 'h', 's', 'd', 'c' };

        return !(card==null)
                && StringUtils.containsAny(card, cardArray)
                && StringUtils.containsAny(suit, suitArray);
    }
}
