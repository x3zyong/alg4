package com.xzy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class TetrisComponent extends JComponent implements KeyEventPostProcessor {

	boolean[][] data;
	int currentShape;
	int posx, posy;
	ArrayList<GameListener> gameListeners;
	
	public TetrisComponent() {

		data = new boolean[Constants.HIGTH][Constants.GAME_WIDTH];
		gameListeners = new ArrayList<GameListener>();
		initData();
		setBounds(new Rectangle(2, 2, Constants.GAME_WIDTH_PIX, Constants.HIGTH_PIX));

		KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		kfm.addKeyEventPostProcessor(this);
		
	}

	private void initUI(Graphics g) {
		g.setColor(Color.BLACK);
		int initx = 2;
		int inity = 2;

		for (int i = 0; i <= Constants.GAME_WIDTH; i++) {
			g.drawLine(initx + i * Constants.UNIT_PIX, inity, 
					initx + i * Constants.UNIT_PIX,
					inity + Constants.HIGTH_PIX);
		}

		for (int i = 0; i <= Constants.HIGTH; i++) {
			g.drawLine(initx, inity + i * Constants.UNIT_PIX, 
					initx + Constants.GAME_WIDTH_PIX,
					inity + i * Constants.UNIT_PIX);
		}

		g.setColor(Color.GRAY);
		// draw shape
		for (int i = 0; i < Constants.HIGTH; i++)
			for (int j = 0; j < Constants.GAME_WIDTH; j++) {
				if (data[i][j]) {
					g.fillRect(initx + j * Constants.UNIT_PIX, inity + i * Constants.UNIT_PIX, 
							Constants.UNIT_PIX,	Constants.UNIT_PIX);
				}
			}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		initUI(g);
	}

	public void initData() {
		posx = 3;
		posy = 0;
		currentShape = Constants.getRadomShapeType();
		for (int i = 0; i < Constants.HIGTH; i++)
			for (int j = 0; j < Constants.GAME_WIDTH; j++) {
				data[i][j] = false;
			}
		refreshdata();
	}
	
	public void nextShape(){
		posx = 3;
		posy = 0;
		currentShape = Constants.getRadomShapeType();		
	}

	private void refreshdata() {
		boolean[][] shapedata = Constants.getShape(currentShape);
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				if (shapedata[i][j]) {
					data[posy + i][posx + j] = shapedata[i][j];
				}
			}
		
		//remove full row
		int rowCount =0;
		for (int i = 0; i < Constants.HIGTH; i++){
			boolean rowData = true;
			for (int j = 0; j < Constants.GAME_WIDTH; j++) {
				rowData = rowData && data[i][j] ;
			}
			if (rowData) {
				for(int m=i;m>=0;m--)
					for(int n=0;n< Constants.GAME_WIDTH; n++) {
						if(m==0) 
							data[m][n]=false;
						else 
						   data[m][n]=data[m-1][n];
					}
				rowCount++;
			}
		}
		
		if(rowCount>0) notifyGameListeners(rowCount);
		
	}

	private boolean canTurn() {
		boolean[][] currentshapedata = Constants.getShape(currentShape);
		int turnShape = Constants.getTurn(currentShape);
		boolean[][] turndata = Constants.getShape(turnShape);
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				if (currentshapedata[i][j])
					continue;
				if (posy + i > Constants.HIGTH - 1 || posy + i < 0)
					continue;
				if (posx + j > Constants.GAME_WIDTH - 1 || posx + j < 0)
					continue;
				if (turndata[i][j] && data[posy + i][posx + j])
					return false;
			}

		return true;
	}

	private boolean canLeft() {
		boolean[][] shapedata = Constants.getShape(currentShape);
		int currentShapeCol = Constants.getShapeLeftNotNullCol(currentShape);

		for (int i = 0; i < 4; i++) {
			if (posx + currentShapeCol - 1 < 0)
				return false;
			if (shapedata[i][currentShapeCol] && data[posy + i][posx + currentShapeCol - 1])
				return false;
		}
		return true;
	}

	private boolean canRight() {
		boolean[][] shapedata = Constants.getShape(currentShape);
		int currentShapeCol = Constants.getShapeRightNotNullCol(currentShape);

		for (int i = 0; i < 4; i++) {
			if (posx + currentShapeCol + 1 > Constants.GAME_WIDTH - 1)
				return false;
			if (shapedata[i][currentShapeCol] && data[posy + i][posx + currentShapeCol + 1])
				return false;
		}
		return true;
	}

	private boolean canDown() {
		boolean[][] shapedata = Constants.getShape(currentShape);
		int currentShapeRow = Constants.getShapeButtomNotNullCol(currentShape);
		for (int j = 0; j < 4; j++) {
			if (posy + currentShapeRow + 1 > Constants.HIGTH - 1)
				return false;
			if (shapedata[currentShapeRow][j] && data[posy + currentShapeRow + 1][posx + j])
				return false;
		}
		return true;
	}

	private void clearcurrentShape() {
		boolean[][] shapedata = Constants.getShape(currentShape);
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				if (shapedata[i][j])
					data[posy + i][posx + j] = false;
			}
	}

	private void doTurn() {
		if (canTurn()) {
			clearcurrentShape();
			currentShape = Constants.getTurn(currentShape);
			refreshdata();
		}
	}

	private void doLeft() {
		if (canLeft()) {
			clearcurrentShape();
			posx--;
			refreshdata();
		}
	}

	private void doRight() {
		if (canRight()) {
			clearcurrentShape();
			posx++;
			refreshdata();
		}
	}

	public boolean doDown() {
		if (canDown()) {
			clearcurrentShape();
			posy++;
			refreshdata();
			return true;
		}
		return false;
	}

	@Override
	public boolean postProcessKeyEvent(KeyEvent e) {
		// handle key event globally
		if (e.getID() != KeyEvent.KEY_PRESSED)
			return true;

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			doTurn();
			break;
		case KeyEvent.VK_DOWN:
			doDown();

			break;
		case KeyEvent.VK_LEFT:
			doLeft();
			break;
			
		case KeyEvent.VK_RIGHT:
			doRight();
			break;
		default:
			return true;
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				repaint();
			}
		});
		return true;
	}
	
	public void registerGameListener(GameListener listener){
		gameListeners.add(listener);
	}
	
	public void notifyGameListeners(int rowNum){
		for(GameListener listener:gameListeners){
			listener.removeRowAction(rowNum);
		}
	}
	
}
