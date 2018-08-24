package com.rakaneth.chronicles;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.rakaneth.chronicles.ui.GameController;
import com.rakaneth.chronicles.ui.Terminal;
import com.rakaneth.chronicles.ui.screens.TitleScreen;

import lombok.Getter;

public class AppMain extends JFrame implements KeyListener {
  private static final long serialVersionUID = 8044344233873237057L;
  @Getter private GameController controller;
  private final int MAPW = 50;
  private final int MAPH = 30;
  private final int STATW = 30;
  private final int STATH = 30;
  private final int MSGW = 50;
  private final int MSGH = 10;
  private final int INFOW = 30;
  private final int INFOH = 10;

  public AppMain() {
    Terminal map = new Terminal(MAPW, MAPH);
    Terminal info = new Terminal(INFOW, INFOH);
    Terminal msgs = new Terminal(MSGW, MSGH);
    Terminal stats = new Terminal(STATW, STATH);
    controller = new GameController(map, stats, msgs, info);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GridBagLayout gb = new GridBagLayout();
    setLayout(gb);
    GridBagConstraints constraints = new GridBagConstraints();
    setPanel(map, gb, constraints, 0, 0);
    setPanel(stats, gb, constraints, 1, 0);
    setPanel(msgs, gb, constraints, 0, 1);
    setPanel(info, gb, constraints, 1, 1);
    addKeyListener(this);
    pack();
  }

  private void setPanel(Terminal panel, GridBagLayout gb,
      GridBagConstraints constraints, int x, int y) {
    constraints.gridx = x;
    constraints.gridy = y;
    gb.setConstraints(panel, constraints);
    add(panel);
  }

  public static void main(String[] args) {
    AppMain app = new AppMain();
    app.getController().register(new TitleScreen());
    app.startGame();
    app.setVisible(true);
  }

  public void startGame() {
    // TODO: Game initializing procedures
    controller.switchScreen("title");
  }

  public void repaint() {
    controller.refresh();
    super.repaint();
  }

  public void keyPressed(KeyEvent e) {
    controller.getScreen().handleKeys(e.getKeyCode(), e.isShiftDown());
    repaint();
  }

  public void keyReleased(KeyEvent arg0) {}

  public void keyTyped(KeyEvent arg0) {}
}
