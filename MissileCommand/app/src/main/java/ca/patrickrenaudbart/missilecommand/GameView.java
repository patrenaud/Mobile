package ca.patrickrenaudbart.missilecommand;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;
import java.util.List;

import static android.view.MotionEvent.ACTION_CANCEL;
import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_POINTER_DOWN;
import static android.view.MotionEvent.ACTION_POINTER_UP;
import static android.view.MotionEvent.ACTION_UP;

class GameView extends View implements Constants, IUpdatable {

    private List<IDrawable> drawables = new LinkedList<>();
    private List<IUpdatable> updatables = new LinkedList<>();
    private List<ITouchable> touchables = new LinkedList<>();

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
                    case ACTION_POINTER_UP:
                        return true;
                    case ACTION_CANCEL:
                        return true;

                }

                return false;
            }
        });

    }

    // List des game objects


    @Override
    protected void onDraw(Canvas canvas) {

        for(IDrawable drawable : drawables)
        {
            drawable.Draw(canvas);
        }
    }

    @Override
    public void Update() {

        for(IUpdatable updatable : updatables)
        {
            updatable.Update();
        }
        invalidate();
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
}
