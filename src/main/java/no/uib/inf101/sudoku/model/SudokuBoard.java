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
  private static int boardNumber;
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

  /**
   * Generates a Sudoku board based on the specified difficulty level.
   * 
   * @param level the difficulty level of the Sudoku board to generate.
   * @return the generated Sudoku board.
   */
  public SudokuBoard generateBoard(String level) {
    Random random = new Random();
    boardNumber = random.nextInt(10000) + 1;
    System.out.println("boardNumber: " + boardNumber);
    System.out.println("level: " + level);

    try {
      intBoard = readSudokuFromFile(level, rows, cols, boardNumber);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.fillBoard(intBoard);
    //this.fillBoard(incompleteValidTestBoard);

    boardCopy = cloneBoard();
    
    System.out.println(this);
    return this;
  }

  /**
   * Fills the Sudoku board with values from the provided two-dimensional array.
   * 
   * @param filledBoard the two-dimensional array containing the values to fill the board with.
   */
  public void fillBoard(int[][] filledBoard) {
    for (int row = 0; row < this.rows; row++) {
      for (int col = 0; col < this.cols; col++) {
        int number = filledBoard[row][col];
        CellPosition pos = new CellPosition(row, col);
        this.set(pos, number);
      }
    }
  }

  /**
   * I have four files with 10 000 sudokuboards each, each file is level of difficulty. 
   * This method reads the sudoku at line number num from the file fileLevel, and returns
   * the sudoku as a two-dimensional array of Integers.
   * Files found at: https://www.printable-sudoku-puzzles.com/wfiles/
   * 
   * @param fileLevel which file to pick a sudoku from.
   * @param rows number of rows on the board.
   * @param cols number of cols on the board.
   * @param num which sudoku to read from the file, a random Integer between 0 and 10 000.
   * @return the sudoku from the file as a two-dimensional array of Integers.
   * @throws FileNotFoundException
   * @throws IOException
   */
  private static int[][] readSudokuFromFile(String fileLevel, int rows, int cols, int num) throws FileNotFoundException, IOException {
    String filePath = "src/main/java/no/uib/inf101/sudoku/model/data/" + fileLevel + ".txt";
  
    try (LineNumberReader lnr = new LineNumberReader(new FileReader(filePath))) {
      StringBuilder sb1 = new StringBuilder();
      for (String line = null; (line = lnr.readLine()) != null;) {
        if (lnr.getLineNumber() == num) {
          sb1.append(line).append(File.pathSeparatorChar);
          sudokuAsLine = sb1;
        }
      }
    }
    
    int[][] sudoku = new int[rows][cols];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        char c = sudokuAsLine.charAt(i * rows + j);
        sudoku[i][j] = Character.getNumericValue(c);
      }
    }

    return sudoku;
  }

  private SudokuBoard cloneBoard() {
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

  /**
   * Checks if the value at the specified position in the Sudoku board is original (is a given value).
   * 
   * @param pos the position to check.
   * @return true if the value is original, otherwise false.
   */
  public boolean isPosOriginal(CellPosition pos) {
    if (boardCopy.get(pos) == 0) {
      return false;
    }
    return true;
  }

  /**
   * Returns a string representation of the Sudoku board.
   * 
   * @return a string representing the Sudoku board.
   */
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
  
  /**
   * Checks if the given Sudoku board is a valid solution.
   * 
   * @param board the board to be checked.
   * @return true if the board is a valid solution, otherwise false.
   */
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
    //System.out.println("Valid board found");
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
  
  /**
   * Checks if the given Sudoku board is complete.
   * 
   * @param board the board to be checked.
   * @return true if the board is complete, otherwise false.
   */
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

  /**
   * Checks if placing a number in given position on the board is a valid move.
   * 
   * @param pos the position whose number to be checked.
   * @param board the board on which the position is.
   * @return true if the number is valid, otherwise false.
   */
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
      }
    }
    return true;
  }  
}
