package study.com.shadowimageview;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by  HONGDA on 2019/1/17.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        Glide.init(this,new GlideBuilder());
    }
}
