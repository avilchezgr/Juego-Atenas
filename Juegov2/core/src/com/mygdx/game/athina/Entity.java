package com.mygdx.game.athina;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;


import java.util.ArrayList;

public class Entity {
	private static final String TAG = Entity.class.getSimpleName();
	private Json json;
	private EntityConfig entityConfig;
	public static int FRAME_WALK_HEIGHT = 64;
	public static int FRAME_WALK_WIDTH = 64;
	public static enum Direction {
		UP, RIGHT, DOWN, LEFT;
		static public Direction getRandomNext() {
			return Direction.values()[MathUtils
					.random(Direction.values().length - 1)];
		}

		public Direction getOpposite() {
			Direction dir = UP;
			if (this == LEFT) {
				dir = RIGHT;
			} else if (this == RIGHT) {
				dir = LEFT;
			} else if (this == UP) {
				dir = DOWN;
			} else if (this == DOWN) {
				dir = UP;
			}
			return dir;
		}
	}

	public static enum State {
		IDLE, WALKING,

		IMMOBILE;// This should always be last
		static public State getRandomNext() {
			// Ignore IMMOBILE which should be last state
			return State.values()[MathUtils.random(State.values().length - 2)];
		}
	}

	public static enum AnimationType {
		WALK_LEFT, WALK_RIGHT, WALK_UP, WALK_DOWN, IDLE, IMMOBILE
	}

	public static final int FRAME_WALKING_WIDTH = 64;
	public static final int FRAME_WALKING_HEIGHT = 64;
	
	private static final int MAX_COMPONENTS = 5;
	private Array<Component> components;

	private InputComponent inputComponent;
	private GraphicsComponent graphicsComponent;
	private PhysicsComponent physicsComponent;

	public Entity(InputComponent inputComponent,
			PhysicsComponent physicsComponent,
			GraphicsComponent graphicsComponent) {
		entityConfig = new EntityConfig();
		json = new Json();
		components = new Array<Component>(MAX_COMPONENTS);

		this.inputComponent = inputComponent;
		this.physicsComponent = physicsComponent;
		this.graphicsComponent = graphicsComponent;

		components.add(inputComponent);
		components.add(physicsComponent);
		components.add(graphicsComponent);

	}

	public EntityConfig getEntityConfig() {
		return entityConfig;
	}

	public void sendMessage(Component.MESSAGE messageType, String... args) {
		String fullMessage = messageType.toString();

		for (String string : args) {
			
			fullMessage += Component.MESSAGE_TOKEN + string;
		}
		for (Component component : components) {
			component.receiveMessage(fullMessage);
		}
	}

	public void update(MapManager mapMgr, Batch batch, float delta) {
		inputComponent.update(this, delta);
		physicsComponent.update(this ,mapMgr, delta);
		graphicsComponent.update(this, mapMgr, batch, delta);

	}
	public void dispose(){
		for(Component component: components){
			component.dispose();
		}
	}
	public Rectangle getCurrentBoundingBox(){
		return physicsComponent.boundingBox;
	}
	public void setEntityConfig(EntityConfig entityConfig){
		this.entityConfig = entityConfig;
	}
	static public EntityConfig getEntityConfig(String configFilePath){
		Json json = new Json();
		return json.fromJson(EntityConfig.class, Gdx.files.internal(configFilePath));
	}
	
	static public Array<EntityConfig> getEntityConfigs(String configFilePath){
		Json json = new Json();
		Array<EntityConfig> configs = new Array<EntityConfig>();
		ArrayList<JsonValue> list = json.fromJson(ArrayList.class, Gdx.files.internal(configFilePath));
		for (JsonValue jsonVal : list) {
			configs.add(json.readValue(EntityConfig.class,
			jsonVal));
			}
			return configs;
	}

	public static EntityConfig loadEntityConfigByPath(String entityConfigPath){
		EntityConfig entityConfig = Entity.getEntityConfig(entityConfigPath);
		EntityConfig serializedConfig = ProfileManager.getInstance().getProperty(entityConfig.getEntityID(), EntityConfig.class);

		if( serializedConfig == null ){
			return entityConfig;
		}else{
			return serializedConfig;
		}
	}
	public void updateInput(float delta){
		inputComponent.update(this, delta);
	}
	public InputProcessor getInputProcessor(){
		return inputComponent;
	}
	public static Entity initEntity(EntityConfig entityConfig){
		Json json = new Json();
		Entity entity = EntityFactory.getEntity(EntityFactory.EntityType.NPC);
		entity.setEntityConfig(entityConfig);

		entity.sendMessage(Component.MESSAGE.LOAD_ANIMATIONS, json.toJson(entity.getEntityConfig()));
		entity.sendMessage(Component.MESSAGE.INIT_START_POSITION, json.toJson(new Vector2(0,0)));
		entity.sendMessage(Component.MESSAGE.INIT_STATE, json.toJson(entity.getEntityConfig().getState()));
		entity.sendMessage(Component.MESSAGE.INIT_DIRECTION, json.toJson(entity.getEntityConfig().getDirection()));

		return entity;
	}
}
