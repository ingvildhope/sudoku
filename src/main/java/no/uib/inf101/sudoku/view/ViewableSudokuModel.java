package no.uib.inf101.sudoku.view;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.sudoku.model.GameState;
import no.uib.inf101.sudoku.model.SudokuBoard;

public interface ViewableSudokuModel {

  /**
   * @author Torstein Strømme
   * Hentet fra clickablegrid 10.04.24
   * 
   * The dimensions of the board, number of rows and columns
   *
   * @return  an object of type GridDimension
   */
  GridDimension getDimension();

  double getX();

  double getY();

  /** 
   * @author Torstein Strømme
   * Hentet fra clickablegrid 10.04.24
   * 
   * Gets the selected cell in the grid.  
   */
  CellPosition getSelected();
  
  /**
   * Returns the value of the selected cell.
   * 
   * @return the value of the selected cell.
   */
  int getSelectedValue();

  /**
   * Returns true if the board is full and the soulution is valid, otherwise false.
   * 
   * @param board the board to be checked.
   * @return true if board is full and solution is valid, otherwise false.
   */
  boolean isBoardFinished();

  /**
   * Retrieves the current game state.
   * 
   * @return The current game state.
   */
  GameState getGameState();

  /**
   * Checks if the input value is legal. 
   */
  boolean checkInput(CellPosition pos);

  /**
   * Returns the difficulty level of the sudoku as String.
   * 
   * @return the difficulty of the sudoku.
   */
  String getLevel();

  /**
   * Returns the current time the user has used on the sudoku.
   * 
   * @return the used solving the sudoku.
   */
  String getFormatedTime();

  /**
   * Returns the Sudoku board.
   * 
   * @return the Sudoku board.
   */
  SudokuBoard getBoard();

  /**
   * Retruns the number of times the time should be updated in a second.
   * 
   * @return the time between each time the timer updates.
   */
  int getMilliseconds();
}
