package com.dm.wargamesimulator.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dm.wargamesimulator.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1024;
        config.height = 640;
        config.title = "Battle Simulator";

        //config.fullscreen = true;
		new LwjglApplication(new Game(), config);
	}
}
