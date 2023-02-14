package org.noopi.utils.machine;

public class Transition {
  private final State oldState;
  private final Symbol oldSymbol;
  private final Direction newDirection;
  private final State newState;
  private final Symbol newSymbol;

  public Transition(
    State oldState,
    Symbol oldSymbol,
    Direction newDirection,
    State newState,
    Symbol newSymbol
  ) {
    this.oldState = oldState;
    this.oldSymbol = oldSymbol;
    this.newDirection = newDirection;
    this.newState = newState;
    this.newSymbol = newSymbol;
  }

  public State getOldState() {
    return oldState;
  }

  public Symbol getOldSymbol() {
    return oldSymbol;
  }

  public Direction getNewDirection() {
    return newDirection;
  }

  public State getNewState() {
    return newState;
  }

  public Symbol getNewSymbol() {
    return newSymbol;
  }
}