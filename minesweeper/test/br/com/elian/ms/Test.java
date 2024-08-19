package br.com.elian.ms;

import static org.junit.jupiter.api.Assertions.*;

class Test {

	@org.junit.jupiter.api.Test
	void testIfEqualsTwo() {
		int a = 1 + 1;
		
		assertEquals(2, a);
	}

	@org.junit.jupiter.api.Test
	void testIfEqualsThree() {
		int x = 2 + 10 - 9;
		
		assertEquals(3, x);
	}
}
