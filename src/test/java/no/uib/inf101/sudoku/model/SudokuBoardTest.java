package no.uib.inf101.sudoku.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;

public class SudokuBoardTest {
  private SudokuBoard sBoard;
  private SudokuBoard board = new SudokuBoard(9, 9);
  
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
        
  private int[][] emptyTestBoard = 
    {{0, 0, 0, 0, 0, 0, 0, 0, 0},
     {0, 0, 0, 0, 0, 0, 0, 0, 0},
     {0, 0, 0, 0, 0, 0, 0, 0, 0},
     {0, 0, 0, 0, 0, 0, 0, 0, 0},
     {0, 0, 0, 0, 0, 0, 0, 0, 0},
     {0, 0, 0, 0, 0, 0, 0, 0, 0},
     {0, 0, 0, 0, 0, 0, 0, 0, 0},
     {0, 0, 0, 0, 0, 0, 0, 0, 0},
     {0, 0, 0, 0, 0, 0, 0, 0, 0}};

  @Test
  public void isValidSolutionTest() {
    board.fillBoard(validTestBoard);
    System.out.println("board " + board);
    assertTrue(board.isValidSolution(board));

    board.fillBoard(invalidTestBoard);
    assertFalse(board.isValidSolution(board));
  }
  
  @Test
  public void isBoardCompleteTest() {
    board.fillBoard(incompleteValidTestBoard);
    assertFalse(board.isBoardComplete(board));
  }

  @Test
  public void toStringTest() {
    SudokuBoard board = new SudokuBoard(9, 9);
    board.set(new CellPosition(0, 0), 1);
    board.set(new CellPosition(0, 8), 2);
    board.set(new CellPosition(8, 0), 3);
    board.set(new CellPosition(8, 8), 4);

    String expected = String.join("\n", new String[] {
        "_____________",
        "|100|000|002|",
        "|000|000|000|",
        "|000|000|000|",
        "_____________",
        "|000|000|000|",
        "|000|000|000|",
        "|000|000|000|",
        "_____________",
        "|000|000|000|",
        "|000|000|000|",
        "|300|000|004|",
        "_____________",
        ""
    });

    assertEquals(expected, board.toString());
  }

  @Test
  public void initializationTest() {
    SudokuBoard board = new SudokuBoard(9, 9);
    assertEquals(9, board.rows());
    assertEquals(9, board.cols());
    assertNotNull(board);
  }

  @Test
  public void setAndGetTest() {
    SudokuBoard board = new SudokuBoard(9, 9);
    CellPosition pos = new CellPosition(0, 0);
    board.set(pos, 5);

    assertEquals(5, board.get(pos));
    assertNotEquals(1, board.get(pos));
  }

  @Test
  public void isValidMoveTest() {
    sBoard = new SudokuBoard(9, 9);
    sBoard.fillBoard(incompleteValidTestBoard);

    CellPosition pos1 = new CellPosition(0, 8);
    CellPosition pos2 = new CellPosition(0, 7);

    sBoard.set(pos1, 2);
    assertTrue(sBoard.isValidMove(pos1, sBoard));

    System.out.println("boa " + sBoard);
    sBoard.set(pos2, 2);
    System.out.println("board " + sBoard);

    assertFalse(sBoard.isValidMove(pos2, sBoard));
  }

  @Test
  public void fillBoardTest() {
    SudokuBoard board = new SudokuBoard(9, 9);
    board.fillBoard(incompleteValidTestBoard);

    CellPosition pos = new CellPosition(0, 0);
    assertEquals(5, board.get(pos));
  }

  @Test
  public void generateBoardTest() {
    SudokuBoard board = new SudokuBoard(9, 9);
    SudokuBoard emptyBoard = new SudokuBoard(9, 9);

    board.generateBoard("Easy");
    board.fillBoard(emptyTestBoard);

    assertNotEquals(emptyBoard, board);
  }
}
