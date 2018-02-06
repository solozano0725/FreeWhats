package whatsapp.app.freewhats;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {

    Button btnchat;
    CountryCodePicker ccp;
    EditText edtnumero;
    String pref="", number = "";
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnchat = (Button) findViewById(R.id.chat);
        ccp = (CountryCodePicker) findViewById(R.id.prefijo);
        edtnumero = (EditText) findViewById(R.id.numero);

        MobileAds.initialize(this, "ca-app-pub-1206184245610579~7021666599");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1206184245610579/2409959004");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        btnchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                pref = ccp.getSelectedCountryCode();
                number = edtnumero.getText().toString();
                if(!pref.isEmpty() && !number.isEmpty()){
                    whatsapp(MainActivity.this, pref+number);
                } else{
                    Toast.makeText(MainActivity.this, R.string.advertencia, Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public static void whatsapp(Activity activity, String phone) {
        try{
            Intent sendIntent =new Intent("android.intent.action.MAIN");
            sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra("jid", phone +"@s.whatsapp.net");
            sendIntent.setPackage("com.whatsapp");
            activity.startActivity(sendIntent);
        }
        catch(Exception e)
        {
            Toast.makeText(activity,"Error/n"+ e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.acerca) {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
        } else if(id == R.id.guia){
            Intent intent = new Intent(this, Guia.class);
            startActivity(intent);
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
