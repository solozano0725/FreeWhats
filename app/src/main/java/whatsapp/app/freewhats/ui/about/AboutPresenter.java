package whatsapp.app.freewhats.ui.about;

import android.net.Uri;

public class AboutPresenter implements AboutContract.AboutPresenter {

    private AboutContract.AboutView aboutView ;

    public AboutPresenter(AboutContract.AboutView aboutView) {
        this.aboutView = aboutView;
    }

    @Override
    public void hadleLinkedIn(Uri uri) {
        aboutView.sendIntent(uri);
    }

}
