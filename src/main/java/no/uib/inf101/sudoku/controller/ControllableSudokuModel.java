package no.uib.inf101.sudoku.controller;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.sudoku.model.GameState;

public interface ControllableSudokuModel {
  /**
   * @author Torstein Strømme
   * Hentet fra clickablegrid 10.04.24
   * 
   * Set the selected position in the grid.
   * 
   * @param selectedPosition new position to be selected, or null if new selection
   *                         should be the empty selection
   */
  void setSelected(CellPosition selectedPosition);

  /** 
   * @author Torstein Strømme
   * Hentet fra clickablegrid 10.04.24
   * 
   * Gets the selected cell in the grid.  
   */
  CellPosition getSelected();

  /**
   * Compares the values of two positions on the board, returns true if equal, 
   * otherwise false.
   * 
   * @param pos1 position of first cell to be compared.
   * @param pos2 position of second cell to be compared.
   * @return true if values at first and second position is equal, otherwise false.
   */
  boolean isValueEqual(CellPosition pos1, CellPosition pos2);

  /**
   * If the cell is not empty, then capture the value.
   * 
   * @param pos position of value to be captured.
   */
  void captureClick(CellPosition pos);
  
  /**
   * Sets the value of a cell to the given value.
   * 
   * @param value the value to be set for the cell.
   */
  void setNumberInCell(int value);

  /**
   * Returns true if the board is full and the soulution is valid, otherwise false.
   * 
   * @param board the board to be checked.
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
   * Starts the game by setting the game state to ACTIVE_GAME.
   */
  void startGame();

  /**
   * Sets the game state to WELCOME_SCREEN.
   */
  void returnToWelcomeState();

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
   * Checks if the input value is legal. 
   */
  boolean checkInput();
}