package no.uib.inf101.sudoku.view;

import java.awt.Color;

public class DefaultColorTheme implements ColorTheme {

  @Override
  public Color getSTDCellColor( ) {
    Color color = Color.WHITE;
    return color;
  }

  @Override
  public Color getBackgroundColor() {
    Color color = Color.decode("#72A0C1");
    return color;
  }

  @Override
  public Color getGameFinishedColor() {
    Color color = new Color(0, 0, 0, 180);
    return color;
  }

  @Override
  public Color getBorderColor() {
    Color color = Color.decode("#2E5894").darker();
    return color;
  }

  @Override
  public Color getWelcomeColor() {
    Color color = Color.decode("#2E5894");
    return color;
  }

  @Override
  public Color getOriginalNumColor() {
    Color color = Color.BLACK;
    return color;
  }

  @Override
  public Color getInputNumColor() {
    Color color = Color.decode("#1e5cd2");
    return color;
  }

  @Override
  public Color getWrongIntColor() {
    Color color = Color.RED;
    return color;
  }

  @Override
  public Color getSelectedCellColor() {
    Color color = Color.decode("#7CB9E8");
    return color;
  }

  @Override
  public Color getSameValueCellColor() {
    Color color = Color.decode("#BCD4E6");
    return color;
  }
}