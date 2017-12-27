package com.example.nikolakosmajac.user.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nikolakosmajac.user.R;
import com.example.nikolakosmajac.user.model.User;

/**
 * Created by nikola.kosmajac on 27-Dec-17.
 */

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_create);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        EditText editId = (EditText)findViewById(R.id.id_user);
        EditText editUsername = (EditText)findViewById(R.id.username_user);
        EditText editPassword = (EditText)findViewById(R.id.pass_user);
        EditText editName = (EditText)findViewById(R.id.name_user);
        EditText editSurname = (EditText)findViewById(R.id.surname_user);
        EditText editAdress = (EditText)findViewById(R.id.adress_user);
        EditText editImage = (EditText)findViewById(R.id.image_user);


        final int id = Integer.parseInt(editId.getText().toString());
        final String username = editUsername.getText().toString();
        final String password = editPassword.getText().toString();
        final String name = editName.getText().toString();
        final String surname = editSurname.getText().toString();
        final String adress = editAdress.getText().toString();
        final String image = editImage.getText().toString();

        Button btnReturn = (Button)findViewById(R.id.btn_create_ok);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("id",id);
                bundle.putString("username",username);
                bundle.putString("password",password);
                bundle.putString("name",name);
                bundle.putString("surname",surname);
                bundle.putString("adress",adress);
                bundle.putString("image",image);
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                finish();

            }
        });





    }
}
