package com.xzy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class TetrisComponent extends JComponent implements KeyListener{
	
	boolean[][] data;
	int currentShape;
	int posx,posy;

	public TetrisComponent(){

		posx = 3;
		posy = 0;
		data = new boolean[Constants.HIGTH][Constants.GAME_WIDTH];
		setBounds(new Rectangle(2, 2, Constants.GAME_WIDTH_PIX, Constants.HIGTH_PIX));
		addKeyListener(this);
	}

	private void initUI(Graphics g){
		g.setColor(Color.BLACK);
		int initx = 2 ;
		int inity = 2 ;
		
		for(int i=0;i<=Constants.GAME_WIDTH;i++){
			g.drawLine(initx+i*Constants.UNIT_PIX, inity, initx+i*Constants.UNIT_PIX, inity + Constants.HIGTH_PIX);
		}

		for(int i=0;i<=Constants.HIGTH;i++){
			g.drawLine(initx, inity + i * Constants.UNIT_PIX, 
					initx+ Constants.GAME_WIDTH_PIX, inity + i * Constants.UNIT_PIX);
		}
		
		//draw shape
		for(int i=0;i<Constants.HIGTH;i++)
			for(int j=0;j<Constants.GAME_WIDTH;j++){
				if(data[i][j]) {
					g.fillRect(initx + j * Constants.UNIT_PIX, inity + i *Constants.UNIT_PIX, 
							Constants.UNIT_PIX, Constants.UNIT_PIX);
				}
			}
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
        initUI(g);
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			if(canTurn()) doTurn();break;
		case KeyEvent.VK_DOWN:
			if(canDown()) doDown();break;
		case KeyEvent.VK_LEFT:
			if(canLeft()) doLeft();break;
		case KeyEvent.VK_RIGHT:
			if(canRight()) doRight();break;
		default:
			return;
		}
		refreshdata();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                repaint();
            }
        });		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void refreshdata(){
		boolean[][] shapedata = Constants.getShape(currentShape);
		for (int m = 0; m < 4; m++)
			for (int n = 0; n < 4; n++) {
				if (shapedata[m][n]) {
	                data[posx + m][posy + n] = shapedata[m][n];
				}
			}
	}
	

    
	private boolean canTurn(){
//		if(shapeType == Constants.)
		boolean[][] shapedata = Constants.getShape(currentShape);
		
		return false;
	}
	
	private boolean canLeft(){
		return false;
	}
	private boolean canRight(){
		return false;
	}
	private boolean canDown(){
		return false;
	}
	
	private void doTurn(){
		
	}
	
	private void doLeft(){
		posx--;
	}
	private void doRight(){
		posx++;
	}
	private void doDown(){
		posy++;
	}

	
}
