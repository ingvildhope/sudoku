package no.uib.inf101.sudoku.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Random;

import no.uib.inf101.grid.CellPosition;

public class SudokuGenerator {
  private SudokuBoard board;
  private SudokuBoard boardCopy;
  private static int rows;
  private static int cols;
  private static int randomInt;
  private static StringBuilder sudokuAsLine = new StringBuilder();
  private String level;

  @SuppressWarnings("unused")
  private int[][] validTestBoard = 
    {{5, 3, 4, 6, 7, 8, 9, 1, 2},
     {6, 7, 2, 1, 9, 5, 3, 4, 8},
     {1, 9, 8, 3, 4, 2, 5, 6, 7},
     {8, 5, 9, 7, 6, 1, 4, 2, 3},
     {4, 2, 6, 8, 5, 3, 7, 9, 1},
     {7, 1, 3, 9, 2, 4, 8, 5, 6},
     {9, 6, 1, 5, 3, 7, 2, 8, 4},
     {2, 8, 7, 4, 1, 9, 6, 3, 5},
     {3, 4, 5, 2, 8, 6, 1, 7, 9}};
     
  @SuppressWarnings("unused")
  private int[][] invalidTestBoard = 
    {{5, 3, 4, 6, 7, 8, 9, 1, 1},
     {6, 7, 2, 1, 9, 5, 3, 4, 8},
     {1, 9, 8, 3, 4, 2, 5, 6, 7},
     {8, 5, 9, 7, 6, 1, 4, 2, 3},
     {4, 2, 6, 8, 5, 3, 7, 9, 1},
     {7, 1, 3, 9, 2, 4, 8, 5, 6},
     {9, 6, 1, 5, 3, 7, 2, 8, 4},
     {2, 8, 7, 4, 1, 9, 6, 3, 5},
     {3, 4, 5, 2, 8, 6, 1, 7, 9}};
     
  @SuppressWarnings("unused")
  private int[][] incompleteValidTestBoard = 
    {{5, 3, 4, 6, 7, 8, 9, 1, 0},
     {6, 7, 2, 1, 9, 5, 3, 4, 8},
     {1, 9, 8, 3, 4, 2, 5, 6, 7},
     {8, 5, 0, 7, 6, 1, 4, 2, 3},
     {4, 2, 6, 8, 5, 3, 7, 9, 1},
     {7, 1, 3, 9, 2, 4, 8, 5, 6},
     {9, 6, 1, 5, 3, 7, 2, 8, 4},
     {2, 8, 7, 4, 1, 9, 6, 3, 5},
     {3, 4, 5, 2, 8, 6, 1, 7, 9}};
     
  
  private int[][] intBoard;
  private int[][] intBoardCopy;
     
  @SuppressWarnings("static-access")
  public SudokuGenerator(int rows, int cols, SudokuBoard board, String level) {
    this.board = board;
    this.rows = board.rows();
    this.cols = board.cols();
    this.level = level;
  }

  public SudokuBoard generateBoard() {
    Random random = new Random();
    randomInt = random.nextInt(10000) + 1;
    System.out.println("randInt: " + randomInt);
    System.out.println("level: " + level);

    try {
      intBoard = readSudokuFromFile(level, randomInt);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    fillBoard(board, intBoard);
    //fillBoard(board, incompleteValidTestBoard);

    System.out.println(board);
    return board;
  }
  
  public SudokuBoard copyBoard() {
    fillBoard(boardCopy, intBoard);
    return boardCopy;
  }
  
  public static void fillBoard(SudokuBoard board, int[][] filledBoard) {
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        int number = filledBoard[row][col];
        CellPosition pos = new CellPosition(row, col);
        board.set(pos, number);
      }
    }
  }

  private static int[][] readSudokuFromFile(String filename, int num) throws FileNotFoundException, IOException {
    String filePath = "/Users/ingvild/UiB/INF101/sem2/Ingvild.H.Hope_tetris/src/main/java/no/uib/inf101/sudoku/model/" + filename
        + ".txt";
   
    try (LineNumberReader lnr = new LineNumberReader(new FileReader(filePath))) {
      StringBuilder sb1 = new StringBuilder();
      for (String line = null; (line = lnr.readLine()) != null;) {
        if (lnr.getLineNumber() == num) {
          sb1.append(line).append(File.pathSeparatorChar);
          sudokuAsLine = sb1;
        }
      }
      System.out.println("liness: " + sb1);
    }
  
    System.out.println("linett: " + sudokuAsLine);
    int[][] sudoku = new int[rows][cols];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        char c = sudokuAsLine.charAt(i * rows + j);
        sudoku[i][j] = Character.getNumericValue(c);
      }
    }

    return sudoku;
  }
}
