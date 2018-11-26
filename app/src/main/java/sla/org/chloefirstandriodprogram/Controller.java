package sla.org.chloefirstandriodprogram;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.media.Image;

import java.lang.reflect.Array;
import java.util.List;

import java.util.ArrayList;

public class Controller {


    private EditText TheResponse;
    private Spinner CB;
    private ListView BottomView;
    private ImageView Pictures;
    private ArrayAdapter<String> BVArrayAdapter;
    private ArrayAdapter<String> CBArrayAdapter;

    Image[] pictures;
    int currentPicture;




    private Model model;

    Controller(EditText TextField, ListView BottomListView, Spinner Choice, Context mycontext, ImageView Pics) {
        //Create Images
        currentPicture = 0;
            pictures = new Image[3];
            //pictures[0] = new Image();

        Pictures = Pics;
        TheResponse = TextField;
        CB = Choice;
        BottomView = BottomListView;
        BVArrayAdapter = (ArrayAdapter<String>)BottomView.getAdapter();
        CBArrayAdapter = (ArrayAdapter<String>)CB.getAdapter();


        model = new Model(mycontext);

        TheResponse.setText(model.getTextFieldText());

        String CBchoice = model.getCBchoice();
        if (CBchoice.equalsIgnoreCase("I love it")) {
            CB.setSelection(0);
        }
        if (CBchoice.equalsIgnoreCase("It's ok")) {
            CB.setSelection(1);
        }
        if (CBchoice.equalsIgnoreCase("It's horrible")) {
            CB.setSelection(2);
        }


        ArrayList BotListViewTexts = model.getBottomListViewTexts();
        for (int i = 0; i < BotListViewTexts.size(); i++) {
            BVArrayAdapter.add((String)BotListViewTexts.get(i));
        }



        TheResponse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {


                }
        });

        TheResponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextFieldReady();
            }
        });


        }

    private void TextFieldReady() {
        BVArrayAdapter.add(TheResponse.getText().toString());
        BVArrayAdapter.notifyDataSetChanged();
        TheResponse.setText("");

        StartAnew();
    }

    private void StartAnew() {
        //set cybertron image as first image
       // currentPicture = currentPicture + 1;
        //Pictures.setImageResource(pictures[currentPicture]);

        //String choiceFromCB = (String) CB.();
        //if (choiceFromCB.equals("")) {
            //return;
        //} else {
          //  if (CB.getValue().equals("I love it")) {

                //move to next image
            //    System.out.println("They have moved!");

            //} else if (CB.getValue().equals("It's okay")) {

                //move to next image
                //System.out.println("They have moved!");


            //} else  if (CB.getValue().equals("It's horrible")) {

                //move to next image
                //System.out.println("They have moved!");


            //}
            //Start Response over
            //CB.setValue(null);



       // }
    }

    public void save() {
        // push the latest GUI text into the model
        int cbPosition = CB.getSelectedItemPosition();
        String CBText = new String();
        if (cbPosition == 0) {
            CBText = "I love it";
        }
        if (cbPosition == 1) {
            CBText = "It's ok";
        }
        if (cbPosition == 2) {
            CBText = "It's horrible";
        }

        model.setAllData(TheResponse.getText().toString(), BVArrayAdapter, CBText);
        model.save();
    }
}


