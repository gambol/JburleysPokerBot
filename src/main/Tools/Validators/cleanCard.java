package main.Tools.Validators;

/**
 *
 */
public class cleanCard
{
    public static String run(String in)
    {
        //TODO: Run if removing the '0' from this list has consiquences ->
        String newCard = in;
        newCard.trim();
        newCard = newCard.replace("S", "8");
        newCard = newCard.replaceAll("[^JQKA123456789]", "");
        newCard = newCard.replace("1", "T");

        if (newCard.length()>1){
            newCard = newCard.substring(0,1);
        }

        return newCard;
    }

}
