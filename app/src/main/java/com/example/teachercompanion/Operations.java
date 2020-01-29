package com.example.teachercompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;


public class Operations extends AppCompatActivity {
    GridLayout mainGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operations);
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
                        Intent intent= new Intent(Operations.this,LessonPlan.class);
                        startActivity(intent);
                    }else if(finali==1){
                        Intent intent=new Intent(Operations.this,ReminderAdd.class);
                        startActivity(intent);
                    }else if(finali==2){
                        Intent intent=new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse("https://www.freekcsepastpapers.com/kcse-past-papers/"));
                        startActivity(intent);

                    }
                    else if(finali==3){
                        Intent intent=new Intent(Operations.this,Profile.class);
                        startActivity(intent);

                    }



                }
            });
        }
    }
}
