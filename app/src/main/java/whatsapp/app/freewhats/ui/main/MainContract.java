package whatsapp.app.freewhats.ui.main;

import android.content.Context;

public interface MainContract {

    interface MainView{
        void loadAds();
        void loadRewardAds();
        void successMsg(String message);
        void errorMsg(String message);
        void warningMsg(String message);
        void sendIntent(Context packageContext, Class<?> cls);
    }

    interface MainPresenter{
        void chatWhatsapp(Context c, String phone);
    }
}
