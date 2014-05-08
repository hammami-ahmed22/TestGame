package com.android.games.testgame;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.ui.activity.BaseGameActivity;

import android.widget.Toast;

public class Main extends BaseGameActivity {
	
	public static final int WIDTH = 800;
	public static final int HEIGH = 480;
	
	private Scene mScene;
	private Camera mCamera;
	private ITiledTextureRegion mButtonsRegeion;
	private BitmapTextureAtlas mButtonsBitmap;
	
	
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		mCamera = new Camera(0, 0, WIDTH, HEIGH);		
		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(HEIGH, HEIGH), mCamera);
	}

	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws Exception {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		mButtonsBitmap = new BitmapTextureAtlas(getTextureManager(), 512, 512);
		mButtonsRegeion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mButtonsBitmap, this, "button-tiled.png", 0, 0, 2, 1);
		mButtonsBitmap.load();
		pOnCreateResourcesCallback.onCreateResourcesFinished();

	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws Exception {
		mScene = new Scene();
		mScene.setTouchAreaBindingOnActionDownEnabled(true);
		pOnCreateSceneCallback.onCreateSceneFinished(mScene);
		// TODO Auto-generated method stub

	}

	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
		
		ButtonSprite buttonSprite = new ButtonSprite(WIDTH * 0.5f, HEIGH * 0.5f, mButtonsRegeion, getVertexBufferObjectManager()){
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {			
		                int eventaction = pSceneTouchEvent.getAction(); 		             
		                switch (eventaction) {
		                   case TouchEvent.ACTION_DOWN:{
		                	   Main.this.runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									Toast.makeText(getApplicationContext(), "Button Pressed " , Toast.LENGTH_LONG).show();									
								}
							});
		                   }
		        		break;
		                   case TouchEvent.ACTION_MOVE: {
		            	       
		            	        break;
		            	    }
		                   case TouchEvent.ACTION_UP:
		                        break;
		                }
				return true;
			}
		};
		
		mScene.registerTouchArea(buttonSprite);
		mScene.attachChild(buttonSprite);
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
