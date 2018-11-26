package sla.org.chloefirstandriodprogram;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;



public class AndriodActivity extends AppCompatActivity {

    Controller myController;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_andriod);


        ImageView theImage = findViewById(R.id.imageView);
        theImage.setImageResource(R.drawable.purplebutterfly);

        TextView QO = findViewById(R.id.questionOne);
        TextView QT = findViewById(R.id.questionTwo);
        EditText YR = findViewById(R.id.Response);

        Spinner SP = findViewById(R.id.Spin);
        String[] objects = {"I love it", "It's ok", "It's horrible"};
        ArrayAdapter BVarrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1 , objects);
        SP.setAdapter(BVarrayAdapter);
        SP.setSelection(0);

        Context mycontext;


        ListView LA = findViewById(R.id.listAnswers);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        LA.setAdapter(arrayAdapter);



        myController = new Controller(YR, LA, SP, getApplicationContext(), theImage);
    }

    @Override
    protected void onStop() {
        myController.save();
        super.onStop();
    }
}
