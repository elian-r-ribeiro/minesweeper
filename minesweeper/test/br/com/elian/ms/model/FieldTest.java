package br.com.elian.ms.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.elian.ms.exception.ExplosionException;

public class FieldTest {

	private Field field;
	
	@BeforeEach
	void startField() {
		field = new Field(3, 3);
	}
	
	@Test
	void testNeighborDistance1Left() {
		Field neighborLeft = new Field(3, 2);
		boolean resultNeighborLeft = field.addNeighbor(neighborLeft);
		
		assertTrue(resultNeighborLeft);
	}
	
	@Test
	void testNeighborDistance1Right() {
		Field neighborRight = new Field(3, 4);
		boolean resultNeighborRight = field.addNeighbor(neighborRight);
		
		assertTrue(resultNeighborRight);
	}
	
	@Test
	void testNeighborDistance1Above() {
		Field neighborAbove = new Field(4, 3);
		boolean resultNeighborAbove = field.addNeighbor(neighborAbove);

		assertTrue(resultNeighborAbove);
	}
	
	@Test
	void testNeighborDistance1Under() {
		Field neighborUnder = new Field(2, 3);
		boolean resultNeighborUnder = field.addNeighbor(neighborUnder);

		assertTrue(resultNeighborUnder);
	}
	
	@Test
	void testNeighborDistance1TopLeft() {
		Field neighborTopLeft = new Field(2, 2);
		boolean resultNeighborTopLeft = field.addNeighbor(neighborTopLeft);
		
		assertTrue(resultNeighborTopLeft);
	}
	
	@Test
	void testNeighborDistance1TopRight() {
		Field neighborTopRight = new Field(2, 4);
		boolean resultNeighborTopRight = field.addNeighbor(neighborTopRight);
		
		assertTrue(resultNeighborTopRight);
	}
	
	@Test
	void testNeighborDistance1BottomLeft() {
		Field neighborBottomLeft = new Field(4, 2);
		boolean resultNeighborBottomLeft = field.addNeighbor(neighborBottomLeft);
		
		assertTrue(resultNeighborBottomLeft);
	}
	
	@Test
	void testNeighborDistance1BottomRight() {
		Field neighborBottomRight = new Field(4, 4);
		boolean resultNeighborBottomRight = field.addNeighbor(neighborBottomRight);
		
		assertTrue(resultNeighborBottomRight);
	}
	
	@Test
	void testNeighotNotDistance1() {
		Field notNeighbor = new Field(5, 4);
		boolean resultNotNeighbor = field.addNeighbor(notNeighbor);

		assertFalse(resultNotNeighbor);
	}
	
	@Test
	void testDefaultMarkedValue() {
		assertFalse(field.isMarked());
	}
	
	@Test
	void testToggleMarked() {
		field.toggleMarked();
		
		assertTrue(field.isMarked());
	}
	
	@Test
	void testToggleMarkedTwoTimes() {
		field.toggleMarked();
		field.toggleMarked();
		
		assertFalse(field.isMarked());
	}
	
	@Test
	void openFieldNotUnderminedAndNotMarked() {
		assertTrue(field.open());;
	}
	
	@Test
	void openFieldNotUnderminedButMarked() {
		field.toggleMarked();
		
		assertFalse(field.open());
	}
	
	@Test
	void openFieldUnderminedAndMarked() {
		field.toggleMarked();
		field.mine();
		
		assertFalse(field.open());
	}
	
	@Test
	void openFieldUnderminedButNotMarked() {
		field.mine();
		
		assertThrows(ExplosionException.class, () -> {
			field.open();
		});
	}
	
	@Test
	void openFieldWithNeighbors() {
		Field field11 = new Field(1, 1);
		Field field12 = new Field(1, 2);
		Field field22 = new Field(2, 2);
		
		field12.mine();
		
		field22.addNeighbor(field11);
		field22.addNeighbor(field12);
		field.addNeighbor(field22);
		
		field.open();
		
		assertTrue(field22.isOpen() && !field11.isOpen());
	}
}
