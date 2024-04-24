package no.uib.inf101.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Grid<E> implements IGrid<E> {
  private E[][] grid;
  private int rows;
  private int cols;

  @SuppressWarnings("unchecked")
  public Grid(int rows, int cols) {

    this.grid = (E[][]) new Object[rows][cols];
  }


  public Grid(int rows, int cols, E defaultValue) {
    this(rows, cols);
    this.rows = rows;
    this.cols = cols;
    initializeBoard(rows, cols, defaultValue);
  }
  

private void initializeBoard(int rows, int cols, E value){
  for (int row = 0; row < rows; row++) {
    for (int col = 0; col < cols; col++) {
      CellPosition pos = new CellPosition(row, col);
      set(pos, value);
    }
  }
}

@Override
public void set(CellPosition pos, E value) {
  int row = pos.row();
  int col = pos.col();
  
  grid[row][col] = value;
}
  
  @Override
  public int rows() {
    return rows;
  }

  @Override
  public int cols() {
    return cols;
  }

  @Override
  public Iterator<GridCell<E>> iterator() {
    List<GridCell<E>> list = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        CellPosition pos = new CellPosition(i, j);
        E value = get(pos);
        GridCell<E> cell = new GridCell<>(pos, value);
        list.add(cell);
      }
    }
    return list.iterator();
  }  

  @Override
  public E get(CellPosition pos) {
    int row = pos.row();
    int col = pos.col();

    return grid[row][col];
  }

  @Override
  public boolean positionIsOnGrid(CellPosition pos) {
    boolean isWithinRowBound = pos.row() >= 0 && pos.row() < rows;
    boolean isWithinColBound = pos.col() >= 0 && pos.col() < cols;

    return isWithinRowBound && isWithinColBound;
  }
}
