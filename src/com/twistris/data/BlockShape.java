package com.twistris.data;

import com.badlogic.gdx.math.Vector2;

public enum BlockShape {
	Square,
	lShape,
	jShape,
	zShape,
	sShape,
	iShape,
	tShape;
	
	public Vector2[] getOffsets(){
		switch(this){
		case Square:
			return new Vector2[]{
					new Vector2(0,0), 
					new Vector2(1,0), 
					new Vector2(0,1), 
					new Vector2(1,1)};
		case lShape:
			return new Vector2[]{
					new Vector2(0,0), 
					new Vector2(0,1), 
					new Vector2(0,2), 
					new Vector2(1,2)};
		case jShape:
			return new Vector2[]{
					new Vector2(1,0), 
					new Vector2(1,1), 
					new Vector2(1,2), 
					new Vector2(0,2)};
		case iShape:
			return new Vector2[]{
					new Vector2(0,0), 
					new Vector2(0,1), 
					new Vector2(0,2), 
					new Vector2(0,3)};
		case tShape:
			return new Vector2[]{
					new Vector2(0,0), 
					new Vector2(1,0), 
					new Vector2(2,0), 
					new Vector2(1,1)};
		case sShape:
			return new Vector2[]{
					new Vector2(1,0), 
					new Vector2(2,0), 
					new Vector2(0,1), 
					new Vector2(1,1)};
		case zShape:
			return new Vector2[]{
					new Vector2(0,0), 
					new Vector2(1,0), 
					new Vector2(1,1), 
					new Vector2(2,1)};
		default:
			return new Vector2[]{};
		}
	}
}
