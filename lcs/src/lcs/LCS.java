package lcs;

import java.util.*;

public class LCS {
    
	
	//	WILL THE STRINGS ALWAYS BE THE SAME LENGTH? 
    /**
     * memoCheck is used to verify the state of your tabulation after
     * performing bottom-up and top-down DP. Make sure to set it after
     * calling either one of topDownLCS or bottomUpLCS to pass the tests!
     */
    public static int[][] memoCheck;
    
    // -----------------------------------------------
    // Shared Helper Methods
    // -----------------------------------------------
    
    // [!] TODO: Add your shared helper methods here!
    

    // -----------------------------------------------
    // Bottom-Up LCS
    // -----------------------------------------------
    
    private static void makeBottomUpTable(String rStr, String cStr) {
    	rStr = "0" + rStr;
    	cStr = "0" + cStr;
    	int[][] bottomUp = new int[rStr.length()][cStr.length()];
    	for (int row = 0; row < rStr.length(); row++) {
    		for (int column = 0; column < cStr.length(); column++) {
    			if(column == 0 && row == 0) {
    				bottomUp[row][column] = 0;
    			} else {
    				if(rStr.charAt(row) == cStr.charAt(column)) {
    					bottomUp[row][column] = bottomUp[row - 1][column] + 1;
    				} else if (rStr.charAt(row) != cStr.charAt(column)){
    					if (column == 0) {
    						bottomUp[row][column] = bottomUp[row - 1][column];
    					} else if (row == 0) {
    						bottomUp[row][column] = bottomUp[row][column - 1];
    					} else {
        			    	bottomUp[row][column] = Math.max(bottomUp[row - 1][column], bottomUp[row][column - 1]);
    					}
    				}
    			}
    		}
    	}
    	memoCheck = bottomUp;
    	for(int i = 0; i < rStr.length(); i++) {
    		for(int j = 0; j < cStr.length(); j++) {
    			if (j != cStr.length() - 1) {
            		System.out.print(Integer.toString(memoCheck[i][j]) + " ");
    			} else {
            		System.out.println(memoCheck[i][j]);
    			}
        	}
    	}
    }
    
    // HELPER FUNCTION THAT HANDLES ADDING THE LAST CHAR
    private static Set<String> newSetWithAddedCharToEachLCSString(char currentChar, Set<String> LCSs) {
    	Set<String> LCSsCopy = new HashSet<String>();
    	for(String LCS: LCSs) {
    		LCSsCopy.add( LCS + Character.toString(currentChar));
//    		System.out.println(LCS);
    	}
    	return LCSsCopy;
    }
    
    /**
     * Bottom-up dynamic programming approach to the LCS problem, which
     * solves larger and larger subproblems iterative using a tabular
     * memoization structure.
     * @param rStr The String found along the table's rows
     * @param cStr The String found along the table's cols
     * @return The longest common subsequence between rStr and cStr +
     *         [Side Effect] sets memoCheck to refer to table
     */
    
    private static Set<String> collectSolution(String rStr, String cStr, int r, int c, int[][] memo) {
//    	System.out.println(Integer.toString(r) + "," + Integer.toString(c));
    	if(r == 0 || c == 0) {
//    		System.out.println("I GOT THE EMPTY SET");
        	Set<String> setContainingEmptyString = new HashSet<String>();
        	setContainingEmptyString.add("");
        	return setContainingEmptyString;
    	}
    	if(rStr.charAt(r) == cStr.charAt(c)) {
//    		System.out.println("CHARS MATCH");
    		return newSetWithAddedCharToEachLCSString(rStr.charAt(r), collectSolution(rStr, cStr, r - 1, c - 1, memo));
    	}
    	Set<String> LCSs = new HashSet<String>();
		if(memoCheck[r][c -1] >= memoCheck[r - 1][c]) {
    		LCSs.addAll(collectSolution(rStr, cStr, r, c - 1, memo));
		}
		if (memoCheck[r][c -1] <= memoCheck[r - 1][c]) {
    		LCSs.addAll(collectSolution(rStr, cStr, r - 1, c, memo));
		}
    	return LCSs;
    }
    
    public static Set<String> bottomUpLCS (String rStr, String cStr) {
    	
    	// 2 ISSUES:
    		// 1) IT WON'T LET ME ITERATE OVER THE VALUES IN THE HASH SET SO
    		// NOTHING IS UPDATING, i think the issue is the way i'm unionizing 
    		// because in the example i use there's a scenario where the letter's
    		// don't match but they're equal 
    		// 2) I DON'T THINK I HAVE A BACKWARDS APPROACH RIGHT NOW THE WAY
    			// THE RECURSION IS STRUCTURED FOR THIS IS CONFUSING ME 
    			// PLEASE EXPLAIN THE UNION
    			// anser: it has to do with the call stack each recursive call piles on top of the previous call 
    		// WHY WE NEED A TOP DOWN FILL IS CUZ YOU NEED TO COMPARE ABOVE AND TO THE LEFT SO YOU CANT JUST DO COLLECT 
    			// becakse ou need to compare in the table the numbebrs at the top and left 
    		
    	makeBottomUpTable(rStr, cStr);
    	rStr = "0" + rStr;
    	cStr = "0" + cStr;
    	return collectSolution(rStr, cStr, rStr.length() - 1, cStr.length() - 1, memoCheck);
    		
    }
    
    // [!] TODO: Add any bottom-up specific helpers here!
    
    
    // -----------------------------------------------
    // Top-Down LCS
    // -----------------------------------------------
    
    private static void makeTopDownTable(String rStr, String cStr) {
    	int[][] topDown = new int[rStr.length()][cStr.length()];
    	// you need to make a separate 2d array to memoize this it needs to be a boolean array true or false if you've
    	// already arrived at this location 
    }
    
    
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
        throw new UnsupportedOperationException();
    }
    
    // [!] TODO: Add any top-down specific helpers here!
    public static void main(String args[]) {
    	// DO WE CARE CAPITALIZATION
    	// THIS DOESN'T WORK FROM THE JUMP BECAUSE INITIAL IS 3,3
//    	bottomUpLCS("AXBCZ","XABZC");
    	// WORKS PARTIALLY FOR THIS ONE IT GETS TO A BUT FAILS WHEN 
    	// IT'S B BECAUSE 0 TO THE TOP AND 0 TO THE RIGHT 
    	Set<String> solutionBottomUp = bottomUpLCS("AXBCZ","XABZC");
    	for(String LCS : solutionBottomUp) {
    		System.out.println(LCS);
    	}
    }
    
}