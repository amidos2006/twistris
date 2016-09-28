package com.twistris;

import java.util.Random;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.twistris.state.BaseState;

public class Global {
	public static int backgroundColor;
	public static int[] colors;
	public static int[] keys;
	public static int difficulty;
	public static Random random;
	public static BaseState currentState;
	public static int tileSize;
	
	public static void initialize(){
		backgroundColor = 0x000000;
		colors = new int[]{0x56f442, 0x424ef4, 0xaa42f4, 0xf4ee42, 
				0xf4a742, 0xf45942, 0xffffff, 0x000000};
		keys = new int[]{Input.Keys.Q, Input.Keys.W, Input.Keys.E, Input.Keys.A, 
				Input.Keys.S, Input.Keys.D, Input.Keys.Z, Input.Keys.X};
		difficulty = 1;
		random = new Random();
		currentState = null;
		tileSize = 32;
	}
	
	public static int getKey(int color){
		for(int i=0; i<colors.length; i++){
			if(colors[i] == color){
				return keys[i];
			}
		}
		
		return -1;
	}
	
	public static Color getColor(int color){
		Color result = new Color();
		result.set(color << 8 | 0xff);
		return result;
	}
}
