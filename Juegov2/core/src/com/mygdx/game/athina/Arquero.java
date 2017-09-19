package com.mygdx.game.athina;

import java.util.UUID;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.athina.Entity.Direction;

public class Arquero extends Heroe {
	// defaultSpritePath
// CONSTANTS
//	public static float walkingSpeed = 0.15f;
//	//static float walkingSpeed = 0.25f;
//	public static float shootingSpeed = 0.05f;
//	//offset in pixels
//	public static int xOffset = 15;
//	public static int yOffset = 0;
//	//percentage reduced of from the original tiled to the walking real width and height
//	public static float percenXReduced = 0.5f;
//	public static float percenYReduced = 0.7f;
//	public Arquero() {
//		super();
//	}
//
//	@Override
//	public void initEntity() {
//		super.initEntity();
//		defaultSpritePath = "images/archer_orc.png";
//		Utility.loadTextureAsset(defaultSpritePath);
//		loadDefaultSprite();
//		loadAllAnimations();
//
//	}
//
//	public void update(float delta) {
//		frameTime = (frameTime + delta) % 5;// Want to avoid overflow
//		// cada 5 sec se resetea
//		setBoundingBoxSize(percenXReduced, percenYReduced);
//	
//		
//	}
//
//	
//
//	/**
//	 * Customizes the bounding box
//	 * */
//	
//	public void setBoundingBoxSize(float percentageWidthReduced,
//			float percentageHeightReduced) {
//		// Update the current bounding box
//		float width;
//		float height;
//		float widthReductionAmount = 1.0f - percentageWidthReduced;
//		// .8f for 20% (1 - .20)
//		float heightReductionAmount = 1.0f - percentageHeightReduced;
//		// .8f for 20% (1 - .20)
//		if (widthReductionAmount > 0 && widthReductionAmount < 1) {
//			width = FRAME_WALK_WIDTH * widthReductionAmount;
//		} else {
//			width = FRAME_WALK_WIDTH;
//		}
//		if (heightReductionAmount > 0 && heightReductionAmount < 1) {
//			height = FRAME_WALK_HEIGHT * heightReductionAmount;
//		} else {
//			height = FRAME_WALK_HEIGHT;
//		}
//		if (width == 0 || height == 0) {
//			Gdx.app.debug(TAG, "Width and Height are 0!! " + width + ":"
//					+ height);
//		}
//		// Need to account for the unitscale, since the map coordinates
//		// will be in pixels
//		float minX;
//		float minY;
//		
//		if (MapManager.UNIT_SCALE > 0) {
//			//lets try to add offset	
//			minX = (nextPlayerPosition.x / MapManager.UNIT_SCALE)+xOffset;
//			minY = (nextPlayerPosition.y / MapManager.UNIT_SCALE)+yOffset;
//			
//			//minX = nextPlayerPosition.x / MapManager.UNIT_SCALE;
//			//minY = nextPlayerPosition.y / MapManager.UNIT_SCALE;
//		} else {
//			minX = nextPlayerPosition.x;
//			minY = nextPlayerPosition.y;
//		}
//		boundingBox.set(minX, minY, width, height);
//	}
//
//	protected void loadDefaultSprite() {
//
//		Texture texture = Utility.getTextureAsset(defaultSpritePath);
//		TextureRegion[][] textureFrames = TextureRegion.split(texture,
//				FRAME_WALK_WIDTH, FRAME_WALK_HEIGHT);
//		frameSprite = new Sprite(textureFrames[0][0].getTexture(), 0, 0,
//				FRAME_WALK_WIDTH, FRAME_WALK_HEIGHT);
//
//		currentFrame = textureFrames[0][0];
//	}
//
//	public void loadAllAnimations() {
//
//		// load texture
//		Texture texture = Utility.getTextureAsset(defaultSpritePath);
//		//TextureRegion tRegionAnimation = new TextureRegion(texture);
//		TextureRegion[][] arrayTextures = TextureRegion.split(texture,
//				FRAME_WALK_WIDTH, FRAME_WALK_HEIGHT);
//
//		// animations walking
//		/**
//		Array<TextureRegion> walkUpTRegion = Utility.getFilaArrayTextRegion(8,
//				9, arrayTextures);
//		Array<TextureRegion> walkDownTRegion = Utility.getFilaArrayTextRegion(
//				10, 9, arrayTextures);
//		Array<TextureRegion> walkRightTRegion = Utility.getFilaArrayTextRegion(
//				11, 9, arrayTextures);
//		Array<TextureRegion> walkLeftTRegion = Utility.getFilaArrayTextRegion(
//				9, 9, arrayTextures);
//*/
//		TextureRegion[] walkUpTRegion = Auxiliar.getFilaArrayTextRegion(8,9,arrayTextures);
//		TextureRegion[] walkDownTRegion = Auxiliar.getFilaArrayTextRegion(10,9,arrayTextures);
//		TextureRegion[] walkRightTRegion = Auxiliar.getFilaArrayTextRegion(11,9,arrayTextures);
//		TextureRegion[] walkLeftTRegion = Auxiliar.getFilaArrayTextRegion(9,9,arrayTextures);
//		
//		walkUpAnimation = new Animation(walkingSpeed, walkUpTRegion);
//		//walkUpAnimation.setFrameDuration(walkingSpeed);
//		//walkUpAnimation = new Animation()
//		walkDownAnimation = new Animation(walkingSpeed, walkDownTRegion);
//		//walkDownAnimation.setFrameDuration(walkingSpeed);
//		//System.out.println(walkDownAnimation.getFrameDuration());
//		walkRightAnimation = new Animation(walkingSpeed, walkRightTRegion);
//		walkLeftAnimation = new Animation(walkingSpeed, walkLeftTRegion);
//
//	}
	

}
