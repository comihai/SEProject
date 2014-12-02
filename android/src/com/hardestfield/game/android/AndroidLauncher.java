package com.hardestfield.game.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.hardestfield.game.hardestField;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        /**
         * conserve battery
         */
        config.useAccelerometer = false;
        config.useCompass = false;
		initialize(new hardestField(), config);
	}
}
