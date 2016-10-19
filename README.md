JBURLEYS POKERBOT
-----------------
Here we have a poker bot fully written in Java. At the bots current state this it works as a great tool to discard rubbish hands, provide equity calculations, generate pot odds, and it’s useful for finding a good table position on your opponents. However what I wanted was full automation, and for this I needed AI. So I’ve decided to release the source code to those who might improve on what I’ve done so far.

------------------
How does it work?

Basically it uses template matching to determine the pot size, the opponent bet, how many opponents are on the table, the cards you are holding, and what community cards are on offer.
With this information the bot will provide you with equity calculations, a custom Chen score, pot odds, and even carry out the task of raising, checking, and folding. 
You can recalibrate the Chen score to your own style of play.

------------------
Set up
------------------
You can use this bot on any piece of poker software, you just have to set it up. It can be quite daunting at first, but once you get the idea it’s pretty easy, and you can even improve on how it works from there. I have included as many comments as I can to help you get started. To begin, start off with the ThreadController.Java class. It’s where it all starts.

It’s not at all fair to say my bot uses MVC, but it I tried to get a class to generate a value, send it to the switchboard class, which is then makes it accessible to other classes via getter/setters. So if you want to find a value, check the switchboard. If you want to use it though, you will need to “generate” it first.

------------------
Q&A
------------------
- What is the Chen score?

My software uses a style of evaluation based on the formula by William "Bill" Chen.

- What is Omega?

It’s my term for the hole card code, i.e “KhQd”. 

- Raise hell? What?

“Raising hell” is a scripting shortcut designed to steamroll an opponents all in bet.
 
- Fast fold ()?

I decided to target the “zoom” style tables, it’s quicker and opponents won’t catch onto what you’re doing. You can turn this off in the Thread controller and the Decision Maker class.

- Round vs stage

A round consists of 5 stages: empty, preflop, flop, turn, and river. Each round gets its own token to keep it unique.

- TTA?

Time-To-Act. It’s the moment the program needs to calculate and make the split-second decision, to check, raise, or fold. 

------------------
History
------------------
It started off as a personal project of mine. It used the optical character recognition libraries (tesseract) but was unstable, so I moved over to template matching.

------------------
Issues
------------------
-Needs OOP
-Needs AI
-Lacks multi-table functionality.
-Sometimes the Monte Carlo simulation doesn’t work with pocket queens.

------------------
Testing
------------------
The testing is a bit messy but you can read the code to get a better understanding of what things are supposed to do.

------------------
Recommendations:
I built this project with IntelliJ. It’s an amazing IDE and I would like to recommend it to Java developers. It’s free for open source material and you can find it here.
https://www.jetbrains.com/idea/download/
__________________________________________________________________________________

Finally I would like to thank the open source community.  It feels good to give something back. 
If you do manage to get this program working with AI, please send a copy my way :)

J

