package no.uib.inf101.sudoku.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Color;

import org.junit.jupiter.api.Test;

public class DefaultColorThemeTest {

  @Test
  public void sanityDefaultColorThemeTest() {
    ColorTheme colors = new DefaultColorTheme();
    assertEquals(Color.PINK.darker(), colors.getBackgroundColor());
    assertThrows(IllegalArgumentException.class, () -> colors.getCellColor('\n'));
  }
}