package main.Tools.Generate.TTA;

import org.junit.Test;

import static org.junit.Assert.*;

public class PotTest
{
    @Test
    public void run() throws Exception
    {
        main.Tests.setObserver.staticImage();
        main.Tools.Generate.TTA.Pot.run();
    }
}