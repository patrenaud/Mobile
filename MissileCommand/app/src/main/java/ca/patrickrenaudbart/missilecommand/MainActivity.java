package ca.patrickrenaudbart.missilecommand;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends Activity implements Constants, IUpdatable{


    private GameView gameView;
    private long lastTime;
    private long lag;
    private Handler handler = new Handler();

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

        gameView = findViewById(R.id.game_view);
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


    @Override
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
