package com.example.teachercompanion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SIGNUP extends AppCompatActivity {
EditText mname,mtno,midno,mpno,muname,mpass,mcpass;
Button mregister,mlogin;
ProgressDialog pd;
FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mname=findViewById(R.id.tname);
        mtno=findViewById(R.id.tnumber);
        midno=findViewById(R.id.idnum);
        mpno=findViewById(R.id.pno);
        muname=findViewById(R.id.uname);
        mpass=findViewById(R.id.pass);
        mcpass=findViewById(R.id.cpass);
        mregister=findViewById(R.id.register);
        db=FirebaseFirestore.getInstance();
        pd=new ProgressDialog(this);
        mlogin=findViewById(R.id.login);
        mregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // Sign up
                final String tname=mname.getText().toString().trim();
                String tscno=mtno.getText().toString().trim();
                String idno=midno.getText().toString().trim();
                String phone=mpno.getText().toString().trim();
                String user=muname.getText().toString().trim();
                String pass=mpass.getText().toString().trim();
                String copass=mcpass.getText().toString().trim();
                if(TextUtils.isEmpty(tname)){
                    mname.setError("ENTER YOUR NAME");
                }else if(TextUtils.isEmpty(tscno)){
                    mtno.setError("ENTER YOUR TSC NUMBER ");
                }else if(TextUtils.isEmpty(idno)){
                    midno.setError("ENTER YOUR ID NUMBER");
                }else if(TextUtils.isEmpty(phone)){
                    mpno.setError("ENTER YOUR PHONE NUMBER");
                }else if(TextUtils.isEmpty(user)){
                    muname.setError("ENTER YOUR USERNAME");
                }else if(TextUtils.isEmpty(pass)){
                    mpass.setError("ENTER YOUR PASSWORD");
                }else if(TextUtils.isEmpty(copass)){
                    mcpass.setError("ENTER YOUR CONFIRMATION PASSWORD");
                }else if(!pass.equals(copass)){
                    Toast.makeText(getApplicationContext(),"Passwords Do Not Match",Toast.LENGTH_SHORT).show();
                }else {
                    pd.setTitle("Adding "+tname+" Please Wait..");
                    pd.show();
                   Map<String, Object> map = new HashMap<>();
                    map.put("TEACHER NAME", tname);
                    map.put("TSC NUMBER ", tscno);
                    map.put("IDENTITY NUMBER", idno);
                    map.put("PHONE NUMBER", phone);
                    map.put("USERNAME", user);
                    map.put("PASSWORD", pass);
                    db.collection("Teachers")
                            .document(user)
                            .set(map)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getApplicationContext(), tname + "Added", Toast.LENGTH_SHORT).show();
                                    mname.setText("");
                                    mtno.setText("");
                                    midno.setText("");
                                    mpno.setText("");
                                    muname.setText("");
                                    mpass.setText("");
                                    mcpass.setText("");
                                    pd.dismiss();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    //display error message
                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                    mname.setText("");
                                    mtno.setText("");
                                    midno.setText("");
                                    mpno.setText("");
                                    muname.setText("");
                                    mpass.setText("");
                                    mcpass.setText("");
                                    pd.dismiss();
                                }
                            });

                }
            }
        });
        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SIGNUP.this,LOGIN.class);
                startActivity(intent);
            }
        });
    }
}
