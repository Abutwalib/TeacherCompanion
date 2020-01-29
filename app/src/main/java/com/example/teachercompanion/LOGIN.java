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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Source;

import java.util.Map;

public class LOGIN extends AppCompatActivity {
EditText uname,password;
Button login,register;
FirebaseFirestore db;
ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uname=findViewById(R.id.uname);
        password=findViewById(R.id.pass);
        login=findViewById(R.id.login);
        register=findViewById(R.id.register);
        db=FirebaseFirestore.getInstance();
        pd=new ProgressDialog(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String name=uname.getText().toString().trim();
              final String pass=password.getText().toString().trim();
              if(TextUtils.isEmpty(name)){
                  uname.setError("Username Required");
              }else if(TextUtils.isEmpty(pass)){
                  password.setError("Password Required");
              }else{
                  pd.setTitle("Authenticating... PLease Wait");
                  pd.show();
                  db.collection("Teachers")
                          .document(name)
                          .get(Source.DEFAULT)
                          .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                              @Override
                              public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                  if(task.isSuccessful()){
                                      DocumentSnapshot doc=task.getResult();
                                      if(doc.exists()){
                                          Map map=doc.getData();
                                          if(pass.equals(map.get("PASSWORD"))){
                                              Intent intent=new Intent(LOGIN.this,Operations.class);
                                              startActivity(intent);
                                              uname.setText("");
                                              password.setText("");
                                              pd.dismiss();

                                          }else{
                                             Toast.makeText(getApplicationContext(),"AUTHENTICATION FAILED",Toast.LENGTH_SHORT).show();
                                              uname.setText("");
                                              password.setText("");
                                              pd.dismiss();
                                             pd.dismiss();
                                          }
                                      }else{
                                          Toast.makeText(getApplicationContext(),"AUTHENTICATION FAILED",Toast.LENGTH_SHORT).show();
                                          uname.setText("");
                                          password.setText("");
                                          pd.dismiss();
                                          pd.dismiss();

                                      }
                                  }
                              }
                          })
                          .addOnFailureListener(new OnFailureListener() {
                              @Override
                              public void onFailure(@NonNull Exception e) {
                                  //get and display an error message
                                  Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                                  uname.setText("");
                                  password.setText("");
                                  pd.dismiss();
                              }
                          });


              }

            }
        });
      register.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(LOGIN.this,SIGNUP.class);
              startActivity(intent);
          }
      });
    }
}
