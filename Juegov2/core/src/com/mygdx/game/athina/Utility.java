package com.mygdx.game.athina;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;

public final class Utility {
	public final static AssetManager assetManager = new AssetManager();
	private final static String TAG  = Utility.class.getSimpleName();
	private static InternalFileHandleResolver 
	filePathResolver = new InternalFileHandleResolver(); 	
	
	public static void unloadAsset(String assetFilenamePath){
		//once the Asset manager is done loading
		if(assetManager.isLoaded(assetFilenamePath)){
			assetManager.unload(assetFilenamePath);
		}else{
			Gdx.app.debug(TAG, "Asset is not loaded Nothing to unload: "+ assetFilenamePath);
		}
	} 
	
	public static float loadCompleted(){
		return assetManager.getProgress();
	}
	
	public static int numberAssetQueued(){
		return assetManager.getQueuedAssets();
	}
	
	public static boolean updateAssetLoading(){
		return assetManager.update();
	}
	
	public static boolean isAssetLoaded(String fileName){
		return assetManager.isLoaded(fileName);
	}
	
	public static void loadMapAsset(String mapFileNamePath){
		if(mapFileNamePath == null || mapFileNamePath.isEmpty()){
			
			return;
		
		}
		if(filePathResolver.resolve(mapFileNamePath).exists()){
			
			assetManager.setLoader(TiledMap.class, new TmxMapLoader(filePathResolver));
			assetManager.load(mapFileNamePath,TiledMap.class);
			
			//Until We add loading the screen,
			//Just block until we load the map.
			assetManager.finishLoadingAsset(mapFileNamePath);
			Gdx.app.debug(TAG, "Map Loaded!: " + mapFileNamePath);
		}
		else{
			
			Gdx.app.debug(TAG, "Map doesn't exist!: " + mapFileNamePath);
			
		}
		
	}

	public static TiledMap getMapAsset(String mapFilenamePath) {
		TiledMap map = null;
		// once the asset manager is done loading
		if (assetManager.isLoaded(mapFilenamePath)) {
			map = assetManager.get(mapFilenamePath, TiledMap.class);
		} else {
			Gdx.app.debug(TAG, "Map is not loaded:" + mapFilenamePath);
		}
		return map;
	}

	public static void loadTextureAsset(String textureFilenamePath) {
		if (textureFilenamePath == null || textureFilenamePath.isEmpty()) {
			return;
		}
		// load asset
		if (filePathResolver.resolve(textureFilenamePath).exists()) {
			assetManager.setLoader(Texture.class, new TextureLoader(
					filePathResolver));
			assetManager.load(textureFilenamePath, Texture.class);
			// Until we add loading screen,
			// just block until we load the map
			assetManager.finishLoadingAsset(textureFilenamePath);
		} else {
			Gdx.app.debug(TAG, "Texture doesn’t exist!: " + textureFilenamePath);
		}
	}
	
	public static Texture getTextureAsset(String textureFilenamePath) {
		Texture texture = null;
		// once the asset manager is done loading
		if (assetManager.isLoaded(textureFilenamePath)) {
			texture = assetManager.get(textureFilenamePath, Texture.class);
		} else {
			Gdx.app.debug(TAG, "Texture is not loaded: " + textureFilenamePath);
		}
		return texture;
	}
	/**
	 * Hace falta saber la fila y el numero de frames que queremos que tenga la animacion para llamar a este metodo.
	 * @param fila fila que queremos coger
	 * @param nColumnas numero de frames que va a tener la animacion
	 * @param array array de texture region que vamos a usar.
	 * */
	
	
	public static TextureRegion[] getFilaArrayTextRegion(int fila,int nColumnas, TextureRegion array[][]){
		
		//int nColumnas=array[0].length;
		System.out.println("asset"	);
		TextureRegion vector[]= new TextureRegion[nColumnas] ;
		for(int i=0;i<nColumnas;i++){
			
			vector[i]=array[fila][i];
			
			
			
		}
		return vector;
		
	
	}
	
	/**
public static Array<TextureRegion> getFilaArrayTextRegion(int fila,int nColumnas, TextureRegion array[][]){
		
		// int nColumnas=array[0].length;

		TextureRegion vector[] = new TextureRegion[nColumnas];
		Array a = new Array<TextureRegion>(nColumnas);

		for (int i = 0; i < nColumnas; i++) {

			// vector[i]=array[fila][i];
			// a.add(array[fila][i]);
			a.insert(i, array[fila][i]);
		}

		return a;
		
	
	}
	*/
	
	/**
	public static TextureRegion[] fromArrayToRowTextRegion(TextureRegion[][] arrayTextures){
		int sizeX = arrayTextures.length, sizeY = arrayTextures[0].length;
		int n = 0;
		TextureRegion[] auxFila;
		TextureRegion[] row = new TextureRegion[sizeX*sizeY];
		for(int i = 0; i < sizeY; i++){
			auxFila = getFilaArrayTextRegion(i,sizeX,arrayTextures);
			for(int j = 0; j < sizeX; j++){
				row[n] = auxFila[j];
				n++;
			}
		}
		return row;
		
	}
*/
	
}
