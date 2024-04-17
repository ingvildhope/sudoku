package no.uib.inf101.sudoku.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.sudoku.controller.ControllableSudokuModel;
import no.uib.inf101.sudoku.view.ViewableSudokuModel;


public class SudokuModel implements ViewableSudokuModel, ControllableSudokuModel {
  private SudokuBoard board;
  private GameState gameState;

  private CellPosition selectedPosition = null;
  private int selectedValue = 0;


  public SudokuModel(SudokuBoard board) {
    this.board = board;
    this.gameState = GameState.ACTIVE_GAME;
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


  @Override
  public int getSelectedValue() {
    try {
      selectedValue = board.get(selectedPosition);
      return selectedValue;
    } catch (NullPointerException e) {
      System.out.println("No cell selected");
    }

    return -1;
  }
  
  @Override
  public void setNumberInCell(int value) {
    board.set(selectedPosition, value);
    if (board.get(selectedPosition) == 0) {
      board.set(selectedPosition, value);
    }
  }

  @Override
  public boolean isBoardFinished() {
    if ((board.isBoardComplete(board)) && (board.isValidSolution(board))) {
      gameState = GameState.GAME_FINISHED;
      System.out.println("GameState: " + gameState);
      return true;
    }
    return false;
  }
  
  @Override
  public GameState getGameState() {
    return gameState;
  }

  @Override
  public void captureClick(CellPosition pos) {
    
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
