package com.mygdx.game.athina;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class MapManager implements ProfileObserver{
	private static final String TAG = MapManager.
			class.getSimpleName();
	/**
	
	//All maps for the game
	private Hashtable<String,String> mapTable;
	private Hashtable<String, Vector2> playerStartLocationTable;
	
	//maps
	private final static String TOP_WORLD = "TOP_WORLD";
	private final static String TOWN = "TOWN";
	private final static String CASTLE_OF_DOOM = "CASTLE_OF_DOOM";
	
	//Map layers
	private final static String MAP_COLLISION_LAYER =
	"MAP_COLLISION_LAYER";
	private final static String MAP_SPAWNS_LAYER =
	"MAP_SPAWNS_LAYER";
	private final static String MAP_PORTAL_LAYER =
	"MAP_PORTAL_LAYER";
	private final static String MAP_FLOOR_LAYER = 
			"MAP_FLOOR_LAYER";
	private final static String MAP_DECORATION_LAYER =
			"MAP_DECORATION_LAYER";
	private final static String MAP_FOREGROUND_LAYER =
			"MAP_FOREGROUND_LAYER";
	private final static String MAP_HIDE_LAYER = 
			"MAP_HIDE_LAYER";
	
	private final static String PLAYER_START = "PLAYER_START";
	private Vector2 playerStartPositionRect;
	
	private Vector2 closestPlayerStartPosition;
	private Vector2 convertedUnits;

	private Vector2 playerStart;
	private TiledMap currentMap = null;
	private String currentMapName;
	private MapLayer collisionLayer = null;
	private MapLayer portalLayer = null;
	private MapLayer spawnsLayer = null;
	private MapLayer decorationLayer = null;
	private MapLayer hideLayer = null;
	private MapLayer foregroundLayer = null;
	private MapLayer floorLayer = null;
	
	// he cambiado de 1/16 a 1/64
	public final static float UNIT_SCALE
	= 1/64f;
	

*/
	
	 private Camera camera;
	    private boolean mapChanged = false;
	    private Map currentMap;
	    private Entity player;
	    private Entity currentSelectedEntity = null;
	    
	    public MapManager(){
	    }

/**
 * metodo modificado
 * */

	    public void loadMap(MapFactory.MapType mapType){
	        Map map = MapFactory.getMap(mapType);

	        if( map == null ){
	            Gdx.app.debug(TAG, "Map does not exist!  ");
	            return;
	        }

	        currentMap = map;
	        mapChanged = true;
	        clearCurrentSelectedMapEntity();
	        Gdx.app.debug(TAG, "Player Start: (" + currentMap.getPlayerStart().x + "," + currentMap.getPlayerStart().y + ")");
	    }



	    public void setClosestStartPositionFromScaledUnits(Vector2 position) {
	        currentMap.setClosestStartPositionFromScaledUnits(position);
	    }

	    public MapLayer getCollisionLayer(){
	        return currentMap.getCollisionLayer();
	    }

	    public MapLayer getPortalLayer(){
	        return currentMap.getPortalLayer();
	    }



	    public MapFactory.MapType getCurrentMapType(){
	        return currentMap.getCurrentMapType();
	    }

	    public Vector2 getPlayerStartUnitScaled() {
	        return currentMap.getPlayerStartUnitScaled();
	    }

	    public TiledMap getCurrentTiledMap(){
	        if( currentMap == null ) {
	            loadMap(MapFactory.MapType.TOWN);
	        }
	        return currentMap.getCurrentTiledMap();
	    }


	
	    public void updateCurrentMapEntities(MapManager mapMgr, Batch batch, float delta){
	        currentMap.updateMapEntities(mapMgr, batch, delta);
	    }


	    public final Array<Entity> getCurrentMapEntities(){
	        return currentMap.getMapEntities();
	    }



	  
	    public Entity getCurrentSelectedMapEntity(){
	        return currentSelectedEntity;
	    }

	    public void setCurrentSelectedMapEntity(Entity currentSelectedEntity) {
	        this.currentSelectedEntity = currentSelectedEntity;
	    }

	    public void clearCurrentSelectedMapEntity(){
	        if( currentSelectedEntity == null ) return;
	        currentSelectedEntity.sendMessage(Component.MESSAGE.ENTITY_DESELECTED);
	        currentSelectedEntity = null;
	    }

	    public void setPlayer(Entity entity){
	        this.player = entity;
	    }

	    public Entity getPlayer(){
	        return this.player;
	    }

	    public void setCamera(Camera camera){
	        this.camera = camera;
	    }

	    public Camera getCamera(){
	        return camera;
	    }

	    public boolean hasMapChanged(){
	        return mapChanged;
	    }

	    public void setMapChanged(boolean hasMapChanged){
	        this.mapChanged = hasMapChanged;
	    }
		

	public MapLayer getMapFloorLayer(){
		 return currentMap.getFloorLayer();
		
	}
	public MapLayer getMapForegroundLayer(){
		 return currentMap.getForegroundLayer();
		
	}
	public MapLayer getMapHideLayer(){
		 return currentMap.getHideLayer();
		
	}
	public MapLayer getMapDecorationLayer(){
		return currentMap.getDecorationLayer();
	}
	
	public void renderNoHideLayers(OrthogonalTiledMapRenderer  mapRenderer){
		mapRenderer.renderTileLayer((TiledMapTileLayer) getMapFloorLayer());
		mapRenderer.renderTileLayer((TiledMapTileLayer) getMapForegroundLayer());
		mapRenderer.renderTileLayer((TiledMapTileLayer) getMapDecorationLayer());
		
	}
	public void renderHideLayer(OrthogonalTiledMapRenderer  mapRenderer){
		mapRenderer.renderTileLayer((TiledMapTileLayer) getMapHideLayer());
	}

	@Override
	public void onNotify(ProfileManager profileManager, ProfileEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}

