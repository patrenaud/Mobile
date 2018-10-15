package ca.patrickrenaudbart.minesweaper;


import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {

    public int[] data = new int[100];
    public static final int FLAG = 2;
    public static final int MINE = 1;
    public static final int EXPOSED = 0;
    GridLayout grid;

    Button startButton;
    TextView mineCounter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grid = findViewById(R.id.grid);

        mineCounter = findViewById(R.id.Counter);
        mineCounter.setText("Mines Remaining: 10");

        startButton = findViewById(R.id.Starter);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Restart();
            }
        });


        SetBombPos();

        // This is to access OnCellClick on every Buttons
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                final int x = j;
                final int y = i;
                int index = x + y * 10;

                Button button = (Button) grid.getChildAt(index);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OnCellClicked(x, y);
                    }
                });
                button.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        OnLongCellClicked(x, y);
                        return true;
                    }
                });
            }
        }
    }

    public void Restart()
    {
        Refresh();
        SetBombPos();
    }

    public void Refresh()
    {
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                int index = i + j * 10;
                Button button = (Button) grid.getChildAt(index);
                button.setBackgroundResource(R.drawable.ic_button_normal);
                TurnBitOff(index, EXPOSED);
                TurnBitOff(index, FLAG);
                TurnBitOff(index, MINE);
                button.setEnabled(true);
            }
        }
        SetBombPos();
    }


    public void OnLongCellClicked(int x, int y) {
        int index = x + y * 10;
        Button button = (Button) grid.getChildAt(index);


        if (button.isClickable() && !IsBitOn(index, EXPOSED)) {
            button.setBackgroundResource(R.drawable.ic_button_flag);
            button.setClickable(false);
            TurnBitOn(index, FLAG);

        }
        else if (!IsBitOn(index, EXPOSED)){
            button.setClickable(true);
            button.setBackgroundResource(R.drawable.ic_button_normal);
            TurnBitOff(index, FLAG);

        }

        SetMineCounter(x, y);
    }

    public void SetMineCounter(int x, int y)
    {
        int counter = 10;

        for(int i = 0; i < 100; i++)
        {
            if(IsBitOn(i, FLAG))
            {
                counter -= 1;
            }
        }
        mineCounter.setText("Mines Remaining: " + counter);
    }

    public void SetBombPos() {
        ArrayList<Integer> bombs = new ArrayList<>(100);

        for (int i = 0; i < 100; ++i) {
            bombs.add(i);
        }

        Collections.shuffle(bombs);

        for (int i = 0; i < 10; i++) {
            int index = bombs.get(i);
            TurnBitOn(index, MINE);
        }
    }

    public void OnCellClicked(int x, int y) {

        int index = x + y * 10;
        if(!IsBitOn(index, EXPOSED) && !IsBitOn(index,FLAG))
        {
            Reveal(x, y);
        }


        int winCounter = 0;
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                int newIndex = i + j * 10;
                if(IsBitOn(newIndex, EXPOSED))
                {
                    winCounter += 1;
                    if(winCounter == 90)
                    {
                        BlockAllCells();
                    }
                }
            }
        }
    }

    public void Reveal(int x, int y) {
        int index = x + y * 10;
        Button button = (Button) grid.getChildAt(index);
        TurnBitOn(index, EXPOSED);

        if (IsBitOn(index, MINE)) {

            // GameOver

            for (int i = 0; i < 10; i++) {

                for (int j = 0; j < 10; j++) {
                    if(i == x && j == y)
                    {
                        button.setBackgroundResource(R.drawable.ic_button_bombblow);
                        continue;
                    }
                    RevealAll(i,j);

                }
            }

            BlockAllCells();

        }
        else {
            switch (CountBombs(x, y)) {
                case 1:
                    button.setBackgroundResource(R.drawable.ic_button_1);
                    break;
                case 2:
                    button.setBackgroundResource(R.drawable.ic_button_2);
                    break;
                case 3:
                    button.setBackgroundResource(R.drawable.ic_button_3);
                    break;
                case 4:
                    button.setBackgroundResource(R.drawable.ic_button_4);
                    break;
                case 5:
                    button.setBackgroundResource(R.drawable.ic_button_5);
                    break;
                case 6:
                    button.setBackgroundResource(R.drawable.ic_button_6);
                    break;
                case 7:
                    button.setBackgroundResource(R.drawable.ic_button_7);
                    break;
                case 8:
                    button.setBackgroundResource(R.drawable.ic_button_8);
                    break;
                case 0:
                    button.setBackgroundResource(R.drawable.ic_button_empty);
                    RevealOthers(x, y);
                    break;
            }
        }
    }

    public void RevealAll(int x, int y)
    {
        int index = x + y * 10;
        Button button = (Button) grid.getChildAt(index);

        switch (CountBombs(x, y)) {
            case 1:
                button.setBackgroundResource(R.drawable.ic_button_1);
                break;
            case 2:
                button.setBackgroundResource(R.drawable.ic_button_2);
                break;
            case 3:
                button.setBackgroundResource(R.drawable.ic_button_3);
                break;
            case 4:
                button.setBackgroundResource(R.drawable.ic_button_4);
                break;
            case 5:
                button.setBackgroundResource(R.drawable.ic_button_5);
                break;
            case 6:
                button.setBackgroundResource(R.drawable.ic_button_6);
                break;
            case 7:
                button.setBackgroundResource(R.drawable.ic_button_7);
                break;
            case 8:
                button.setBackgroundResource(R.drawable.ic_button_8);
                break;
            case 0:
                button.setBackgroundResource(R.drawable.ic_button_empty);
                break;
        }


            if(IsBitOn(index, FLAG) && !IsBitOn(index, MINE) && !IsBitOn(index, EXPOSED))
            {
                button.setBackgroundResource((R.drawable.ic_button_flagerror));
            }
            else if(IsBitOn(index, FLAG) && IsBitOn(index, MINE) && !IsBitOn(index, EXPOSED))
            {
                button.setBackgroundResource((R.drawable.ic_button_flag));
            }
            else if(IsBitOn(index, MINE) && !IsBitOn(index, EXPOSED) && !IsBitOn(index, FLAG))
            {
                button.setBackgroundResource(R.drawable.ic_button_bomb);
            }

    }

    public void RevealOthers(int x, int y) {
        for (int i = x - 1; i <= x + 1; i++) {
            if (i < 0 || i > 9) {
                continue;
            }
            for (int j = y - 1; j <= y + 1; j++) {
                if (j < 0 || j > 9) {
                    continue;
                }
                int index = i + j * 10;
                if (!IsBitOn(index, EXPOSED) && !IsBitOn(index, MINE) && !IsBitOn(index, FLAG)){
                    Reveal(i, j);
                }
            }
        }
    }

    public int CountBombs(int x, int y) {

        int count = 0;

        for (int i = x - 1; i <= x + 1; i++) {
            if (i < 0 || i > 9) {
                continue;
            }
            for (int j = y - 1; j <= y + 1; j++) {
                if (j < 0 || j > 9) {
                    continue;
                }

                int newIndex = i + j * 10;
                if (IsBitOn(newIndex, MINE)) {
                    count += 1;
                }
            }
        }
        return count;
    }

    public void BlockAllCells()
    {
        for (int i = 0; i < 10; i++) {

            for (int j = 0; j < 10; j++) {
                int index = i + j * 10;
                Button button = (Button) grid.getChildAt(index);
                button.setEnabled(false);

            }
        }
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
