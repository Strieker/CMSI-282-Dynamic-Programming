package lcs;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.HashSet;
import java.util.Arrays;

public class LCSTests {

// Bottom-up LCS Tests
// -----------------------------------------------
	@Test
	public void BULCSTest_t0() {
		assertEquals(new HashSet<>(Arrays.asList("")), LCS.bottomUpLCS("", ""));
		// LCS.memoCheck can either be the 1 element matrix
		// or null -- up to you, I won't check for cases with
		// empty-String arguments
	}

	@Test
	public void BULCSTest_t1() {
		// First assertion: correctness test for set with LCS
		assertEquals(new HashSet<>(Arrays.asList("")), LCS.bottomUpLCS("A", "B"));
		// Second assertion: proper format / solution of memo table
		assertArrayEquals(new int[][] { { 0, 0 }, { 0, 0 } }, LCS.memoCheck);
	}

	@Test
	public void BULCSTest_t2() {
		assertEquals(new HashSet<>(Arrays.asList("A")), LCS.bottomUpLCS("A", "A"));
		assertArrayEquals(new int[][] { { 0, 0 }, { 0, 1 } }, LCS.memoCheck);
	}

	@Test
	public void BULCSTest_t3() {
		assertEquals(new HashSet<>(Arrays.asList("ABC")), LCS.bottomUpLCS("ABC", "ABC"));
		assertArrayEquals(new int[][] { { 0, 0, 0, 0 }, { 0, 1, 1, 1 }, { 0, 1, 2, 2 }, { 0, 1, 2, 3 } },
				LCS.memoCheck);
	}

	@Test
	public void BULCSTest_t4() {
		assertEquals(new HashSet<>(Arrays.asList("AA", "BA")), LCS.bottomUpLCS("ABA", "BAA"));
		assertArrayEquals(new int[][] { { 0, 0, 0, 0 }, { 0, 0, 1, 1 }, { 0, 1, 1, 1 }, { 0, 1, 2, 2 } },
				LCS.memoCheck);
	}

	@Test
	public void BULCSTest_t5() {
		assertEquals(new HashSet<>(Arrays.asList("AT")), LCS.bottomUpLCS("CAT", "AT"));
		assertArrayEquals(new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 1, 1 }, { 0, 1, 2 } }, LCS.memoCheck);
	}

	@Test
	public void BULCSTest_t6() {
		assertEquals(new HashSet<>(Arrays.asList("")), LCS.bottomUpLCS("CAT", "DOG"));
		assertArrayEquals(new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } },
				LCS.memoCheck);
	}

	@Test
	public void BULCSTest_t7() {
		assertEquals(new HashSet<>(Arrays.asList("GCCTG", "GCGCG", "GCCAG")), LCS.bottomUpLCS("GCGCAATG", "GCCCTAGCG"));
		LCS.bottomUpLCS("GCGCAATG", "GCCCTAGCG");
		assertArrayEquals(new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 1, 2, 2, 2, 2, 2, 2, 2, 2 }, { 0, 1, 2, 2, 2, 2, 2, 3, 3, 3 }, { 0, 1, 2, 3, 3, 3, 3, 3, 4, 4 },
				{ 0, 1, 2, 3, 3, 3, 4, 4, 4, 4 }, { 0, 1, 2, 3, 3, 3, 4, 4, 4, 4 }, { 0, 1, 2, 3, 3, 4, 4, 4, 4, 4 },
				{ 0, 1, 2, 3, 3, 4, 4, 5, 5, 5 },

		}, LCS.memoCheck);
	}

	@Test
	public void BULCSTest_t8() {
		assertEquals(new HashSet<>(Arrays.asList("AT")), LCS.bottomUpLCS("CAT", "AT"));
		assertArrayEquals(new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 1, 1 }, { 0, 1, 2 }

		}, LCS.memoCheck);
	}

	@Test
	public void BULCSTest_t9() {
		assertEquals(new HashSet<>(Arrays.asList("ZZA")), LCS.bottomUpLCS("RAZZMATAZZ", "SCUZZBALLS"));
		assertArrayEquals(new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1 }, { 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2 }, { 0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2 },
				{ 0, 0, 0, 0, 1, 2, 2, 3, 3, 3, 3 }, { 0, 0, 0, 0, 1, 2, 2, 3, 3, 3, 3 },
				{ 0, 0, 0, 0, 1, 2, 2, 3, 3, 3, 3 }, { 0, 0, 0, 0, 1, 2, 2, 3, 3, 3, 3 },
				{ 0, 0, 0, 0, 1, 2, 2, 3, 3, 3, 3 } }, LCS.memoCheck);
	}

	public void BULCSTest_t11() {
		assertEquals(new HashSet<>(Arrays.asList("AD!")), LCS.bottomUpLCS("A.D!", "AD!+"));
		assertArrayEquals(new int[][] { { 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 1 }, { 0, 1, 1, 1, 1 }, { 0, 1, 2, 2, 2 },
				{ 0, 1, 2, 3, 3 } }, LCS.memoCheck);
	}

	public void BULCSTest_t12() {
		assertEquals(new HashSet<>(Arrays.asList("")), LCS.bottomUpLCS("CAT", "DOG"));
		assertArrayEquals(new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } },
				LCS.memoCheck);
	}

