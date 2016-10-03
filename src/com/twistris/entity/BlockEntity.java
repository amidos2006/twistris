package com.twistris.entity;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.twistris.Global;
import com.twistris.data.BlockShape;

public class BlockEntity extends BaseEntity{
	public BlockShape shape;
	public int orientation;
	public ArrayList<TileEntity> tiles;
	
	public BlockEntity(int x, int y, BlockShape shape, Color color){
		this.x = x;
		this.y = y;
		this.orientation = 0;
		this.shape = shape;
		
		this.tiles = new ArrayList<TileEntity>();
		Vector2[] offsets = this.shape.getOffsets();
		for(int i=0; i<offsets.length; i++){
			this.tiles.add(new TileEntity((int)offsets[i].x, (int)offsets[i].y, "graphics/tile.png", color));
		}
	}
	
	private void changeTileOffsets(Vector2[] offsets){
		for(int i=0; i<offsets.length; i++){
			this.tiles.get(i).xOffset = (int)offsets[i].x;
			this.tiles.get(i).yOffset = (int)offsets[i].y;
		}
	}
	
	public int getHeight(){
		int minY = this.tiles.get(0).yOffset, maxY = this.tiles.get(0).yOffset;
		for(int i=0; i<this.tiles.size(); i++){
			if(minY > this.tiles.get(i).yOffset){
				minY = this.tiles.get(i).yOffset;
			}
			if(maxY < this.tiles.get(i).yOffset){
				maxY = this.tiles.get(i).yOffset;
			}
		}
		
		return maxY - minY + 1;
	}
	
	public int getWidth(){
		int minX = this.tiles.get(0).xOffset, maxX = this.tiles.get(0).xOffset;
		for(int i=0; i<this.tiles.size(); i++){
			if(minX > this.tiles.get(i).xOffset){
				minX = this.tiles.get(i).xOffset;
			}
			if(maxX < this.tiles.get(i).xOffset){
				maxX = this.tiles.get(i).xOffset;
			}
		}
		
		return maxX - minX + 1;
	}
	
	@Override
	public void begin() {
		super.begin();
		for(int i=0; i<this.tiles.size(); i++){
			this.state.addEntity(this.tiles.get(i));
			this.tiles.get(i).x = this.x;
			this.tiles.get(i).y = this.y;
		}
	}
	
	public boolean checkCollision(int xShift, int yShift, MapEntity e){
		for(int i=0; i<this.tiles.size(); i++){
			Vector2 p = new Vector2(
					xShift + this.tiles.get(i).xOffset, 
					yShift + this.tiles.get(i).yOffset);
			if(e.checkCollision(this.x + (int)p.x * Global.tileSize, 
					this.y + (int)p.y * Global.tileSize)){
				return true;
			}
		}
		return false;
	}
	
	public void move(int xDir, int yDir, MapEntity e){
		if(!this.checkCollision(xDir, yDir, e)){
			this.x += xDir * Global.tileSize;
			this.y += yDir * Global.tileSize;
		}
	}
	
	public void rotate(MapEntity e){
		Vector2[] oldOffsets = this.shape.rotate(this.orientation);
		Vector2[] newOffsets = this.shape.rotate((this.orientation + 90));
		this.changeTileOffsets(newOffsets);
		//TODO:check if can resolved if not
		this.changeTileOffsets(oldOffsets);
		//if succeed
		this.orientation = (this.orientation + 90)%360;
	}
	
	@Override
	public void update() {
		super.update();
		for(int i=0; i<this.tiles.size(); i++){
			this.tiles.get(i).x = this.x;
			this.tiles.get(i).y = this.y;
		}
	}
}
