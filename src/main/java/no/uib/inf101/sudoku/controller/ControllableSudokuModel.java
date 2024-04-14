package no.uib.inf101.sudoku.controller;

import no.uib.inf101.grid.CellPosition;

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
   * compares the values of two positions on the board, returns true if equal, 
   * otherwise false.
   * @param pos1 position of first cell to be compared.
   * @param pos2 position of second cell to be compared.
   * @return true if values at first and second position is equal, otherwise false.
   */
  boolean isValueEqual(CellPosition pos1, CellPosition pos2);
}