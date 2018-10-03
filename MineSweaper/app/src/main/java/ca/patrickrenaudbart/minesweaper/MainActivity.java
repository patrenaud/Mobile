package ca.patrickrenaudbart.minesweaper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    public int[] data = new int[100];
    public static final int MINE = 0;
    public static final int EXPOSED = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout grid = findViewById(R.id.grid);
        for(int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                final int x = j;
                final int y = i;
                int index = x + y * 10;

                Button button = (Button) grid.getChildAt(index);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OnCellClicked(x,y);
                    }
                });
            }
        }
    }

    public void OnCellClicked(int x, int y)
    {
        //Reveal(x, y)
        //Refresh()
    }

    public boolean IsBitOn(int index, int n)
    {
        int value = 1 << n;

        return (data[index] & value) == value;
    }

    public void TurnBitOn(int index, int n)
    {

        int value = 1 << n;
        data[index] |= value;
    }

    public void TurnBitOff(int index, int n)
    {
        int value = 1 << n;
        data[index] &= ~value;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
