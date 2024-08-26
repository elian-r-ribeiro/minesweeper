package br.com.elian.ms.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.elian.ms.exception.ExplosionException;

public class FieldTest {

	private Field field11;
	private Field field12;
	private Field field22;
	private Field field33;
	
	@BeforeEach
	void startFields() {
		field33 = new Field(3, 3);
		field11 = new Field(1, 1);
		field12 = new Field(1, 2);
		field22 = new Field(2, 2);
	}
	
	@Test
	void testNeighborDistance1Left() {
		Field neighborLeft = new Field(3, 2);
		boolean resultNeighborLeft = field33.addNeighbor(neighborLeft);
		
		assertTrue(resultNeighborLeft);
	}
	
	@Test
	void testNeighborDistance1Right() {
		Field neighborRight = new Field(3, 4);
		boolean resultNeighborRight = field33.addNeighbor(neighborRight);
		
		assertTrue(resultNeighborRight);
	}
	
	@Test
	void testNeighborDistance1Above() {
		Field neighborAbove = new Field(4, 3);
		boolean resultNeighborAbove = field33.addNeighbor(neighborAbove);

		assertTrue(resultNeighborAbove);
	}
	
	@Test
	void testNeighborDistance1Under() {
		Field neighborUnder = new Field(2, 3);
		boolean resultNeighborUnder = field33.addNeighbor(neighborUnder);

		assertTrue(resultNeighborUnder);
	}
	
	@Test
	void testNeighborDistance1TopLeft() {
		Field neighborTopLeft = new Field(2, 2);
		boolean resultNeighborTopLeft = field33.addNeighbor(neighborTopLeft);
		
		assertTrue(resultNeighborTopLeft);
	}
	
	@Test
	void testNeighborDistance1TopRight() {
		Field neighborTopRight = new Field(2, 4);
		boolean resultNeighborTopRight = field33.addNeighbor(neighborTopRight);
		
		assertTrue(resultNeighborTopRight);
	}
	
	@Test
	void testNeighborDistance1BottomLeft() {
		Field neighborBottomLeft = new Field(4, 2);
		boolean resultNeighborBottomLeft = field33.addNeighbor(neighborBottomLeft);
		
		assertTrue(resultNeighborBottomLeft);
	}
	
	@Test
	void testNeighborDistance1BottomRight() {
		Field neighborBottomRight = new Field(4, 4);
		boolean resultNeighborBottomRight = field33.addNeighbor(neighborBottomRight);
		
		assertTrue(resultNeighborBottomRight);
	}
	
	@Test
	void testNeighotNotDistance1() {
		Field notNeighbor = new Field(5, 4);
		boolean resultNotNeighbor = field33.addNeighbor(notNeighbor);

		assertFalse(resultNotNeighbor);
	}
	
	@Test
	void testDefaultMarkedValue() {
		assertFalse(field33.isMarked());
	}
	
	@Test
	void testToggleMarked() {
		field33.toggleMarked();
		
		assertTrue(field33.isMarked());
	}
	
	@Test
	void testToggleMarkedTwoTimes() {
		field33.toggleMarked();
		field33.toggleMarked();
		
		assertFalse(field33.isMarked());
	}
	
	@Test
	void openFieldNotUnderminedAndNotMarked() {
		assertTrue(field33.open());;
	}
	
	@Test
	void openFieldNotUnderminedButMarked() {
		field33.toggleMarked();
		
		assertFalse(field33.open());
	}
	
	@Test
	void openFieldUnderminedAndMarked() {
		field33.toggleMarked();
		field33.mine();
		
		assertFalse(field33.open());
	}
	
	@Test
	void openFieldUnderminedButNotMarked() {
		field33.mine();
		
		assertThrows(ExplosionException.class, () -> {
			field33.open();
		});
	}
	
	@Test
	void openFieldWithNeighbors() {		
		field12.mine();
		
		field22.addNeighbor(field11);
		field22.addNeighbor(field12);
		field33.addNeighbor(field22);
		
		field33.open();
		
		assertTrue(field22.isOpen() && !field11.isOpen());
	}
	
	@Test
	void isGoalAchieved1() {
		field33.open();
		
		assertTrue(field33.goalAchieved());
	}
	
	@Test
	void isGoalAchieved2() {
		field33.mine();
		field33.toggleMarked();
		
		assertTrue(field33.goalAchieved());
	}
	
	@Test
	void isGoalAchieved3() {
		field33.open();
		field33.mine();
		
		assertFalse(field33.goalAchieved());
	}
	
	@Test
	void isGameRestarted() {
		field33.toggleMarked();
		
		field33.restartGame();
		
		assertFalse(field33.isMarked());
	}
	
	@Test
	void testToString() {
		field33.open();
		
		assertEquals(field33.toString(), " ");
	}
}
