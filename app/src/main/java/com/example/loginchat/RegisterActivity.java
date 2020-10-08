package com.example.loginchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    EditText entername,enteremail,enterpassword;
    Button signup;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        enteremail = findViewById(R.id.enteremail);
        entername = findViewById(R.id.entername);
        enterpassword = findViewById(R.id.enterpassword);
        signup = findViewById(R.id.btnsignup);
        firebaseAuth=FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=enteremail.getText().toString();
                String password=enterpassword.getText().toString();
                String user_name=entername.getText().toString();

                if (TextUtils.isEmpty(email)||TextUtils.isEmpty(password)||TextUtils.isEmpty(user_name)){
                    Toast.makeText(RegisterActivity.this, "Please Fill All Required Fields", Toast.LENGTH_SHORT).show();
                }else{
                    RegisterBase(user_name,email,password);
                }
            }
        });
    }
private void RegisterBase(final String username, String useremail, String userpasswrod){
        firebaseAuth.createUserWithEmailAndPassword(useremail,userpasswrod).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                    String id=firebaseUser.getUid();
                    databaseReference= FirebaseDatabase.getInstance().getReference("MyUsers").child(id);
                    HashMap<String,String>hashMap=new HashMap<>();
                    hashMap.put("Username",username);
                    hashMap.put("UserId",id);
                    hashMap.put("Image","default");
                    databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Intent i=new Intent(RegisterActivity.this,MainActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                                finish();
                            }else{
                                Toast.makeText(RegisterActivity.this, "Regter Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

}
}