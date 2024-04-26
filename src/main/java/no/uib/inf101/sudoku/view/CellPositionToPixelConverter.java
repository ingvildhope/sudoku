package no.uib.inf101.sudoku.view;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;

/**
 * Hentet fra tetris-rammeverket, men utvidet til å passe sudoku.
 * Hentet 05.04.24
 */
public class CellPositionToPixelConverter {

  private final Rectangle2D box;
  private final GridDimension gd;
  private final double margin;
  private double cellW;
  private double cellH;

  public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin) {
    this.box = box;
    this.gd = gd;
    this.margin = margin;

    this.cellW = (box.getWidth() - margin * gd.cols() - margin) / gd.cols();
    this.cellH = (box.getHeight() - margin * gd.rows() - margin) / gd.rows();
  }

  public Rectangle2D getBoundsForCell(CellPosition cellPosition) {
    double cellW = (box.getWidth() - margin * gd.cols() - margin) / gd.cols();
    double cellH = (box.getHeight() - margin * gd.rows() - margin) / gd.rows();
    double cellX = box.getX() + margin + (cellW + margin) * cellPosition.col();
    double cellY = box.getY() + margin + (cellH + margin) * cellPosition.row();
    return new Rectangle2D.Double(cellX, cellY, cellW, cellH);
  }

  /**
   * @author Torstein Strømme
   * Hentet fra clickablegrid (https://git.app.uib.no/ii/inf101/23v/students/clickablegrid.git) 10.04.24
   * 
   * Gets the CellPosition of the grid corresponding to the given (x, y)
   * coordinate. Returns null if the point does not correspond to a cell
   * in the grid.
   * 
   * @param point a non-null pixel location
   * @return the corresponding CellPosition, or null if none exist
   */
  public CellPosition getCellPositionOfPoint(Point2D point) {
    // Same math as getBoundsForCell, but isolate the col/row on one side
    // and replace cellX with point.getX() (cellY with point.getY())
    double col = (point.getX() - box.getX() - margin) / (cellW + margin);
    double row = (point.getY() - box.getY() - margin) / (cellH + margin);

    // When row or col is out of bounds
    if (row < 0 || row >= gd.rows() || col < 0 || col >= gd.cols()) return null;

    // Verify that the point is indeed inside the bounds of the cell, and not on
    // the margin border
    CellPosition pos = new CellPosition((int) row, (int) col);
    if (getBoundsForCell(pos).contains(point)) {
      return pos;
    } else {
      return null;
    }
  }

}
