package org.noopi.model.tape;

import org.noopi.utils.MachineAction;
import org.noopi.utils.Symbol;

public class TapeTester {

  private static final Symbol DEFAULT_SYMBOL = new Symbol("D");
  private static final Symbol TEST_SYMBOL = new Symbol("T");

  public static void main(String[] args) {
    ITape tape = new Tape(DEFAULT_SYMBOL);

    assert tape.readSymbol().equals(DEFAULT_SYMBOL);

    tape.writeSymbol(TEST_SYMBOL);
    assert tape.readSymbol().equals(TEST_SYMBOL);

    tape.shift(MachineAction.TAPE_RIGHT);
    assert tape.readSymbol().equals(DEFAULT_SYMBOL);

    tape.shift(MachineAction.TAPE_LEFT);
    assert tape.readSymbol().equals(TEST_SYMBOL);

    tape.shift(MachineAction.TAPE_LEFT);
    assert tape.readSymbol().equals(DEFAULT_SYMBOL);

    tape.reset(TEST_SYMBOL);
    assert tape.readSymbol().equals(TEST_SYMBOL);

    tape.shift(MachineAction.TAPE_LEFT);
    assert tape.readSymbol().equals(TEST_SYMBOL);

    Symbol[] testList = { TEST_SYMBOL, DEFAULT_SYMBOL, DEFAULT_SYMBOL, TEST_SYMBOL };
    tape.reset(DEFAULT_SYMBOL, testList);
    System.out.println("-----");
    for (Symbol s : tape.getSlice(5)) {
      System.out.print(s);
    }
    System.out.println("\n-----");

    for (Symbol s : testList) {
      assert tape.readSymbol().equals(s);
      tape.shift(MachineAction.TAPE_RIGHT);
    }

    System.out.println("yipee");
  }
}
