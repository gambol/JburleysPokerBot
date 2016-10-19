package main.Tests.Run;

import org.junit.Test;

import java.io.IOException;

public class scanHoleCards
{
    @Test
    public void ReadHoleCards() throws IOException
    {
        main.Tests.setObserver.staticImage();
        System.out.println(main.Tools.Generate.Board.cardGen(0));
        System.out.println(main.Tools.Generate.Board.cardGen(1));
    }
}
