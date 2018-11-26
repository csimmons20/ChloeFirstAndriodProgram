package sla.org.chloefirstandriodprogram;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Model {

    Context CText;


    private String TextFieldText;
    private String CBchoice;
    private ArrayList<String> BottomListViewTexts;


    Model(Context mycontext) {
        CText = mycontext;

        TextFieldText = "";
        CBchoice = "";
        BottomListViewTexts = new ArrayList();

       try {
           // Open file to read saved text
           System.out.println("Model() opening SavedText.txt");
           File savedText = new File(CText.getFilesDir(), "Saved.txt");
           if (savedText.exists()) {
               System.out.println("Model() found Saved.txt");
               BufferedReader input = new BufferedReader(new FileReader(savedText));

               TextFieldText = input.readLine();
               System.out.println("Model() read topTextFieldText:" + TextFieldText);
               CBchoice = input.readLine();
               System.out.println("Model() read topTextFieldText:" + CBchoice);

               String newBottomListText = input.readLine();
               int i = 1;
               System.out.println("Model() read newBottomListText " + i++ + ":" + newBottomListText);
               while (newBottomListText != null) {
                   BottomListViewTexts.add(newBottomListText);
                   newBottomListText = input.readLine();
                   System.out.println("Model() read newBottomListText " + i++ + ":" + newBottomListText);
               }

               System.out.println("Model() closing Saved.txt");
               // Close file
               input.close();
               System.out.println("Model() closed Saved.txt");
           }
       } catch (Exception e) {
           System.out.println("Model() threw exception");
           e.printStackTrace();
       }
    }
    void save() {
        try {
            System.out.println("Model.save() opening Saved.txt");
            // Create the file if necessary
            File savedText = new File(CText.getFilesDir(), "Saved.txt");

            // Write the final model to a saved file
            BufferedWriter writers = new BufferedWriter(new FileWriter(savedText));
            if (writers != null) {
                if (TextFieldText != null) {
                    System.out.println("Model.save() wrote TextFieldText: " + TextFieldText);
                    writers.write(TextFieldText);
                } else {
                    System.out.println("Model.save() wrote TextFieldText: ");
                    writers.write("");
                }
                writers.newLine();
                if (CBchoice != null) {
                    System.out.println("Model.save() wrote CBchoice: " + CBchoice);
                    writers.write(CBchoice);
                } else {
                    System.out.println("Model.save() wrote CBchoice: ");
                    writers.write("");
                }
                writers.newLine();
                int length = BottomListViewTexts.size();
                if (length > 0) {
                    for (int i = 0; i < length; i++) {
                        System.out.println("Model.save() wrote BottomListViewTexts" + i + ": " + BottomListViewTexts.get(i));
                        writers.write(BottomListViewTexts.get(i));
                        writers.newLine();
                    }
                } else {
                    System.out.println("Model.save() wrote BottomListViewTexts: ");
                    writers.write("");
                    writers.newLine();
                }
            }
            System.out.println("Model.save() closing Saved.txt");
            writers.close();
            System.out.println("Model.save() closed Saved.txt");

        } catch (Exception e) {
            System.out.println("Model.save() threw exception!!!");
            e.printStackTrace();
        }

    }

    String getTextFieldText() {
        return TextFieldText;
    }
    ArrayList<String> getBottomListViewTexts() { return BottomListViewTexts; }
    String getCBchoice() {return CBchoice;}


    void setAllData(String updatedBottomTextFieldText, ArrayAdapter<String> listViewItems,
                    String UpdateCBchoice) {
        //Update the model with all text currently seen in View
        TextFieldText = updatedBottomTextFieldText;
        CBchoice = UpdateCBchoice;

        int length = listViewItems.getCount();
        BottomListViewTexts.clear();
        for (int i = 0; i < length; i++) {
            BottomListViewTexts.add(listViewItems.getItem(i));
        }
            }

    }


