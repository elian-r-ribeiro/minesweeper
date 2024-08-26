package br.com.elian.ms;

import br.com.elian.ms.model.Board;

public class Application {

	public static void main(String[] args) {
		Board board = new Board(6, 6, 6);
		
		board.open(3, 3);
		board.toggleMarked(4, 4);
		board.toggleMarked(4, 5);
		
		System.out.println(board);
	}
}
