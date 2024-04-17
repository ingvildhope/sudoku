package no.uib.inf101.sudoku.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;

public class SudokuGeneratorTest {
  private SudokuBoard board = new SudokuBoard(9, 9);
  private final int[][] validTestBoard = 
    {{5, 3, 4, 6, 7, 8, 9, 1, 2},
     {6, 7, 2, 1, 9, 5, 3, 4, 8},
     {1, 9, 8, 3, 4, 2, 5, 6, 7},
     {8, 5, 9, 7, 6, 1, 4, 2, 3},
     {4, 2, 6, 8, 5, 3, 7, 9, 1},
     {7, 1, 3, 9, 2, 4, 8, 5, 6},
     {9, 6, 1, 5, 3, 7, 2, 8, 4},
     {2, 8, 7, 4, 1, 9, 6, 3, 5},
     {3, 4, 5, 2, 8, 6, 1, 7, 9}};
     
  private final int[][] invalidTestBoard = 
    {{5, 3, 4, 6, 7, 8, 9, 1, 1},
     {6, 7, 2, 1, 9, 5, 3, 4, 8},
     {1, 9, 8, 3, 4, 2, 5, 6, 7},
     {8, 5, 9, 7, 6, 1, 4, 2, 3},
     {4, 2, 6, 8, 5, 3, 7, 9, 1},
     {7, 1, 3, 9, 2, 4, 8, 5, 6},
     {9, 6, 1, 5, 3, 7, 2, 8, 4},
     {2, 8, 7, 4, 1, 9, 6, 3, 5},
     {3, 4, 5, 2, 8, 6, 1, 7, 9}};
     
  private final int[][] incompleteValidTestBoard = 
    {{5, 3, 4, 6, 7, 8, 9, 0, 0},
     {6, 7, 2, 1, 9, 5, 3, 4, 8},
     {1, 9, 8, 3, 4, 2, 5, 6, 7},
     {8, 5, 0, 7, 6, 1, 4, 2, 3},
     {4, 2, 6, 8, 5, 3, 7, 9, 1},
     {7, 1, 3, 9, 2, 4, 8, 5, 6},
     {9, 6, 1, 5, 3, 7, 2, 8, 4},
     {2, 8, 7, 4, 1, 9, 6, 3, 5},
     {3, 4, 5, 2, 8, 6, 1, 7, 0}};
        
  @Test
  public void isValidSolutionTest() {
    SudokuGenerator.fillBoard(board, validTestBoard);
    assertTrue(board.isValidSolution(board));

    SudokuGenerator.fillBoard(board, invalidTestBoard);
    assertFalse(board.isValidSolution(board));
  }
  
  @Test
  public void isBoardCompleteTest() {
    SudokuGenerator.fillBoard(board, incompleteValidTestBoard);
    assertFalse(board.isBoardComplete(board));
  }
}
