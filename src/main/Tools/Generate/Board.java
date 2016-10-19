package main.Tools.Generate;

import main.Classes.ValidateCard;
import main.Tools.Scanners.ScanPixelColour;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Switchboard.*;

public class Board
{
    public static String cardGen(int ex)
    {
        String card = "";
        String suit;
        String beta = "";

        //Rapid Pixel Checker.
        String result = ScanPixelColour.scanPixelColour(248, 240);
        if(!(result.equals("ForestGreen")))
        {
            Rectangle cardArea = new Rectangle(Locations.eRekter(ex));
            BufferedImage image = Screen.getBimage();

            ITesseract tinstance = new Tesseract();
            try
            {
                card = tinstance.doOCR(image, cardArea);
            } catch (TesseractException e)
            {
                e.printStackTrace();
            }
            card = main.Tools.Validators.cleanCard.run(card);

            suit = ScanPixelColour.scanPixelColour(Locations.gap(ex).x, Locations.gap(ex).y);
            suit = main.Tools.Validators.cleanSuit.run(suit);

            if ( ValidateCard.run(card, suit) )
            {
                if ( !(card.isEmpty() && suit.isEmpty() && (card.length() == 2)) )
                {
                    beta = card + suit;
                }
            }
        }
        return beta;
    }

    public static void omegaGen() //Welcome to OmegaGen!
    {
        //So here we need to build the Omega, breaking it down, we need to generate the data for card 1.
        //This means we need its number and suit.
        String omega = "";
        String card1 = main.Tools.Generate.cardGenMkII.run(0);//<<CTRL+click "run" and off we go.
        String card2 = main.Tools.Generate.cardGenMkII.run(1);
        String beta = card1 + card2;

        if ( beta.length() == 4 )
        {
            omega = beta;
        }
        setOmega(omega);
    }

    public static void flopGen()
    {
        String flop = "";
        String card2 = main.Tools.Generate.cardGenMkII.run(2);
        String card3 = main.Tools.Generate.cardGenMkII.run(3);
        String card4 = main.Tools.Generate.cardGenMkII.run(4);
        String beta = card2 + card3 + card4;

        if ( beta.length() == 6 )
        {
            flop = beta;
        }

        setFlop(flop);
    }

    public static void turnGen()
    {
        String turn = "";
        String card5 = main.Tools.Generate.cardGenMkII.run(5);
        String beta = card5;

        if ( beta.length() == 2 )
        {
            turn = beta;
        }
        setTurn(turn);
    }

    public static void riverGen()
    {
        String river = "";
        String card6 = main.Tools.Generate.cardGenMkII.run(6);
        String beta = card6;

        if ( beta.length() == 2 )
        {
            river = beta;
        }
        setRiver(river);
    }

    public static int stageGen()
    {
        int cardsOnTheBoard;
        String card;
        String bomega = "";

        for (int i = 0; i<7; i++)
        {
            card = main.Tools.Generate.cardGenMkII.cardGenTemplateMatch(i);
            //System.out.println("Card: "+card);

            if (!(card.isEmpty()))
            {
                bomega = bomega+ card;
                //System.out.println("Bomega: "+bomega);
            }
            else {
                break;
            }
        }

        cardsOnTheBoard = bomega.length();
        int stage = 0;

        if (cardsOnTheBoard == 2){
            stage = 1;
        }
        if (cardsOnTheBoard == 5){
            stage = 2;
        }
        if (cardsOnTheBoard == 6){
            stage = 3;
        }
        if (cardsOnTheBoard == 7){
            stage = 4;
        }

        //System.out.println("STAGE: "+stage);
        main.Switchboard.setStage(stage);
        return stage;
    }

    public static String compileBoard(int stage)
    {

        String noCardsOnBoard = "";

        if (stage==2){
            main.Tools.Generate.Board.flopGen();
            return main.Switchboard.getFlop();
        }
        else if (stage==3){
            main.Tools.Generate.Board.flopGen();
            main.Tools.Generate.Board.turnGen();
            return getFlop()+getTurn();
        }
        else if (stage==4){
            main.Tools.Generate.Board.flopGen();
            main.Tools.Generate.Board.turnGen();
            main.Tools.Generate.Board.riverGen();
            return getFlop()+getTurn()+getRiver();
        }
        else {
            return noCardsOnBoard;
        }
    }
}