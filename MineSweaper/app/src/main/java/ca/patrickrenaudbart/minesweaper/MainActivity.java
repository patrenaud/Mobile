package ca.patrickrenaudbart.minesweaper;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public int[] data = new int[100];
    public static final int FLAG = 2;
    public static final int MINE = 1;
    public static final int EXPOSED = 0;
    GridLayout grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grid = findViewById(R.id.grid);

        // This is to get random bombs location
        ArrayList<Integer> bombs = new ArrayList<Integer>(10);
        for (int i = 0; i < 10; i++)
        {
            int number = GetRandomNumbers(0,100);
            bombs.add(new Integer(number));
        }
        SetBombs(bombs);

        // This is to access OnCellClick on every Buttons
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

    public int GetRandomNumbers(int min, int max)
    {

        return new Random().nextInt((max - min) + 1) + min;
    }

    public void SetBombs(ArrayList<Integer> bombs)
    {

        for(int i = 0; i < bombs.size(); i++)
        {
            TurnBitOn(data[bombs.get(i)], 1);
        }
    }


    public void OnCellClicked(int x, int y)
    {
        int index = x + y * 10;
        Button button = (Button) grid.getChildAt(index);

        if(IsBitOn(index, FLAG))
        {
            button.setBackgroundResource(R.drawable.ic_button_flag);
        }
        else if(IsBitOn(index, MINE))
        {
            // GameOver
            button.setBackgroundResource((R.drawable.ic_button_bombblow));
        }
        else
        {
            button.setBackgroundResource(R.drawable.ic_button_1);
        }


        // Reveal(x, y);

        //Refresh()
    }

    public void Reveal(int x, int y)
    {

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
