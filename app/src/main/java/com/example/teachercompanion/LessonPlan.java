package com.example.teachercompanion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class LessonPlan extends AppCompatActivity {
    private static final int STORAGE_CODE =1000 ;
    EditText subj,matopic,letopic,lenumber,fo,obj,intro,ldeve,lconc;
    DatePicker dat;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_plan);
        subj=findViewById(R.id.subject);
        matopic=findViewById(R.id.mtopic);
        letopic=findViewById(R.id.ltopic);
        lenumber=findViewById(R.id.lno);
        fo=findViewById(R.id.form);
        obj=findViewById(R.id.objectives);
        intro=findViewById(R.id.introduction);
        ldeve=findViewById(R.id.ldevelopment);
        lconc=findViewById(R.id.lconclusion);
        save=findViewById(R.id.savetopdf);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //we need to handle runtime permissions for devices with  marshmallow and above
                if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
                    //system os >=marshmallow (6.0), check if permission is enabled or not
                    if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                        //permission was not granted request it
                        String[] permissions={Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions,STORAGE_CODE);

                    }else{
                        //PERMISSION ALREADY GRANTED,call savepdf method
                        SavePdf();

                    }

                }else{
                    //System os <Marshmallow not required to check runtime permission,call save pdf method
                    SavePdf();
                }
            }
        });
    }

    private void SavePdf() {
        //create object of document class
        Document mDoc=new Document();
        //pdf file name
        String mFileName=new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(System.currentTimeMillis());
        //pdf file path
        String mFilepath= Environment.getExternalStorageDirectory() +"/"+mFileName+".pdf";
        try{
            //make instance of pdf writer class
            PdfWriter.getInstance(mDoc,new FileOutputStream(mFilepath));
            //open document for writing
            mDoc.open();
            //getText from edit text field i.e text that was to be converted to pdf
            String sub="SUBJECT:"+subj.getText().toString();
            String major="MAJOR TOPIC: "+matopic.getText().toString();

            String les="LESSON TOPIC: "+letopic.getText().toString();
            String lno="LESSON NUMBER: "+lenumber.getText().toString();
            String form="CLASS: "+fo.getText().toString();
            String obje="OBJECTIVES: "+obj.getText().toString();
            String intrd="INTRODUCTION: "+intro.getText().toString();
            String lde="LESSON DEVELOPMENT:"+ldeve.getText().toString();
            String lco="LESSON CONCLUSION: "+lconc.getText().toString();
            String lplan=sub+"\n"+major+"\n"+les+"\n"+lno+"\n"+form+"\n"+obje+"\n"+intrd+"\n"+lde+"\n"+lco+"\n";
            //add Subject of the document
            mDoc.addSubject("OMAR DOCUMENT");
            mDoc.addTitle("LESSON PLAN FOR LESSON "+lno);
            //add author of the document
            mDoc.addAuthor("BOAZ OMARE");
            //add paragraph to the document
            mDoc.add(new Paragraph(lplan));

            //close the document
            mDoc.close();
            //show message that file is saved ,Show filename and filepath
            Toast.makeText(getApplicationContext(),mFileName+".pdf\n is saved to"+mFilepath,Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){
            //if anything goes wrong ,get and show exception message
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    //handle permission result

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case STORAGE_CODE:
            {
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //permission was granted from popup

                }else{
                    //permission was denied,show error message
                    Toast.makeText(getApplicationContext(),"Permission denied",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
