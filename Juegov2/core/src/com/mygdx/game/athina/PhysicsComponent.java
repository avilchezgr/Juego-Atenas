package com.mygdx.game.athina;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;




public abstract class PhysicsComponent implements Component {
	protected Vector2 nextEntityPosition;
	 private static final String TAG = PhysicsComponent.class.getSimpleName();
	public abstract void update(Entity entity, MapManager mapMgr, float delta);
	//...
	public Rectangle boundingBox;
	protected BoundingBoxLocation boundingBoxLocation;
	protected Json json;
	protected Vector2 velocity;
    protected Ray selectionRay;
    protected final float selectRayMaximumDistance =128.0f;
    protected Vector2 currentEntityPosition;
    protected Entity.Direction currentDirection;
    //cambiar el offsetx e y y meterlos en el json
    protected int offsetX = 0;
    protected int offsetY = 0;
	public static enum BoundingBoxLocation{
		BOTTOM_LEFT,
		BOTTOM_CENTER,
		CENTER,
	}
	
	PhysicsComponent(){
		 this.nextEntityPosition = new Vector2(0,0);
	        this.currentEntityPosition = new Vector2(0,0);
	        this.velocity = new Vector2(2f,2f);
	        this.boundingBox = new Rectangle();
	        this.json = new Json();
//	        this.tempEntities = new Array<Entity>();
	        boundingBoxLocation = BoundingBoxLocation.BOTTOM_LEFT;
	        selectionRay = new Ray(new Vector3(), new Vector3());
	}
	
	protected boolean isCollisionWithMapEntities(Entity entity, MapManager mapMgr){
		Array<Entity> entities = mapMgr.getCurrentMapEntities();
		boolean isCollisionWithMapEntities = false;
		for(Entity mapEntity: entities){
			//Check for testing against self
			if(mapEntity.equals(entity)){
				continue;
			}
			Rectangle targetRect = mapEntity.getCurrentBoundingBox();
			if(boundingBox.overlaps(targetRect)){
				//Collision
				entity.sendMessage(MESSAGE.COLLISION_WITH_ENTITY);
				isCollisionWithMapEntities = true;
				break;
			}
		}
		return isCollisionWithMapEntities;
	}
	/**    
	 * This method is similar to the previous but 
	 * this one is meant more of a convenience to test against
	 * two specific entities instead of an entire collection. 
	 * This method is primarily used to test wheter an NPC
	 * entity has collided with the player-character entity
	 * The player character entity is not included in the currently 
	 * loaded container of map entities since its lifetime persists 
	 * outside the current map
	 * */
	protected boolean isCollision(Entity entitySource, Entity entityTarget){
		boolean isCollisionWithMapEntities = false;
		
		if(entitySource.equals(entityTarget)){
			return false;
		}
		if(entitySource.getCurrentBoundingBox().overlaps(entityTarget.getCurrentBoundingBox())){
			//Collision
			entitySource.sendMessage(MESSAGE.COLLISION_WITH_ENTITY);
			isCollisionWithMapEntities = true;
		}
		return isCollisionWithMapEntities;
	}
	protected boolean isCollisionWithMapLayer(Entity entity, MapManager mapMgr){
		 MapLayer mapCollisionLayer =  mapMgr.getCollisionLayer();

	        if( mapCollisionLayer == null ){
	            return false;
	        }

	        Rectangle rectangle = null;

	        for( MapObject object: mapCollisionLayer.getObjects()){
	            if(object instanceof RectangleMapObject) {
	                rectangle = ((RectangleMapObject)object).getRectangle();
	                if( boundingBox.overlaps(rectangle) ){
	                    //Collision
	                    entity.sendMessage(MESSAGE.COLLISION_WITH_MAP);
	                    return true;
	                }
	            }
	        }

	        return false;
	}
	protected void setNextPositionToCurrent(Entity entity){
	    this.currentEntityPosition.x = nextEntityPosition.x;
        this.currentEntityPosition.y = nextEntityPosition.y;

        //Gdx.app.debug(TAG, "SETTING Current Position " + entity.getEntityConfig().getEntityID() + ": (" + _currentEntityPosition.x + "," + _currentEntityPosition.y + ")");
        entity.sendMessage(MESSAGE.CURRENT_POSITION, json.toJson(currentEntityPosition));
	}
	protected void calculateNextPosition(float deltaTime){
		 if( currentDirection == null ) return;

	        if( deltaTime > .7) return;

	        float testX = currentEntityPosition.x;
	        float testY = currentEntityPosition.y;

	        velocity.scl(deltaTime);

	        switch (currentDirection) {
	            case LEFT :
	                testX -=  velocity.x;
	                break;
	            case RIGHT :
	                testX += velocity.x;
	                break;
	            case UP :
	                testY += velocity.y;
	                break;
	            case DOWN :
	                testY -= velocity.y;
	                break;
	            default:
	                break;
	        }

	        nextEntityPosition.x = testX;
	        nextEntityPosition.y = testY;

	        //velocity
	        velocity.scl(1 / deltaTime);
	}
	
