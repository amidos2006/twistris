package com.twistris.data;

import com.badlogic.gdx.math.Vector2;
import com.twistris.Global;

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
	
	public Vector2[] rotate(int angle){
		double[][] matrix = new double[][]{{Math.cos(Math.toRadians(angle)), -Math.sin(Math.toRadians(angle))},
											{Math.sin(Math.toRadians(angle)), Math.cos(Math.toRadians(angle))}};
		Vector2[] initial = this.getOffsets();
		Vector2[] result = this.getOffsets();
		for(int i=0; i<initial.length; i++){
			result[i].x = (float) Math.round(matrix[0][0] * initial[i].x + matrix[0][1] * initial[i].y);
			result[i].y = (float) Math.round(matrix[1][0] * initial[i].x + matrix[1][1] * initial[i].y);
		}
		double minX = result[0].x;
		double minY = result[0].y;
		for(int i=0; i<result.length; i++){
			if(result[i].x < minX){
				minX = result[i].x;
			}
			if(result[i].y < minY){
				minY = result[i].y;
			}
		}
		for(int i=0; i<result.length; i++){
			result[i].x -= minX;
			result[i].y -= minY;
		}
		
		return result;
	}

	public static BlockShape getRandomShape(){
		BlockShape[] values = BlockShape.values();
		return values[Global.random.nextInt(values.length)];
	}
}
