package ca.patrickrenaudbart.missilecommand;

import android.graphics.Canvas;
import android.graphics.Paint;

public class FriendlyMissile implements IDrawable, IUpdatable {


    public static Paint paint = new Paint();


    @Override
    public void Draw(Canvas canvas) {

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
