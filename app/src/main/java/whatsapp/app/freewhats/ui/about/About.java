package whatsapp.app.freewhats.ui.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;

import whatsapp.app.freewhats.R;
import whatsapp.app.freewhats.utils.CommonConstants;

import static whatsapp.app.freewhats.utils.CommonConstants.REWARD_ADS_ID;

public class About extends AppCompatActivity implements AboutContract.AboutView {

    private ImageView btnlink;
    private AboutPresenter aboutPresenter;
    private InterstitialAd mInterstitialAd;
    private RewardedVideoAd mRewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);

        initializeView();

        initializeAds();
        aboutPresenter = new AboutPresenter(this);

        btnlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAds();
                loadRewardAds();
                aboutPresenter.hadleLinkedIn(Uri.parse(CommonConstants.URL_LINKEDIN));
            }
        });

    }

    private void initializeView(){
        btnlink = findViewById(R.id.btnlinkedin);
    }

    private void initializeAds(){
        MobileAds.initialize(this, CommonConstants.MOBILE_ADS_INI);
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.loadAd(REWARD_ADS_ID, new AdRequest.Builder().build());
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
    public void sendIntent(Uri uri) {
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}
