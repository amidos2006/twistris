package com.twistris.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TileEntity extends BaseEntity{
	public int xOffset;
	public int yOffset;
	
	public TileEntity(int xOffset, int yOffset, String tileImage, Color color){
		this.graphics.setColor(color);
		this.graphics.setTexture(new Texture(tileImage));
		
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	@Override
	public void render(SpriteBatch batch) {
		this.x += xOffset;
		this.y += yOffset;
		
		super.render(batch);
		
		this.x -= xOffset;
		this.y -= yOffset;
	}
}
