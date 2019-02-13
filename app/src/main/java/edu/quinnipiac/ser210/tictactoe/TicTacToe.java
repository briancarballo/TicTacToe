package edu.quinnipiac.ser210.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;


/**
 * TicTacToe class implements the interface
 * @author relkharboutly
 * Edited by Brian Carballo
 * @date 1/31/2019
 */
public class TicTacToe implements ITicTacToe {
		 
	   // The game board and the game status
	   private static final int ROWS = 3, COLS = 3; // number of rows and columns
	   private int[][] board = new int[ROWS][COLS]; // game board in 2D array
	  
	/**
	 * clear board and set current player   
	 */
	public TicTacToe(){
		
	}
	@Override
	public void clearBoard() {
		//For each array on the tic tac toe board, each space is filled with 0
		for(int[] row: board) {
			Arrays.fill(row, 0);
		}
	}

	@Override
	public void setMove(int player, int location) {
		//If location is outside the bounds of the board
		if(board[location/3][location%3] != 0) {
			System.out.println("Illegal move, turn forfeited");
		}
		//Places mark on board for player
		else board[location/3][location%3] = player; 
	}

	@Override
	public int getComputerMove() {
		int value,check1,check2,check3;
		
		//Blocks if two consecutive spaces are filled by opposite player
		for(int r = 0; r < 3; r++){
			check1 = board[r][0];
			check2 = board[r][1];
			check3 = board[r][2];
			if(check1 > 0 && check1 == check2 && check3 == 0) return ((r + 1) * 3) - 1;
		}
		//Places random space if opposite player cannot be blocked
		do {
		  value = ThreadLocalRandom.current().nextInt(0,9);
		} while(board[value/3][value%3] != 0);
		return value;
		
	}

	@Override
	public int checkForWinner() {
		int check1,check2,check3;
		int tieCheck = 0;
		
		//Checks each row for winner
		for(int r = 0; r < 3; r++){
			check1 = board[r][0];
			check2 = board[r][1];
			check3 = board[r][2];
			if(check1 == check2 && check2 == check3 && check3 > 0) return check1 + 1;
		}
		//Checks each column for winner
		for(int c = 0; c < 3; c++){
			check1 = board[0][c];
			check2 = board[1][c];
			check3 = board[2][c];
			if(check1 == check2 && check2 == check3 && check3 > 0) return check1 + 1;
		}
		
		//Checks for diagonal win
		check1 = board[0][0];
		check2 = board[1][1];
		check3 = board[2][2];
		if(check1 == check2 && check2 == check3 && check3 > 0) return check1 + 1;
		
		check1 = board[0][2];
		check2 = board[1][1];
		check3 = board[2][0];
		if(check1 == check2 && check2 == check3 && check3 > 0) return check1 + 1;
		
		//Checks for tie
		for(int[] row: board) {
			if(Arrays.binarySearch(row, 0) == -1) tieCheck++;
		}
		if(tieCheck == 3) return 1;
		return 0;
		
	}
	
	
	

	  /**
	   *  Print the game board 
	   */
	   public Integer[] printBoard() {
          ArrayList<Integer> state = new ArrayList<Integer>();
	      for (int row = 0; row < ROWS; ++row) {
	         for (int col = 0; col < COLS; ++col) {
	            printCell(board[row][col],state); // print each of the cells
	            //if (col != COLS - 1) {
	              // System.out.print("|");   // print vertical partition
	           // }
	         }
	        // System.out.println();
	         //if (row != ROWS - 1) {
	           // System.out.println("-----------"); // print horizontal partition
	        // }
	      }
	      //System.out.println();
	      Integer[] arr = state.toArray(new Integer[state.size()]);
	      return arr;
	   }
	 
	   /**
	    * Print a cell with the specified "content" 
	    * @param content either CROSS, NOUGHT or EMPTY
	    */
	   public  void printCell(int content, ArrayList<Integer> state) {
	      switch (content) {
	         case EMPTY:  state.add(0); break;
	         case NOUGHT: state.add(1); break;
	         case CROSS:  state.add(2); break;
	      }
	   }

}
