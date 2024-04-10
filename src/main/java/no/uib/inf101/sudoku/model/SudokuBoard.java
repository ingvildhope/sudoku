package no.uib.inf101.sudoku.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.sudoku.view.ViewableSudokuModel;

public class SudokuBoard extends Grid<Integer>{
  private int rows;
  private int cols;
  private int boardSize;
  
  public SudokuBoard(int rows, int cols) {
    super(rows, cols, 0);
    this.rows = rows;
    this.cols = cols;
    this.boardSize = rows;
    /*
         * {{5, 3, 4, 6, 7, 8, 9, 1, 2},
          {6, 7, 2, 1, 9, 5, 3, 4, 8},
          {1, 9, 8, 3, 4, 2, 5, 6, 7},
          {8, 5, 9, 7, 6, 1, 4, 2, 3},
          {4, 2, 6, 8, 5, 3, 7, 9, 1},
          {7, 1, 3, 9, 2, 4, 8, 5, 6},
          {9, 6, 1, 5, 3, 7, 2, 8, 4},
          {2, 8, 7, 4, 1, 9, 6, 3, 5},
          {3, 4, 5, 2, 8, 6, 1, 7, 9}}
         */
  }

  public String toString() {
    int colCount = 0;
    int rowCount = 0;
    String sb = "";
    for (int i = 0; i < 13; i++) {
      sb += '_';
    }
    sb += "\n";
    sb += '|';
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        colCount++;
        CellPosition pos = new CellPosition(row, col);
        sb += (get(pos));
        if (colCount % 3 == 0) {
          sb += "|";
        }
      }
      rowCount++;
      sb += "\n";
      if (rowCount % 3 == 0) {
        for (int i = 0; i < 13; i++) {
          sb += '_';
        }
        sb += "\n";
      }
      if (colCount % 3 == 0) {
        sb += "|";
      }
    }
    sb = sb.substring(0, sb.length()-1);
    sb.trim();
    return sb.toString();
  }

  public void setNumber(int row, int col, int number) {
    set(new CellPosition(row, col), number);
  }

}
