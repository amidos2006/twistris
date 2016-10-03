package com.twistris;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.twistris.state.GameplayState;

public class TwistrisGame extends ApplicationAdapter {
	SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		Global.initialize();
		Global.nextState = new GameplayState();
	}

	@Override
	public void render () {
		if(Global.currentState != null){
			Global.currentState.update();
			Global.currentState.render(batch);
		}
		
		if(Global.nextState != Global.currentState){
			if(Global.currentState != null){
				Global.currentState.end();
			}
			Global.currentState = Global.nextState;
			Global.currentState.begin();
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
