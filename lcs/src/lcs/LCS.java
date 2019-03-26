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
    
    public static void makeBottomUpTable(String rStr, String cStr) {
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
    
    /**
     * Bottom-up dynamic programming approach to the LCS problem, which
     * solves larger and larger subproblems iterative using a tabular
     * memoization structure.
     * @param rStr The String found along the table's rows
     * @param cStr The String found along the table's cols
     * @return The longest common subsequence between rStr and cStr +
     *         [Side Effect] sets memoCheck to refer to table
     */
    
    public static Set<String> collectSolution(String rStr, String cStr, int r, int c, int[][] memo, Set<String> LCSs) {
    	System.out.println(Integer.toString(r) + Integer.toString(c));
    	System.out.println(" ");
    	if(r == 0 || c == 0) {
    		return LCSs;
//        	return new HashSet<String>();
    	}
    	System.out.println(r);
    	System.out.println(c);
    	if(rStr.charAt(r) == cStr.charAt(c)) {
    		System.out.println("gotya");
    		for(String LCS : LCSs) {
    			String newLCS = LCS + Character.toString(rStr.charAt(r));
    			LCSs.remove(LCS);
    			LCSs.add(newLCS);
    			System.out.println("fuck");
    		}
    		return collectSolution(rStr.substring(0, rStr.length() - 1), cStr.substring(0, cStr.length() - 1), r - 1, c - 1, memo, LCSs);
    	}
    	LCSs = new HashSet<String>();
    	if(rStr.charAt(r) != cStr.charAt(c)) {
    		if(memoCheck[r][c -1] >= memoCheck[r - 1][c]) {
        		LCSs.addAll(collectSolution(rStr.substring(0, rStr.length()), cStr.substring(0, cStr.length() - 1), r, c - 1, memo, LCSs));
    		} else if (memoCheck[r][c -1] <= memoCheck[r - 1][c]) {
        		LCSs.addAll(collectSolution(rStr.substring(0, rStr.length() - 1), cStr.substring(0, cStr.length()), r - 1, c, memo, LCSs));
    		}
    	}
    	return LCSs;
    }
    
    public static Set<String> bottomUpLCS (String rStr, String cStr) {
    	makeBottomUpTable(rStr, cStr);
    	rStr = "0" + rStr;
    	cStr = "0" + cStr;
    	System.out.println("made it");
    	Set<String> LCSs = new HashSet<String>();
    	LCSs.add("");
    	return collectSolution(rStr, cStr, rStr.length() - 1, cStr.length() - 1, memoCheck, LCSs);
    }
    
    // [!] TODO: Add any bottom-up specific helpers here!
    
    
    // -----------------------------------------------
    // Top-Down LCS
    // -----------------------------------------------
    
    
    public static void makeTopDownTable(String rStr, String cStr) {
    	int row = rStr.length() - 1;
    	int column = cStr.length() - 1;
//    	memoCheck = topDown;
//    	for(int i = 0; i < rStr.length(); i++) {
//    		for(int j = 0; j < cStr.length(); j++) {
//    			if (j != cStr.length() - 1) {
//            		System.out.print(Integer.toString(memoCheck[i][j]) + " ");
//    			} else {
//            		System.out.println(memoCheck[i][j]);
//    			}
//        	}
//    	}
    	// 
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
    	makeBottomUpTable("AXBCZ","XABZC");
    	bottomUpLCS("AXBCZ","XABZC");
    	
    }
    
}
