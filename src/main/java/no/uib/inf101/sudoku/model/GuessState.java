package no.uib.inf101.sudoku.model;

/**
 * Represents the possible states a guess can have.
 */
public enum GuessState {
  TO_GUESS,         // An empty cell, need to guess.
  WRONG_GUESS,      // A guess, but was wrong when checked.
  PENDING_GUESS,    // A guess, but hasn't been checked.
  CORRECT_GUESS     // Correct guess.
}
