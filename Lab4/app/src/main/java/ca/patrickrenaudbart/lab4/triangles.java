package ca.patrickrenaudbart.lab4;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

class triangles extends View {


    private float cx, cy, radius, scale, angle;
    private int sides;
    private float size;


    private Paint paint = new Paint();



    public triangles(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.triangles, defStyle, 0);
        try{

            paint.setColor(a.getColor(R.styleable.triangles_color, Color.BLUE));
            angle = a.getFloat(R.styleable.triangles_angle, 0);
            sides = a.getInt(R.styleable.triangles_sides, 3);
            size = a.getDimension(R.styleable.triangles_size, 50);
        }
        finally{
            a.recycle();
        }

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        cx = w/2;
        cy = h/2;
        radius = Math.min(cx, cy);
        scale = radius / 100.0f;

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.translate(cx,cy);
        canvas.scale(scale,scale);

        float internal = 360.0f/sides;
        float external = 180.0f - internal;

        int counter = sides;
        float shrink = size;



        while (counter > 2)
        {
            internal = 360.0f/counter;
            external = 180f - internal;


            for(int j = 0; j < sides; j++)
            {
                canvas.save();
                for(int i = 0; i < sides; i++)
                {
                    canvas.save();
                    {
                        canvas.translate(-shrink,0);

                        canvas.rotate(external/2);
                        canvas.drawLine(0,0,shrink,0,paint);
                        canvas.rotate(-external);
                        canvas.drawLine(0,0,shrink,0,paint);
                    }
                    canvas.restore();

                    canvas.rotate(internal);
                }
                canvas.restore();
                canvas.rotate(internal);
            }

            canvas.translate(-shrink,0);

            counter -= 1;
            shrink /= 2;
        }
    }

}



