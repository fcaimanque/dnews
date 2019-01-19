package cl.ucn.disc.dsm.fcaimanque.dnews;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import org.acra.ACRA;
import org.acra.annotation.AcraMailSender;
import org.acra.annotation.AcraToast;

import com.facebook.drawee.backends.pipeline.Fresco;



@AcraMailSender(mailTo = "fernando.caimanque@alumnos.ucn.cl")
@AcraToast(resText = R.string.acra_toast_text, length = Toast.LENGTH_LONG)
public final class MainApp extends Application {

    @Override
    protected void attachBaseContext(Context base){
        super.attachBaseContext(base);
        //ini
        ACRA.init(this);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Inicializar Fresco.
        Fresco.initialize(this);
    }

}
