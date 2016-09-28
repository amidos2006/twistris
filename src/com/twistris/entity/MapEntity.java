package com.twistris.entity;

public class MapEntity extends BaseEntity {
	public TileEntity[][] tiles;
	
	public MapEntity(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.tiles = new TileEntity[width][height];
	}
	
	public boolean checkCollision(int pX, int pY){
		int mX = pX - this.x;
		int mY = pY - this.y; 
		if(mX < 0 || mY < 0 || mX >= this.tiles[0].length || mY >= this.tiles.length){
			return true;
		}
		
		return this.tiles[mY][mX] != null;
	}
}
