package ca.patrickrenaudbart.labo2;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    String name = "";
    TextView Title = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText EditName = findViewById(R.id.EditField);

        Title = findViewById(R.id.TextField);


        Button button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = EditName.getText().toString();
                Title.setText(getString(R.string.Greeting) + " " + name);
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {


        outState.putString("name", name);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        name = savedInstanceState.getString("name", "");
        Title.setText(getString(R.string.Greeting) + " " + name);

        super.onRestoreInstanceState(savedInstanceState);
    }
}
