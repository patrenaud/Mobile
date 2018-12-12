package ca.patrickrenaudbart.missilecommand;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Missile implements IDrawable, IUpdatable {

    public static Paint paint = new Paint();
    private float line;
    private float StartPos, EndPos;
    private boolean posSet = false;
    private boolean isShooting = true;

    @Override
    public void Draw(Canvas canvas)
    {
        if(!posSet)
        {
            SetPos(canvas);
            posSet = true;
        }

        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);

        canvas.save();
        canvas.translate(StartPos, 0);
        canvas.drawLine(0, 0,
                (EndPos - StartPos)* line ,(canvas.getHeight() - canvas.getHeight()/10) * line, paint);
        canvas.restore();
    }

    @Override
    public void Update()
    {
        if(isShooting)
        {
            line += 0.002;
            if(line >= 1)
            {
                GameView.GetHit();
                line = 0;
                isShooting = false;
            }
        }
    }

    private void SetPos(Canvas canvas)
    {
        StartPos = GameView.GetRandomPos(canvas);
        EndPos = GameView.GetRandomPos(canvas);
    }
}
