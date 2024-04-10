package no.uib.inf101.sudoku;

import java.lang.ModuleLayer.Controller;

import javax.swing.JFrame;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.sudoku.controller.SudokuController;
import no.uib.inf101.sudoku.model.SudokuBoard;
import no.uib.inf101.sudoku.model.SudokuGenerator;
import no.uib.inf101.sudoku.model.SudokuModel;
import no.uib.inf101.sudoku.view.SudokuView;
import no.uib.inf101.sudoku.view.ViewableSudokuModel;

public class SudokuMain {
  private static final int boardSize = 9;
  public static final int cellSize = 40;
  private static final int margin = 0;
  public static final String WINDOW_TITLE = "INF101 Sudoku";


  /**
   * 
   * @param args The command line arguments
   */
  public static void main(String[] args) {
    //SudokuBoard board = new SudokuBoard(boardSize, boardSize);
    
    //SudokuBoard board = SudokuGenerator.generateBoard(); 
    SudokuBoard board = new SudokuBoard(boardSize, boardSize);
    SudokuGenerator gen = new SudokuGenerator(boardSize, boardSize, board);
    SudokuModel model = new SudokuModel(board);
    SudokuView view = new SudokuView(model, cellSize, margin);
    gen.generateBoard();
    new SudokuController(model, view);
    /* 
    board.set(new CellPosition(0, 0), 1);
    board.set(new CellPosition(0, 8), 2);
    board.set(new CellPosition(8, 0), 3);
    board.set(new CellPosition(8, 8), 4);
*/
    System.out.println(board);

    JFrame frame = new JFrame(WINDOW_TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //frame.add(view); 

    frame.setContentPane(view);
    frame.pack();
    frame.setVisible(true);
  }
}
