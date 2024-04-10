package no.uib.inf101.sudoku.view;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

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
   * Retrieves an iterable object that iterates over all tiles 
   * in the Tetris board.
   * 
   * @return An iterable object that iterates over all tiles in the Tetris board.
   */
   //Iterable<GridCell<Character>> getTilesOnBoard();
  
    /** 
   * @author Torstein Strømme
   * Hentet fra clickablegrid 10.04.24
   * 
   * Gets the selected cell in the grid.  
   */
  CellPosition getSelected();
}
