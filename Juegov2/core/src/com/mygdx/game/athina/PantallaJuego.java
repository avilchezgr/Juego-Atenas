package com.mygdx.game.athina;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;


public class PantallaJuego {//extends Pantalla {

//	private static final String TAG = PantallaJuego.class.getSimpleName();
////pagina 65 habla sobre viewports
//	private static class VIEWPORT {
//		static float viewportWidth;
//		static float viewportHeight;
//		static float physicalHeight;
//		static float physicalWidth;
//		static float aspectRatio;
//		static float virtualWidth;
//		static float virtualHeight;
//
//	}
//
//	private PlayerController controller;
//	private TextureRegion currentPlayerFrame;
//	private Sprite currentPlayerSprite;
//
//	private OrthogonalTiledMapRenderer mapRenderer = null;
//	private OrthographicCamera camera = null;
//	private static MapManager mapMgr;
//	private static Entity player;
//
//	public PantallaJuego(Athina athina) {
//		super(athina);
//		// TODO Auto-generated constructor stub
//		mapMgr = new MapManager();
//	}
//
//	@Override
//	public void show() {
//		// camera setup
//		setupViewport(10, 10);
//
//		camera = new OrthographicCamera();
//		camera.setToOrtho(false, VIEWPORT.viewportWidth,
//				VIEWPORT.viewportHeight);
//
//		mapRenderer = new OrthogonalTiledMapRenderer(mapMgr.getCurrentMap(),
//				MapManager.UNIT_SCALE);
//		mapRenderer.setView(camera);
//
//		Gdx.app.debug(TAG, "UnitScale value is: " + mapRenderer.getUnitScale());
//
//		player = new Arquero();
//		
//			
//		
//		player.init(mapMgr.getPlayerStartUnitScaled().x,
//				mapMgr.getPlayerStartUnitScaled().y);
//		currentPlayerSprite = player.getFrameSprite();
//		controller = new PlayerController(player);
//		Gdx.input.setInputProcessor(controller);
//		
//	}
//
//	@Override
//	public void hide() {
//
//	}
//
//	/*
//	 * Method called every frame, primary location for rendering, updating,
//	 * checking for collisions in the game life cycle, first we lock the
//	 * viewport to the current position of the player so that the player is
//	 * always in the center of the screen We will check also if the player
//	 * activated a portal and the collisions with the map
//	 */
//	@Override
//	public void render(float delta) {
//		// Clear the screen
//		
//		Gdx.gl.glClearColor(0, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		
//		// We lock and center the camera to the player's position
//
//		camera.position.set(currentPlayerSprite.getX(),
//				currentPlayerSprite.getY(), 0f);
//		camera.update();
//		player.update(delta);
//		currentPlayerFrame = player.getFrame();
//		updatePortalLayerActivation(player.boundingBox);
//
//		if (!isCollisionWithMapLayer(player.boundingBox)) {
//			player.setNextPositionToCurrent();
//		}
//		controller.update(delta);
//
//		mapRenderer.setView(camera);
//		//mapRenderer.render();
//		
//		//suelo foreground decoracion
//
//		mapRenderer.getBatch().begin();
//		mapMgr.renderNoHideLayers(mapRenderer);
//		mapRenderer.getBatch().draw(currentPlayerFrame,
//				currentPlayerSprite.getX(), currentPlayerSprite.getY(), 1, 1);
//		mapMgr.renderHideLayer(mapRenderer);
//		mapRenderer.getBatch().end();
//		//render the layers that hide the characters.
//		
//	}
//	
//	/**
//	 * @param width
//	 * @param height
//	 * */
//	public void setupViewport(int width, int height) {
//		// Make the viewport a percentage of the total display area
//		VIEWPORT.virtualWidth = width;
//		VIEWPORT.virtualHeight = height;
//		// Current viewport dimensions
//		VIEWPORT.viewportWidth = VIEWPORT.virtualWidth;
//		VIEWPORT.viewportHeight = VIEWPORT.virtualHeight;
//		// pixel dimensions of display
//		VIEWPORT.physicalWidth = Gdx.graphics.getWidth();
//		VIEWPORT.physicalHeight = Gdx.graphics.getHeight();
//		// aspect ratio for current viewport
//
//		VIEWPORT.aspectRatio = (VIEWPORT.virtualWidth / VIEWPORT.virtualHeight);
//		// update viewport if there could be skewing
//		if (VIEWPORT.physicalWidth / VIEWPORT.physicalHeight >= VIEWPORT.aspectRatio) {
//			// Letterbox left and right
//			VIEWPORT.viewportWidth = VIEWPORT.viewportHeight
//					* (VIEWPORT.physicalWidth / VIEWPORT.physicalHeight);
//			VIEWPORT.viewportHeight = VIEWPORT.virtualHeight;
//		} else {
//			// letterbox above and below
//			VIEWPORT.viewportWidth = VIEWPORT.virtualWidth;
//			VIEWPORT.viewportHeight = VIEWPORT.viewportWidth
//					* (VIEWPORT.physicalHeight / VIEWPORT.physicalWidth);
//		}
//		Gdx.app.debug(TAG, "WorldRenderer: virtual: (" + VIEWPORT.virtualWidth
//				+ "," + VIEWPORT.virtualHeight + ")");
//
//		Gdx.app.debug(TAG, "WorldRenderer: viewport: ("
//				+ VIEWPORT.viewportWidth + "," + VIEWPORT.viewportHeight + ")");
//
//		Gdx.app.debug(TAG, "WorldRenderer: physical: ("
//				+ VIEWPORT.physicalWidth + "," + VIEWPORT.physicalHeight + ")");
//
//	}
//
//	/**
//	 * Creo de momento detecta colisiones con rectangulos pero no con circulos
//	 * pag 73
//	 * */
//	private boolean isCollisionWithMapLayer(Rectangle boundingBox) {
//		MapLayer mapCollisionLayer = mapMgr.getCollisionLayer();
//
//		if (mapCollisionLayer == null) {
//			return false;
//		}
//		Rectangle rectangle = null;
//		for (MapObject object : mapCollisionLayer.getObjects()) {
//			if (object instanceof RectangleMapObject) {
//				rectangle = ((RectangleMapObject) object).getRectangle();
//				if (boundingBox.overlaps(rectangle)) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//	/*
//	private boolean isCollisionWithMapLayer(Rectangle boundingBox) {
//		MapLayer mapCollisionLayer = mapMgr.getCollisionLayer();
//
//		if (mapCollisionLayer == null) {
//			return false;
//		}
//		Rectangle rectangle = null;
//		for (MapObject object : mapCollisionLayer.getObjects()) {
//			if (object instanceof RectangleMapObject) {
//				rectangle = ((RectangleMapObject) object).getRectangle();
//				if (boundingBox.overlaps(rectangle)) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//*/
//	private boolean updatePortalLayerActivation(Rectangle boundingBox) {
//
//		MapLayer mapPortalLayer = mapMgr.getPortalLayer();
//
//		if (mapPortalLayer == null) {
//			return false;
//		}
//
//		Rectangle rectangle = null;
//		for (MapObject object : mapPortalLayer.getObjects()) {
//			if (object instanceof RectangleMapObject) {
//				rectangle = ((RectangleMapObject) object).getRectangle();
//				if (boundingBox.overlaps(rectangle)) {
//					String mapName = object.getName();
//					if (mapName == null) {
//						return false;
//					}
//					mapMgr.setClosestStartPositionFromScaledUnits(player
//							.getCurrentPosition());
//					mapMgr.loadMap(mapName);
//					player.init(mapMgr.getPlayerStartUnitScaled().x,
//							mapMgr.getPlayerStartUnitScaled().y);
//					mapRenderer.setMap(mapMgr.getCurrentMap());
//					Gdx.app.debug(TAG, "Portal Activated");
//					return true;
//				}
//			}
//		}
//		return false;
//	}

}
