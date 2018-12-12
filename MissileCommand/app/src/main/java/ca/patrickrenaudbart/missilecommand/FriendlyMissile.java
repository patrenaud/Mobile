package ca.patrickrenaudbart.missilecommand;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class FriendlyMissile implements IDrawable, IUpdatable {

    private float PosX, PosY;

    public static Paint paint = new Paint();

    public FriendlyMissile(float x, float y) {
        PosX = x;
        PosY = y;
    }


    @Override
    public void Draw(Canvas canvas) {

        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);

        canvas.save();
        canvas.translate(canvas.getWidth()/2, canvas.getHeight()-canvas.getHeight()/10);
        canvas.scale(1, -1);
        // Draw line from 0,0 to touch position
        canvas.restore();

    }

    @Override
    public void Update() {

    }
}
