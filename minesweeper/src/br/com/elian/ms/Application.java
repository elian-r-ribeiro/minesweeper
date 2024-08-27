package br.com.elian.ms;

import br.com.elian.ms.model.Board;
import br.com.elian.ms.view.ConsoleBoard;

public class Application {

	public static void main(String[] args) {
		Board board = new Board(6, 6, 6);
		
		new ConsoleBoard(board);
	}
}
