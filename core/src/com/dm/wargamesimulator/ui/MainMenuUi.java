package com.dm.wargamesimulator.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dm.wargamesimulator.Game;
import com.dm.wargamesimulator.ndfbinparser.ndfbin.NdfbinObject;
import com.dm.wargamesimulator.ndfbinparser.types.alltypes.NdfString;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Mathieu
 * Date: 08/05/14
 * Time: 13:39
 * To change this template use File | Settings | File Templates.
 */
public class MainMenuUi extends Stage {

    public static final String TAG = "MainMenuUi";
    private Table mMainTable;
    private TextButton mStartButton;
    private Slider mSpeedSlider;
    private ScrollPane mRedForceScrollPane;
    private ScrollPane mBlueForceScrollPane;
    private List<TextField> mBlueForceSelector;
    private List<TextField> mRedForceSelector;
    private Skin mSkin;
    private Array<String> mRedForces = new Array<String>();
    private Array<String> mBlueForces = new Array<String>();
    private int mLastSelected = -1;

    private static final int RED = 0;
    private static final int BLUE = 1;


    public MainMenuUi() {
        initUi();
    }


    public MainMenuUi(Viewport viewport) {
        this();
        setViewport(viewport);
    }


    private void initUi() {

        Gdx.input.setInputProcessor(this);
        mMainTable = new Table();
        mMainTable.setFillParent(true);

        loadForcesNames();
        mSkin = new Skin(Gdx.files.internal("core/assets/uiskin"));

        initStartButton();
        initRedPane();
        initBluePane();
        initSlider();


        addActor(mMainTable);

    }



    private void initSlider() {
        mSpeedSlider = new Slider(1, 20, 0.1f, false, mSkin, "default" );
        mSpeedSlider.setWidth(400);
        mMainTable.addActor(mSpeedSlider);

    }

    private void initBluePane() {
        mBlueForceSelector = new List<TextField>(mSkin, "blueforce");
        mBlueForceSelector.setItems(mBlueForces);
        mBlueForceSelector.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log(TAG, mBlueForces.get(mBlueForceSelector.getSelectedIndex()));
            }
        });


        mBlueForceScrollPane = new ScrollPane(mBlueForceSelector, mSkin, "default");
        mBlueForceScrollPane.setOverscroll(false, false);
        mBlueForceScrollPane.setScrollBarPositions(false, true);
        mBlueForceScrollPane.setHeight(500);
        mBlueForceScrollPane.setWidth(225);

        mMainTable.addActor(mBlueForceScrollPane);

    }

    private void initRedPane() {
        mRedForceSelector = new List<TextField>(mSkin, "redforce");
        mRedForceSelector.setItems(mRedForces);

        mRedForceScrollPane = new ScrollPane(mRedForceSelector, mSkin, "default");
        mRedForceScrollPane.setOverscroll(false, false);
        mRedForceScrollPane.setScrollBarPositions(false, true);
        mRedForceScrollPane.setHeight(500);
        mRedForceScrollPane.setWidth(225);
        mRedForceScrollPane.setX(getWidth() - mRedForceScrollPane.getWidth());

        mMainTable.addActor(mRedForceScrollPane);


    }

    private void initStartButton() {
       mStartButton = new TextButton("Start simulation", mSkin.get("default", TextButton.TextButtonStyle.class));
       mStartButton.addListener(new ChangeListener() {
           @Override
           public void changed(ChangeEvent event, Actor actor) {
               Gdx.app.log(TAG, "Start !!");
               Gdx.app.log(TAG, "Position Button = (" + mStartButton.getX() + ", " + mStartButton.getY() + ")\n" +
                       "Position Table = (" + mMainTable.getX() + ", " + mMainTable.getY() + ")\n");
           }
       });
       mMainTable.addActor(mStartButton);
    }

    private void loadForcesNames() {
        if(Game.GameData != null){
            java.util.List<NdfbinObject> units = Game.
                    GameData.getAllInstancesOfClass("TUniteAuSolDescriptor");
            for(NdfbinObject unit : units){
                NdfString ndfName = (NdfString) unit.getPropertyByName("ClassNameForDebug").value;
                mBlueForces.add(ndfName.value.toString().substring(5));
                mRedForces.add(ndfName.value.toString().substring(5));
            }
        }

    }

    public void setRedForceNames(Array<String> names){
        mRedForces.clear();
        mRedForces.addAll(names);
        mRedForceSelector.setItems(mRedForces);
    }

    public void setBlueForceNames(Array<String> names){
        mBlueForces.clear();
        mBlueForces.addAll(names);
        mBlueForceSelector.setItems(mBlueForces);
    }





}

