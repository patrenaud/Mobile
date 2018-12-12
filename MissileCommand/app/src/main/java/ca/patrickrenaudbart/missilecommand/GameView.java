package ca.patrickrenaudbart.missilecommand;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

import static android.view.MotionEvent.*;
import static android.view.MotionEvent.ACTION_CANCEL;
import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_POINTER_DOWN;
import static android.view.MotionEvent.ACTION_POINTER_UP;
import static android.view.MotionEvent.ACTION_UP;
import java.util.Random;
import java.util.Timer;

class GameView extends View implements Constants, IUpdatable {

    static Random rand = new Random();

    private List<IDrawable> drawables = new LinkedList<>();
    private List<IUpdatable> updatables = new LinkedList<>();
    private List<ITouchable> touchables = new LinkedList<>();

    private Paint enemyPaint = new Paint();
    private Paint playerPaint = new Paint();

    private Paint launcherPaint = new Paint();

    private Paint healthPaint = new Paint();
    private Paint backHealthPaint = new Paint();

    private static int hitsTaken = 0;
    private float counter = 0;
    private boolean canShoot = true;



    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);


        CreateObject(new ITouchable() {
            @Override
            public boolean OnTouch(MotionEvent motionEvent) {


                switch (motionEvent.getActionMasked())
                {
                    case ACTION_DOWN:

                    case ACTION_POINTER_DOWN:
                        return true;
                    case ACTION_MOVE:
                        return true;
                    case ACTION_UP:
                        CreateObject(new FriendlyMissile(motionEvent.getX(), motionEvent.getY()));
                    case ACTION_POINTER_UP:
                        return true;
                    case ACTION_CANCEL:
                        return true;

                }

                return false;
            }
        });

    }


    @Override
    protected void onDraw(Canvas canvas) {

        SetColors();


        for(IDrawable drawable : drawables)
        {
            drawable.Draw(canvas);
        }

        DrawLauncher(canvas);
        DrawHealthBar(canvas);
    }

    private void SetColors()
    {
        // Launcher
        launcherPaint.setColor(Color.BLUE);
        launcherPaint.setStyle(Paint.Style.FILL);

        // HealthBar
        healthPaint.setColor(Color.GREEN);
        healthPaint.setStyle(Paint.Style.FILL);

        backHealthPaint.setColor(Color.RED);
        backHealthPaint.setStyle(Paint.Style.FILL);
    }

    private void DrawLauncher(Canvas canvas)
    {
        canvas.save();

        canvas.translate(canvas.getWidth()/2, canvas.getHeight() - canvas.getHeight()/10);
        canvas.scale(1, -1);

        canvas.drawCircle(0,0,canvas.getWidth()/10,launcherPaint);

        canvas.restore();

    }

    private void DrawHealthBar(Canvas canvas)
    {
        canvas.save();

        canvas.translate(0, canvas.getHeight());
        canvas.scale(1,-1);

        canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight()/10, backHealthPaint);
        canvas.drawRect(0,0,canvas.getWidth() - (hitsTaken * canvas.getWidth()/20),canvas.getHeight()/10, healthPaint);

        canvas.restore();
    }


    private void DrawPlayerMissiles(Canvas canvas)
    {

    }

    @Override
    public void Update() {

        for(IUpdatable updatable : updatables)
        {
            updatable.Update();
        }

        CreateMissiles();

        invalidate();
    }

    private void CreateMissiles()
    {
        counter += 0.01f;
        if(counter >= 2.0f)
        {
            counter = 0.0f;
            canShoot = true;
        }

        if(canShoot)
        {
            CreateObject(new Missile());
            canShoot = false;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        for(ITouchable touchable : touchables)
        {
            if(touchable.OnTouch(event))
            {
                return true;
            }
        }
        return super.onTouchEvent(event);
    }


    public void CreateObject(Object obj)
    {
        if(obj instanceof  IDrawable)
        {
            drawables.add((IDrawable) obj);
        }
        if(obj instanceof  IUpdatable)
        {
            updatables.add((IUpdatable) obj);
        }
        if(obj instanceof  ITouchable)
        {
            touchables.add((ITouchable) obj);
        }
    }

    public void DestroyObject(Object obj)
    {
        if(obj instanceof IDrawable)
        {
            drawables.remove(obj);
        }
        if(obj instanceof  IUpdatable)
        {
            updatables.remove(obj);
        }
        if(obj instanceof  ITouchable)
        {
            touchables.remove(obj);
        }
    }

    public static int GetRandomPos(Canvas canvas)
    {
        int Pos = rand.nextInt(canvas.getWidth());
        return Pos;
    }
    
    public static void GetHit()
    {
        hitsTaken += 1;
    }
}
