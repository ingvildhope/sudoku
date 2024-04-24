package no.uib.inf101.sudoku.view;

import java.awt.Color;

/**
 * Defines the color theme for the game.
 */
public interface ColorTheme {

  /**
   * Returns the color associated with the given integer. 
   * 
   * @return the color of the cell.
   */
  Color getSTDCellColor();

  /**
   * Returns the cell color of the selected cell.
   * 
   * @return the color of the cell.
   */
  Color getSelectedCellColor();

  /**
   * Returns the cell color of cells with the same value as the selected cell.
   * 
   * @return the color of the cell.
   */
  Color getSameValueCellColor();
  
  /**
   * Returns the background color.
   * 
   * @return the background color of the game.
   */
  Color getBackgroundColor();

  /**
   * Returns the color of the box when the game is finished.
   * 
   * @return the color of the game finished box.
   */
  Color getGameFinishedColor();

  /**
   * Returns the color of the border around the box.
   * 
   * @return the color of the boarder.
   */
  Color getBorderColor();

  /**
   * Returns the color of the welcome screen.
   * 
   * @return the color of the welcome screen.
   */
  Color getWelcomeColor();

  /**
   * Returns the color of the original numbers in the cells.
   * 
   * @return the color of the original numbers in the cells.
   */
  Color getOriginalNumColor();

  /**
   * Returns the color of the typed numbers in the cells.
   * 
   * @return the color of the typed numbers in the cells.
   */
  Color getInputNumColor();

  /**
   * Returns the color of a wrong input number in the cell.
   * 
   * @return the color of the incorrect input.
   */
  Color getWrongIntColor();
}
