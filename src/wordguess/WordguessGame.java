package wordguess;

import java.util.Random;

public class WordguessGame {
		WordGuessIO userInput = new WordGuessIO();
		Words word = new Words();
		GameHuman gh = new GameHuman();
		GameComputer gc = new GameComputer();
		
	
	public void start() {
		System.out.println("=========================================================");
		System.out.println("| Welkom bij Wordguess!                                 |");
		System.out.println("=========================================================");
		System.out.println("| Je gaat tegen de computer spelen.                     |");
		System.out.println("| Het doel is zo snel mogelijk een woord te raden.      |");
		System.out.println("| Het 12-letterwoord moet gekozen worden uit de lijst.  |");
		System.out.println("| Jij raadt een woord van de computer en andersom.      |");
		System.out.println("| Wie het snelst het woord raadt wint.                  |");
		System.out.println("| Bij een gelijkspel spelen we nog een ronde.           |");
		System.out.println("=========================================================");
		System.out.print("Wat is je naam? : ");
		userInput.setPlayerName();
		System.out.println("Welkom " + userInput.getPlayerName());
		doToss();
	}
	
	public void doToss() {
		System.out.println("Eerst doen we een TOSS om wie mag beginnen.");
		System.out.print("wil je kop of munt? : ");
		userInput.setHeadOrTail();
		System.out.println("Er wordt 5 keer gegooid. De kant die het meeste");
		System.out.println("keer gegooid wordt, mag beginnen");
		Random random = new Random();
		String[] kopOfMuntArray = new String[5];
		int aantalKeerKop = 0;
		int aantalKeerMunt = 0;
		
		// Voorbereiding voor het flippen
		for (int i = 0; i < kopOfMuntArray.length; i++) {
			boolean randomBoolean = random.nextBoolean();
			if (randomBoolean) {
				kopOfMuntArray[i] = "kop";
				aantalKeerKop++;
			} else {
				kopOfMuntArray[i] = "munt";
				aantalKeerMunt++;
			}
		}
		// daadwerkelijk flippen
		System.out.print("flipping: ");
		for (int i = 0; i < kopOfMuntArray.length; i++) {
			System.out.print(kopOfMuntArray[i] + "|");
		}
		
		// Resultaat flippen
		String result = " ";
		System.out.println();
		System.out.print("Het is geworden: ");
		if (aantalKeerKop < aantalKeerMunt) {
			System.out.println("munt (" + aantalKeerMunt + " keer gegooid)");
			result = "munt";
		} else {
			System.out.println("kop (" + aantalKeerKop + " keer gegooid)");
			result = "kop";
		}
		
		// Wie mag er beginnen?
		if (result.equals(userInput.getHeadOrTail())) {
			System.out.println(userInput.getPlayerName() + " jij mag beginnen!");
			new GameHuman().play();
			new GameComputer().play();
			endOfGames();
		} else {
			System.out.println("De computer begint!");
			new GameComputer().play();
			new GameHuman().play();
			endOfGames();
		}
		
	}
	
	public void endOfGames() {
		System.out.println("De score van de computer is: " + GameComputer.getComputerScore());
		System.out.println(userInput.getPlayerName() + ", jouw score is: " + GameHuman.getHumanScore());
		if (word.checkCheatmode()) {
			System.out.println("Het is gelijk spel omdat je de cheatmode hebt aangezet, valsspeler! We gaan nog een ronde spelen.");
			doToss();
		}
		
		if (GameComputer.getComputerScore() == GameHuman.getHumanScore()) {
			System.out.println("Het is gelijk spel. We gaan nog een ronde spelen! Zet hem op!");
			doToss();
		} else if (GameComputer.getComputerScore() < GameHuman.getHumanScore()) {
			System.out.println("De computer wint! Je hebt je laten verslaan door de computer, " + userInput.getPlayerName() + "!");
		} else {
			System.out.println(userInput.getPlayerName() + ", jij hebt gewonnen van de computer. "
					+ "Logisch natuurlijk, want jij bent natuurlijk veel slimmer dan de computer!");
		}
		
		System.out.println("=======================================================");
		System.out.println("=======================================================");
		System.out.println("Tot ziens!");
		System.out.println("PS: Heb je de easter egg al gevonden? Hint: https://www.youtube.com/watch?v=eWM2joNb9NE");
		System.out.println("PPS: Misschien moet je een deel van de naam van het liedje invoeren als je een letter/positie invoert?");
	}
	
