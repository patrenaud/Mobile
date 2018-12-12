package ca.patrickrenaudbart.missilecommand;

import android.graphics.Canvas;
import android.graphics.Paint;


public class Missile implements IDrawable, IUpdatable {

    public static Paint paint = new Paint();




    @Override
    public void Draw(Canvas canvas)
    {
        canvas.save();
        canvas.translate(0, -canvas.getHeight());
        canvas.drawLine(GameView.GetRandomPos(canvas),0,GameView.GetRandomPos(canvas),canvas.getHeight()*2, paint);
        canvas.restore();
    }

    @Override
    public void Update()
    {


    }
}
