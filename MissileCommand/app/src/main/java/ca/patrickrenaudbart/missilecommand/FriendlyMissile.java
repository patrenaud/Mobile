package ca.patrickrenaudbart.missilecommand;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class FriendlyMissile implements IDrawable, IUpdatable {

    private float PosX, PosY, tempX, tempY;
    private boolean isShooting = true;
    private float line = 0.0f;

    public static Paint paint = new Paint();

    public FriendlyMissile(float x, float y) {
        tempX = x;
        tempY = y;
    }

    @Override
    public void Draw(Canvas canvas) {

        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);

        canvas.save();
        canvas.translate(canvas.getWidth()/2, canvas.getHeight()-canvas.getHeight()/10);
        canvas.scale(1, -1);
        SetPos(canvas);
        canvas.drawLine(0,0,PosX * line, PosY * line, paint);

        canvas.restore();
    }

    private void SetPos(Canvas canvas)
    {
        PosX = tempX - canvas.getWidth()/2;
        PosY = canvas.getHeight() - tempY - canvas.getHeight()/10;
    }

    @Override
    public void Update()
    {
        if(isShooting)
        {
            line += 0.01;
            if(line >= 1)
            {
                // CREATE CIRCLE
                line = 0;
                isShooting = false;
            }
        }
    }
}
