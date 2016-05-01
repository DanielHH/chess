package se.liu.ida.danhe178.rical803.tddd78.schack.main;

import se.liu.ida.danhe178.rical803.tddd78.schack.actionlisteners.AIVAIListener;
import se.liu.ida.danhe178.rical803.tddd78.schack.actionlisteners.LoadListener;
import se.liu.ida.danhe178.rical803.tddd78.schack.actionlisteners.PVAIListener;
import se.liu.ida.danhe178.rical803.tddd78.schack.actionlisteners.PVPListener;
import se.liu.ida.danhe178.rical803.tddd78.schack.actionlisteners.PauseListener;
import se.liu.ida.danhe178.rical803.tddd78.schack.actionlisteners.QuitListener;
import se.liu.ida.danhe178.rical803.tddd78.schack.actionlisteners.ResetListener;
import se.liu.ida.danhe178.rical803.tddd78.schack.actionlisteners.SaveListener;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Contains the menu and all of the menu options.
 * Sets actionListeners and then sets an instance of JMenuBar on an instance of class ChessFrame.
 */
class Menus {
    private final JMenuBar menuBar = new JMenuBar();

    private final JMenu options = new JMenu("Options");
    private final JMenuItem load = new JMenuItem("Load board");
    private final JMenuItem save = new JMenuItem("Save board");
    private final JMenuItem reset = new JMenuItem("Reset");
    private final JMenuItem quit = new JMenuItem("Quit");

    private final JMenu mode = new JMenu("Mode");
    private final JRadioButtonMenuItem pvp = new JRadioButtonMenuItem("PvP");
    private final JRadioButtonMenuItem pvai = new JRadioButtonMenuItem("PvAI");
    private final JRadioButtonMenuItem aivai = new JRadioButtonMenuItem("AIvAI");
    private final JRadioButtonMenuItem pause = new JRadioButtonMenuItem("Pause");
    private final ButtonGroup whichMode = new ButtonGroup();

    private final ChessFrame chessFrame;

    Menus(ChessFrame frame) {
	chessFrame = frame;
    }

    void createMenus() { // begins the creation of the menu
	whichMode.add(pvp);
	whichMode.add(pvai);
	whichMode.add(aivai);
	whichMode.add(pause);

	options.add(load);
	options.add(save);
	options.add(reset);
	options.add(quit);
	menuBar.add(options);

	setListeners();

	mode.add(pvp);
	mode.add(pvai);
	mode.add(aivai);
	mode.add(pause);
	menuBar.add(mode);
	chessFrame.setJMenuBar(menuBar);
    }

    private void setListeners() { // sets all the listeners for the menu
	reset.addActionListener(new ResetListener(pvp, chessFrame));
	reset.setMnemonic(KeyEvent.VK_R);
	reset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));

	quit.addActionListener(new QuitListener(chessFrame));
	quit.setMnemonic(KeyEvent.VK_Q);
	quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));

	load.addActionListener(new LoadListener(chessFrame));
	load.setMnemonic(KeyEvent.VK_L);
	load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));

	save.addActionListener(new SaveListener(chessFrame.getBoard()));
	save.setMnemonic(KeyEvent.VK_S);
	save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));

	pvp.addActionListener(new PVPListener());
	pvp.setSelected(true);
	pvp.setMnemonic(KeyEvent.VK_P);
	pvp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));

	pvai.addActionListener(new PVAIListener());
	pvai.setMnemonic(KeyEvent.VK_V);
	pvai.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));

	aivai.addActionListener(new AIVAIListener());
	aivai.setMnemonic(KeyEvent.VK_A);
	aivai.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));

	pause.addActionListener(new PauseListener());
	pause.setMnemonic(KeyEvent.VK_P);
	pause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_MASK));
    }
}
