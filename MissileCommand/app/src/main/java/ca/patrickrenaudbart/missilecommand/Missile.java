package ca.patrickrenaudbart.missilecommand;

import android.graphics.Canvas;
import android.graphics.Paint;


public class Missile implements IDrawable, IUpdatable {

    public static Paint paint = new Paint();
    private float line;

    @Override
    public void Draw(Canvas canvas)
    {
        canvas.save();
        canvas.drawLine(GameView.GetRandomPos(canvas), 0,
                GameView.GetRandomPos(canvas) * line,(canvas.getHeight() - canvas.getHeight()/10) * line, paint);
        canvas.restore();
    }

    @Override
    public void Update()
    {
        line += 0.001;
        if(line >= 1)
        {
            //System.exit(0);
        }
    }
}
