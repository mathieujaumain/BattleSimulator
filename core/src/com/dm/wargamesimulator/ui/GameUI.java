package com.dm.wargamesimulator.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dm.wargamesimulator.Game;

/**
 * Created by inv on 06/05/14.
 */
public class GameUI extends Stage  {

    private Table mMainTable;
    private TextButton mStartButton;
    public static final String TAG ="GameUI";

    public GameUI(){
        initUi();
    }

    public GameUI(Viewport viewPort){
        this();
        setViewport(viewPort);

    }

    private void initUi() {
        Gdx.input.setInputProcessor(this);
        mMainTable = new Table();
        mMainTable.setFillParent(true);
        Skin skin = new Skin(Gdx.files.internal("core/assets/uiskin"));
        mStartButton = new TextButton("BOUTON", skin, "default");
        mStartButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log(TAG, "Starto !!!");

                //Game.setBackground(null);
                Gdx.app.log(TAG, "Position Button = ("+mStartButton.getX() + ", " + mStartButton.getY() + ")\n" +
                        "Position Table = ("+mMainTable.getX() + ", " + mMainTable.getY() + ")\n");
            }
        });
        mMainTable.addActor(mStartButton);
        addActor(mMainTable);
    }

}
