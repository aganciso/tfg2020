package com.example.alfonsogonzaleztfg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DigidexHome extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView digimonName;
    TextView digimonType;
    TextView digimonLevel;
    TextView digimonSignatureMove;
    TextView digimonDescription;
    ImageView digimonImage;
    Spinner spinnerDigimon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digidex_home);
        loadTestData();
        digimonName = findViewById(R.id.digimonName);
        digimonType = findViewById(R.id.digimonType);
        digimonLevel = findViewById(R.id.digimonLevel);
        digimonSignatureMove = findViewById(R.id.digimonSignatureMove);
        digimonDescription = findViewById(R.id.digimonDescription);
        digimonImage = findViewById(R.id.digimonImage);
        spinnerDigimon = (Spinner) findViewById(R.id.spinnerDigimon);
        spinnerDigimon.setOnItemSelectedListener(this);
    }

    private void loadTestData() {
        DataHelper dataHelper=new DataHelper(this);
        dataHelper.insertDigimon("Agumon", Type.VACCINE, Level.ROOKIE, "Pepper Breath",
                "It has grown up and become able to walk on two legs, and has an appearance like a tiny dinosaur. " +
                        "Because it is still on the way to adulthood, " +
                        "its power is low, but as its personality is quite ferocious, it doesn't understand fear.",
                R.drawable.agumon);
        dataHelper.insertDigimon("Gabumon", Type.VACCINE, Level.ROOKIE, "Blue Flame",
                "Although it is covered by a fur pelt, it is still clearly a Reptile Digimon. " +
                        "Due to its extremely timid and shy personality, it always gathers up the data which Garurumon leaves behind, " +
                        "and shapes it into a fur pelt to wear.",
                R.drawable.gabumon);
        dataHelper.insertDigimon("Patamon", Type.DATA, Level.ROOKIE, "Air Shoot",
                "It is characterized by its large ears, and is able to fly through the air by using them as large wings, " +
                        "but because it only goes at a speed of 1 kph, it is said that it is definitely faster walking.",
                R.drawable.patamon);
        dataHelper.insertDigimon("Biyomon", Type.VACCINE, Level.ROOKIE, "Spiral Twister",
                "Its wings have developed to become like arms and it is able to dexterously operate them and use them to grip objects, " +
                        "but for that reason, it is poor at flying through the air.",
                R.drawable.biyomon);
        dataHelper.insertDigimon("Gatomon", Type.VACCINE, Level.CHAMPION, "Lighting Paw",
                "It has a very healthy curiosity, so it loves pranks. Although its body is small, it is a precious Holy-species Digimon," +
                        " and its appearance does not match the true strength it possesses.",
                R.drawable.tailmon);
        dataHelper.insertDigimon("Beelzemon", Type.VIRUS, Level.MEGA, "Double Impact",
                "It is one of the \"Seven Great Demon Lords\", representing Venus and the sin of Gluttony. " +
                        "While it possesses the power to preside over the many Devil Digimon, it dares to observe a solitary existence.",
                R.drawable.beelzemon);
        ArrayList<String> listDigimon=dataHelper.getAllDigimon();
        Spinner sp = (Spinner)findViewById(R.id.spinnerDigimon);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout,R.id.txt, listDigimon);
        sp.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        DataHelper dataHelper = new DataHelper(this);
        digimonName.setText(dataHelper.getDigimonInfo(item, Column.digimonName));
        digimonType.setText(dataHelper.getDigimonInfo(item, Column.digimonType));
        digimonLevel.setText(dataHelper.getDigimonInfo(item, Column.digimonLevel));
        digimonSignatureMove.setText(dataHelper.getDigimonInfo(item, Column.digimonSignatureMove));
        digimonDescription.setText(dataHelper.getDigimonInfo(item, Column.digimonDescription));
        digimonImage.setImageResource(dataHelper.getDigimonImage(item));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}