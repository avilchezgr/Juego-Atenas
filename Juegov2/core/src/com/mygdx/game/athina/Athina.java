package com.mygdx.game.athina;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Athina extends Game {
	private SpriteBatch batch;
	Texture img;
	//protected Pantalla pantallaJuego;
	protected GameScreen mainGameScreen;
	@Override
	public void create () {
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		mainGameScreen = new MainGameScreen(this);
		setScreen(mainGameScreen);
	}

	
	@Override
	public void dispose(){
		
		mainGameScreen.dispose();
		
	}
}
