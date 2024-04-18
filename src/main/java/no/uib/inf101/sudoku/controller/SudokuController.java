package no.uib.inf101.sudoku.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.geom.Point2D;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.sudoku.model.GameState;
import no.uib.inf101.sudoku.view.CellPositionToPixelConverter;
import no.uib.inf101.sudoku.view.SudokuView;

//import inf101.sudoku.model.SudokuModel;
//

public class SudokuController extends MouseAdapter implements KeyListener{
  private ControllableSudokuModel model;
  private SudokuView view;
  private CellPosition pos;
  private GameState gameState;
  //private int guess;

  public SudokuController(ControllableSudokuModel model, SudokuView view) {
    this.model = model;
    this.view = view;
    this.gameState = model.getGameState();

    view.addMouseListener(this);
    view.addKeyListener(this);
    view.setFocusable(true);
  }
  /* 
  public void makeGuess(int row, int col, int number) {
    //model.makeGuess(row, col, number);
  }*/

  @Override
  public void mousePressed(MouseEvent e) {
    if (gameState == GameState.ACTIVE_GAME) {
      Point2D mouseCoordinate = e.getPoint();
      CellPositionToPixelConverter converter = view.getCellPositionToPixelConverter();
      pos = converter.getCellPositionOfPoint(mouseCoordinate);
      model.setSelected(pos);
      //System.out.println("Cell clicked: " + pos);
      view.repaint();
    }
  }
  
  @Override
  public void keyPressed(KeyEvent e) {
    if (gameState == GameState.WELCOME_SCREEN) {
      if (e.getKeyCode() == KeyEvent.VK_E) {
        //timer.restart();
        model.setLevel("Easy");
        model.startGame();
        gameState = model.getGameState();
        view.repaint();
      }
      else if (e.getKeyCode() == KeyEvent.VK_M) {
        //timer.restart();
        model.setLevel("Medium");
        model.startGame();
        gameState = model.getGameState();
        view.repaint();
      }
      else if (e.getKeyCode() == KeyEvent.VK_H) {
        //timer.restart();
        model.setLevel("Hard");
        model.startGame();
        gameState = model.getGameState();
        view.repaint();
      }
      else if (e.getKeyCode() == KeyEvent.VK_X) {
        //timer.restart();
        model.setLevel("Extreme");
        model.startGame();
        gameState = model.getGameState();
        view.repaint();
      }
    }

    else if (gameState == GameState.ACTIVE_GAME) {
      char keyPressed = e.getKeyChar();
      if (Character.isDigit(keyPressed)) {
        int digit = Character.getNumericValue(keyPressed);
        model.setNumberInCell(digit);
        //model.checkInput(model.getSelected());
        model.isBoardFinished();
        gameState = model.getGameState();
        view.repaint();
      }
      if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        model.returnToWelcomeState();
        gameState = model.getGameState();
        view.repaint();
      }
    }

    else if (gameState == GameState.GAME_FINISHED) {
      if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        model.returnToWelcomeState();
        gameState = model.getGameState();
        view.repaint();
      }
    }
  }
  
  @Override public void mouseClicked(MouseEvent e) { /* ignore */ }
  @Override public void mouseReleased(MouseEvent e) { /* ignore */ }
  @Override public void mouseEntered(MouseEvent e) { /* ignore */ }
  @Override public void mouseExited(MouseEvent e) { /* ignore */ }

  @Override public void keyTyped(KeyEvent e) { /* ignore */ }
  @Override public void keyReleased(KeyEvent e) { /* ignore */ }
}
