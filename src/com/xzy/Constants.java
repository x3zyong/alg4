package com.xzy;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class Constants {
	
	public final static int SCORE_UNIT = 100;
	public final static int UNIT_PIX=30;
	
	public final static int HELP_WIDTH=6;
	public final static int GAME_WIDTH=10;
	public final static int SCOPE_WIDTH=6;
	public final static int HIGTH=15;
	
	public final static int HELP_WIDTH_PIX = HELP_WIDTH * UNIT_PIX;
	public final static int GAME_WIDTH_PIX = GAME_WIDTH * UNIT_PIX;
	public final static int SCOPE_WIDTH_PIX = SCOPE_WIDTH * UNIT_PIX;
	public final static int HIGTH_PIX = HIGTH * UNIT_PIX;
	
	public final static int MAIN_WIDTH_PIX = (HELP_WIDTH+GAME_WIDTH+SCOPE_WIDTH) * UNIT_PIX;
	


	//
	public final static int SHAPETYPE_SQUARE_0 = 0;
	public final static int SHAPETYPE_RECT_0 = 1;
	public final static int SHAPETYPE_RECT_1 = 2;
	public final static int SHAPETYPE_RIGHT_PARALLELOGRAM_0 = 3;
	public final static int SHAPETYPE_RIGHT_PARALLELOGRAM_1 = 4;
	public final static int SHAPETYPE_LEFT_PARALLELOGRAM_0 = 5;
	public final static int SHAPETYPE_LEFT_PARALLELOGRAM_1 = 6;
	public final static int SHAPETYPE_TRIANGLE_0 = 7;
	public final static int SHAPETYPE_TRIANGLE_1 = 8;
	public final static int SHAPETYPE_TRIANGLE_2 = 9;
	public final static int SHAPETYPE_TRIANGLE_3 = 10;

    //
	public final static boolean[][] SQUARE_0={
			{false,false,false,false},
			{false,true,true,false},
			{false,true,true,false},
			{false,false,false,false},
	};
	public final static boolean[][] RECT_0={
			{false,false,false,false},
			{true,true,true,true},
			{false,false,false,false},
			{false,false,false,false},
	};
	public final static boolean[][] RECT_1={
			{false,true,false,false},
			{false,true,false,false},
			{false,true,false,false},
			{false,true,false,false},
	};
	
	public final static boolean[][] RIGHT_PARALLELOGRAM_0={
			{false,false,false,false},
			{false,true,true,false},
			{true,true,false,false},
			{false,false,false,false},
	};
	
	public final static boolean[][] RIGHT_PARALLELOGRAM_1={
			{false,true,false,false},
			{false,true,true,false},
			{false,false,true,false},
			{false,false,false,false},

	};
	
	public final static boolean[][] LEFT_PARALLELOGRAM_0={
			{true,true,false,false},
			{false,true,true,false},
			{false,false,false,false},
			{false,false,false,false},			

	};
	public final static boolean[][] LEFT_PARALLELOGRAM_1={
			{false,false,true,false},
			{false,true,true,false},
			{false,true,false,false},
			{false,false,false,false},				

	};
	public final static boolean[][] TRIANGLE_0={
			{false,true,false,false},
			{true,true,true,false},
			{false,false,false,false},	
			{false,false,false,false},	
	};
	public final static boolean[][] TRIANGLE_1={
			{false,true,false,false},
			{false,true,true,false},
			{false,true,false,false},
			{false,false,false,false},	
	};	
	public final static boolean[][] TRIANGLE_2={
			{true,true,true,false},
			{false,true,false,false},
			{false,false,false,false},	
			{false,false,false,false},	
	};
	public final static boolean[][] TRIANGLE_3={
			{false,true,false,false},
			{true,true,false,false},
			{false,true,false,false},
			{false,false,false,false},	
	};	
	
    
	
	public static HashMap<Integer,boolean[][]> shapeMap  = new HashMap<Integer,boolean[][]>();
	static {
	   shapeMap.put(SHAPETYPE_SQUARE_0,SQUARE_0);
	   shapeMap.put(SHAPETYPE_RECT_0,RECT_0);
	   shapeMap.put(SHAPETYPE_RECT_1,RECT_1);
	   shapeMap.put(SHAPETYPE_RIGHT_PARALLELOGRAM_0,RIGHT_PARALLELOGRAM_0);
	   shapeMap.put(SHAPETYPE_RIGHT_PARALLELOGRAM_1,RIGHT_PARALLELOGRAM_1);
	   shapeMap.put(SHAPETYPE_LEFT_PARALLELOGRAM_0,LEFT_PARALLELOGRAM_0);
	   shapeMap.put(SHAPETYPE_LEFT_PARALLELOGRAM_1,LEFT_PARALLELOGRAM_1);
	   shapeMap.put(SHAPETYPE_TRIANGLE_0,TRIANGLE_0);
	   shapeMap.put(SHAPETYPE_TRIANGLE_1,TRIANGLE_1);
	   shapeMap.put(SHAPETYPE_TRIANGLE_2,TRIANGLE_2);
	   shapeMap.put(SHAPETYPE_TRIANGLE_3,TRIANGLE_3);
	}
	
	public static boolean[][] getShape(int shapeType){
		if (!shapeMap.containsKey(shapeType)) 
			throw new NoSuchElementException("shapeType["+shapeType+"] is invalid");
		return shapeMap.get(shapeType);
	}
	
	public static int getTurn(int shapeType){
		switch(shapeType){
		
			case SHAPETYPE_SQUARE_0: return SHAPETYPE_SQUARE_0;
			case SHAPETYPE_RECT_0: return SHAPETYPE_RECT_1;
			case SHAPETYPE_RECT_1: return SHAPETYPE_RECT_0;
			
			case SHAPETYPE_RIGHT_PARALLELOGRAM_0: return SHAPETYPE_RIGHT_PARALLELOGRAM_1;
			case SHAPETYPE_RIGHT_PARALLELOGRAM_1: return SHAPETYPE_RIGHT_PARALLELOGRAM_0;
			
			case SHAPETYPE_LEFT_PARALLELOGRAM_0: return SHAPETYPE_LEFT_PARALLELOGRAM_1;
			case SHAPETYPE_LEFT_PARALLELOGRAM_1: return SHAPETYPE_LEFT_PARALLELOGRAM_0;
			
			case SHAPETYPE_TRIANGLE_0: return SHAPETYPE_TRIANGLE_1;
			case SHAPETYPE_TRIANGLE_1: return SHAPETYPE_TRIANGLE_2;
			case SHAPETYPE_TRIANGLE_2: return SHAPETYPE_TRIANGLE_3;
			case SHAPETYPE_TRIANGLE_3: return SHAPETYPE_TRIANGLE_0;
		
		}
		throw new RuntimeException("shapeType["+ shapeType + "] is invalid.");
	}
	public static int getRadomShapeType(){
		return (int)(System.currentTimeMillis()%11);
	}
	
	public static int getShapeLeftNotNullCol(int shapeType){
		boolean[][] shapedata = getShape(shapeType);
		for(int i=0;i<4;i++)
		    for(int j=0;j<4;j++){
			    if(shapedata[j][i]) return i;
		    }
		throw new RuntimeException("shapeType["+shapeType+"] is invalid");
	}
	
	public static int getShapeRightNotNullCol(int shapeType){
		boolean[][] shapedata = getShape(shapeType);
		for(int i=3;i>=0;i--)
		    for(int j=0;j<4;j++){
			    if(shapedata[j][i]) return i;
		    }
		throw new RuntimeException("shapeType["+shapeType+"] is invalid");
	}
	
	public static int getShapeButtomNotNullCol(int shapeType){
		boolean[][] shapedata = getShape(shapeType);
		for(int i=3;i>=0;i--)
		    for(int j=0;j<4;j++){
			    if(shapedata[i][j]) return i;
		    }
		throw new RuntimeException("shapeType["+shapeType+"] is invalid");
	}	
}
