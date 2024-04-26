package no.uib.inf101.grid;

/**
 * Hentet fra tetris-rammeverket, men utvidet til å passe sudoku.
 * Hentet 05.04.24
 */
public interface GridDimension {

  /** Number of rows in the grid */
  int rows();

  /** Number of columns in the grid */
  int cols();
}
