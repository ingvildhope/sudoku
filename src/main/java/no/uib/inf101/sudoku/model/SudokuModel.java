package no.uib.inf101.sudoku.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.sudoku.controller.ControllableSudokuModel;
import no.uib.inf101.sudoku.view.ViewableSudokuModel;


public class SudokuModel implements ViewableSudokuModel, ControllableSudokuModel {
  private SudokuBoard board;
  private GameState gameState;
  private String level;

  private CellPosition selectedPosition = null;
  private int selectedValue = 0;
 
  public SudokuModel(SudokuBoard board) {
    this.board = board;
    this.gameState = GameState.WELCOME_SCREEN;
  }

  public SudokuBoard getBoard() {
    return board;
  }

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
    }

    return -1;
  }
  
  @Override
  public void setNumberInCell(int value) {
    board.set(selectedPosition, value);
  }

  @Override
  public boolean isBoardFinished() {
    if ((board.isBoardComplete(board)) && (board.isValidSolution(board))) {
      gameState = GameState.GAME_FINISHED;
      setSelected(null);
      return true;
    }
    return false;
  }
  
  @Override
  public GameState getGameState() {
    return gameState;
  }

  @Override
  public void startGame() {
    gameState = GameState.ACTIVE_GAME;
    SudokuGenerator gen = new SudokuGenerator(board.rows(), board.cols(), board, level);
    gen.generateBoard();
  }

  @Override
  public void returnToWelcomeState() {
    gameState = GameState.WELCOME_SCREEN;
  }

  @Override
  public void setLevel(String level) {
    this.level = level;
  }

  @Override
  public String getLevel() {
    return level;
  }

  @Override
  public boolean checkInput() {
    if (getSelectedValue() != -1) {
      if (board.isValidMove(selectedPosition, board))
        System.out.println("Value OK");
        return true;
    }
    System.out.println("NOT OK");
    return false;
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
