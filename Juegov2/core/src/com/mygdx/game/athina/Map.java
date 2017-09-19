package com.mygdx.game.athina;

import java.util.Hashtable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.athina.MapFactory.MapType;






public class Map {
		private static final String TAG = Map.class.getSimpleName();
		public final static float UNIT_SCALE  = 1/64f;
	//Map layers
	
		public final static String MAP_COLLISION_LAYER =
		"MAP_COLLISION_LAYER";
		public final static String MAP_SPAWNS_LAYER =
		"MAP_SPAWNS_LAYER";
		public final static String MAP_PORTAL_LAYER =
		"MAP_PORTAL_LAYER";
		public final static String MAP_FLOOR_LAYER = 
				"MAP_FLOOR_LAYER";
		public final static String MAP_DECORATION_LAYER =
				"MAP_DECORATION_LAYER";
		public final static String MAP_FOREGROUND_LAYER =
				"MAP_FOREGROUND_LAYER";
		public final static String MAP_HIDE_LAYER = 
				"MAP_HIDE_LAYER";
		public final static String PLAYER_START = "PLAYER_START";
		public final static String NPC_START = "NPC_START";
		
		protected Json json;
		protected Vector2 playerStartPosition;
		protected Vector2 closestPlayerStartPosition;
		protected Vector2 convertedUnits;
		protected TiledMap currentMap = null;
		protected Vector2 playerStart;
		protected Array<Vector2> npcStartPositions;
		protected Hashtable<String, Vector2> specialNPCStartPositions;
		protected MapLayer collisionLayer = null;
		protected MapLayer portalLayer = null;
		protected MapLayer spawnsLayer = null;
		private MapLayer decorationLayer = null;
		private MapLayer hideLayer = null;
		private MapLayer foregroundLayer = null;
		private MapLayer floorLayer = null;
		protected MapType currentMapType;
		protected Array<Entity> mapEntities;
		protected Vector2 playerStartPositionRect;
		
		public Map(MapType mapType, String fullMapPath){
			
			json = new Json();
	        mapEntities = new Array<Entity>(10);
	        currentMapType = mapType;
	        playerStart = new Vector2(0,0);
	        playerStartPositionRect = new Vector2(0,0);
	        closestPlayerStartPosition = new Vector2(0,0);
	        convertedUnits = new Vector2(0,0);
	        
	        if( fullMapPath == null || fullMapPath.isEmpty() ) {
	            Gdx.app.debug(TAG, "Map is invalid");
	            return;
	        }

	        Utility.loadMapAsset(fullMapPath);
	        if( Utility.isAssetLoaded(fullMapPath) ) {
	            currentMap = Utility.getMapAsset(fullMapPath);
	        }else{
	            Gdx.app.debug(TAG, "Map not loaded");
	            return;
	        }

	        collisionLayer = currentMap.getLayers().get(MAP_COLLISION_LAYER);
	        if( collisionLayer == null ){
	            Gdx.app.debug(TAG, "No collision layer!");
	        }

	        portalLayer = currentMap.getLayers().get(MAP_PORTAL_LAYER);
	        if( portalLayer == null ){
	            Gdx.app.debug(TAG, "No portal layer!");
	        }

	        spawnsLayer = currentMap.getLayers().get(MAP_SPAWNS_LAYER);
	        if( spawnsLayer == null ){
	        	
	            Gdx.app.debug(TAG, "No spawn layer!");
	        }else{
	            setClosestStartPosition(playerStart);
	        }
	        
	        specialNPCStartPositions = getSpecialNPCStartPositions();
	        npcStartPositions = getNPCStartPositions();
	        
		}
		public Array getMapEntities(){
			return mapEntities;
		}
		public Vector2 getPlayerStart(){
			return playerStart;
		}
		  public MapFactory.MapType getCurrentMapType(){
		        return currentMapType;
		    }
		   protected void updateMapEntities(MapManager mapMgr, Batch batch, float delta){
		        for( int i=0; i < mapEntities.size; i++){
		            mapEntities.get(i).update(mapMgr, batch, delta);
		        }
		    //    for( int i=0; i < _mapQuestEntities.size; i++){
		      //      _mapQuestEntities.get(i).update(mapMgr, batch, delta);
		       // }
		    }
		public MapLayer getCollisionLayer(){
			return collisionLayer;
		}
		 
		public MapLayer getPortalLayer(){
			return portalLayer;
		}
		public TiledMap getCurrentTiledMap(){
			return currentMap;
		}
		
