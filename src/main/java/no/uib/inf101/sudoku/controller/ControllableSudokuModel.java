package no.uib.inf101.sudoku.controller;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.sudoku.model.GameState;
import no.uib.inf101.sudoku.model.SudokuBoard;

public interface ControllableSudokuModel {
  /**
   * @author Torstein Strømme
   * Hentet fra clickablegrid (https://git.app.uib.no/ii/inf101/23v/students/clickablegrid.git) 10.04.24
   * 
   * Set the selected position in the grid.
   * 
   * @param selectedPosition new position to be selected, or null if new selection
   *                         should be the empty selection
   */
  void setSelected(CellPosition selectedPosition);

  /** 
   * @author Torstein Strømme
   * Hentet fra clickablegrid (https://git.app.uib.no/ii/inf101/23v/students/clickablegrid.git) 10.04.24
   * 
   * Gets the selected cell in the grid.
   * 
   * @return the position of the selected cell in the grid.  
   */
  CellPosition getSelected();

  /**
   * Returns the value of the selected cell.
   * 
   * @return the value of the selected cell.
   */
  int getSelectedValue();

  /**
   * Sets the value of a cell to the given value.
   * 
   * @param value the value to be set for the cell.
   */
  void setNumberInCell(int value);

  /**
   * Returns true if the board is full and the soulution is valid, otherwise false.
   * 
   * @return true if board is full and solution is valid, otherwise false.
   */
  boolean isBoardFinished();
 
  /**
   * Gets the current game state.
   * 
   * @return a GameState object.
   */
  GameState getGameState();

  /**
   * Starts the game by setting the game state to ACTIVE_GAME, and generates the board.
   * 
   * @param startBoard the board to start the game with, null if the user selects.
   */
  void startGame(SudokuBoard startBoard);

  /**
   * Sets the game state to WELCOME_SCREEN, and resets the total time paused,
   * the selected position, and the value of the selected position.
   */
  void returnToWelcomeState();

  /**
   * Changes the game state from active to pause, and the
   * other way around. Returns true if game is paused, otherwise false.
   * 
   * @return true if game is paused, otherwise false.
   */
  boolean pauseGame();

  /**
   * Sets the difficulty level of the sudoku.
   * 
   * @param level the level of difficulty to play.
   */
  void setLevel(String level);

  /**
   * Returns the difficulty level of the sudoku as String.
   * 
   * @return the difficulty of the sudoku.
   */
  String getLevel();

  /**
   * Checks if the value of the given CellPosition is on the original board, 
   * if yes, returns true, otherwise returns false. 
   * 
   * @param pos the position whose value to be checked.
   * @return true if the value is original, otherwise false.
   */
  boolean isOriginal(CellPosition pos);
}