import android.app.Application;
import com.parth.chatapp.database.DBHelper;

public class ChatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DBHelper.getInstance(this);
    }
}
