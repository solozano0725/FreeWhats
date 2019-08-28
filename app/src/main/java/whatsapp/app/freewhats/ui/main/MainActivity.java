package whatsapp.app.freewhats.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.hbb20.CountryCodePicker;

import es.dmoral.toasty.Toasty;
import whatsapp.app.freewhats.ui.about.About;
import whatsapp.app.freewhats.R;
import whatsapp.app.freewhats.utils.CommonConstants;
import whatsapp.app.freewhats.utils.Utils;

import static whatsapp.app.freewhats.utils.CommonConstants.REWARD_ADS_ID;

public class MainActivity extends AppCompatActivity implements MainContract.MainView{

    private MainPresenter mainPresenter;

    private Button btnChat;
    private CountryCodePicker ccp;
    private EditText edtNumber;
    private String pref="", number = "";

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private RewardedVideoAd mRewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeView();
        initializeAds();

        mainPresenter = new MainPresenter(this);

        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAds();
                loadRewardAds();

                pref = ccp.getSelectedCountryCode();
                number = edtNumber.getText().toString();

                if(!Utils.isNull(pref) && !Utils.isNull(number)){
                    mainPresenter.chatWhatsapp(getApplicationContext(), pref+number);
                } else{
                    errorMsg(getResources().getString(R.string.adv));
                }
            }
        });

    }

    private void initializeView(){
        btnChat = findViewById(R.id.btnChat);
        ccp =  findViewById(R.id.prefix);
        edtNumber =  findViewById(R.id.number);
        mAdView = findViewById(R.id.adView);
    }

    private void initializeAds(){
        MobileAds.initialize(this, CommonConstants.MOBILE_ADS_INI);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(CommonConstants.AD_UNIT_ID);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.loadAd(REWARD_ADS_ID, new AdRequest.Builder().build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuAbout) {
           loadAds();
           loadRewardAds();
           sendIntent(this, About.class);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loadAds() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    @Override
    public void loadRewardAds() {
        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }
    }

    @Override
    public void successMsg(String message) {
        Toasty.success(getApplicationContext(), message, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void errorMsg(String message) {
        Toasty.error(getApplicationContext(), message, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void warningMsg(String message) {
        Toasty.warning(getApplicationContext(), message, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void sendIntent(Context packageContext, Class<?> cls) {
        startActivity(new Intent(packageContext, cls));
    }

}
