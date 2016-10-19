import main.ThreadController;

import java.io.FileNotFoundException;

public class Tess3
{
    public static void main(String[] args) throws FileNotFoundException
    {
        // Log code here:
            //String fileName = new SimpleDateFormat("ddhhmm'.txt'").format(new Date());
            //PrintStream log = new PrintStream(new FileOutputStream("Tess3\\log files\\"+fileName+"output.txt"));
            //System.setOut(log);

        Runnable controller = new ThreadController.THREADS();
        Thread Controller = new Thread(controller);

        /*
        Basically you will need to set the poker client window to a constant size,
        be aware there are security features in the client to overcome this.
        As there are deliberate and subtle changes in the screen size and colour to throw bots off,
        but a little further design can overcome this, but that’s up to you to implement,
        for now if you don't intend on using full automation, it's not a problem.
         */

        main.Tools.Generate.Screen.setScreenX(882); //<< Poker client top X value on my screen
        main.Tools.Generate.Screen.setScreenY(2);   //<< Poker client top Y value on my screen
        Controller.start();


        //It's not as simple as this to set up multi screens, but it was worth a try:
            /*
            Runnable controller2 = new ThreadController.THREADS();
            Thread Controller2 = new Thread(controller2);
            main.Tools.Generate.Screen.setScreenX(882);
            main.Tools.Generate.Screen.setScreenY(362);
            Controller2.start();
            */

    }
}