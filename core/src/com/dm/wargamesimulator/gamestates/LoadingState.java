package com.dm.wargamesimulator.gamestates;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.dm.wargamesimulator.Game;
import com.dm.wargamesimulator.interfaces.IGameStateOwner;
import com.dm.wargamesimulator.ndfbinparser.ndfbin.Ndfbin;
import com.dm.wargamesimulator.tasks.NdfbinLoadTask;

/**
 * Created by inv on 17/06/14.
 */
public class LoadingState extends GameState {

    private AssetManager manager;

    public LoadingState(IGameStateOwner owner) {
        super(owner);
    }

    @Override
    public void execute(long elapsedTime) {
        synchronized (manager){
            if(manager.isLoaded(Game.GAME_DATA_PATH)) {
                getOwner().changeGameState(new MenuState(getOwner()));
            }
        }
    }

    @Override
    public void exitState() {
        Game.GameData = manager.get(Game.GAME_DATA_PATH, Ndfbin.class);

    }

    @Override
    public void enterState() {
        manager = getOwner().getAssetManager();
        getOwner().setBackground(Game.assetManager.get(Game.LOADING_IMAGE_PATH, Texture.class));
        getOwner().setMusic(null);

        NdfbinLoadTask loader = new NdfbinLoadTask(getOwner());
        loader.execute();

    }
}
