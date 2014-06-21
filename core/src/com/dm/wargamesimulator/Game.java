package com.dm.wargamesimulator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dm.wargamesimulator.gamestates.GameState;
import com.dm.wargamesimulator.gamestates.LoadingState;
import com.dm.wargamesimulator.gamestates.NilState;
import com.dm.wargamesimulator.interfaces.IGameStateOwner;
import com.dm.wargamesimulator.interfaces.IRenderable;
import com.dm.wargamesimulator.interfaces.IUpdatable;
import com.dm.wargamesimulator.ndfbinparser.NdfbinParser;
import com.dm.wargamesimulator.ndfbinparser.ndfbin.Ndfbin;

public class Game extends ApplicationAdapter implements IUpdatable, IRenderable,
                                                        IGameStateOwner{

    public static final String LOADING_IMAGE_PATH = "core/assets/sprites/map.jpg";
    public static final String GAME_DATA_PATH = "core/assets/data/everything.ndfbin";
    public static final String MAIN_TITLE_IMAGE_PATH = "core/assets/background/main_title_resized.png";
    public static final String MAIN_MUSIC_PATH = "core/assets/sounds/main_title_music.mp3";

    public static AssetManager assetManager;
	private SpriteBatch batch;
    private NdfbinParser mParser;

    public static Texture mCurrentBackground;
    public static Music mCurrentMusic;
    public static Stage mCurrentUI;
    public static Ndfbin GameData;

    private long elapsedTime = 0;


    public GameState mCurrentState = new NilState(this);
	
	@Override
	public void create () {

        assetManager = new AssetManager();
        loadAssets();
        changeGameState(new LoadingState(this));
        assetManager.finishLoading();
        batch = new SpriteBatch();

	}

    private void loadAssets() {
        assetManager.load(MAIN_TITLE_IMAGE_PATH, Texture.class);
        assetManager.load(MAIN_MUSIC_PATH, Music.class);
        assetManager.load(LOADING_IMAGE_PATH, Texture.class);
        assetManager.finishLoading();

    }


    @Override
	public void render () {
        super.render();
        float deltaTime = Gdx.graphics.getDeltaTime();
        elapsedTime += deltaTime * 1000;
        //Gdx.app.log("TIME", "tick = " + elapsedTime);
        update((long) deltaTime);
        draw();
	}

    @Override
    public void update(long deltaSinceLastUpdate) {
        if(mCurrentUI != null)
            mCurrentUI.act(deltaSinceLastUpdate);
        if(mCurrentState != null)
            mCurrentState.execute(deltaSinceLastUpdate);
    }

    @Override
    public void draw() {
        Gdx.gl.glClearColor(1, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if(mCurrentBackground != null)
            batch.draw(mCurrentBackground, 0, 0);
        batch.end();
        if(mCurrentUI != null)
            mCurrentUI.draw();

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        if(mCurrentUI != null)
            mCurrentUI.getViewport().update(width, height, true);
    }

    @Override
    public void setMusic(Music music) {
        if(music != null){
            if(mCurrentMusic != null && mCurrentMusic.isPlaying())
                mCurrentMusic.stop();
            mCurrentMusic = music;
            mCurrentMusic.setLooping(true);
            mCurrentMusic.play();
        } else {
            if(mCurrentMusic != null)
            mCurrentMusic.setVolume(0);
        }
    }


    @Override
    public void setUi(Stage stage){
        if(stage != null){
            mCurrentUI = stage;
        } else {
            mCurrentUI = null;
        }
    }

    @Override
    public void setBackground(Texture back) {
        if(back == null){
           mCurrentBackground.dispose();
        } else {
           mCurrentBackground = back;
        }

    }

    @Override
    public void dispose () {
        if(mCurrentBackground != null)
            mCurrentBackground.dispose();
        if(mCurrentUI != null)
            mCurrentUI.dispose();
        if(mCurrentMusic != null)
            mCurrentMusic.dispose();
    }


    @Override
    public void changeGameState(GameState state) {
        mCurrentState.exitState();
        mCurrentState = state;
        mCurrentState.enterState();
    }

    @Override
    public AssetManager getAssetManager() {
        return assetManager;
    }
}