package no.uib.inf101.sudoku.model;

/**
 * Represents the possible states of the Tetris game.
 * - ACTIVE_GAME: The game is active.
 * - GAME_OVER: The game has ended.
 * - WELCOME_SCREEN: The initial screen displayed when the game is 
 * launched or when restarting.
 * - PAUSE: The game is paused.
 */
public enum GameState {
  ACTIVE_GAME,
  GAME_FINISHED,
  WELCOME_SCREEN,
  PAUSE

}