			public Vector2 getPlayerStartUnitScaled(){
		        Vector2 playerStart = this.playerStart.cpy();
		        playerStart.set(playerStart.x * UNIT_SCALE, playerStart.y * UNIT_SCALE);
		        return playerStart;
		    }
		
		public Array<Vector2> getNPCStartPositions(){
			  Array<Vector2> npcStartPositions = new Array<Vector2>();

		        for( MapObject object: spawnsLayer.getObjects()){
		            String objectName = object.getName();

		            if( objectName == null || objectName.isEmpty() ){
		                continue;
		            }

		            if( objectName.equalsIgnoreCase(NPC_START) ){
		                //Get center of rectangle
		                float x = ((RectangleMapObject)object).getRectangle().getX();
		                float y = ((RectangleMapObject)object).getRectangle().getY();

		                //scale by the unit to convert from map coordinates
		                x *= UNIT_SCALE;
		                y *= UNIT_SCALE;

		                npcStartPositions.add(new Vector2(x,y));
		            }
		        }
		        return npcStartPositions;
		}
		public Hashtable getSpecialNPCStartPositions(){
			  Hashtable<String, Vector2> specialNPCStartPositions = new Hashtable<String, Vector2>();
			  
		        for( MapObject object: spawnsLayer.getObjects()){
		            String objectName = object.getName();

		            if( objectName == null || objectName.isEmpty() ){
		                continue;
		            }

		            //This is meant for all the special spawn locations, a catch all, so ignore known ones
		            if(     objectName.equalsIgnoreCase(NPC_START) ||
		                    objectName.equalsIgnoreCase(PLAYER_START) ){
		                continue;
		            }

		            //Get center of rectangle
		            float x = ((RectangleMapObject)object).getRectangle().getX();
		            float y = ((RectangleMapObject)object).getRectangle().getY();

		            //scale by the unit to convert from map coordinates
		            x *= UNIT_SCALE;
		            y *= UNIT_SCALE;

		            specialNPCStartPositions.put(objectName, new Vector2(x,y));
		        }
		        return specialNPCStartPositions;
		}
		  private void setClosestStartPosition(final Vector2 position){
		         Gdx.app.debug(TAG, "setClosestStartPosition INPUT: (" + position.x + "," + position.y + ") " + currentMapType.toString());

		        //Get last known position on this map
		        playerStartPositionRect.set(0,0);
		        closestPlayerStartPosition.set(0,0);
		        float shortestDistance = 0f;

		        //Go through all player start positions and choose closest to last known position
		        for( MapObject object: spawnsLayer.getObjects()){
		            String objectName = object.getName();

		            if( objectName == null || objectName.isEmpty() ){
		                continue;
		            }

		            if( objectName.equalsIgnoreCase(PLAYER_START) ){
		                ((RectangleMapObject)object).getRectangle().getPosition(playerStartPositionRect);
		                float distance = position.dst2(playerStartPositionRect);

		                Gdx.app.debug(TAG, "DISTANCE: " + distance + " for " + currentMapType.toString());

		                if( distance < shortestDistance || shortestDistance == 0 ){
		                    closestPlayerStartPosition.set(playerStartPositionRect);
		                    shortestDistance = distance;
		                    Gdx.app.debug(TAG, "closest START is: (" +
		                    closestPlayerStartPosition.x + "," + closestPlayerStartPosition.y + ") " + 
		                    		currentMapType.toString());
		                }
		            }
		        }
		        playerStart =  closestPlayerStartPosition.cpy();
		    }
		  public void setClosestStartPositionFromScaledUnits(Vector2 position){
		        if( UNIT_SCALE <= 0 )
		            return;

		        convertedUnits.set(position.x/UNIT_SCALE, position.y/UNIT_SCALE);
		        setClosestStartPosition(convertedUnits);
		    }
		public MapLayer getFloorLayer() {
			// TODO Auto-generated method stub
			return floorLayer;
		}
		public MapLayer getForegroundLayer() {
			// TODO Auto-generated method stub
			return foregroundLayer;
		}
		public MapLayer getHideLayer() {
			// TODO Auto-generated method stub
			return hideLayer;
		}
		public MapLayer getDecorationLayer() {
			// TODO Auto-generated method stub
			return decorationLayer;
		}
		public void dispose() {
			   for( int i=0; i < mapEntities.size; i++){
		            mapEntities.get(i).dispose();
		        }
//		        for( int i=0; i < _mapQuestEntities.size; i++){
//		            _mapQuestEntities.get(i).dispose();
//		        }
//		        for( int i=0; i < _mapParticleEffects.size; i++){
//		            _mapParticleEffects.get(i).dispose();
//		        }
		}
}