// Top-Down LCS Tests
// -----------------------------------------------
	@Test
	public void TDLCSTest_t0() {
		assertEquals(new HashSet<>(Arrays.asList("")), LCS.topDownLCS("", ""));
		// LCS.memoCheck can either be the 1 element matrix
		// or null -- up to you, I won't check for cases with
		// empty-String arguments
	}

	@Test
	public void TDLCSTest_t1() {
		assertEquals(new HashSet<>(Arrays.asList("")), LCS.topDownLCS("A", "B"));
		assertArrayEquals(new int[][] { { 0, 0 }, { 0, 0 } }, LCS.memoCheck);
	}

	@Test
	public void TDLCSTest_t2() {
		assertEquals(new HashSet<>(Arrays.asList("A")), LCS.topDownLCS("A", "A"));
		assertArrayEquals(new int[][] { { 0, 0 }, { 0, 1 } }, LCS.memoCheck);
	}

	@Test
	public void TDLCSTest_t3() {
		assertEquals(new HashSet<>(Arrays.asList("ABC")), LCS.topDownLCS("ABC", "ABC"));
		assertArrayEquals(new int[][] { { 0, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 2, 0 }, { 0, 0, 0, 3 } },
				LCS.memoCheck);
	}

	@Test
	public void TDLCSTest_t4() {
		assertEquals(new HashSet<>(Arrays.asList("AA", "BA")), LCS.topDownLCS("ABA", "BAA"));
		assertArrayEquals(new int[][] { { 0, 0, 0, 0 }, { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 2 } },
				LCS.memoCheck);
	}

	@Test
	public void TDLCSTest_t5() {
		assertEquals(new HashSet<>(Arrays.asList("AT")), LCS.topDownLCS("CAT", "AT"));
		assertArrayEquals(new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 2 } }, LCS.memoCheck);
	}

	@Test
	public void TDLCSTest_t6() {
		assertEquals(new HashSet<>(Arrays.asList("")), LCS.topDownLCS("CAT", "DOG"));
		assertArrayEquals(new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } },
				LCS.memoCheck);
	}

	@Test
	public void TDLCSTest_t7() {
		assertEquals(new HashSet<>(Arrays.asList("AD!")), LCS.topDownLCS("A.D!", "AD!+"));
		assertArrayEquals(new int[][] { { 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 1 }, { 0, 1, 1, 1, 1 }, { 0, 0, 2, 2, 2 },
				{ 0, 0, 0, 3, 3 } }, LCS.memoCheck);
	}

	@Test
	public void TDLCSTest_t8() {
		assertEquals(new HashSet<>(Arrays.asList("ZZA")), LCS.topDownLCS("RAZZMATAZZ", "SCUZZBALLS"));
		assertArrayEquals(new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1 }, { 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2 }, { 0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2 },
				{ 0, 0, 0, 0, 1, 2, 2, 3, 3, 3, 3 }, { 0, 0, 0, 0, 1, 2, 2, 3, 3, 3, 3 },
				{ 0, 0, 0, 0, 1, 2, 2, 3, 3, 3, 3 }, { 0, 0, 0, 0, 1, 2, 2, 3, 3, 3, 3 },
				{ 0, 0, 0, 0, 0, 2, 2, 3, 3, 3, 3 } }, LCS.memoCheck);
	}

	@Test
	public void TDLCSTest_t9() {
		assertEquals(new HashSet<>(Arrays.asList("GCCTG", "GCGCG", "GCCAG")), LCS.topDownLCS("GCGCAATG", "GCCCTAGCG"));
		LCS.topDownLCS("GCGCAATG", "GCCCTAGCG");
		assertArrayEquals(new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 1, 1, 1, 0, 0, 0 },
				{ 0, 0, 2, 2, 2, 2, 2, 0, 0, 0 }, { 0, 1, 2, 2, 2, 2, 2, 3, 0, 0 }, { 0, 1, 2, 3, 3, 3, 3, 3, 4, 0 },
				{ 0, 1, 2, 3, 3, 3, 4, 4, 4, 0 }, { 0, 1, 2, 3, 3, 0, 4, 4, 4, 0 }, { 0, 0, 0, 0, 0, 4, 4, 4, 4, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 5 }, }, LCS.memoCheck);
	}

}
