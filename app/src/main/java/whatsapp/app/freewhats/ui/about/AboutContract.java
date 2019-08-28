package whatsapp.app.freewhats.ui.about;

import android.content.Context;
import android.net.Uri;

public interface AboutContract {

    interface AboutView{
        void loadAds();
        void loadRewardAds();
        void sendIntent(Uri uri);
    }

    interface AboutPresenter{
        void hadleLinkedIn(Uri uri);
    }
}
