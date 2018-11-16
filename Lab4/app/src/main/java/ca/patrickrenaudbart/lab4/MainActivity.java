package ca.patrickrenaudbart.lab4;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends Activity {


    private triangles gameView;
    private long lastTime;
    private long lag;
    private Handler handler = new Handler();

    long UPDATE_TIME_MS = 10L;
    long UPDATE_TIME_NS = UPDATE_TIME_MS * 1000000L;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Update();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameView = findViewById(R.id.gameView);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        lastTime = System.nanoTime();
        RequestUpdate();
    }

    private void RequestUpdate()
    {
        handler.postDelayed(runnable, UPDATE_TIME_MS);
    }



    public void Update()
    {
        long currentTime = System.nanoTime();
        long elapsedTime = currentTime - lastTime;
        lag += elapsedTime;

        while(lag >= UPDATE_TIME_NS)
        {
            lag -= UPDATE_TIME_NS;
            gameView.Update();
        }

        RequestUpdate();

        lastTime = currentTime;
    }

    private void CancelUpdate()
    {
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        CancelUpdate();
    }

}
