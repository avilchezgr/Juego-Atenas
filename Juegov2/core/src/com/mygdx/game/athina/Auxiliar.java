package com.mygdx.game.athina;

import java.math.BigDecimal;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public  class Auxiliar {

	/**
	 * Hace falta saber la fila y el numero de frames que queremos que tenga la animacion para llamar a este metodo.
	 * @param fila fila que queremos coger
	 * @param nColumnas numero de frames que va a tener la animacion
	 * @param array array de texture region que vamos a usar.
	 * */
	public static TextureRegion[] getFilaArrayTextRegion(int fila,int nColumnas, TextureRegion array[][]){
		
		//int nColumnas=array[0].length;
		
		TextureRegion vector[]= new TextureRegion[nColumnas] ;
		for(int i=0;i<nColumnas;i++){
			
			vector[i]=array[fila][i];
			
			
			
		}
		return vector;
		
	
	}
	
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
	/**
	 * Returns a pseudo-random number between min and max, inclusive.
	 * The difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min Minimum value
	 * @param max Maximum value.  Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	/**
	 * Metodo para truncar un float 
	 * 
	 * @param d numero a truncar
	 * @param decimalPlace numero de cifras que queremos
	 * 
	 * */
	 public static float round(float d, int decimalPlace) {
	        BigDecimal bd = new BigDecimal(Float.toString(d));
	        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
	        return bd.floatValue();
	    }
	
	
}