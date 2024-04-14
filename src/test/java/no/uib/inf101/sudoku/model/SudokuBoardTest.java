package no.uib.inf101.sudoku.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;

public class SudokuBoardTest {

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
  public void testInitialization() {
    SudokuBoard board = new SudokuBoard(9, 9);
    assertEquals(9, board.rows());
    assertEquals(9, board.cols());
  }
    

  @Test
  public void testSetAndGet() {
    SudokuBoard board = new SudokuBoard(9, 9);
    CellPosition pos = new CellPosition(0, 0);
    board.set(pos, 5);
    assertEquals(5, board.get(pos));
  }
}
