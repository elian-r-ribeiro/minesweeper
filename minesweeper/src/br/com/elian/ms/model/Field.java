package br.com.elian.ms.model;

import java.util.ArrayList;
import java.util.List;

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
	
	//boolean addNeighbor(Field neighbor) {
	//	
	//}
}
