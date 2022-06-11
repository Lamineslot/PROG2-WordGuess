package wordguess;

import java.util.Iterator;
import java.util.Random;
import java.util.ArrayList;

public class GameHuman {

	WordGuessIO userInput = new WordGuessIO();
	Random random = new Random();
	Words word = new Words();
	private int amountOfMistakes; 
	private static int amountOfTries;
	private boolean guessed; 
	private String playerName;
	private char[] listOfCharactersOutput; // de puntjes of de letters die telkens geprint worden
	private char[] wordArray; // array van het woord dat de computer random heeft gekozen
	private char[] currentGuessArray; // array van de huidige poging om het woord te raden
	private ArrayList<Integer> indexOfLetters;
	private ArrayList<Character> wrongGuesses;

	public GameHuman() {
		indexOfLetters = new ArrayList<Integer>();
		wrongGuesses = new ArrayList<Character>();
		currentGuessArray = new char[12];
		listOfCharactersOutput = new char[12];
		for (int i = 0; i < listOfCharactersOutput.length; i++) {
			listOfCharactersOutput[i] = '.';
		}
	}
	
	public static int getHumanScore() {
		return amountOfTries;
	}

	public void play() {
		amountOfTries = 0;
		playerName = WordGuessIO.getPlayerName();
		System.out.println("---------------------------------------------------------");
		System.out.println("| " + playerName.toUpperCase() + " jij gaat nu raden.                             |");
		System.out.println("| Probeer het 12-letterwoord zo snel mogelijk te raden. |");
		System.out.println("| Geef per keer een letter in, of het hele woord.       |");
		System.out.println("| Bij 10 keer een foute letter ben je af.               |");
		System.out.println("| Als je het woord fout raadt, krijg je 5 strafpunten.  |");
		System.out.println("| Succes!                                               |");
		System.out.println("---------------------------------------------------------");
		System.out.println("[ENTER] om door te gaan.");
		userInput.setEnterToContinue();

		String randomWord = word.getRandomWord();
		wordArray = randomWord.toCharArray();

		if (word.checkCheatmode()) {
			System.out.println("Cheat mode staat aan! Het woord is: " + randomWord);
		}

		while (!guessed && amountOfMistakes < 10) {
			new WordguessGame().printArt(amountOfMistakes);
			if (amountOfMistakes == 0) {
				System.out.println(amountOfMistakes + " fouten, aantal invoer: " + amountOfTries);
			} else {
				System.out.print(amountOfMistakes + " fout(en) (");
				for (char guess : wrongGuesses) {
					System.out.print(guess);
				}
				System.out.println("), aantal invoer: " + amountOfTries);
			}
			// Print de puntjes of letters die al geraden zijn
			for (char c : listOfCharactersOutput) {
				System.out.print(c + " ");
			}

			// Input
			System.out.println();
			System.out.print(
					"Raad een letter of het woord (let op dat je geen spaties invoert, maar alleen een letter of woord) :");
			userInput.setGameInput();
			String currentGuess = userInput.getGameInput();

			// Debugging doeleinden, stopt het spel onmiddelijk door uit de loop te breken
			// en returnt de score.
			if (currentGuess.equals("PINKFLUFFYUNICORNS")) {
				System.out.println("Wow, er verschijnen spontaan roze, pluizige eenhoorns die aan het dansen zijn op regenbogen!");
				System.out.println("Druk op [ENTER] om ze te zien!");
				userInput.setEnterToContinue();
				new WordguessGame().printArt(1000);
				new WordguessGame().printArt(1000);
				System.out.println("Oke, nu even serieus, je hebt besloten om deze beurt te stoppen!");
				break;
			}

			currentGuessArray = currentGuess.toCharArray();

			amountOfTries++;
			System.out.println();

			// letter raden

			// Onderstaande gedeelte bepaalt index van ingevoerde letter in het gekozen
			// 12-letterwoord en slaat deze op
			boolean charWasFound = false;
			if (currentGuess.length() == 1) {
				char currentGuessChar = currentGuess.charAt(0);
				for (int i = 0; i < wordArray.length; i++) {
					if (wordArray[i] == currentGuessChar) {
						indexOfLetters.add(i);
						charWasFound = true;
					}
				}
				if (!charWasFound) {
					wrongGuesses.add(currentGuessChar);
					amountOfMistakes++;
					continue;
				}

				// Onderstaande gedeelte zet de puntjes om in de juiste gekozen letter, als deze
				// correct is
				for (int i = 0; i < listOfCharactersOutput.length; i++) {
					for (Integer index : indexOfLetters) {
						if (i == index) {
							listOfCharactersOutput[i] = wordArray[index];
						}
					}
				}

				int amountOfGapsLeft = 0;
				// Checkt of er nog letters over zijn die nog niet geraden zijn
				for (int i = 0; i < listOfCharactersOutput.length; i++) {
					if (listOfCharactersOutput[i] == '.') {
						amountOfGapsLeft++;
					}
				}

				if (amountOfGapsLeft == 0) {
					guessed = true;
				}

				// woord raden
			} else if (currentGuess.length() == 12) {
				if (currentGuess.equals(randomWord)) {
					for (int i = 0; i < listOfCharactersOutput.length; i++) {
						listOfCharactersOutput[i] = currentGuessArray[i];
					}
					guessed = true;
				} else {
					System.out.println("Het woord is fout, je krijgt 5 strafpunten.");
					amountOfTries += 4;
				}

			} else {
				System.out.println(
						"Je hebt een onjuiste waarde ingevoerd. Zorg dat je invoer 1 karakter OF 12 karakters lang is. "
								+ "We beginnen opnieuw.");
				amountOfTries--;
				continue;
			}

		}
		// zorgt ervoor dat alle relevante informatie nog een keer wordt geprint voordat
		// hij de score weergeeft als het woord al geraden is
		if (guessed || amountOfMistakes >= 10) {
			if (guessed) {
				new WordguessGame().printArt(123);
			} else {
				new WordguessGame().printArt(amountOfMistakes);
			}
			if (amountOfMistakes == 0) {
				System.out.println(amountOfMistakes + " fouten, aantal invoer: " + amountOfTries);
			} else {
				if (amountOfMistakes == 10) {
					System.out.println("Je hebt het niet geraden. Het woord was:" + randomWord);
				}
				System.out.print(amountOfMistakes + " fouten (");
				for (char guess : wrongGuesses) {
					System.out.print(guess);
				}
				System.out.println("), aantal invoer: " + amountOfTries);
			}
			for (char c : listOfCharactersOutput) {
				System.out.print(c + " ");
			}
			System.out.println();
		}

		System.out.println(playerName.toUpperCase() + " jouw score is: " + amountOfTries);

	}
}
