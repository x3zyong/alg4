package com.xzy;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
		JPanel scorePanel = new JPanel();
		
		initHelpPanel(helpPanel);
		TetrisComponent tetrisComponent = TetrisManager.getInstance().getTetrisComponent();
		initScorePanel(scorePanel);
		
		mainFrame.getContentPane().add(helpPanel,BorderLayout.WEST);
		mainFrame.getContentPane().add(tetrisComponent,BorderLayout.CENTER);
		mainFrame.getContentPane().add(scorePanel,BorderLayout.EAST);
		
		mainFrame.setVisible(true);
	}
	
	private static void initMainFrame(JFrame mainFrame){
		
		mainFrame.setTitle("Tetris");
		mainFrame.setSize(Constants.MAIN_WIDTH_PIX+4,Constants.HIGTH_PIX+4+50);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private static void initHelpPanel(JPanel panel){
		panel.setBounds(new Rectangle(2, 2, Constants.HELP_WIDTH_PIX,Constants.HIGTH_PIX));
		panel.add(new JLabel("turn !"));
	}

	private static void initScorePanel(JPanel panel){
		panel.setBounds(new Rectangle((Constants.HELP_WIDTH_PIX + Constants.GAME_WIDTH_PIX) + 2,  
				2, Constants.SCOPE_WIDTH_PIX,Constants.HIGTH_PIX));
		panel.setLayout(new GridLayout(4, 1));
		panel.add(new JLabel("score"));
		panel.add(new JLabel("acture score"));
		JLabel scoreLabel = new JLabel("0");
		panel.add(scoreLabel);
		TetrisManager.getInstance().setScoreLabel(scoreLabel);
		JButton startButton = new JButton("start");
		panel.add(startButton);
		
		startButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				TetrisManager.getInstance().startGame();
			}});
	}	
}
