package com.mygdx.game.athina;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class EntityA {
//	protected String TAG = Entity.class.getSimpleName();
//	protected String defaultSpritePath;
//	protected Vector2 velocity;
//	protected String entityID;
//	protected Direction currentDirection = Direction.UP;
//	protected Direction previousDirection = Direction.UP;
//	
//	protected Animation walkLeftAnimation;
//	protected Animation walkRightAnimation;
//	protected Animation walkUpAnimation;
//	protected Animation walkDownAnimation;
//	
//	
//	protected Array<TextureRegion> walkLeftFrames;
//	protected Array<TextureRegion> walkRightFrames;
//	protected Array<TextureRegion> walkUpFrames;
//	protected Array<TextureRegion> walkDownFrames;
//	
//	protected Vector2 nextPlayerPosition;
//	protected Vector2 currentPlayerPosition;
//	protected State state;
//	protected float frameTime;
//	protected Sprite frameSprite = null;
//	protected TextureRegion currentFrame = null;
//	protected  int FRAME_WALK_WIDTH = 64;
//	protected int FRAME_WALK_HEIGHT = 64;
//	public static Rectangle boundingBox;
//	
//	public enum State {
//		IDLE, WALKING, SHOOTING,HITTING,CASTING
//	}
//	public enum Direction {
//		UP,RIGHT,DOWN,LEFT;
//	}
//	public Entity(){
//		initEntity();
//	}
//	/**
//	 * Inicializamos la Entity
//	 * */
//	public void initEntity(){
//		this.nextPlayerPosition = new Vector2();
//		this.currentPlayerPosition = new Vector2();
//		this.boundingBox = new Rectangle();
//	}
//	public abstract void loadAllAnimations();
//	public abstract void update(float delta);
//	
//	public void init(float startX, float startY) {
//		this.currentPlayerPosition.x = startX;
//		this.currentPlayerPosition.y = startY;
//		this.nextPlayerPosition.x = startX;
//		this.nextPlayerPosition.y = startY;
//	}
//	public void dispose(){
//		Utility.unloadAsset(defaultSpritePath);
//		
//	}
//	public void setState(State state){
//		this.state = state;
//	}
//	
//	public Sprite getFrameSprite(){
//		return frameSprite;
//	}
//	public TextureRegion getFrame(){
//		return currentFrame;
//	}
//	public Vector2 getCurrentPosition(){
//		return currentPlayerPosition;
//	}
//	public void setCurrentPosition(float currentPositionX, float currentPositionY){
//		frameSprite.setX(currentPositionX);
//		frameSprite.setY(currentPositionY);
//		this.currentPlayerPosition.x = currentPositionX;
//		this.currentPlayerPosition.y = currentPositionY;
//	}
//	
//	public void setNextPositionToCurrent(){
//		setCurrentPosition(nextPlayerPosition.x,
//		nextPlayerPosition.y);
//	}
//	
//	
//
//	public void calculateNextPosition(Direction currentDirection,
//			float deltaTime) {
//		float testX = currentPlayerPosition.x;
//		float testY = currentPlayerPosition.y;
//		velocity.scl(deltaTime);
//
//		switch (currentDirection) {
//		case LEFT:
//			testX -= velocity.x;
//			break;
//		case RIGHT:
//			testX += velocity.x;
//			break;
//		case UP:
//			testY += velocity.y;
//			break;
//		case DOWN:
//			testY -= velocity.y;
//			break;
//		default:
//			break;
//		}
//		nextPlayerPosition.x = testX;
//		nextPlayerPosition.y = testY;
//		// velocity
//		velocity.scl(1 / deltaTime);
//	}
//	public void setDirection(Direction direction, float deltaTime){
//		this.previousDirection = this.currentDirection;
//		this.currentDirection = direction;
//		
//		//Look into the appropiate variable when changing position
//		
//		switch(currentDirection){
//		case DOWN:
//			currentFrame = walkDownAnimation.getKeyFrame(frameTime,true);		
//			break;
//		case LEFT:
//			currentFrame = walkLeftAnimation.getKeyFrame(frameTime,true);		
//			break;
//		case UP:
//			currentFrame = walkUpAnimation.getKeyFrame(frameTime,true);
//			break;
//		case RIGHT:
//			currentFrame = walkRightAnimation.getKeyFrame(frameTime,true);
//			break;
//			default:
//				break;
//		}
//		
//		
//	}


}
