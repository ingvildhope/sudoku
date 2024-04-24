package no.uib.inf101.sudoku;

import javax.swing.JFrame;

import no.uib.inf101.sudoku.controller.SudokuController;
import no.uib.inf101.sudoku.model.SudokuBoard;
import no.uib.inf101.sudoku.model.SudokuModel;
import no.uib.inf101.sudoku.view.SudokuView;

public class SudokuMain {
  private static final int boardSize = 9;
  public static final int cellSize = 40;
  private static final int margin = 0;
  public static final String WINDOW_TITLE = "Sudoku";

  /**
   * 
   * @param args The command line arguments
   */
  public static void main(String[] args) {
    SudokuBoard board = new SudokuBoard(boardSize, boardSize);
    SudokuModel model = new SudokuModel(board);
    SudokuView view = new SudokuView(model, cellSize, margin);
    
    new SudokuController(model, view);

    JFrame frame = new JFrame(WINDOW_TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(view);
    frame.pack();
    frame.setVisible(true);
  }
}
