package com.twistris.entity;

import java.util.ArrayList;

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
}
