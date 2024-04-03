package com.example.lab6_md18309;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class resignter extends AppCompatActivity {
    private EditText emailedit,passedit;
    private Button btn_resister;
    private FirebaseAuth mAuth;
    Button btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resignter);
        btnback = findViewById(R.id.btnback_esis);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(resignter.this,login.class);
                startActivity(i);
            }
        });
        mAuth = FirebaseAuth.getInstance();
        emailedit = findViewById(R.id.Email_resis);
        passedit = findViewById(R.id.Pass_resis);
        btn_resister = findViewById(R.id.btnresis);
        btn_resister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resister();
            }
        });
    }

    private void resister() {
        String email,pass;
        email = emailedit.getText().toString();
        pass = passedit.getText().toString();
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Bạn chưa nhập Email",Toast.LENGTH_SHORT).show();
            return;

        }else if (TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Bạn chưa nhập Pass",Toast.LENGTH_SHORT).show();
            return;
        }else if(pass.length() <=6){
            Toast.makeText(this, "Mật khẩu phải dài hơn 6 kí tự", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(resignter.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Log.w(TAG,"createUserWithEmail:failure",task.getException());
                    Toast.makeText(resignter.this, "Tạo tài khoản không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}