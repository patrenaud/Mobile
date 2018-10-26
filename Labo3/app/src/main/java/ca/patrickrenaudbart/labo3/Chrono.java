package ca.patrickrenaudbart.labo3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Paint;

class Chrono extends View {

    private float cx, cy, radius;
    private Paint paint = new Paint();
    private Paint Spaint = new Paint();
    private Paint Mpaint = new Paint();
    private Paint Lpaint = new Paint();

    private int seconds;
    private int minutes;
    private int hours;


    public Chrono(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Chrono, defStyle, 0);
        try{

            paint.setColor(a.getColor(R.styleable.Chrono_color, Color.BLUE));
            seconds = a.getInt(R.styleable.Chrono_seconds, 0);
            minutes = a.getInt(R.styleable.Chrono_minutes, 0);
            hours = a.getInt(R.styleable.Chrono_hours,0);

        }
        finally{

            a.recycle();
        }
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        Spaint.setStrokeWidth(2);
        Mpaint.setStrokeWidth(5);
        Lpaint.setStrokeWidth(10);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        cx = w / 2;
        cy = h / 2;
        radius = Math.min(cx, cy);
    }

    private void SetBaseLines(Canvas canvas)
    {
        canvas.drawCircle(cx, cy, radius, paint);
        for(int i = 0; i < 12; i++)
        {
            canvas.drawLine(
                    cx + 0.9f * radius * (float)Math.cos(Math.toRadians((i*30))),
                    cy - 0.9f * radius * (float)Math.sin(Math.toRadians((i*30))),
                    cx + radius * (float)Math.cos(Math.toRadians((i*30))),
                    cy - radius * (float)Math.sin(Math.toRadians((i*30))), paint);
        }
        for(int i = 0; i < 12; i++)
        {
            canvas.drawLine(
                    cx + 0.95f * radius * (float)Math.cos(Math.toRadians(105-(i*30))),
                    cy - 0.95f * radius * (float)Math.sin(Math.toRadians(105-(i*30))),
                    cx + radius * (float)Math.cos(Math.toRadians(105-(i*30))),
                    cy - radius * (float)Math.sin(Math.toRadians(105-(i*30))), paint);
        }
    }

    private void SetSeconds(Canvas canvas)
    {
        canvas.drawLine(cx, cy, cx + radius * (float)Math.cos(Math.toRadians(90 - seconds * 6)),
                cy - radius * (float)Math.sin(Math.toRadians(90 - seconds * 6)), Spaint);
    }
    private void SetMinutes(Canvas canvas)
    {
        canvas.drawLine(cx, cy, cx + 0.9f *radius * (float)Math.cos(Math.toRadians(90 - minutes * 6)),
                cy - 0.9f *radius * (float)Math.sin(Math.toRadians(90 - minutes * 6)), Mpaint);
    }
    private void SetHours(Canvas canvas)
    {
        canvas.drawLine(cx, cy, cx + 0.75f *radius * (float)Math.cos(Math.toRadians(90 - hours * 30)),
                cy - 0.75f *radius * (float)Math.sin(Math.toRadians(90 - hours * 30)), Lpaint);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        SetBaseLines(canvas);
        SetSeconds(canvas);
        SetMinutes(canvas);
        SetHours(canvas);

    }
}
