package wordguess;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GameComputer {
	WordGuessIO userInput = new WordGuessIO();
	Words word = new Words();
	Random r = new Random();
	private int amountOfMistakes;
	private static int amountOfTries;
	private boolean guessed;
	private String playerName;
	private char[] listOfCharactersOutput;
	private char[] wordArray;
	private char[] currentGuessArray;
	private ArrayList<Integer> indexOfLetters;
	private ArrayList<Character> wrongGuesses;
	private ArrayList<String> possibleWords;

	public GameComputer() {
		indexOfLetters = new ArrayList<Integer>();
		wrongGuesses = new ArrayList<Character>();
		possibleWords = new ArrayList<String>();
		currentGuessArray = new char[12];
		listOfCharactersOutput = new char[12];
		for (int i = 0; i < listOfCharactersOutput.length; i++) {
			listOfCharactersOutput[i] = '.';
		}
	}
	
	public static int getComputerScore() {
		return amountOfTries;
	}

	public void play() {
		amountOfTries = 0;
		playerName = WordGuessIO.getPlayerName();
		System.out.println("---------------------------------------------------------");
		System.out.println("| De COMPUTER gaat nu raden.                            |");
		System.out.println("| Neem een 12-letterwoord uit de lijst in gedachten.    |");
		System.out.println("| De computer gaat letters raden (of het hele woord).   |");
		System.out.println("| "+ playerName.toUpperCase() + ", jij antwoord met posities,                       |");
		System.out.println("| gescheiden door spaties (vb: 3 9 10)                  |");
		System.out.println("| of '-' als de letter niet voorkomt.                   |");
		System.out.println("---------------------------------------------------------");
		System.out.print("[ENTER] om door te gaan.");
		userInput.setEnterToContinue();

		possibleWords = word.getAllWords();

		while (!guessed && amountOfMistakes < 10) {

			System.out.print("De computer raadt: ");

			// Kiezen van een willekeurige letter
			int indexCurrentWord = r.nextInt(possibleWords.size());
			String currentWord = possibleWords.get(indexCurrentWord);
			currentGuessArray = currentWord.toCharArray();
			char randomLetter = 0;
			boolean letterCanBeChosen = false;

			// Voorwaarden om te checken of de letter voldoet aan de vereisten
			while (!letterCanBeChosen) {
				randomLetter = (char) (r.nextInt(26) + 'a');
				randomLetter = Character.toUpperCase(randomLetter);
				letterCanBeChosen = false;
				for (int i = 0; i < currentGuessArray.length; i++) {
					if (currentGuessArray[i] == randomLetter) {
						letterCanBeChosen = true;
					} else {
						continue;
					}
				}
				for (int i = 0; i < listOfCharactersOutput.length; i++) {
					if (listOfCharactersOutput[i] != '.') {
						if (listOfCharactersOutput[i] == randomLetter) {
							letterCanBeChosen = false;
						}
					}
				}
				for (char wrongGuess : wrongGuesses) {
					if (randomLetter == wrongGuess) {
						letterCanBeChosen = false;
					}
				}

				if (letterCanBeChosen) {
					break;
				}
			}
			System.out.println(randomLetter);
			System.out.print("Geef de (reeks) plaats(en) of '-' : ");
			userInput.setGameInput();
			String currentGuess = userInput.getGameInput();
			// Debugging doeleinden, stopt het spel onmiddelijk door uit de loop te breken
			// en returnt de score.
			if (currentGuess.equals("PINKFLUFFYUNICORNS")) {
				System.out.println(
						"Wow, er verschijnen spontaan roze, pluizige eenhoorns die aan het dansen zijn op regenbogen!");
				System.out.println("Druk op [ENTER] om ze te zien!");
				userInput.setEnterToContinue();
				new WordguessGame().printArt(1000);
				new WordguessGame().printArt(1000);
				System.out.println("Oke, nu even serieus, je hebt besloten om deze beurt te stoppen!");
				break;
			}
			
			String[] numbers = null;
			amountOfTries++;
			System.out.println();
			if (!currentGuess.equals("-")) {
				final char chosenLetter = randomLetter;
				if ((currentGuess.length() == 1 || currentGuess.length() == 2)) {
					int currentGuessAsNumber = Integer.parseInt(currentGuess);
					listOfCharactersOutput[currentGuessAsNumber - 1] = randomLetter;
					// Stap 1 van het verwijderen van woorden
					possibleWords.removeIf(n -> n.charAt(currentGuessAsNumber-1) != chosenLetter);
				} else {
					numbers = currentGuess.split(" ");
					for (int i = 0; i < numbers.length; i++) {
						listOfCharactersOutput[Integer.parseInt(numbers[i]) - 1] = randomLetter;
						final int index = Integer.parseInt(numbers[i]);
						possibleWords.removeIf(n -> n.charAt(index-1) != chosenLetter);
					}
				}
			} else {
				amountOfMistakes++;
				wrongGuesses.add(randomLetter);
				String randomLetterAsString = String.valueOf(randomLetter);
				possibleWords.removeIf(l -> l.contains(randomLetterAsString));
			}
			
			// Print fouten e.d.
			new WordguessGame().printArt(amountOfMistakes);
			if (amountOfMistakes == 0) {
				System.out.println(amountOfMistakes + " fouten, aantal invoer: " + amountOfTries);
			} else {
				System.out.print(amountOfMistakes + " fouten (");
				for (char guess : wrongGuesses) {
					System.out.print(guess);
				}
				System.out.println("), aantal invoer: " + amountOfTries);
			}
			if (word.checkCheatmode()) {
				for (String possibleWord : possibleWords) {
					System.out.println(possibleWord);
				}
				System.out.println(possibleWords.size());
			}

			if (possibleWords.size() == 1) {
				char[] guessedWordArray = possibleWords.get(0).toCharArray();
				for (int i = 0; i < listOfCharactersOutput.length; i++) {
					listOfCharactersOutput[i] = guessedWordArray[i];
				}
			}

			// Print de puntjes of letters die al geraden zijn
			for (char c : listOfCharactersOutput) {
				System.out.print(c + " ");
			}
			System.out.println();
			System.out.println();

			int amountOfGapsLeft = 0;
			// Checkt of er nog letters over zijn die nog niet geraden zijn
			for (int i = 0; i < listOfCharactersOutput.length; i++) {
				if (listOfCharactersOutput[i] == '.') {
					amountOfGapsLeft++;
				}
			}

			if (amountOfGapsLeft == 0 || possibleWords.size() == 1) {
				guessed = true;
			}

			if (amountOfMistakes == 10) {
				System.out.println("De computer heeft verloren. Hij heeft het woord niet geraden.");
			}

		}
		System.out.println("De score van de computer is: " + amountOfTries);
		System.out.println();
		System.out.println();
	}
}
