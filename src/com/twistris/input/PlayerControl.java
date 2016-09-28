package com.twistris.input;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.twistris.Global;

public class PlayerControl {
	private ArrayList<Integer> leftColor;
	private ArrayList<Integer> rightColor;
	private ArrayList<Integer> downColor;
	private ArrayList<Integer> rotateColor;
	
	private boolean leftDown;
	private boolean rightDown;
	private boolean downDown;
	
	public PlayerControl(){
		this.leftColor = new ArrayList<Integer>();
		this.rightColor = new ArrayList<Integer>();
		this.downColor = new ArrayList<Integer>();
		this.rotateColor = new ArrayList<Integer>();
		
		this.leftDown = false;
		this.rightDown = false;
		this.downDown = false;
	}
	
	private boolean checkDown(ArrayList<Integer> color){
		for(int i=0; i<color.size(); i++){
			if(!Gdx.input.isKeyPressed(Global.getKey(color.get(i)))){
				return false;
			}
		}
		
		return true;
	}
	
	private boolean checkPressed(ArrayList<Integer> color){
		boolean result = false;
		for(int i=0; i<color.size(); i++){
			if(Gdx.input.isKeyJustPressed(Global.getKey(color.get(i)))){
				result |= true;
			}
		}
		
		return result;
	}
	
	private void changeColor(ArrayList<Integer> color, int size){
		color.clear();
		for(int i=0; i<size; i++){
			color.add(Global.colors[Global.random.nextInt(Global.colors.length)]);
		}
	}
	
	public ButtonsResult update(){
		boolean leftTemp = checkDown(leftColor);
		boolean rightTemp = checkDown(rightColor);
		boolean downTemp = checkDown(downColor);
		boolean rotateTemp = checkPressed(rotateColor);
		
		if(leftDown && !leftTemp){
			changeColor(leftColor, Global.difficulty);
		}
		if(rightDown && !rightTemp){
			changeColor(rightColor, Global.difficulty);
		}
		if(downDown && !downTemp){
			changeColor(downColor, Global.difficulty);
		}
		if(rotateTemp){
			changeColor(rotateColor, Global.difficulty);
		}
		
		leftDown = leftTemp;
		rightDown = rightTemp;
		downDown = downTemp;
		
		ButtonsResult result = new ButtonsResult();
		if(leftDown){
			result.direction.x -= 1;
		}
		if(rightDown){
			result.direction.x += 1;
		}
		if(downDown){
			result.direction.y += 1;
		}
		if(rotateTemp){
			result.rotation = true;
		}
		
		return result;
	}
}
