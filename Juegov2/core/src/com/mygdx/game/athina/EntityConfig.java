package com.mygdx.game.athina;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.mygdx.game.athina.Entity.AnimationType;
import com.mygdx.game.athina.Entity.Direction;
import com.mygdx.game.athina.Entity.State;


public class EntityConfig {
	private Array<AnimationConfig> animationConfig;
	private State state;
	private Direction direction;
	private String entityID;
	private ObjectMap<String, String> entityProperties;
	
	EntityConfig(){
		 animationConfig = new Array<AnimationConfig>();
	       // inventory = new Array<ItemTypeID>();
	        entityProperties = new ObjectMap<String, String>();
	}
	EntityConfig(EntityConfig config){
        state = config.getState();
        direction = config.getDirection();
        entityID = config.getEntityID();
        
//        conversationConfigPath = config.getConversationConfigPath();
//        questConfigPath = config.getQuestConfigPath();
//        currentQuestID = config.getCurrentQuestID();
//        itemTypeID = config.getItemTypeID();

        animationConfig = new Array<AnimationConfig>();
        animationConfig.addAll(config.getAnimationConfig());
        System.out.println(config.entityProperties);
//        inventory = new Array<ItemTypeID>();
//        inventory.addAll(config.getInventory());

        entityProperties = new ObjectMap<String, String>();
        entityProperties.putAll(config.entityProperties);
    }
	public String getEntityID(){
		return entityID;
	}
	public void setEntityID(String entityID){
		this.entityID = entityID;
	}
	public Direction getDirection(){
		return direction;
	}
	public void setDirection(Direction direction){
		this.direction = direction;
	}
	public State getState(){
		return state;
	}
	public void setState(State state){
		this.state = state;
	}
	public Array<AnimationConfig> getAnimationConfig(){
		return animationConfig;
	}
	public void addAnimationConfig(AnimationConfig animationConfig){
		this.animationConfig.add(animationConfig);
	}
	static public class AnimationConfig{
        private float frameDuration = 1.0f;
        private AnimationType animationType;
        private Array<String> texturePaths;
        private Array<GridPoint2> gridPoints;

        public AnimationConfig(){
            animationType = AnimationType.IDLE;
            texturePaths = new Array<String>();
            gridPoints = new Array<GridPoint2>();
        }

        public float getFrameDuration() {
            return frameDuration;
        }

        public void setFrameDuration(float frameDuration) {
            this.frameDuration = frameDuration;
        }

        public Array<String> getTexturePaths() {
            return texturePaths;
        }

        public void setTexturePaths(Array<String> texturePaths) {
            this.texturePaths = texturePaths;
        }

        public Array<GridPoint2> getGridPoints() {
            return gridPoints;
        }

        public void setGridPoints(Array<GridPoint2> gridPoints) {
            this.gridPoints = gridPoints;
        }

        public AnimationType getAnimationType() {
            return animationType;
        }

        public void setAnimationType(AnimationType animationType) {
            this.animationType = animationType;
        }
    }
}
