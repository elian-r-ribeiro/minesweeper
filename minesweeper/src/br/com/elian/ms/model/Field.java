package br.com.elian.ms.model;

import java.util.ArrayList;
import java.util.List;

import br.com.elian.ms.exception.ExplosionException;

public class Field {

	private final int line;
	private final int column;
	private boolean open;
	private boolean undermined;
	private boolean marked;
	private List<Field> neighbors = new ArrayList<>();

	Field(int line, int column) {
		this.line = line;
		this.column = column;
	}

	boolean addNeighbor(Field neighbor) {
		boolean differentLine = this.line != neighbor.line;
		boolean differentColumn = this.column != neighbor.column;
		boolean diagonal = differentColumn && differentLine;
		int lineDelta = Math.abs(this.line - neighbor.line);
		int columnDelta = Math.abs(this.column - neighbor.column);
		int generalDelta = lineDelta + columnDelta;

		if (generalDelta == 1 && !diagonal) {
			neighbors.add(neighbor);
			return true;
		} else if (generalDelta == 2 && diagonal) {
			neighbors.add(neighbor);
			return true;
		} else {
			return false;
		}
	}

	void toggleMarked() {
		if (!open) {
			marked = !marked;
		}
	}
	
	void setOpen(boolean open) {
		this.open = open;
	}

	boolean open() {

		if (!open && !marked) {
			open = true;

			if (undermined) {
				throw new ExplosionException();
			}

			if (safeNeighbors()) {
				neighbors.forEach(n -> n.open());
			}

			return true;
		} else {
			return false;
		}
	}

	boolean safeNeighbors() {
		return neighbors.stream().noneMatch(n -> n.undermined);
	}
	
	void mine() {
		undermined = true;
	}
	
	public boolean isMarked() {
		return marked;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public boolean isUndermined() {
		return undermined;
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}
	
	boolean goalAchieved() {
		boolean unraveled = !undermined && open;
		boolean isProtected = undermined && marked;
		return unraveled || isProtected;
	}
	
	long minesOnNeighborhood() {
		return neighbors.stream().filter(v -> v.undermined).count();
	}
	
	void restartGame() {
		open = false;
		undermined = false;
		marked = false;
	}
	
	@Override
	public String toString() {
		if(marked) {
			return "x";
		} else if (open && undermined) {
			return "*";
		} else if (open && minesOnNeighborhood() > 0) {
			return Long.toString(minesOnNeighborhood());
		} else if (open) {
			return " "; 
		} else {
			return "?";
		}
	}
}
