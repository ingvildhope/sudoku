package no.uib.inf101.sudoku.view;

import no.uib.inf101.sudoku.model.GuessState;
//import javafx.scene.paint.Color;
import java.awt.Color;

/**
 * Defines the color theme for the game.
 */
public interface ColorTheme {

  /**
   * Returns the color associated with the given integer. 
   * 
   * @param a the number representing the cell.
   * @return the color of the cell.
   */
  Color getCellColor(int a);
  
  /**
   * Returns the background color.
   * 
   * @return the background color of the game.
   */
  Color getBackgroundColor();

  /**
   * Returns the color of the integer in a cell, depending on the GuessState.
   * @param state the GuessState of the integer typed in.
   * @return the color of the integer of the cell.
   */
  Color getIntColor(GuessState state);

  /**
   * Returns the color of the box when the game is finished.
   * 
   * @return The color of the game finished box.
   */
  Color getGameFinishedColor();

  /**
   * Returns the color of the border around the box.
   * 
   * @return The color of the boarder.
   */
  Color getBoarderColor();
}
