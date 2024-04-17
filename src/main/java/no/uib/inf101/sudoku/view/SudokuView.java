package no.uib.inf101.sudoku.view;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Objects;
import java.awt.Graphics;
import java.awt.Font;

import javax.swing.JPanel;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.sudoku.model.GameState;
import no.uib.inf101.sudoku.model.SudokuBoard;
import no.uib.inf101.sudoku.model.SudokuModel;

public class SudokuView extends JPanel{
  private ViewableSudokuModel model;
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
  private static final Color SELECTED_COLOR = Color.decode("#7CB9E8");
  private static final Color SAME_VALUE_COLOR = SELECTED_COLOR.brighter();
  private static final Color STD_COLOR = Color.WHITE;
  private static final Color WRONG_INT_COLOR = Color.RED;
  private Color cellTextColor;
 


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
    this.converter = getCellPositionToPixelConverter();

    this.setPreferredSize(new Dimension(preferredW, preferredH));
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    switch (model.getGameState()) {
      case WELCOME_SCREEN:
        drawWelcomeScreen(g2);
        break;
      case ACTIVE_GAME:
        drawGame(g2);
        break;
      case GAME_FINISHED:
        drawGame(g2);
        drawFinishedGame(g2);
        break;
      case PAUSE:
        break;
    }
  }

  private void drawGame(Graphics2D g2) {
    Rectangle2D box = new Rectangle2D.Double(0, 0, preferredW, preferredH);

    g2.setColor(colorTheme.getBackgroundColor());
    g2.fill(box);
    drawCells(g2);
    drawBoarder(g2);
  }

  private void drawBoarder(Graphics2D g2) {
    Rectangle2D frame = new Rectangle2D.Double(xb, yb, boardSize, boardSize);
    
    g2.setColor(colorTheme.getBoarderColor());
    g2.draw(frame);
  }
  
  private void drawCells(Graphics2D g2) {
    for (int row = 0; row < this.model.getDimension().rows(); row++) {
      for (int col = 0; col < this.model.getDimension().cols(); col++) {
        CellPosition pos = new CellPosition(row, col);
        Rectangle2D box = converter.getBoundsForCell(pos);
        int number = board.get(pos);
      
        Color color = STD_COLOR;

        if (Objects.equals(pos, model.getSelected())) {
          color = SELECTED_COLOR;
        }
        else {
          if ((board.get(pos) == model.getSelectedValue()) && (number != 0)) {
            color = SAME_VALUE_COLOR;
          }
        }

        g2.setColor(color);
        g2.fill(box);
        g2.setColor(Color.LIGHT_GRAY);
        g2.drawRect((int) box.getX(), (int) box.getY(), (int) box.getWidth(), (int) box.getHeight());

        if (col % 3 == 0) {
          g2.setColor(Color.BLACK);
          BasicStroke stroke = new BasicStroke(3);
          g2.setStroke(stroke);
          g2.drawLine((int) box.getX(), (int) box.getY(), (int) box.getX(), (int) box.getY() + cellSize);
        }

        if (row % 3 == 0) {
          g2.setColor(Color.BLACK);
          BasicStroke stroke = new BasicStroke(3);
          g2.setStroke(stroke);
          g2.drawLine((int) box.getX(), (int) box.getY(), (int) box.getX() + cellSize, (int) box.getY());
        }

        if (number != 0) {
          cellTextColor = colorTheme.getCellTextColor();
          if ((model.getGameState() == GameState.ACTIVE_GAME) && (model.getSelectedValue() != -1)) {
            if (!model.checkInput()) {
              System.out.println("Color is red");
              cellTextColor = WRONG_INT_COLOR;
            }
            
          }

          g2.setColor(cellTextColor);
          g2.setFont(new Font("Arial", Font.PLAIN, cellSize / 3 * 2));
          String numberString = String.valueOf(number);
          Inf101Graphics.drawCenteredString(g2, numberString, box.getX() + (cellSize / 2), box.getY() + (cellSize / 2));
        }
      }
    }

  }
  
  private void drawFinishedGame(Graphics2D g2) {
    g2.setColor(colorTheme.getGameFinishedColor());
    g2.fillRect(xb, yb, boardSize, boardSize);

    g2.setColor(Color.WHITE);
    g2.setFont(new Font("Arial", Font.BOLD, boardSize / 15));

    String text1 = "";
    String text2 = "";
    String text3 = "";
    if (model.isBoardFinished()) {
      text1 = "Congratulations!";
      text2 = "You solved the Sudoku!";
      text3 = "Press 'ENTER' to return to home";
    } else {
      text1 = "There seems to be a mistake!";
    }
    Inf101Graphics.drawCenteredString(g2, text1, xb, yb - 40, boardSize, boardSize);
    Inf101Graphics.drawCenteredString(g2, text2, xb, yb - 10, boardSize, boardSize);

    g2.setFont(new Font("Arial", Font.PLAIN, boardSize / 20));
    Inf101Graphics.drawCenteredString(g2, text3, xb, yb + 30, boardSize, boardSize);
  }

  private void drawWelcomeScreen(Graphics2D g2) {
    g2.setColor(colorTheme.getWelcomeColor());
    g2.fillRect(0, 0, preferredW, preferredH);

    int fontSize1 = preferredW / 12;
    int fontsize2 = preferredW / 18;
    Font font1 = new Font("Arial", Font.BOLD, fontSize1);
    Font font2 = new Font("Arial", Font.BOLD, fontsize2);

    g2.setColor(Color.WHITE);
    g2.setFont(font1);
    Inf101Graphics.drawCenteredString(g2, "Welcome to Sudoku!", 0, 0, preferredW, preferredH / 2);

    g2.setColor(Color.WHITE);
    g2.setFont(font2);
    Inf101Graphics.drawCenteredString(g2, "Press 'E' for easy", 0, 60, preferredW, preferredH / 2);
    Inf101Graphics.drawCenteredString(g2, "Press 'M' for medium", 0, 100, preferredW, preferredH / 2);
    Inf101Graphics.drawCenteredString(g2, "Press 'H' for hard", 0, 140, preferredW, preferredH / 2);
    Inf101Graphics.drawCenteredString(g2, "Press 'X' for extreme", 0, 180, preferredW, preferredH / 2);
  
  }
  /* 
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

  }*/
  
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
