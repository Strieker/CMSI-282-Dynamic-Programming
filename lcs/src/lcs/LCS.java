package lcs;

import java.util.*;

public class LCS {
    
/**
 * memoCheck is used to verify the state of your tabulation after
 * performing bottom-up and top-down DP. Make sure to set it after
 * calling either one of topDownLCS or bottomUpLCS to pass the tests!
 */
public static int[][] memoCheck;

// -----------------------------------------------
// Shared Helper Methods
// -----------------------------------------------


/**
 * Once a character is found that both strings in question have in common
 * it is added to all current LCS solutions that are currently in the set
 * by means of this method. 
 * @param newChar The char that are common to both strings
 * @param LCSs The Set of substring LCS solutions found in the table 
 * @return The updated set of Strings after adding the new char to each solution in the set
 */
private static Set<String> updateLCSSolutionWithNewChar(char newChar, Set<String> LCSs) {
	Set<String> LCSsCopy = new HashSet<String>();
	for(String LCS: LCSs) {
		LCSsCopy.add( LCS + Character.toString(newChar));
	}
	return LCSsCopy;
}


/**
 * Dynamic programming approach that collects the LCS problem solution
 * for both bottom-up and top-down.
 * @param rStr The String found along the table's rows
 * @param cStr The String found along the table's cols
 * @param row The int that represents the index row 
 * @param column The int that represents the index of the column 
 * @return The longest common subsequence between rStr and cStr +
 *         [Side Effect] sets memoCheck to refer to table
 */
private static Set<String> collectSolution(String rStr, String cStr, int row, int column, int[][] memoizedTable) {
	if(row == 0 || column == 0) {
    	Set<String> setContainingEmptyString = new HashSet<String>();
    	setContainingEmptyString.add("");
    	return setContainingEmptyString;
	}
	if(rStr.charAt(row) == cStr.charAt(column)) {
		return updateLCSSolutionWithNewChar(rStr.charAt(row), collectSolution(rStr, cStr, row - 1, column - 1, memoizedTable));
	}
	Set<String> LCSs = new HashSet<String>();
	if(memoizedTable[row][column -1] >= memoizedTable[row - 1][column]) {
		LCSs.addAll(collectSolution(rStr, cStr, row, column - 1, memoizedTable));
	}
	if (memoizedTable[row][column -1] <= memoizedTable[row - 1][column]) {
		LCSs.addAll(collectSolution(rStr, cStr, row - 1, column, memoizedTable));
	}
	return LCSs;
}

// -----------------------------------------------
// Bottom-Up LCS
// -----------------------------------------------

/**
 * Bottom-up dynamic programming approach to the LCS problem, which
 * solves larger and larger subproblems iterative using a tabular
 * memoization structure.
 * @param rStr The String found along the table's rows
 * @param cStr The String found along the table's cols
 * @return The longest common subsequence between rStr and cStr +
 *         [Side Effect] sets memoCheck to refer to table
 */

public static Set<String> bottomUpLCS (String rStr, String cStr) {
	int[][] bottomUpTable = new int[rStr.length()][cStr.length()];
	rStr = "0" + rStr;
	cStr = "0" + cStr;
	memoCheck = bottomUpTableFill(rStr, cStr, bottomUpTable);
	return collectSolution(rStr, cStr, rStr.length() - 1, cStr.length() - 1, memoCheck);
}


/**
 * Creates table iteratively to achieve bottom-up dynamic programming approach 
 * to the LCS problem.
 * @param rStr The String found along the table's rows
 * @param cStr The String found along the table's cols
 * @param bottomUpTable The table itself
 * @return The longest common subsequence between rStr and cStr +
 *         [Side Effect] sets memoCheck to refer to table
 */
private static int[][] bottomUpTableFill(String rStr, String cStr, int[][] bottomUpTable) {
	bottomUpTable = new int[rStr.length()][cStr.length()];
	for (int row = 0; row < rStr.length(); row++) {
		for (int column = 0; column < cStr.length(); column++) {
			if(column == 0 && row == 0) {
				bottomUpTable[row][column] = 0;
			} else {
				if(rStr.charAt(row) == cStr.charAt(column)) {
					bottomUpTable[row][column] = bottomUpTable[row - 1][column - 1] + 1;
				} else if (rStr.charAt(row) != cStr.charAt(column)){
					if (column == 0) {
						bottomUpTable[row][column] = bottomUpTable[row - 1][column];
					} else if (row == 0) {
						bottomUpTable[row][column] = bottomUpTable[row][column - 1];
					} else {
						bottomUpTable[row][column] = Math.max(bottomUpTable[row - 1][column], bottomUpTable[row][column - 1]);
					}
				}
			}
		}
	}
	return bottomUpTable;
}


// -----------------------------------------------
// Top-Down LCS
// -----------------------------------------------

/**
 * Top-down dynamic programming approach to the LCS problem, which
 * solves smaller and smaller subproblems recursively using a tabular
 * memoization structure.
 * @param rStr The String found along the table's rows
 * @param cStr The String found along the table's cols
 * @return The longest common subsequence between rStr and cStr +
 *         [Side Effect] sets memoCheck to refer to table  
 */
public static Set<String> topDownLCS (String rStr, String cStr) {
	boolean[][] haveVisited = new boolean[rStr.length() + 1][cStr.length() + 1];
	memoCheck = new int[rStr.length() + 1][cStr.length() + 1];
	rStr = "0" + rStr;
	cStr = "0" + cStr;
	topDownTableFill(rStr, cStr, rStr.length() - 1, cStr.length() - 1, haveVisited, memoCheck);
	for(int i = 0; i < rStr.length(); i++) {
		for(int j = 0; j < cStr.length(); j++) {
			if (j != cStr.length() - 1) {
        		System.out.print(Integer.toString(memoCheck[i][j]) + " ");
			} else {
        		System.out.println(memoCheck[i][j]);
			}
    	}
	}
	return collectSolution(rStr, cStr, rStr.length() - 1, cStr.length() - 1, memoCheck);
}


/**
 * Creates table iteratively to achieve bottom-up dynamic programming approach 
 * to the LCS problem.
 * @param rStr The String found along the table's rows
 * @param cStr The String found along the table's cols
 * @return The longest common subsequence between rStr and cStr +
 *         [Side Effect] sets memoCheck to refer to table  
 */
private static int topDownTableFill(String rStr, String cStr, int row, int column, boolean[][] haveVisited, int[][] topDownTable) {
	if(column == 0 || row == 0) {
		topDownTable[row][column] = 0;
		return 0;
	}
	if(haveVisited[row][column] != true) {
		if(rStr.charAt(row) == cStr.charAt(column)) {
			topDownTable[row][column] = topDownTableFill(rStr, cStr, row - 1, column - 1, haveVisited, topDownTable) + 1;
		} else {
			topDownTable[row][column] = Math.max(topDownTableFill(rStr, cStr, row - 1, column, haveVisited, topDownTable), 
					topDownTableFill(rStr, cStr, row, column - 1, haveVisited, topDownTable));
		}
		haveVisited[row][column] = true;
	}
	return topDownTable[row][column];
}




// [!] TODO: Add any top-down specific helpers here!
public static void main(String args[]) {
	// DO WE CARE CAPITALIZATION
	// THIS DOESN'T WORK FROM THE JUMP BECAUSE INITIAL IS 3,3
//    	bottomUpLCS("AXBCZ","XABZC");
	// WORKS PARTIALLY FOR THIS ONE IT GETS TO A BUT FAILS WHEN 
//	// IT'S B BECAUSE 0 TO THE TOP AND 0 TO THE RIGHT 
//	Set<String> solutionBottomUp = bottomUpLCS("GCGCAATG", "GCCCTAGCG");
//    	for(String LCS : solutionBottomUp) {
//    		System.out.println(LCS);
//    	}
////    memoCheck = new int[6][6];
	Set<String> solutionTopDown = topDownLCS("RAZZMATAZZ","SCUZZBALLS");
	for(String LCS : solutionTopDown) {
		System.out.println(LCS);
	}
	}
}
