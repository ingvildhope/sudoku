package no.uib.inf101.sudoku.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.time.LocalDate;
import java.util.Objects;
import java.awt.Graphics;
import java.awt.Font;

import javax.swing.JPanel;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.sudoku.controller.SudokuController;
import no.uib.inf101.sudoku.model.SudokuBoard;
import no.uib.inf101.sudoku.model.SudokuModel;

public class SudokuView extends JPanel{
  private ViewableSudokuModel model;
  private SudokuController controller;
  private ColorTheme colorTheme;
  private SudokuBoard board;
  private CellPositionToPixelConverter converter;
  private int boardSize;
  private int cellSize;
  private int preferredH;
  private int preferredW;
  private int xb;
  private int yb;
  private int margin;
  private Rectangle2D bounds;
  private GridDimension gridSize;
  private static final Color SELECTED_COLOR = Color.ORANGE.darker();
  private static final Color STD_COLOR = Color.WHITE;


  public SudokuView(ViewableSudokuModel model, int cellSize, int margin) {
    this.model = model;
    this.board = ((SudokuModel) model).getBoard();
    this.colorTheme = new DefaultColorTheme();
    this.boardSize = (board.rows() * cellSize);
    this.cellSize = cellSize;
    this.preferredH = boardSize + 100;
    this.preferredW = boardSize + 200;
    this.xb = (preferredW / 2) - (boardSize / 2);
    this.yb = preferredH - boardSize - 10;
    this.margin = margin;
    this.gridSize = model.getDimension();


    this.bounds = new Rectangle2D.Double(xb, yb, boardSize, boardSize);
    this.converter = new CellPositionToPixelConverter(bounds, gridSize, margin);

    this.setPreferredSize(new Dimension(preferredW, preferredH));
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    drawGame(g2);
  }

  private void drawGame(Graphics2D g2) {
    Rectangle2D box = new Rectangle2D.Double(0, 0, preferredW, preferredH);

    g2.setColor(colorTheme.getBackgroundColor());
    g2.fill(box);
    drawBoard(g2);
  }

  private void drawBoard(Graphics2D g2) {
    
    Rectangle2D box = new Rectangle2D.Double(xb - 2, yb - 2, boardSize + 5, boardSize + 5);
    g2.setColor(Color.BLUE);
    g2.fill(box);
    drawCells(g2);
  }
  
  private void drawCells(Graphics2D g2) {
    CellPositionToPixelConverter converter = this.getCellPositionToPixelConverter();
    for (int row = 0; row < this.model.getDimension().rows(); row++) {
      for (int col = 0; col < this.model.getDimension().cols(); col++) {
        CellPosition pos = new CellPosition(row, col);
        Rectangle2D box = converter.getBoundsForCell(pos);
        int number = board.get(pos);
        

        //g2.setColor(Color.WHITE);
        //g2.fill(box);
        Color color = Objects.equals(pos, model.getSelected()) ? SELECTED_COLOR : STD_COLOR;
        g2.setColor(color);
        g2.fill(box);
        g2.setColor(Color.LIGHT_GRAY);
        g2.drawRect((int) box.getX(), (int) box.getY(), (int) box.getWidth(), (int) box.getHeight());
        if (col % 3 == 0) {
          g2.setColor(Color.BLACK);
          g2.drawLine((int) box.getX(), (int) box.getY(), (int) box.getX(), (int) box.getY() + cellSize);
        }
        if (row % 3 == 0) {
          g2.setColor(Color.BLACK);
          g2.drawLine((int) box.getX(), (int) box.getY(), (int) box.getX() + cellSize, (int) box.getY());
        }
        if (number != 0) {
          g2.setColor(Color.BLACK);
          g2.setFont(new Font("Arial", Font.PLAIN, cellSize / 3 * 2));
          String numberString = String.valueOf(number);
          Inf101Graphics.drawCenteredString(g2, numberString, box.getX() + (cellSize / 2), box.getY() + (cellSize / 2));
        }
        
      }
    }

  }
  
  private void drawClickedCell(Graphics2D g2, CellPosition pos) {
    Rectangle2D cell = converter.getBoundsForCell(pos);
    int number = board.get(pos);

    g2.setColor(Color.LIGHT_GRAY);
    g2.fill(cell);
    g2.setColor(Color.LIGHT_GRAY);
    g2.drawRect((int) cell.getX(), (int) cell.getY(), (int) cell.getWidth(), (int) cell.getHeight());

    g2.setColor(Color.BLACK);
    g2.setFont(new Font("Arial", Font.PLAIN, cellSize / 3 * 2));
    String numberString = String.valueOf(number);
    Inf101Graphics.drawCenteredString(g2, numberString, cell.getX() + (cellSize / 2), cell.getY() + (cellSize / 2));

  }
  
  /**
   * @author Torstein Strømme
   * Hentet fra clickablegrid 10.04.24
   * 
   * Gets an object which converts between CellPosition in a grid and 
   * their pixel positions on the screen.
   */
  public CellPositionToPixelConverter getCellPositionToPixelConverter() {
    
    return new CellPositionToPixelConverter(bounds, gridSize, margin);
  }

}
