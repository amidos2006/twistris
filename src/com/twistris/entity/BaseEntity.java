package com.twistris.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.twistris.Global;
import com.twistris.state.BaseState;

public class BaseEntity implements Comparable<BaseEntity>{
	public int x, y;
	public Sprite graphics;
	public String name;
	public int layer;
	public BaseState state;
	
	public BaseEntity(){
		this.x = 0;
		this.y = 0;
		this.graphics = new Sprite();
		this.name = "";
		this.layer = 0;
		this.state = null;
	}
	
	public void begin(){
		
	}
	
	public void end(){
		
	}
	
	public void update(){
		
	}
	
	public void render(SpriteBatch batch){
		this.graphics.setX(this.x * Global.tileSize);
		this.graphics.setY(this.y * Global.tileSize);
		
		this.graphics.draw(batch);
	}

	@Override
	public int compareTo(BaseEntity e) {
		return (int)Math.signum(this.layer - e.layer);
	}
}
