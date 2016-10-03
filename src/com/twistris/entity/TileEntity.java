package com.twistris.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.twistris.Global;

public class TileEntity extends BaseEntity{
	public int xOffset;
	public int yOffset;
	
	public TileEntity(int xOffset, int yOffset, String tileImage, Color color){
		this.graphics = new Texture(tileImage);
		this.tint = color;
		
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	@Override
	public void end() {
		super.end();
	}
	
	@Override
	public void render(SpriteBatch batch) {
		this.x += xOffset * Global.tileSize;
		this.y += yOffset * Global.tileSize;
		
		super.render(batch);
		
		this.x -= xOffset * Global.tileSize;
		this.y -= yOffset * Global.tileSize;
	}
}
