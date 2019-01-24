package study.com.shadowimageview;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AnimTestActivity extends AppCompatActivity {

    private ImageView imgAnim;
    private Button btnStart;
    private Button btnPause;
    private AnimationDrawable framAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_test);
        imgAnim = findViewById(R.id.img_anim);
        btnStart = findViewById(R.id.btn_start);
        btnPause = findViewById(R.id.btn_pause);
        imgAnim.setImageResource(R.drawable.btn_xycx);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgAnim.setImageResource(R.drawable.wish_anim);
                framAnim = (AnimationDrawable) imgAnim.getDrawable();
                framAnim.start();
                Log.i("LHD", "num = " + framAnim.getNumberOfFrames());
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgAnim.setImageResource(R.drawable.wish_anim);
                framAnim = (AnimationDrawable) imgAnim.getDrawable();
                framAnim.stop();
                imgAnim.setImageResource(R.drawable.btn_xycx);
            }
        });

    }

}
