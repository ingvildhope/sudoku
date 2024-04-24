package no.uib.inf101.sudoku.view;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics;
import java.awt.Font;

import javax.swing.JPanel;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.sudoku.model.SudokuBoard;

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
  private int xp;
  private int yp;
  private int xe;
  private int ye;
  private int margin;
  private Rectangle2D bounds;
  private GridDimension gridSize;
  private Color cellTextColor;
 
  public SudokuView(ViewableSudokuModel model, int cellSize, int margin) {
    this.model = model;
    this.board = model.getBoard();
    this.colorTheme = new DefaultColorTheme();
    this.boardSize = (board.rows() * cellSize);
    this.cellSize = cellSize;
    this.preferredH = boardSize + 160;
    this.preferredW = boardSize + 240;
    this.xb = (preferredW / 2) - (boardSize / 2);
    this.yb = preferredH - boardSize - 30;
    this.xp = (preferredW / 2) + (boardSize / 2) - 20;
    this.yp = (preferredW / 2) - (boardSize / 2) - 60;
    this.xe = xp - 90;
    this.ye = yb  + boardSize + 20;
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
        drawGame(g2);
        drawPause(g2);
        drawBorder(g2);
        break;
    }
  }

  private void drawGame(Graphics2D g2) {
    Rectangle2D box = new Rectangle2D.Double(0, 0, preferredW, preferredH);

    g2.setColor(colorTheme.getBackgroundColor());
    g2.fill(box);
    drawCells(g2);
    drawBorder(g2);
    try {
      int fontSizeTime = 14;
      Font fontTime = new Font("Arial", Font.PLAIN, fontSizeTime);
      String time = model.getFormatedTime();

      g2.setColor(Color.WHITE);
      g2.setFont(fontTime);
      Inf101Graphics.drawCenteredString(g2, time, 0, 0, preferredW, yb / 2);
    } catch (NullPointerException e) {
    }

    int fontSizeLevel = 34;
    Font fontLevel = new Font("Georgia", Font.BOLD, fontSizeLevel);
    String level = model.getLevel();

    g2.setColor(Color.WHITE);
    g2.setFont(fontLevel);
    Inf101Graphics.drawCenteredString(g2, level, 0, 10, preferredW, yb);

    int fontSizeP = 15;
    Font fontP = new Font("Georgia", Font.PLAIN, fontSizeP);

    g2.setColor(Color.WHITE);
    g2.setFont(fontP);
    g2.drawString("Press 'P' for pause", xp, yp);
    g2.drawString("Press 'ENTER' to return to home", xe, ye);
  }

  private void drawBorder(Graphics2D g2) {
    Rectangle2D frame = new Rectangle2D.Double(xb, yb, boardSize, boardSize);
    
    g2.setColor(colorTheme.getBorderColor());
    g2.draw(frame);
  }
  
  private void drawCells(Graphics2D g2) {
    for (int row = 0; row < this.model.getDimension().rows(); row++) {
      for (int col = 0; col < this.model.getDimension().cols(); col++) {
        CellPosition pos = new CellPosition(row, col);

        cellTextColor = colorTheme.getOriginalNumColor();
        if ((model.getSelectedValue() == board.get(pos)) && (model.getSelectedValue() != 0)) {
          if (!board.isPosOriginal(pos)) {
            cellTextColor = colorTheme.getInputNumColor();
          }
          drawCell(g2, row, col, colorTheme.getSameValueCellColor(), cellTextColor);
        }
        else {
          if (!board.isPosOriginal(pos)) {
            cellTextColor = colorTheme.getInputNumColor();
          }
          drawCell(g2, row, col, colorTheme.getSTDCellColor(), cellTextColor);
        }
      }
    }
    drawSelectedCell(g2);
  }
  
  private void drawSelectedCell(Graphics2D g2) {
    try {
      cellTextColor = Color.decode("#2E5894").darker();
      Color cellColor = colorTheme.getSelectedCellColor();

      CellPosition pos = model.getSelected();
      int value = model.getSelectedValue();

      if ((value > 0) && (!model.checkInput(pos))) {
        //System.out.println("Invalid value in cell");
        cellTextColor = colorTheme.getWrongIntColor();
      }
      drawCell(g2, pos.row(), pos.col(), cellColor, cellTextColor);

    } catch (NullPointerException e) {
    }
  }
  
  private void drawCell(Graphics2D g2, int row, int col, Color cellColor, Color textColor) {
    CellPosition pos = new CellPosition(row, col);
    Rectangle2D box = converter.getBoundsForCell(pos);
    int value = board.get(pos);

    g2.setColor(cellColor);
    g2.fill(box);
    g2.setColor(Color.LIGHT_GRAY);
    g2.drawRect((int) box.getX(), (int) box.getY(), (int) box.getWidth(), (int) box.getHeight());

    g2.setColor(Color.BLACK);
    BasicStroke stroke = new BasicStroke(2);
    g2.setStroke(stroke);

    if (row % 3 == 0) { // Draw bold line above.
      g2.drawLine((int) box.getX(), (int) box.getY(), (int) box.getX() + cellSize, (int) box.getY());
    }
    if (row % 3 == 2) { // Draw bold line below.
      g2.drawLine((int) box.getX(), (int) box.getY() + cellSize, (int) box.getX() + cellSize,
          (int) box.getY() + cellSize);
    }
    if (col % 3 == 0) { // Draw bold line to the left
      g2.drawLine((int) box.getX(), (int) box.getY(), (int) box.getX(), (int) box.getY() + cellSize);
    }
    if (col % 3 == 2) { // Draw bold line to the right
      g2.drawLine((int) box.getX() + cellSize, (int) box.getY(), (int) box.getX() + cellSize,
          (int) box.getY() + cellSize);
    }

    if (value != 0) {
      Font font = new Font("Arial", Font.PLAIN, cellSize / 3 * 2);

      g2.setColor(textColor);
      g2.setFont(font);
      String numberString = String.valueOf(value);
      Inf101Graphics.drawCenteredString(g2, numberString, box.getX() + (cellSize / 2), box.getY() + (cellSize / 2));
    }
  }
  
  private void drawPause(Graphics2D g2) {
    Color textColor = colorTheme.getSTDCellColor();
    for (int row = 0; row < this.model.getDimension().rows(); row++) {
      for (int col = 0; col < this.model.getDimension().cols(); col++) {
        drawCell(g2, row, col, colorTheme.getSTDCellColor(), textColor);
      }
    }

    g2.setColor(colorTheme.getGameFinishedColor());
    g2.fillRect(xb, yb, boardSize, boardSize);

    g2.setColor(Color.WHITE);
    g2.setFont(new Font("Georgia", Font.BOLD, boardSize / 10));

    Inf101Graphics.drawCenteredString(g2, "PAUSE", xb, yb - 40, boardSize, boardSize);

    String time = "Time " + model.getFormatedTime();
    g2.setFont(new Font("Arial", Font.PLAIN, boardSize / 15));
    Inf101Graphics.drawCenteredString(g2, time, xb, yb + 40, boardSize, boardSize);
  }

  private void drawFinishedGame(Graphics2D g2) {
    g2.setColor(colorTheme.getGameFinishedColor());
    g2.fillRect(xb, yb, boardSize, boardSize);

    g2.setColor(Color.WHITE);
    g2.setFont(new Font("Georgia", Font.BOLD, boardSize / 15));

    
    String text1 = "";
    String text2 = "";
    String text3 = "";
    String time = "";
    int millisecs = model.getMilliseconds();
    int secs = (millisecs / 1000) % 60;
    int mins = millisecs / 1000 / 60;
    if (model.isBoardFinished()) {
      text1 = "Congratulations!";
      text2 = "You solved the sudoku in:";
      text3 = "Press 'ENTER' to return to home";
    } else {
      text1 = "There seems to be a mistake!";
    }
    Inf101Graphics.drawCenteredString(g2, text1, xb, yb - 100, boardSize, boardSize);
    Inf101Graphics.drawCenteredString(g2, text2, xb, yb - 50, boardSize, boardSize);

    if (mins == 1) {
      time = mins + " minute and " + secs + " seconds!";
    }
    else {
      time = mins + " minutes and " + secs + " seconds!";
    }
    
    g2.setFont(new Font("Arial", Font.PLAIN, boardSize / 15));
    Inf101Graphics.drawCenteredString(g2, time, xb, yb - 10, boardSize, boardSize);

    g2.setFont(new Font("Georgia", Font.PLAIN, boardSize / 20));
    Inf101Graphics.drawCenteredString(g2, text3, xb, yb + 60, boardSize, boardSize);
  }

  private void drawWelcomeScreen(Graphics2D g2) {
    g2.setColor(colorTheme.getWelcomeColor());
    g2.fillRect(0, 0, preferredW, preferredH);

    int fontSize1 = preferredW / 12;
    int fontsize2 = preferredW / 18;
    Font font1 = new Font("Georgia", Font.BOLD, fontSize1);
    Font font2 = new Font("Georgia", Font.PLAIN, fontsize2);

    g2.setColor(Color.WHITE);
    g2.setFont(font1);
    Inf101Graphics.drawCenteredString(g2, "Welcome to Sudoku!", 0, 0, preferredW, preferredH / 2);

    g2.setColor(Color.LIGHT_GRAY);
    g2.setFont(font2);
    Inf101Graphics.drawCenteredString(g2, "Choose level of difficulty:", 0, 90, preferredW, preferredH / 2);
    Inf101Graphics.drawCenteredString(g2, "Easy (E)", 0, 160, preferredW, preferredH / 2);
    Inf101Graphics.drawCenteredString(g2, "Medium (M)", 0, 210, preferredW, preferredH / 2);
    Inf101Graphics.drawCenteredString(g2, "Hard (H)", 0, 260, preferredW, preferredH / 2);
    Inf101Graphics.drawCenteredString(g2, "Extreme (X)", 0, 310, preferredW, preferredH / 2);
  
  }
  
  /**
   * @author Torstein Strømme
   * Hentet fra clickablegrid (https://git.app.uib.no/ii/inf101/23v/students/clickablegrid.git) 10.04.24
   * 
   * Gets an object which converts between CellPosition in a grid and 
   * their pixel positions on the screen.
   */
  public CellPositionToPixelConverter getCellPositionToPixelConverter() {
    
    return new CellPositionToPixelConverter(bounds, gridSize, margin);
  }
}