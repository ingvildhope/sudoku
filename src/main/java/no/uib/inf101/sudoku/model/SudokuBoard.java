package no.uib.inf101.sudoku.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Random;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;

public class SudokuBoard extends Grid<Integer>{
  private int rows;
  private int cols;
  private static int randomInt;
  private static StringBuilder sudokuAsLine = new StringBuilder();
  private int[][] intBoard;
  private SudokuBoard boardCopy;

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
     
  
  public SudokuBoard(int rows, int cols) {
    super(rows, cols, 0);
    this.rows = rows;
    this.cols = cols;
  }

  public SudokuBoard generateBoard(String level) {
    Random random = new Random();
    randomInt = random.nextInt(10000) + 1;
    System.out.println("randInt: " + randomInt);
    System.out.println("level: " + level);

    try {
      intBoard = readSudokuFromFile(level, rows, cols, randomInt);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.fillBoard(intBoard);
    //this.fillBoard(incompleteValidTestBoard);

    boardCopy = cloneBoard();
    System.out.println("boardCopy: " + boardCopy);
    
    System.out.println(this);
    return this;
  }

  public void fillBoard(int[][] filledBoard) {
    for (int row = 0; row < this.rows; row++) {
      for (int col = 0; col < this.cols; col++) {
        int number = filledBoard[row][col];
        CellPosition pos = new CellPosition(row, col);
        this.set(pos, number);
      }
    }
  }

  private static int[][] readSudokuFromFile(String fileLevel, int rows, int cols, int num) throws FileNotFoundException, IOException {
    String filePath = "/Users/ingvild/UiB/INF101/sem2/Ingvild.H.Hope_tetris/src/main/java/no/uib/inf101/sudoku/model/" + fileLevel
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

  public SudokuBoard cloneBoard() {
    SudokuBoard clone = new SudokuBoard(rows, cols);
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        CellPosition pos = new CellPosition(row, col);
        int value = this.get(pos);
        clone.set(pos, value);
      }
    }
    return clone;
  }

  public boolean isPosOriginal(CellPosition pos) {
    if (boardCopy.get(pos) == 0) {
      return false;
    }
    return true;
  }


  public String toString() {
    int colCount = 0;
    int rowCount = 0;
    String sb = "";
    for (int i = 0; i < 13; i++) {
      sb += '_';
    }
    sb += "\n";
    sb += '|';
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        colCount++;
        CellPosition pos = new CellPosition(row, col);
        sb += (get(pos));
        if (colCount % 3 == 0) {
          sb += "|";
        }
      }
      rowCount++;
      sb += "\n";
      if (rowCount % 3 == 0) {
        for (int i = 0; i < 13; i++) {
          sb += '_';
        }
        sb += "\n";
      }
      if (colCount % 3 == 0) {
        sb += "|";
      }
    }
    sb = sb.substring(0, sb.length() - 1);
    sb.trim();
    return sb.toString();
  }
  
  public boolean isValidSolution(SudokuBoard board) {
    if (!areBoxesValid(board)) {
      return false;
    }
    if (!areColsValid(board)) {
      return false;
    }
    if (!areRowsValid(board)) {
      return false;
    }
    System.out.println("Valid board found");
    return true;
  }
  
  private boolean areRowsValid(SudokuBoard board) {
    for (int row = 0; row < rows; row++) {
      int[] check = new int[rows + 1];

      for (int col = 0; col < cols; col++) {
        int val = board.get(new CellPosition(row, col));

        if ((check[val] != 0) && (val != 0)) {
          System.out.println("Invalid value at " + new CellPosition(row, col));
          return false;
        }
        check[val] += 1;
      }
    }
    return true;
  }

  private boolean areColsValid(SudokuBoard board) {
    for (int col = 0; col < cols; col++) {
      int[] check = new int[cols + 1];

      for (int row = 0; row < rows; row++) {
        int val = board.get(new CellPosition(row, col));

        if ((check[val] != 0) && (val != 0)) {
          System.out.println("Invalid value at " + new CellPosition(row, col));
          return false;
        }
        check[val] += 1;
      }
    }
    return true;
  }

  private boolean areBoxesValid(SudokuBoard board) {
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        int[] check = new int[rows + 1];

        if ((row % 3 == 0) && (col % 3 == 0)) {
          for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
              int val = board.get(new CellPosition(row + r, col + c));

              if ((check[val] != 0) && (val != 0)) {
                System.out.println("Invalid value at " + new CellPosition(row + r, col + c));
                return false;
              }
              check[val] += 1;
            }
          }
        }
      }
    }
    return true;
  }
  
  public boolean isBoardComplete(SudokuBoard board) {
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        int val = board.get(new CellPosition(row, col));

        if ((val < 1) || (val > rows)) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean isValidMove(CellPosition pos, SudokuBoard board) {
    if (isValidInRow(board, pos, board.get(pos)) && isValidInCol(board, pos, board.get(pos)) && isValidInBox(board, pos, board.get(pos))) {
      return true;
    }
    return false;
  }

  private boolean isValidInRow(SudokuBoard board, CellPosition pos, int val) {
    for (int col = 0; col < cols; col++) {
      CellPosition pos1 = new CellPosition(pos.row(), col);

      if (pos1.col() != pos.col()) {
        if (board.get(pos1) == val) {
          return false;
        } 
      }
    }
    return true;  
  }

  private boolean isValidInCol(SudokuBoard board, CellPosition pos, int val) {
    for (int row = 0; row < rows; row++) {
      CellPosition pos1 = new CellPosition(row, pos.col());

      if (pos1.row() != pos.row()) {
        if (board.get(pos1) == val) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean isValidInBox(SudokuBoard board, CellPosition pos, int val) {
    int boxStartRow = pos.row() - (pos.row() % 3);
    int boxStartCol = pos.col() - (pos.col() % 3);
    for (int r = boxStartRow; r < boxStartRow + 3; r++) {
      for (int c = boxStartCol; c < boxStartCol + 3; c++) {
        CellPosition pos1 = new CellPosition(r, c);

        if (!pos.equals(pos1)) {
          if (board.get(pos1) == val) {
            return false;
          }
        }
        /* 
        if (pos1.row() != pos.row()) {
          if (pos1.col() != pos.col()) {
            if (board.get(pos1) == val) {
              return false;
            }
          }
        }*/
      }
    }
    return true;
  }

  
}
