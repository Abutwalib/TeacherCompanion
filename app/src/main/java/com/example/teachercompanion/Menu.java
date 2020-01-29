package com.example.teachercompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.teachercompanion.SIGNUP;

public class Menu extends AppCompatActivity {
    GridLayout mainGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mainGrid=(GridLayout)findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);

    }

    private void setSingleEvent(GridLayout mainGrid) {
        for (int i=0;i<mainGrid.getChildCount();i++){
            CardView cardView=(CardView) mainGrid.getChildAt(i);

            final int finali=i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(finali==0){
                        Intent intent=new Intent(Menu.this, SIGNUP.class);
                        startActivity(intent);
                    }else if(finali==1){
                        Intent intent= new Intent(getApplicationContext(),LOGIN.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }
}
