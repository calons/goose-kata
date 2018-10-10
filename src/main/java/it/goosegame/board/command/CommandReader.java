package it.goosegame.board.command;

import java.util.Scanner;

public class CommandReader {

	
	public static String[] readCommand(Scanner scan) {
		System.out.print("Write command:");
		String text= scan.nextLine();

		String[] command=text.split(" ");
		return command;
	}
}
