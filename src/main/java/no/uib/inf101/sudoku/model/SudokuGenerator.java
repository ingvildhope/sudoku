package no.uib.inf101.sudoku.model;

import java.util.Random;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.sudoku.model.SudokuBoard;

public class SudokuGenerator {
  private SudokuBoard board;
  private static int rows;
  private static int cols;

  private int[][] validTestBoard = 
    {{5, 3, 4, 6, 7, 8, 9, 1, 2},
     {6, 7, 2, 1, 9, 5, 3, 4, 8},
     {1, 9, 8, 3, 4, 2, 5, 6, 7},
     {8, 5, 9, 7, 6, 1, 4, 2, 3},
     {4, 2, 6, 8, 5, 3, 7, 9, 1},
     {7, 1, 3, 9, 2, 4, 8, 5, 6},
     {9, 6, 1, 5, 3, 7, 2, 8, 4},
     {2, 8, 7, 4, 1, 9, 6, 3, 5},
     {3, 4, 5, 2, 8, 6, 1, 7, 9}};
     
  private int[][] invalidTestBoard = 
    {{5, 3, 4, 6, 7, 8, 9, 1, 1},
     {6, 7, 2, 1, 9, 5, 3, 4, 8},
     {1, 9, 8, 3, 4, 2, 5, 6, 7},
     {8, 5, 9, 7, 6, 1, 4, 2, 3},
     {4, 2, 6, 8, 5, 3, 7, 9, 1},
     {7, 1, 3, 9, 2, 4, 8, 5, 6},
     {9, 6, 1, 5, 3, 7, 2, 8, 4},
     {2, 8, 7, 4, 1, 9, 6, 3, 5},
     {3, 4, 5, 2, 8, 6, 1, 7, 9}};
     
  private int[][] incompleteValidTestBoard = 
    {{5, 3, 4, 6, 7, 8, 9, 0, 0},
     {6, 7, 2, 1, 9, 5, 3, 4, 8},
     {1, 9, 8, 3, 4, 2, 5, 6, 7},
     {8, 5, 0, 7, 6, 1, 4, 2, 3},
     {4, 2, 6, 8, 5, 3, 7, 9, 1},
     {7, 1, 3, 9, 2, 4, 8, 5, 6},
     {9, 6, 1, 5, 3, 7, 2, 8, 4},
     {2, 8, 7, 4, 1, 9, 6, 3, 5},
     {3, 4, 5, 2, 8, 6, 1, 7, 0}};
     
  public SudokuGenerator(int rows, int cols, SudokuBoard board) {
    this.board = board;
    this.rows = board.rows();
    this.cols = board.cols();
    fillBoard(board, incompleteValidTestBoard);
  }

  public SudokuBoard generateBoard() {
    int count = 0;
    while (!board.isValidSolution(board)) {
      fillBoard(board, incompleteValidTestBoard);
      count++;
    }
    System.out.println("Found valid board after " + count + " tries");
    return board;
  }
  
  public static void fillBoard(SudokuBoard board, int[][] filledBoard) {
    Random random = new Random();

    for (int row = 0; row < board.rows(); row++) {

      for (int col = 0; col < board.cols(); col++) {
        //int number = random.nextInt(9) + 1;
        int number = filledBoard[row][col];
        CellPosition pos = new CellPosition(row, col);
        //System.out.println("number: " + number);
        board.set(pos, number);
        //System.out.println(board.get(new CellPosition(row, col)));
        /* 
        if (isValidMove(row, col, number)) {
          board.setNumber(row, col, number);
        } else {
          col--;
        }*/
      }
    }
  }

  
  /* 
  public boolean isValidMove(int row, int col, int number) {
    boolean valid = isValidInRow(row, number) && isValidInColumn(col, number) && isValidInBox(row, col, number);
    return valid;
  }

  private boolean isValidInRow(int row, int number) {
    for (int col = 0; col < board.numColumns(); col++) {
      Location loc = new Location(row, col);
      if (board.get(loc) == number) {
        return false;
      }
    }
    return true;
  }

  private boolean isValidInColumn(int col, int number) {
    for (int row = 0; row < board.numRows(); row++) {
      Location loc = new Location(row, col);
      if (board.get(loc) == number) {
        return false;
      }
    }
    return true;
  }

  private boolean isValidInBox(int row, int col, int number) {
    int boxStartRow = row - row % 3;
    int boxStartCol = col - col % 3;
    for (int r = boxStartRow; r < boxStartRow + 3; r++) {
      for (int c = boxStartCol; c < boxStartCol + 3; c++) {
        Location loc = new Location(r, c);
        if (board.get(loc) == number) {
          return false;
        }
      }
    }
    return true;
  }*/

}
