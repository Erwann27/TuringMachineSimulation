package org.noopi.gui;

import javax.swing.JFrame;

import org.noopi.model.tape.ITape;
import org.noopi.utils.IDatabase;
import org.noopi.utils.State;
import org.noopi.utils.StateDatabase;
import org.noopi.utils.Symbol;
import org.noopi.utils.SymbolDatabase;
import org.noopi.utils.events.view.ElementAddedEvent;
import org.noopi.utils.events.view.ElementRemovedEvent;
import org.noopi.utils.exceptions.DatabaseDuplicateException;
import org.noopi.utils.exceptions.DatabaseMissingEntryException;
import org.noopi.utils.listeners.view.ElementAddedEventListener;
import org.noopi.utils.listeners.view.ElementRemovedEventListener;
import org.noopi.model.history.ITransitionHistory;
import org.noopi.model.machine.ITuringMachine;
import org.noopi.view.FrameLayout;
import org.noopi.view.IFrameLayout;

public final class Window {

  // Model
  private ITuringMachine machine;
  private ITape tape;
  private ITransitionHistory history;
  private SymbolDatabase symbols;
  private StateDatabase states;


  // View
  private JFrame frame;
  private IFrameLayout layout;

  public Window() {
    createModel();
    createView();
    placeComponents();
    createController();
  }

  public void display() {
    refreshView();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.pack();
  }

  private void createModel() {
    symbols = new SymbolDatabase();
    states = new StateDatabase();
  }

  private void createView() {
    frame = new JFrame();
    layout = new FrameLayout(
      symbols.toReadable(),
      states.toReadable()
    );
  }

  private void placeComponents() {
    frame.setContentPane(layout.getView());
    frame.setJMenuBar(layout.getMenuBar());
  }

  private void createController() {
    layout.addSymbolRegisteredEventListener(new ElementAddedEventListener() {
      @Override
      public void onElementAdded(ElementAddedEvent e) {
        try {
          symbols.registerEntry(e.getElement());
        } catch (DatabaseDuplicateException e1) {
          // Should never happen
          e1.printStackTrace();
        }
      }
    });
    layout.addSymbolUnRegisteredEventListener(new ElementRemovedEventListener() {
      @Override
      public void onElementRemoved(ElementRemovedEvent e) {
        try {
          symbols.unregisterEntry(e.getElement());
        } catch (DatabaseMissingEntryException e1) {
          // Should mever happen
          e1.printStackTrace();
        }
      }
    });
  }

  private void refreshView() {

  }
}
