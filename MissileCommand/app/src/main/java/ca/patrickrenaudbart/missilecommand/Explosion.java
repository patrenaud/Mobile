package ca.patrickrenaudbart.missilecommand;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

public class Explosion implements  IDrawable, IUpdatable {


    private boolean isExploding = true;
    private boolean isShrinking = false;
    private float posX, posY, tempX, tempY;
    private float circle = 0.0f;


    public static Paint explosionpaint = new Paint();

    public Explosion(float x, float y) {

        tempX = x;
        tempY = y;
    }


    @Override
    public void Draw(Canvas canvas) {

        explosionpaint.setColor(Color.WHITE);
        explosionpaint.setStyle(Paint.Style.FILL);

        canvas.save();
        canvas.translate(canvas.getWidth()/2, canvas.getHeight()-canvas.getHeight()/10);
        canvas.scale(1, -1);
        SetPos(canvas);
        canvas.drawLine(0,0,posX * circle, posY * circle, explosionpaint);
        canvas.restore();
    }

    private void SetPos(Canvas canvas)
    {
        posX = tempX - canvas.getWidth()/2;
        posY = canvas.getHeight() - tempY - canvas.getHeight()/10;
    }


    @Override
    public void Update() {

        if(isExploding)
        {
            if(!isShrinking)
            {
                circle += 0.002f;
                if(circle >= 1)
                {
                    isShrinking = true;
                }
            }

            else
            {
                circle -= 0.002f;
                if(circle <=0)
                {
                    isExploding = false;
                    circle = 0;
                }
            }
        }

    }
}
