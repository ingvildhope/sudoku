package no.uib.inf101.sudoku.view;

import no.uib.inf101.sudoku.model.GuessState;
//import javafx.scene.paint.Color;
import java.awt.Color;

public class DefaultColorTheme implements ColorTheme{

  @Override
  public Color getCellColor(int a) {
    Color color = switch(a) {
      case 0 -> Color.WHITE;
      default -> throw new IllegalArgumentException("No available  color for '" + a + "'");
    };
    return color;
  }

  @Override
  public Color getBackgroundColor() {
    Color color = Color.PINK.darker();
    return color;
  }

  @Override
  public Color getIntColor(GuessState state) {
    Color color = switch (state) {
      case TO_GUESS -> null;
      case WRONG_GUESS -> Color.RED;
      case PENDING_GUESS -> Color.BLUE.brighter();
      case CORRECT_GUESS -> Color.GREEN;
      default -> throw new IllegalArgumentException("No available  color for '" + state + "'");
    };
    return color;
  }
  
}
