package com.xzy;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class TetrisManager implements GameListener{
	private int score;
	private boolean isRunning;
	private static TetrisComponent tetrisComponent;
	private static TetrisManager tetrisManager;
	private JLabel scoreLabel;
	
	public static TetrisManager getInstance(){
		if(tetrisManager == null){
			tetrisComponent = new TetrisComponent();
			tetrisManager = new TetrisManager();
			tetrisComponent.registerGameListener(tetrisManager);
		}
		
		return tetrisManager;
	}
	private TetrisManager(){
		initGameThread();
	}
	public TetrisComponent getTetrisComponent(){
		return tetrisComponent;
	}
	public void setScoreLabel(JLabel scoreLabel){
		this.scoreLabel = scoreLabel;
	}
	private void initGameThread(){
		new Thread(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					while(true){
						Thread.sleep(400);
						if(isRunning){
							if(!tetrisComponent.doDown()){
								if(!tetrisComponent.nextShape()) isRunning=false;
							}
							SwingUtilities.invokeLater(new Runnable() {
								public void run() {
									tetrisComponent.repaint();
								}
							});
						}
					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}}.start();
	}
	
	public void startGame(){

		tetrisComponent.initData();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                tetrisComponent.repaint();
            }
        });
		
		isRunning = true;
	}

	public void stopGame(){
		isRunning = false;
	}
	
	private int sum(int num){
		int total = 0;
		for(int i=1;i<=num;i++){
			total+=num;
		}
		return total;
	}
	
	private int calcScore(int rowNum){
		return sum(rowNum) * Constants.SCORE_UNIT;
	}
	@Override
	public void removeRowAction(int rowNum) {
		// TODO Auto-generated method stub
		score += calcScore(rowNum);
		scoreLabel.setText(String.valueOf(score));
	}

}
