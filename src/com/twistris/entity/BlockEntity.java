package com.twistris.entity;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
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
		Vector2[] offsets = shape.getOffsets();
		for(int i=0; i<offsets.length; i++){
			this.tiles.add(new TileEntity((int)offsets[i].x, (int)offsets[i].y, "graphics/tile.png", color));
		}
	}
	
	@Override
	public void begin() {
		super.begin();
		for(int i=0; i<this.tiles.size(); i++){
			this.state.addEntity(this.tiles.get(i));
		}
	}
	
	public boolean checkCollision(int xShift, int yShift, MapEntity e){
		for(int i=0; i<this.tiles.size(); i++){
			Vector2 p = new Vector2(
					this.x + xShift + this.tiles.get(i).xOffset, 
					this.y + yShift + this.tiles.get(i).yOffset);
			if(e.checkCollision((int)p.x, (int)p.y)){
				return true;
			}
		}
		return false;
	}
	
	public void move(int xDir, int yDir, MapEntity e){
		if(!this.checkCollision(xDir, yDir, e)){
			this.x += xDir;
			this.y += yDir;
		}
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
