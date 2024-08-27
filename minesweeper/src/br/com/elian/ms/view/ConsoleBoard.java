package br.com.elian.ms.view;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.elian.ms.exception.ExitException;
import br.com.elian.ms.exception.ExplosionException;
import br.com.elian.ms.model.Board;

public class ConsoleBoard {

	private Board board;
	private Scanner input = new Scanner(System.in);
	
	public ConsoleBoard(Board board) {
		this.board = board;
		
		executeGame();
	}

	private void executeGame() {
		try {
			boolean continueGame = true;
			gameLoop();
			
			while(continueGame) {
				System.out.println("Outra partida? (S/n) ");
				String answer = input.nextLine();
				
				if("n".equalsIgnoreCase(answer)) {
					
				}
			}
		} catch (ExitException e) {
			System.out.println("Até a próxima!");
		} finally {
			input.close();
		}
	}

	private void gameLoop() {
		try {
			
			while(!board.goalReached()) {
				System.out.println(board);
				
				String typed = getTypedValue("Digite (y, x): ");
				Iterator<Integer> xy = Arrays.stream(typed.split(","))
						.map(e -> Integer.parseInt(e.trim()))
						.iterator();

				typed = getTypedValue("1-Abrir ou 2-(Des)Marcar");
				
				if("1".equalsIgnoreCase(typed)) {
					board.open(xy.next(), xy.next());
				} else if ("2".equalsIgnoreCase(typed)) {
					board.toggleMarked(xy.next(), xy.next());
				}
			}
			
			System.out.println("Você ganhou!");
		} catch (ExplosionException e) {
			System.out.println(board);
			System.out.println("Game over!");
		}
	}
	
	private String getTypedValue(String text) {
		System.out.print(text);
		String typed = input.nextLine();
		
		if("sair".equalsIgnoreCase(typed)) {
			throw new ExitException();
		}
		
		return typed;
	}
}
