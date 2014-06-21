package com.dm.wargamesimulator.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by inv on 16/06/14.
 */
public class LoadingUI extends Stage {

    public static final String TAG ="LoadingUI";
    public static final String LOADING_IMAGE_PATH = "core/assets/background/main_title_resized.png";

    public Image mLoadingImage;

    public LoadingUI(){
        initUi();
    }

    public LoadingUI(Viewport viewPort){
        this();
        setViewport(viewPort);

    }


    private void initUi() {
        Texture loadGdxBitmap = new Texture(Gdx.files.internal(LOADING_IMAGE_PATH));
        mLoadingImage = new Image(loadGdxBitmap);
        addActor(mLoadingImage);

    }


}
