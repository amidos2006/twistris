package com.twistris.state;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.twistris.Global;
import com.twistris.entity.BaseEntity;

public class BaseState {
	public ArrayList<BaseEntity> entities;
	
	private ArrayList<BaseEntity> added;
	private ArrayList<BaseEntity> removed;
	
	public BaseState(){
		this.entities = new ArrayList<BaseEntity>();
		this.added = new ArrayList<BaseEntity>();
		this.removed = new ArrayList<BaseEntity>();
	}
	
	public void begin(){
		this.addEntities();
		this.removeEntities();
		this.added.clear();
		this.removed.clear();
	}
	
	public void end(){
		this.entities.clear();
		this.added.clear();
		this.removed.clear();
	}
	
	public void addEntity(BaseEntity e){
		if(!this.added.contains(e)){
			this.added.add(e);
		}
	}
	
	public void removeEntity(BaseEntity e){
		if(!this.removed.contains(e)){
			e.isAlive = false;
			this.removed.add(e);
		}
	}
	
	private void addEntities(){
		for(int i=0; i<this.added.size(); i++){
			if(this.added.get(i).state == null){
				this.entities.add(this.added.get(i));
				this.added.get(i).state = this;
				this.added.get(i).begin();
			}
		}
	}
	
	private void removeEntities(){
		for(int i=0; i<this.removed.size(); i++){
			if(this.removed.get(i).state != null){
				this.entities.remove(this.removed.get(i));
				this.removed.get(i).end();
				this.removed.get(i).state = null;
			}
		}
	}
	
	public void update(){
		for(int i=0; i<this.entities.size(); i++){
			if(this.entities.get(i).isAlive){
				this.entities.get(i).update();
			}
		}
		
		this.addEntities();
		this.removeEntities();
		
		this.added.clear();
		this.removed.clear();
	}
	
	public void render(SpriteBatch batch){
		Color bkColor = Global.getColor(Global.backgroundColor);
		Gdx.gl.glClearColor(bkColor.r, bkColor.g, bkColor.b, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		this.entities.sort(null);
		
		batch.begin();
		for(int i=0; i<this.entities.size(); i++){
			this.entities.get(i).render(batch);
		}
		batch.end();
	}
}
