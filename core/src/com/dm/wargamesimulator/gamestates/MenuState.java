package com.dm.wargamesimulator.gamestates;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.dm.wargamesimulator.Game;
import com.dm.wargamesimulator.interfaces.IGameStateOwner;
import com.dm.wargamesimulator.ui.GameUI;
import com.dm.wargamesimulator.ui.MainMenuUi;

/**
 * Created by inv on 18/06/14.
 */
public class MenuState extends GameState {

    public static final String TAG = "MenuState";

    private AssetManager manager;

    public MenuState(IGameStateOwner owner) {
        super(owner);
    }

    @Override
    public void execute(long elapsedTime) {

    }

    @Override
    public void exitState() {

    }

    @Override
    public void enterState() {
        getOwner().setUi(new MainMenuUi());
        manager = getOwner().getAssetManager();
        getOwner().setMusic(manager.get(Game.MAIN_MUSIC_PATH, Music.class));
        getOwner().setBackground(manager.get(Game.MAIN_TITLE_IMAGE_PATH, Texture.class));
    }
}