	/**
	 * boundingbox methods refactored and split into two separated methods
	 * initboundinbox is to be called from the specific entities constructor
	 * to set up and initialize the initial position and size of the 
	 * bounding box, we can specifiy the position of the bounding box 
	 * such as centering in the middle of the sprite or on the botton using
	 * BoundingBoxLocation parameter
	 */
	protected void initBoundingBox(float percentageWidthReduced,
			float percentageHeightReduced){
		//Update the current bounding box
		float width;
		float height;
		
		float origWidth = Entity.FRAME_WALK_WIDTH;
		float origHeight = Entity.FRAME_WALK_HEIGHT;
		//.8f for 20% (1-.20)
		float widthReductionAmount = 1.0f - percentageWidthReduced;
		//.8f for 20% (1 - .20)
		float heightReductionAmount = 1.0f - percentageHeightReduced;
		if(widthReductionAmount > 0 && widthReductionAmount < 1){
			width = Entity.FRAME_WALK_WIDTH * widthReductionAmount;
		}else{
			width = Entity.FRAME_WALK_WIDTH;
		}
		
		if(heightReductionAmount > 0 && heightReductionAmount <1){
			height = Entity.FRAME_WALK_HEIGHT * heightReductionAmount;
		}else{
				height = Entity.FRAME_WALK_HEIGHT;
			}
			if(width == 0 || height == 0){
				Gdx.app.debug(TAG, "Width and Height are 0!! "+width + ":"+ height);
			}
			//Need to account for the unitscale, since the map
			//coordinates will be in pixels
			float minX;
			float minY;
			
			if(Map.UNIT_SCALE > 0){
				minX = nextEntityPosition.x / Map.UNIT_SCALE;
				minY = nextEntityPosition.y / Map.UNIT_SCALE;
				
			}else{
				minX = nextEntityPosition.x;
				minY = nextEntityPosition.y;
			}
			
			boundingBox.setWidth(width);
			boundingBox.setHeight(height);
			
			switch(boundingBoxLocation){
				case BOTTOM_LEFT:
					boundingBox.set(minX + offsetX, minY + offsetY, width, height);
					break;
				case BOTTOM_CENTER:
					boundingBox.setCenter(minX + origWidth/2 , minY + origHeight/4 );
					break;
				case CENTER:
					boundingBox.setCenter(minX + origWidth/2, minY + origHeight/2);
					break;
					
			}
		
	}
	/**
	 * update the bounding box position on every frame update
	 * */
	protected void updateBoundingBoxPosition(Vector2 position){
		//Need to account for the unitscale, since the map
		//coordinates will be in pixels
		float minX;
		float minY;
		
		if(Map.UNIT_SCALE > 0){
			minX = position.x / Map.UNIT_SCALE;
			minY = position.y / Map.UNIT_SCALE;
			
		}else{
			minX = position.x;
			minY = position.y;
		}
		switch(boundingBoxLocation){
		case BOTTOM_LEFT:
			boundingBox.set(minX,minY,boundingBox.getWidth(),boundingBox.getHeight());
			break;
		case BOTTOM_CENTER:
			boundingBox.setCenter(minX + Entity.FRAME_WALK_WIDTH/2 + offsetX, minY + Entity.FRAME_WALK_HEIGHT/4 + offsetY);
			break;
		case CENTER:
			boundingBox.setCenter(minX + Entity.FRAME_WALK_WIDTH/2, minY + Entity.FRAME_WALK_HEIGHT/2);
			break;
			
		}
	}
	
}
