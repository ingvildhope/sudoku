package no.uib.inf101.sudoku.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.sudoku.controller.ControllableSudokuModel;
import no.uib.inf101.sudoku.view.ViewableSudokuModel;


public class SudokuModel implements ViewableSudokuModel, ControllableSudokuModel {
  private SudokuBoard board;

  private CellPosition selectedPosition = null;


  public SudokuModel(SudokuBoard board) {
    this.board = board;
  }

  public SudokuBoard getBoard() {
    return board;
  }
/* 
  @Override
  public Iterable<GridCell<Character>> getTilesOnBoard() {
    return board;
  }
*/

  @Override
  public void setSelected(CellPosition selectedPosition) {
    this.selectedPosition = selectedPosition;
  }

  @Override
  public CellPosition getSelected() {
    return this.selectedPosition;
  }

  @Override
  public boolean isValueEqual(CellPosition pos1, CellPosition pos2) {
    if ((board.get(pos1) == board.get(pos2)) && (board.get(pos2) != 0)) {
      return true;
    }
    return false;
  }

  public void makeGuess(int row, int col, int number) {
  /*
      if (isValidMove(row, col, number)) {
        board.setNumber(row, col, number);
      } 
      else {
        throw new IllegalArgumentException("Invalid Guess");
      }
      */
    }

  @Override
  public double getX() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getX'");
  }

  @Override
  public double getY() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getY'");
  }

  @Override
  public GridDimension getDimension() {
    return board;
  }

}
