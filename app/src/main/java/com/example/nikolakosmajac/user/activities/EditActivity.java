package com.example.nikolakosmajac.user.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.nikolakosmajac.user.R;

/**
 * Created by nikola.kosmajac on 26-Dec-17.
 */

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

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

        int id = Integer.parseInt(editId.getText().toString());
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();
        String name = editName.getText().toString();
        String surname = editSurname.getText().toString();
        String adress = editAdress.getText().toString();
        String image = editImage.getText().toString();


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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
        /*    case R.id.action_add:

                break;*/
        }

        return super.onOptionsItemSelected(item);
    }


}
