package no.uib.inf101.sudoku.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;

public class SudokuModelTest {

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

   @Test
  void testStartGame() {
    SudokuBoard board = new SudokuBoard(9, 9);
    SudokuModel model = new SudokuModel(board);
    board.fillBoard(validTestBoard);

    model.startGame();

    assertEquals(GameState.ACTIVE_GAME, model.getGameState());
  }

  @Test
  void testPauseGame() {
    SudokuBoard board = new SudokuBoard(9, 9);
    SudokuModel model = new SudokuModel(board);
    board.fillBoard(validTestBoard);

    model.startGame();
    model.pauseGame();
    
    assertEquals(GameState.PAUSE, model.getGameState());
  }

  @Test
  void testReturnToWelcomeState() {
    SudokuBoard board = new SudokuBoard(9, 9);
    SudokuModel model = new SudokuModel(board);
    board.fillBoard(validTestBoard);

    model.startGame();
    model.returnToWelcomeState(); 

    assertEquals(GameState.WELCOME_SCREEN, model.getGameState()); 
    assertNull(model.getSelected()); 
    assertEquals(-1, model.getSelectedValue());
  }

  @Test
  void testCheckInput() {
    SudokuBoard board = new SudokuBoard(9, 9);
    SudokuModel model = new SudokuModel(board);
    board.fillBoard(incompleteValidTestBoard);

    CellPosition pos1 = new CellPosition(0, 8);
    model.setSelected(pos1);
    model.setNumberInCell(2);

    assertTrue(model.checkInput(pos1));

    CellPosition pos2 = new CellPosition(0, 7);
    model.setSelected(pos2);
    model.setNumberInCell(2);

    assertFalse(model.checkInput(pos2));
  }

  @Test
  void testIsBoardFinished() {
    SudokuBoard board = new SudokuBoard(9, 9);
    SudokuModel model = new SudokuModel(board);
    board.fillBoard(incompleteValidTestBoard);

    assertFalse(model.isBoardFinished());

    board.fillBoard(validTestBoard);

    assertTrue(model.isBoardFinished());
  }
}
