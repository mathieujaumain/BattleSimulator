package com.dm.wargamesimulator.interfaces;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dm.wargamesimulator.gamestates.GameState;
import com.dm.wargamesimulator.ndfbinparser.ndfbin.Ndfbin;

/**
 * Created by inv on 17/06/14.
 */
public interface IGameStateOwner {

    public void setBackground(Texture background);
    public void setMusic(Music music);
    public void setUi(Stage Ui);
    public void changeGameState(GameState state);
    public AssetManager getAssetManager();

}
