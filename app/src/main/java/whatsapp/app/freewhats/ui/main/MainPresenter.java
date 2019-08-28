package whatsapp.app.freewhats.ui.main;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import whatsapp.app.freewhats.R;

public class MainPresenter implements MainContract.MainPresenter {

    private MainContract.MainView mainView;

    public MainPresenter(MainContract.MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void chatWhatsapp(Context c, String phone) {
        try {
            Intent i = new Intent("android.intent.action.MAIN");
            i.setType("text/plain");
            i.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
            i.putExtra("jid", phone + "@s.whatsapp.net");
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            c.startActivity(i);

            if (i.resolveActivity(c.getPackageManager()) == null) {
                mainView.warningMsg(c.getResources().getString(R.string.whatsapp_is_null));
                return;
            }
            mainView.successMsg(c.getResources().getString(R.string.thanks_for_use));
            c.startActivity(i);

        } catch (Exception e) {
            mainView.errorMsg(e.getMessage());
        }
    }
}
