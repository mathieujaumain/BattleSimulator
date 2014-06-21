package com.dm.wargamesimulator;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.dm.wargamesimulator.ndfbinparser.NdfbinParser;
import com.dm.wargamesimulator.ndfbinparser.ndfbin.Ndfbin;

/**
 * Created by inv on 17/06/14.
 */
public class NdfbinLoader extends AsynchronousAssetLoader<Ndfbin, NdfbinLoader.NdfbinParameter> {

    private Ndfbin ndfbin;

    public NdfbinLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, NdfbinParameter parameter) {
        NdfbinParser parser = new NdfbinParser(file.file(), false);
        ndfbin = null;
        ndfbin = parser.parseFile();
    }

    @Override
    public Ndfbin loadSync(AssetManager manager, String fileName, FileHandle file, NdfbinParameter parameter) {
        if(ndfbin == null){
            NdfbinParser parser = new NdfbinParser(file.file(), false);
            ndfbin = parser.parseFile();
        }

        return ndfbin;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, NdfbinParameter parameter) {
        return null;
    }


    static public class NdfbinParameter extends AssetLoaderParameters<Ndfbin> {
    }
}
