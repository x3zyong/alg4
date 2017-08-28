package com.xzy;

import java.awt.BorderLayout;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainFrame {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
	
	private static void createAndShowGUI(){

		JFrame mainFrame = new JFrame();
		initMainFrame(mainFrame);
		
		JPanel helpPanel = new JPanel();
		JPanel scopePanel = new JPanel();
		
		
		initHelpPanel(helpPanel);
		initScopePanel(scopePanel);
		
		mainFrame.getContentPane().add(helpPanel,BorderLayout.WEST);
		mainFrame.getContentPane().add(new TetrisComponent(),BorderLayout.CENTER);
		mainFrame.getContentPane().add(scopePanel,BorderLayout.EAST);

		mainFrame.setVisible(true);
	}
	
	private static void initMainFrame(JFrame mainFrame){
		
		mainFrame.setTitle("Tetris");
		mainFrame.setSize(Constants.MAIN_WIDTH_PIX+4,Constants.HIGTH_PIX+4);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private static void initHelpPanel(JPanel panel){
		panel.setBounds(new Rectangle(2, 2, Constants.HELP_WIDTH_PIX,Constants.HIGTH_PIX));
		panel.add(new JLabel("turn !"));
	}

	private static void initScopePanel(JPanel panel){
		panel.setBounds(new Rectangle((Constants.HELP_WIDTH_PIX + Constants.GAME_WIDTH_PIX) + 2,  2, Constants.SCOPE_WIDTH_PIX,Constants.HIGTH_PIX));
		panel.add(new JLabel("scope"));
	}	
}
