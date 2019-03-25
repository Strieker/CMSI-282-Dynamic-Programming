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
    			if((column == 0 && row == 0) || row == 0) {
    				bottomUp[row][column] = 0;
    			} else {
    				if(rStr.charAt(row) == cStr.charAt(column)) {
    					bottomUp[row][column] = bottomUp[row - 1][column] + 1;
    				} else if (rStr.charAt(row) != cStr.charAt(column)){
    					if (column == 0) {
    						bottomUp[row][column] = bottomUp[row - 1][column];
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
    public static Set<String> bottomUpLCS (String rStr, String cStr) {
    	Set<String> LCS = new HashSet<String>();
    	String currentLCSSolution = "";
    	makeBottomUpTable(rStr, cStr);
    	int row = rStr.length() - 1;
    	int column = cStr.length() - 1;
    	while(true) {
    		// GO THROUGH CASES 
    		if(rStr.charAt(row) != cStr.charAt(column)) {
        		if(memoCheck[row][column] == memoCheck[row - 1][column] 
        			&& memoCheck[row - 1][column] != memoCheck[row][column]) {
        			row = row - 1;
        			if (column == 0) {
        				break;
        			} else {
        				column--;
        			}
        		} else if (memoCheck[row][column] != memoCheck[row - 1][column] 
        			&& memoCheck[row - 1][column] == memoCheck[row][column]) {
        			column = column - 1;
        			if (row == 0) {
        				break;
        			} else {
        				row--;
        			}
        		} else if (memoCheck[row][column] == memoCheck[row - 1][column] 
        			&& memoCheck[row - 1][column] == memoCheck[row][column]) {
        			column = column - 1;
        			if (row == 0) {
        				break;
        			} else {
        				row--;
        			}
        		}
        	} else if (rStr.charAt(row) == cStr.charAt(column)) {
        		currentLCSSolution += Character.toString(rStr.charAt(row - 1));
        		row = row - 1;
        		column = column - 1; 
        	}
    	}
    	System.out.println(currentLCSSolution);
    	// START at the bottom rihgt cell
    	// T[r][c]==T[r−1][c] OR T[r][c]==T[r][c−1]
    		// then the LCS must have come from paths on 
    		// these sides of the current cell, so traverse 
    		//  to the cell (either one if both) that has the same value.
    	// 
    	return LCS;
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
    	makeBottomUpTable("ABA","BAA");
    	bottomUpLCS("ABA","BAA");
    	
    }
    
}
