package test.launcher.mummu.showcasedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.amlcurran.showcaseview.OnShowcaseEventListener;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    private Button oneButton;
    private Button twoButton;
    private TextView twoTextView;
    private ShowcaseView hello;
    private Stack<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        views = new Stack<>();
        setContentView(R.layout.activity_main);
        oneButton = (Button) findViewById(R.id.button);
        twoButton = (Button) findViewById(R.id.button2);

        views.push(twoButton);
        views.push(twoTextView);
        twoTextView = (TextView) findViewById(R.id.textView);

        hello = new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(oneButton)).withMaterialShowcase().setStyle(R.style.CustomShowcaseTheme2)
                .setContentTitle("Hello")
                .setContentText("This is highlighting the Home button")
                .hideOnTouchOutside()
                .build();
        hello.setOnShowcaseEventListener(new OnShowcaseEventListener() {
            @Override
            public void onShowcaseViewHide(ShowcaseView showcaseView) {
                showcaseView.setShowcase(new ViewTarget(twoButton), true);
            }

            @Override
            public void onShowcaseViewDidHide(ShowcaseView showcaseView) {
                hello = new ShowcaseView.Builder(MainActivity.this)
                        .setTarget(new ViewTarget(views.pop())).withMaterialShowcase().setStyle(R.style.CustomShowcaseTheme2)
                        .setContentTitle("Hello")
                        .setContentText("This is highlighting the Home button")
                        .hideOnTouchOutside()
                        .build();
            }

            @Override
            public void onShowcaseViewShow(ShowcaseView showcaseView) {

            }

            @Override
            public void onShowcaseViewTouchBlocked(MotionEvent motionEvent) {

            }
        });
    }
}
