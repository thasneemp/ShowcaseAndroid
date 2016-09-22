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

public class MainActivity extends AppCompatActivity implements OnShowcaseEventListener, View.OnClickListener {
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
        twoTextView = (TextView) findViewById(R.id.textView);

        views.push(twoButton);
        views.push(twoTextView);

        twoTextView = (TextView) findViewById(R.id.textView);

        hello = new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(oneButton)).withMaterialShowcase().setStyle(R.style.CustomShowcaseTheme2)
                .setContentTitle("Hello")
                .setContentText("This is highlighting the Home button")
                .hideOnTouchOutside()
                .build();
        hello.setOnShowcaseEventListener(this);
        hello.overrideButtonClick(this);

    }

    @Override
    public void onShowcaseViewHide(ShowcaseView showcaseView) {

    }

    @Override
    public void onShowcaseViewDidHide(ShowcaseView showcaseView) {
        if (!views.isEmpty()) {
            View pop = views.pop();
            hello = new ShowcaseView.Builder(MainActivity.this)
                    .setTarget(new ViewTarget(pop)).withMaterialShowcase().setStyle(R.style.CustomShowcaseTheme2)
                    .setContentTitle("Hello")
                    .setContentText("This is highlighting the Home button")
                    .hideOnTouchOutside()
                    .build();
            hello.setOnShowcaseEventListener(this);
            hello.overrideButtonClick(this);

        }
    }

    @Override
    public void onShowcaseViewShow(ShowcaseView showcaseView) {

    }

    @Override
    public void onShowcaseViewTouchBlocked(MotionEvent motionEvent) {

    }

    @Override
    public void onClick(View v) {
        hello.hide();
        hello.setOnShowcaseEventListener(null);
    }
}
