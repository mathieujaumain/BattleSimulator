package com.dm.wargamesimulator.tasks;

import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.dm.wargamesimulator.Game;
import com.dm.wargamesimulator.interfaces.IGameStateOwner;
import com.dm.wargamesimulator.ndfbinparser.ndfbin.Ndfbin;

import javax.swing.*;

/**
 * Created by inv on 18/06/14.
 */
public class NdfbinLoadTask extends SwingWorker<Boolean, Boolean> {

    private IGameStateOwner game;

    public NdfbinLoadTask(IGameStateOwner game) {
        this.game = game;
    }

    @Override
    protected Boolean doInBackground() throws Exception {

        game.getAssetManager().setLoader(Ndfbin.class, new com.dm.wargamesimulator.NdfbinLoader(new InternalFileHandleResolver()));
        game.getAssetManager().load(Game.GAME_DATA_PATH, Ndfbin.class);
        game.getAssetManager().finishLoading();

        return true;
    }
}
