package no.uib.inf101.sudoku.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.sudoku.controller.ControllableSudokuModel;
import no.uib.inf101.sudoku.view.ViewableSudokuModel;

public class SudokuModel implements ViewableSudokuModel, ControllableSudokuModel {
  private SudokuBoard board;
  private GameState gameState;
  private String level;
  private boolean pause;
  private int minutes;
  private int seconds;
  private long pauseStart;
  private long pauseStop;
  private long totalPauseTime;
  private long milliseconds;
  private String timeElapsed;
  private long startTime;

  private CellPosition selectedPosition = null;
  private int selectedValue = 0;
 
  public SudokuModel(SudokuBoard board) {
    this.board = board;
    this.pause = false;
    this.gameState = GameState.WELCOME_SCREEN;
  }

  @Override
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
  public void startGame(SudokuBoard startBoard) {
    gameState = GameState.ACTIVE_GAME;
    startTime = System.currentTimeMillis();
    if (startBoard == null) {
      board.generateBoard(level);
    }
    else {
      board = startBoard;
    }
  }

  @Override
  public void returnToWelcomeState() {
    gameState = GameState.WELCOME_SCREEN;
    totalPauseTime = 0;
    selectedPosition = null;
    selectedValue = 0;
  }

  @Override
  public boolean pauseGame() {
    pause = !pause;

    if (pause) {
      gameState = GameState.PAUSE;
      pauseStart = System.currentTimeMillis() - startTime;
    }
    else {
      gameState = GameState.ACTIVE_GAME;
      pauseStop = System.currentTimeMillis() - startTime;
      totalPauseTime += (pauseStop - pauseStart);
    }
    return pause;
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
  public boolean checkInput(CellPosition pos) {
    if (getSelectedValue() > 0) {
      if (board.isValidMove(pos, board)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isOriginal(CellPosition pos) {
    return board.isPosOriginal(pos);
  }

  @Override
  public String getFormatedTime() {
    seconds = getMilliseconds() / 1000;
    minutes = seconds / 60;
    StringBuilder sb = new StringBuilder();
    if (minutes < 10) {
      sb.append("0");
    }
    sb.append(minutes);
    sb.append(":");
    if ((seconds % 60) < 10) {
      sb.append("0");
    }
    
    sb.append(seconds % 60);
    timeElapsed = sb.toString();
    
    return timeElapsed;
  }

  @Override
  public int getMilliseconds() {
    milliseconds = System.currentTimeMillis() - startTime - totalPauseTime;
    int millSecs = (int) milliseconds;
   
    return millSecs;
  }

  @Override
  public GridDimension getDimension() {
    return board;
  }
}
