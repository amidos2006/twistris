package com.twistris.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.twistris.Global;
import com.twistris.entity.BaseEntity;
import com.twistris.input.PlayerControl;

public class InputHUD extends BaseEntity{
	private PlayerControl control;
	
	private Texture rotateSymbol;
	private Texture downSymbol;
	private Texture leftSymbol;
	private Texture rightSymbol;
	
	private Texture[] circleBack;
	
	public InputHUD(int x, int y, PlayerControl control){
		this.x = x;
		this.y = y;
		this.control = control;
		
		this.rotateSymbol = new Texture("graphics/upArrow.png");
		this.downSymbol = new Texture("graphics/downArrow.png");
		this.leftSymbol = new Texture("graphics/leftArrow.png");
		this.rightSymbol = new Texture("graphics/rightArrow.png");
		
		this.circleBack = new Texture[]{new Texture("graphics/leftHalf.png"), new Texture("graphics/rightHalf.png")};
	}
	
	@Override
	public void render(SpriteBatch batch) {
		for(int i=0; i<this.circleBack.length; i++){
			this.graphics = this.circleBack[i];
			this.tint = Global.getColor(this.control.rotateColor.get(i));
			super.render(batch, 0, -40);
		}
		this.tint = Global.getColor(Global.backgroundColor);
		this.graphics = rotateSymbol;
		super.render(batch, 0, -40);
		
		for(int i=0; i<this.circleBack.length; i++){
			this.graphics = this.circleBack[i];
			this.tint = Global.getColor(this.control.leftColor.get(i));
			super.render(batch, -40, 0);
		}
		this.tint = Global.getColor(Global.backgroundColor);
		this.graphics = leftSymbol;
		super.render(batch, -40, 0);
		
		for(int i=0; i<this.circleBack.length; i++){
			this.graphics = this.circleBack[i];
			this.tint = Global.getColor(this.control.rightColor.get(i));
			super.render(batch, 40, 0);
		}
		this.tint = Global.getColor(Global.backgroundColor);
		this.graphics = rightSymbol;
		super.render(batch, 40, 0);
		
		for(int i=0; i<this.circleBack.length; i++){
			this.graphics = this.circleBack[i];
			this.tint = Global.getColor(this.control.downColor.get(i));
			super.render(batch, 0, 0);
		}
		this.tint = Global.getColor(Global.backgroundColor);
		this.graphics = downSymbol;
		super.render(batch, 0, 0);
	}
}
