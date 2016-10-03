package com.twistris.entity;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.twistris.Global;

public class MapEntity extends BaseEntity {
	public TileEntity[][] tiles;
	
	public MapEntity(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.tiles = new TileEntity[height][width];
		this.graphics = new Texture("graphics/tile.png");
		this.tint = Color.BLACK;
		this.scaleX = width;
		this.scaleY = height;
	}
	
	public int getWidth(){
		return tiles[0].length;
	}
	
	public int getHeight(){
		return tiles.length;
	}
	
	public Vector2 getIndexPosition(int pX, int pY){
		Vector2 p = new Vector2();
		p.x = (float)Math.floor((pX - this.x)/Global.tileSize);
		p.y = (float)Math.floor((pY - this.y)/Global.tileSize);
		return p;
	}
	
	public boolean checkCollision(int pX, int pY){
		Vector2 p = this.getIndexPosition(pX, pY);
		if(p.x < 0 || p.y < 0 || p.x >= this.getWidth() || p.y >= this.getHeight()){
			return true;
		}
		
		return this.tiles[(int)p.y][(int)p.x] != null;
	}
	
	public void transferBlock(BlockEntity block){
		for(int i=0; i<block.tiles.size(); i++){
			Vector2 p = this.getIndexPosition(block.tiles.get(i).x, block.tiles.get(i).y);
			this.tiles[(int)p.y + block.tiles.get(i).yOffset]
					[(int)p.x + block.tiles.get(i).xOffset] = block.tiles.get(i);
		}
		this.state.removeEntity(block);
	}
	
	public void checkForDeletion(){
		ArrayList<Integer> rows = new ArrayList<Integer>();
		for(int iy=this.tiles.length - 1; iy>=0; iy--){
			boolean destroyRow = true;
			for(int ix=0; ix<this.tiles[iy].length; ix++){
				if(this.tiles[iy][ix] != null){
					this.tiles[iy][ix].x = this.x;
					this.tiles[iy][ix].y = this.y;
					this.tiles[iy][ix].xOffset = ix;
					this.tiles[iy][ix].yOffset = iy;
				}
				else{
					destroyRow = false;
				}
			}
			if(destroyRow){
				rows.add(iy);
			}
		}
		
		this.deleteRows(rows);
	}
	
	private boolean deleteRows(ArrayList<Integer> rows){
		for(int i=0; i<rows.size(); i++){
			for(int ix=0; ix<this.tiles[rows.get(i)].length; ix++){
				TileEntity e = this.tiles[rows.get(i)][ix];
				this.tiles[rows.get(i)][ix] = null;
				this.state.removeEntity(e);
			}
			this.moveDown(rows.get(i));
		}
		
		return rows.size() > 0;
	}
	
	private void moveDown(int row){
		for(int iy=row-1; iy>=0; iy++){
			for(int ix=0; ix<this.tiles[iy].length; ix++){
				this.tiles[iy+1][ix] = this.tiles[iy][ix];
			}
		}
	}
	
	@Override
	public void update() {
		super.update();
	}
}
