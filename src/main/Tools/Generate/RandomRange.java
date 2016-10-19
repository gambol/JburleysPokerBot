package main.Tools.Generate;
import java.util.Random;

public class RandomRange
{
    public static int run(int start, int end)
    {
        Random rand1 = new Random();
        int randomNum = rand1.nextInt((end - start) + 1) + start;
        return randomNum;
    }
}
