package br.com.elian.ms.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import br.com.elian.ms.exception.ExplosionException;

public class Board {

	private int lines;
	private int columns;
	private int mines;
	private final List<Field> fields = new ArrayList<>();
	
	public Board(int lines, int columns, int mines) {
		this.lines = lines;
		this.columns = columns;
		this.mines = mines;
		
		generateFields();
		joinNeighbors();
		drawMines();
	}

	public void open(int line, int column) {
		try {
			fields.stream()
			.filter(f -> f.getLine() == line && f.getColumn() == column)
			.findFirst()
			.ifPresent(f -> f.open());;
		} catch (ExplosionException e) {
			fields.forEach(f -> f.setOpen(true));
			throw e;
		}	
	}
	
	public void toggleMarked(int line, int column) {
		fields.parallelStream()
		.filter(f -> f.getLine() == line && f.getColumn() == column)
		.findFirst()
		.ifPresent(f -> f.toggleMarked());;
	}
	
	private void generateFields() {
		for (int l = 0; l < lines; l++) {
			for (int c = 0; c < columns; c++) {
				fields.add(new Field(l, c));
			}
		}
	}
	
	private void joinNeighbors() {
		for(Field c1: fields) {
			for(Field c2: fields) {
				c1.addNeighbor(c2);
			}
		}
	}
	
	private void drawMines() {
		long underminedsAlready = 0;
		Predicate<Field> undermined = f -> f.isUndermined();
		
		do {
			int random = (int) (Math.random() * fields.size());
			
			fields.get(random).mine();
			
			underminedsAlready = fields.stream().filter(undermined).count();
		} while (underminedsAlready < mines);
	}
	
	public boolean goalReached() {
		return fields.stream().allMatch(f -> f.goalAchieved());
	}
	
	public void restartGame() {
		fields.stream().forEach(f -> f.restartGame());
		drawMines();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("  ");
		for (int c = 0; c < columns; c++) {
			sb.append(" ");
			sb.append(c);
			sb.append(" ");
		}
		
		sb.append("\n");
		
		int i = 0;
		
		for (int l = 0; l < lines; l++) {
			sb.append(l);
			sb.append(" ");
			for (int c = 0; c < columns; c++) {
				sb.append(" ");
				sb.append(fields.get(i));
				sb.append(" ");
				i++;
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
