package com.twistris.state;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.twistris.Global;
import com.twistris.data.BlockShape;
import com.twistris.entity.BlockEntity;
import com.twistris.entity.MapEntity;
import com.twistris.hud.InputHUD;
import com.twistris.input.ButtonsResult;
import com.twistris.input.PlayerControl;

public class GameplayState extends BaseState{
	public MapEntity[] maps;
	public BlockEntity[] block;
	public PlayerControl[] control;
	public Task movingDown;
	public Task controlUpdate;
	
	public GameplayState(){
		this.maps = new MapEntity[Global.players];
		this.block = new BlockEntity[Global.players];
		this.control = new PlayerControl[Global.players];
	}
	
	@Override
	public void begin() {
		for(int i=0; i<Global.players; i++){
			this.maps[i] = new MapEntity(10 + i * 394, 10, 12, 18);
			this.addEntity(this.maps[i]);
			this.block[i] = new BlockEntity((int)(this.maps[i].x + (this.maps[i].getWidth()/2 - 1) * Global.tileSize), 
					(int)(this.maps[i].y), BlockShape.getRandomShape(), Global.getRandomColor(Global.blockColors));
			this.addEntity(this.block[i]);
			this.control[i] = new PlayerControl(Global.difficulty);
			this.addEntity(new InputHUD(this.maps[i].x + this.maps[i].getWidth()/2 * Global.tileSize, 
					this.maps[i].y + (this.maps[i].getHeight() + 1) * Global.tileSize + 10, this.control[i]));
		}
		super.begin();
		
		movingDown = Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				for(int i=0; i<block.length; i++){
					if(!block[i].checkCollision(0, 1, maps[i])){
						block[i].move(0, 1, maps[i]);
					}
					else{
						maps[i].transferBlock(block[i]);
						block[i] = new BlockEntity((int)(maps[i].x + (maps[i].getWidth()/2 - 1) * Global.tileSize), 
								(int)(maps[i].y), BlockShape.getRandomShape(), Global.getRandomColor(Global.blockColors));
						addEntity(block[i]);
					}
				}
			}
		}, 0.5f, 0.5f);
		
		controlUpdate = Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				for(int i=0; i<control.length; i++){
					ButtonsResult b = control[i].update();
					block[i].move((int)b.direction.x, (int)b.direction.y, maps[i]);
				}
			}
		}, 0.1f, 0.1f);
	}
}
