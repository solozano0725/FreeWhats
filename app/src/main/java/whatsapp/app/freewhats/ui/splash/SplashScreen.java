package whatsapp.app.freewhats.ui.splash;

import android.content.Intent;
import android.view.WindowManager;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import whatsapp.app.freewhats.R;
import whatsapp.app.freewhats.ui.main.MainActivity;

public class SplashScreen extends AwesomeSplash {
    @Override
    public void initSplash(ConfigSplash configSplash) {
        getWindow().setFlags(WindowManager.LayoutParams.ROTATION_ANIMATION_CROSSFADE, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Background
        configSplash.setBackgroundColor(R.color.colorPrimary); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(1000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Logo
        configSplash.setLogoSplash(R.drawable.ic_whatsapp);
        configSplash.setAnimLogoSplashDuration(500);
        configSplash.setAnimLogoSplashTechnique(Techniques.FadeOut);
        //Customize Title
        configSplash.setTitleSplash("");
    }

    @Override
    public void animationsFinished() {
        startActivity(new Intent(SplashScreen.this, MainActivity.class));
        finish();
    }
}