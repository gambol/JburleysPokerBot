package main.Tools.Generate.TTA;

import org.junit.Test;

import static org.junit.Assert.*;

public class templar_tests
{
    @Test
    public void testRaise() throws Exception
    {
        main.Tests.setObserver.staticImage();
        main.Tools.Generate.TTA.RaiseBtnDataMkII.run();
        System.out.println(main.Switchboard.getRaise());
    }
    @Test
    public void testPot() throws Exception
    {
        main.Tests.setObserver.staticImage();
        main.Tools.Generate.TTA.Pot.run();
        System.out.println(main.Switchboard.getPot());
    }
}