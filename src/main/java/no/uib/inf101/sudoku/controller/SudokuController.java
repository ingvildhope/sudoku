package no.uib.inf101.sudoku.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.geom.Point2D;

import javax.swing.Timer;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.sudoku.model.GameState;
import no.uib.inf101.sudoku.model.SudokuBoard;
import no.uib.inf101.sudoku.view.CellPositionToPixelConverter;
import no.uib.inf101.sudoku.view.SudokuView;

public class SudokuController extends MouseAdapter implements KeyListener{
  private ControllableSudokuModel model;
  private SudokuView view;
  private CellPosition pos;
  private GameState gameState;
  private Timer timer;

  public SudokuController(ControllableSudokuModel model, SudokuView view) {
    this.model = model;
    this.view = view;
    this.gameState = model.getGameState();

    timer =  new Timer(1000, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        view.repaint();
      }
    });
  
    view.addMouseListener(this);
    view.addKeyListener(this);
    view.setFocusable(true);
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (gameState == GameState.ACTIVE_GAME) {
      Point2D mouseCoordinate = e.getPoint();
      CellPositionToPixelConverter converter = view.getCellPositionToPixelConverter();
      pos = converter.getCellPositionOfPoint(mouseCoordinate);
      model.setSelected(pos);
      view.repaint();
    }
  }
  
  @Override
  public void keyPressed(KeyEvent e) {
    if (gameState == GameState.WELCOME_SCREEN) {
      timer.restart();
      if (e.getKeyCode() == KeyEvent.VK_E) {
        timer.restart();
        timer.start();
        model.setLevel("Easy");
        model.startGame(null);
        gameState = model.getGameState();
        view.repaint();
      } else if (e.getKeyCode() == KeyEvent.VK_M) {
        timer.restart();
        timer.start();
        model.setLevel("Medium");
        model.startGame(null);
        gameState = model.getGameState();
        view.repaint();
      } else if (e.getKeyCode() == KeyEvent.VK_H) {
        timer.restart();
        timer.start();
        model.setLevel("Hard");
        model.startGame(null);
        gameState = model.getGameState();
        view.repaint();
      } else if (e.getKeyCode() == KeyEvent.VK_X) {
        timer.restart();
        timer.start();
        model.setLevel("Extreme");
        model.startGame(null);
        gameState = model.getGameState();
        view.repaint();
      }
    }

    else if (gameState == GameState.ACTIVE_GAME) {
      timer.restart();
      char keyPressed = e.getKeyChar();
      if (Character.isDigit(keyPressed)) {
        int digit = Character.getNumericValue(keyPressed);
        if (!model.isOriginal(model.getSelected())) {
          model.setNumberInCell(digit);
        }
        if (model.isBoardFinished()) {
          timer.stop();
        }
        gameState = model.getGameState();
        view.repaint();
      }

      if (e.getKeyCode() == KeyEvent.VK_P) {
        timer.stop();
        model.pauseGame();
        gameState = model.getGameState();
        view.repaint();
      }

      else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        timer.restart();
        model.returnToWelcomeState();
        gameState = model.getGameState();
        view.repaint();
      }
    }

    else if (gameState == GameState.PAUSE) {
      if (e.getKeyCode() == KeyEvent.VK_P) {
        timer.start();
        model.pauseGame();
        gameState = model.getGameState();
        view.repaint();
      }
      else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        timer.restart();
        model.returnToWelcomeState();
        gameState = model.getGameState();
        view.repaint();
      }
    }

    else if (gameState == GameState.GAME_FINISHED) {
      timer.stop();
      if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        timer.restart();
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
