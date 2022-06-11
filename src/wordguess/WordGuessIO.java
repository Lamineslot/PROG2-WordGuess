package wordguess;

import java.util.Scanner;

public class WordGuessIO {
	
	private static String playerName;
	private String headOrTail;
	private String humanInput;
	
		 Scanner input = new Scanner(System.in);

	 public void setPlayerName() {
		 String playerNameWithoutFormatting = input.nextLine();
		 playerName = playerNameWithoutFormatting.substring(0, 1).toUpperCase() + playerNameWithoutFormatting.substring(1).toLowerCase();
	 }

	public static String getPlayerName() {
		return playerName;
	}
	
	public void setHeadOrTail() {
		String headOrTailWithoutFormatting = input.nextLine();
		headOrTail = headOrTailWithoutFormatting.toLowerCase();
	}
	
	public String getHeadOrTail() {
		return headOrTail;
	}
	
	public void setEnterToContinue() {
		input.nextLine();
	}
	
	public void setGameInput() {
		String humanInputWithoutFormatting = input.nextLine();
		humanInput = humanInputWithoutFormatting.toUpperCase();
	}
	
	public String getGameInput() {
		return humanInput;
	}
	
}
