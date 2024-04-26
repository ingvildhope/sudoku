package no.uib.inf101.sudoku.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;

/**
 * Hentet fra tetris-rammeverket, men utvidet til å passe sudoku.
 * Hentet 05.04.24
 */
public class DefaultColorThemeTest {

  @Test
  public void sanityDefaultColorThemeTest() {
    ColorTheme colors = new DefaultColorTheme();

    assertEquals(Color.decode("#72A0C1"), colors.getBackgroundColor());
    assertEquals(Color.RED, colors.getWrongIntColor());
    
    assertNotEquals(Color.BLUE, colors.getOriginalNumColor());
    assertNotEquals(Color.decode("#0053a8"), colors.getBorderColor());
  }
}