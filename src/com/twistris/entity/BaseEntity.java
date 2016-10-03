package com.twistris.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.twistris.state.BaseState;

public class BaseEntity implements Comparable<BaseEntity>{
	public int x, y;
	public Color tint;
	public float scaleX, scaleY;
	public float originX, originY;
	public Texture graphics;
	public String name;
	public int layer;
	public BaseState state;
	public boolean isAlive;
	
	public BaseEntity(){
		this.x = 0;
		this.y = 0;
		this.tint = Color.WHITE;
		this.scaleX = 1;
		this.scaleY = 1;
		this.originX = 0;
		this.originY = 0;
		this.graphics = null;
		this.name = "";
		this.layer = 0;
		this.state = null;
	}
	
	public void begin(){
		this.isAlive = true;
	}
	
	public void end(){
		this.isAlive = false;
	}
	
	public void update(){
	}
	
	public void render(SpriteBatch batch){
		this.render(batch, 0, 0);
	}
	
	public void render(SpriteBatch batch, int sX, int sY){
		if(this.graphics != null && this.isAlive){
			batch.setColor(tint);
			batch.draw(graphics, this.x + sX + this.originX * this.graphics.getWidth() * scaleX, 
					Gdx.graphics.getHeight() - (this.y + sY + (1 - this.originY) * this.graphics.getHeight() * scaleY), 
					this.scaleX * this.graphics.getWidth(), this.scaleY * this.graphics.getHeight());
			batch.setColor(Color.WHITE);
		}
	}

	@Override
	public int compareTo(BaseEntity e) {
		return (int)Math.signum(this.layer - e.layer);
	}
}