	public void printArt(int input) {
		if (input == 0) {
			System.out.println("_________________________________________________________\r\n"
					+ "\r\n"
					+ "              o         o         o     o\r\n"
					+ "                        |        <|>   /|\\\r\n"
					+ "__________________________________|____/_\\_______________");
		} else if (input == 1) {
			System.out.println("_________________________________________________________\r\n"
					+ "  ,--.\r\n"
					+ " | oo |\r\n"
					+ " | ~~ |         o         o    o       o\r\n"
					+ " |/\\/\\|                   |     |>      |>\r\n"
					+ "________________________________|______/_\\_______________ ");
		} else if (input == 2) {
			System.out.println("_________________________________________________________\r\n"
					+ "   ,--.\r\n"
					+ "  |  oo|\r\n"
					+ "  |  ~~|         o        o     o      _o/\r\n"
					+ "  |/\\/\\|                  |    <|\\      |\r\n"
					+ "________________________________|_______|________________");
		} else if (input == 3) {
			System.out.println("_________________________________________________________\r\n"
					+ "    ,--.\r\n"
					+ "   |  oo|                                 help\r\n"
					+ "   |  {}|o             o      __o/     \\o/\r\n"
					+ "   |/\\/\\|              |        |       |\r\n"
					+ "________________________________/)______|________________");
		} else if (input == 4) {
			System.out.println("_________________________________________________________\r\n"
					+ "          ,--.\r\n"
					+ "         |  oo|\r\n"
					+ "         |  ~~| o               __o     \\o\r\n"
					+ "         |/\\/\\| |                |\\     |>\r\n"
					+ "_________________________________/_)____|\\_______________ ");
		} else if (input == 5) {
			System.out.println("_________________________________________________________\r\n"
					+ "              ,--.\r\n"
					+ "             |  oo|\r\n"
					+ "             |  {}|o              \\o\r\n"
					+ "             |/\\/\\|               |\\      __\\o\r\n"
					+ "__________________________________|\\_____|)__|___________");
		} else if (input == 6) {
			System.out.println("_________________________________________________________\r\n"
					+ "                         ,--.\r\n"
					+ "                        |  oo|\r\n"
					+ "                        |  ~~|     \\o/     __|\r\n"
					+ "                        |/\\/\\|      |        \\o\r\n"
					+ "___________________________________/_\\_______(_\\_________");
		} else if (input == 7) {
			System.out.println("_________________________________________________________\r\n"
					+ "                           ,--.\r\n"
					+ "                          |  oo|\r\n"
					+ "                          |  {}|o\r\n"
					+ "                          |/\\/\\||          o/__\r\n"
					+ "____________________________________________|_(\\_________ ");
		} else if (input == 8) {
			System.out.println("_________________________________________________________\r\n"
					+ "                                  ,--.\r\n"
					+ "                                 |  oo|\r\n"
					+ "                                 |  ~~|      o__\r\n"
					+ "                                 |/\\/\\|      /\\\r\n"
					+ "_____________________________________________/_|_________ ");
		} else if (input == 9) {
			System.out.println("_________________________________________________________\r\n"
					+ "                                          ,--.\r\n"
					+ "                                         |  oo|\r\n"
					+ "                                         |  {}|o\r\n"
					+ "                                         |/\\/\\||\r\n"
					+ "_________________________________________________________ ");
		} else if (input == 10) {
			System.out.println("_________________________________________________________\r\n"
					+ "  ___     __    __  __   ____   _____   _  _  ____  ____\r\n"
					+ " / __)   /__\\  (  \\/  ) ( ___) (  _  ) ( \\/ )( ___)(  _ \\\r\n"
					+ "( (_-.  /(__)\\  )    (   )__)   )(_)(   \\  /  )__)  )   /\r\n"
					+ " \\___/ (__)(__)(_/\\/\\_) (____) (_____)   \\/  (____)(_)\\_)\r\n"
					+ "_________________________________________________________");
		} else if (input == 123) { // Code voor als je het spel hebt gewonnen/woord hebt geraden
			System.out.println("_________________________________________________________\r\n"
					+ "     ___  _____  ____    ____   ____\r\n"
					+ "    / __)(  _  )(_  _)  (_  _) (_  _)\r\n"
					+ "   ( (_-. )(_)(   )(     _)(_    )(\r\n"
					+ "    \\___/(_____) (__)   (____)  (__)\r\n"
					+ "_________________________________________________________");
		} else if (input == 1000) {
			System.out.println("/\r\n"
					+ "                   __       //\r\n"
					+ "                   -\\= \\=\\ //\r\n"
					+ "                 --=_\\=---//=--\r\n"
					+ "               -_==/  \\/ //\\/--\r\n"
					+ "                ==/   /O   O\\==--\r\n"
					+ "   _ _ _ _     /_/    \\  ]  /--\r\n"
					+ "  /\\ ( (- \\    /       ] ] ]==-\r\n"
					+ " (\\ _\\_\\_\\-\\__/     \\  (,_,)--\r\n"
					+ "(\\_/                 \\     \\-\r\n"
					+ "\\/      /       (   ( \\  ] /)\r\n"
					+ "/      (         \\   \\_ \\./ )\r\n"
					+ "(       \\         \\      )  \\\r\n"
					+ "(       /\\_ _ _ _ /---/ /\\_  \\\r\n"
					+ " \\     / \\     / ____/ /   \\  \\\r\n"
					+ "  (   /   )   / /  /__ )   (  )\r\n"
					+ "  (  )   / __/ '---`       / /\r\n"
					+ "  \\  /   \\ \\             _/ /\r\n"
					+ "  ] ]     )_\\_         /__\\/\r\n"
					+ "  /_\\     ]___\\\r\n"
					+ " (___)");
		}
	}
}
