package no.uib.inf101.sudoku.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.geom.Point2D;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.sudoku.view.CellPositionToPixelConverter;
import no.uib.inf101.sudoku.view.SudokuView;

//import inf101.sudoku.model.SudokuModel;
//

public class SudokuController extends MouseAdapter implements KeyListener{
  private ControllableSudokuModel model;
  private SudokuView view;
  //private int guess;

  public SudokuController(ControllableSudokuModel model, SudokuView view) {
    this.model = model;
    this.view = view;

    view.addMouseListener(this);
    view.addKeyListener(this);
    view.setFocusable(true);
  }
  
  public void makeGuess(int row, int col, int number) {
    //model.makeGuess(row, col, number);
  }

  @Override
  public void mousePressed(MouseEvent e) {
    Point2D mouseCoordinate = e.getPoint();
    CellPositionToPixelConverter converter = view.getCellPositionToPixelConverter();
    CellPosition pos = converter.getCellPositionOfPoint(mouseCoordinate);
    model.setSelected(pos);
    System.out.println("Cell clicked: " + pos);
    view.repaint();
    /*
     * endre her!!!!!!!!
     */
    for (int row = 0; row < 9; row++) {
      for (int col = 0; col < 9; col++) {
        CellPosition pos2 = new CellPosition(row, col);
        if (model.isValueEqual(pos, pos2)) {
          model.setSelected(pos2);
          view.repaint();
        }
        view.repaint();
      }
    }

    view.repaint();
  }
  
  @Override
  public void mouseClicked(MouseEvent e) {
    /*
    int cellSize = model.getBoard().numRows();
    int col = e.getX() / cellSize;
    int row = e.getY() / cellSize;
    
    model.makeGuess(row, col, guess);
    
    Point2D mouseCoordinate = e.getPoint();
    CellPositionToPixelConverter converter = this.view.getCellPositionToPixelConverter();
    CellPosition pos = converter.getCellPositionOfPoint(mouseCoordinate);
    this.model.setSelected(pos);
    this.view.repaint();
    */

  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      view.repaint();
    }
    view.repaint();
  }

 
  @Override public void mouseReleased(MouseEvent e) { /* ignore */ }
  @Override public void mouseEntered(MouseEvent e) { /* ignore */ }
  @Override public void mouseExited(MouseEvent e) { /* ignore */ }

  @Override public void keyTyped(KeyEvent e) { /* ignore */ }
  @Override public void keyReleased(KeyEvent e) { /* ignore */ }
}
