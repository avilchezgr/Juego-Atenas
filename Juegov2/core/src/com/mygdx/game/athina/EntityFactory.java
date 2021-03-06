package com.mygdx.game.athina;

import java.util.Hashtable;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;


public class EntityFactory {
	private static Json json = new Json();
	private static EntityFactory instance = null;
	public static enum EntityType{
		PLAYER,
		DEMO_PLAYER,
		NPC
	}
	
	 public static enum EntityName{
	        PLAYER_PUPPET,
	        TOWN_GUARD_WALKING,
	        TOWN_BLACKSMITH,
	        TOWN_MAGE,
	        TOWN_INNKEEPER,
	        TOWN_FOLK1, TOWN_FOLK2, TOWN_FOLK3, TOWN_FOLK4, TOWN_FOLK5,
	        TOWN_FOLK6, TOWN_FOLK7, TOWN_FOLK8, TOWN_FOLK9, TOWN_FOLK10,
	        TOWN_FOLK11, TOWN_FOLK12, TOWN_FOLK13, TOWN_FOLK14, TOWN_FOLK15,
	        FIRE
	    }
	
	
	public static String PLAYER_CONFIG = "scripts/player.json";
	 private Hashtable<String, EntityConfig> entities;
	    public static String TOWN_GUARD_WALKING_CONFIG = "scripts/town_guard_walking.json";
	    public static String TOWN_BLACKSMITH_CONFIG = "scripts/town_blacksmith.json";
	    public static String TOWN_MAGE_CONFIG = "scripts/town_mage.json";
	    public static String TOWN_INNKEEPER_CONFIG = "scripts/town_innkeeper.json";
	    public static String TOWN_FOLK_CONFIGS = "scripts/town_folk.json";
	    
	    private EntityFactory(){
	        entities = new Hashtable<String, EntityConfig>();

	        Array<EntityConfig> townFolkConfigs = Entity.getEntityConfigs(TOWN_FOLK_CONFIGS);
	        for( EntityConfig config: townFolkConfigs){
	        	
	            entities.put(config.getEntityID(), config);
	        }

//	        Array<EntityConfig> environmentalEntityConfigs = Entity.getEntityConfigs(ENVIRONMENTAL_ENTITY_CONFIGS);
//	        for( EntityConfig config: environmentalEntityConfigs){
//	            entities.put(config.getEntityID(), config);
//	        }

	        
	        //cmabimar para mas entities
	        
	        entities.put(EntityName.TOWN_GUARD_WALKING.toString(), Entity.loadEntityConfigByPath(TOWN_GUARD_WALKING_CONFIG));
//	        entities.put(EntityName.TOWN_BLACKSMITH.toString(), Entity.loadEntityConfigByPath(TOWN_BLACKSMITH_CONFIG));
//	        entities.put(EntityName.TOWN_MAGE.toString(), Entity.loadEntityConfigByPath(TOWN_MAGE_CONFIG));
//	        entities.put(EntityName.TOWN_INNKEEPER.toString(), Entity.loadEntityConfigByPath(TOWN_INNKEEPER_CONFIG));
	        entities.put(EntityName.PLAYER_PUPPET.toString(), Entity.loadEntityConfigByPath(PLAYER_CONFIG));
	    }
	    
	    public static EntityFactory getInstance() {
	        if (instance == null) {
	            instance = new EntityFactory();
	        }
	         
	        return instance;
	    }
	    
	static public Entity getEntity(EntityType entityType){
		Entity entity = null;
		switch(entityType){
		case PLAYER:
			entity = new Entity(
					new PlayerInputComponent(),
					new PlayerPhysicsComponent(),
					new PlayerGraphicsComponent());
			entity.setEntityConfig(Entity.getEntityConfig(EntityFactory.PLAYER_CONFIG));
			
			entity.sendMessage(
					Component.MESSAGE.LOAD_ANIMATIONS,
					json.toJson(entity.getEntityConfig()));
			return entity;
			case DEMO_PLAYER:
				entity = new Entity(new NPCInputComponent(),new PlayerPhysicsComponent(),new PlayerGraphicsComponent());
				return entity;
			case NPC:
				entity = new Entity(new NPCInputComponent(),
				new NPCPhysicsComponent(),
				new NPCGraphicsComponent());
				return entity;
				default:
					return null;
					
		}
	}
	public Entity getEntityByName(EntityName entityName){
		
        EntityConfig config = new EntityConfig(entities.get(entityName.toString()));
        Entity entity = Entity.initEntity(config);
        return entity;
    }
}
