package com.example.nikolakosmajac.user.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.nikolakosmajac.user.R;
import com.example.nikolakosmajac.user.adapters.MyAdapter;
import com.example.nikolakosmajac.user.model.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<User> listUsers = new ArrayList<User>();
    ListView listView;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }


        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(listUsers);
        recyclerView.setAdapter(mAdapter);

        recyclerView.setAdapter(new MyAdapter(listUsers,new MyAdapter.OnItemClickListener(){
            public void onItemClick(final User user){

                final Dialog dialog_update = new Dialog(MainActivity.this);
                dialog_update.setContentView(R.layout.dialog_update);
                dialog_update.setTitle(R.string.dialog_update_title);

                Button btn_ok = (Button)dialog_update.findViewById(R.id.btn_dialog_ok);
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        EditText editUsername = (EditText)dialog_update.findViewById(R.id.username_user);
                        EditText editPassword = (EditText)dialog_update.findViewById(R.id.pass_user);
                        EditText editName = (EditText)dialog_update.findViewById(R.id.name_user);
                        EditText editSurname = (EditText)dialog_update.findViewById(R.id.surname_user);
                        EditText editAdress = (EditText)dialog_update.findViewById(R.id.adress_user);
                        EditText editImage = (EditText)dialog_update.findViewById(R.id.image_user);


                        String username = editUsername.getText().toString();
                        if (!username.equals("")){
                            user.setUserName(username);
                        }

                        String password = editPassword.getText().toString();

                        if (!password.equals("")){
                            user.setPassword(password);
                        }
                        String name = editName.getText().toString();

                        if (!name.equals("")){
                            user.setName(name);
                        }
                        String surname = editSurname.getText().toString();

                        if (!surname.equals("")){
                            user.setSurname(surname);
                        }
                        String adress = editAdress.getText().toString();

                        if (!adress.equals("")){
                            user.setAdress(adress);
                        }
                        String image = editImage.getText().toString();
                        if (!image.equals("")){
                            user.setImage(image);
                        }

                        refresh();

                        dialog_update.dismiss();


                    }
                });

                dialog_update.show();




            }
        }));

        recyclerView.setAdapter(new MyAdapter(listUsers, new MyAdapter.OnItemLongClickListener() {
            @Override
            public void onItemClick(final User user) {

                final Dialog deleteUser = new Dialog(MainActivity.this);
                deleteUser.setContentView(R.layout.dialog_delete);
                deleteUser.setTitle(R.string.dialog_delete_title);

                Button btnYes = (Button)deleteUser.findViewById(R.id.dialog_btn_yes);
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int id = user.getIdUser();
                        if (listUsers.contains(user)){
                            listUsers.remove(user);
                            refresh();
                        }


                        deleteUser.dismiss();

                    }
                });

                Button btnNo = (Button)deleteUser.findViewById(R.id.dialog_btn_no);
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        deleteUser.cancel();

                    }
                });

                deleteUser.show();

            }
        }));


    }


    private void refresh() {
        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);


        if (recyclerView != null) {
            RecyclerView.Adapter recyclerAdapter = (RecyclerView.Adapter)recyclerView.getAdapter();

            if (recyclerAdapter != null) {
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();

            }
        }
    }



    public void addUser(int id, String username, String password, String name, String surname, String adress,String image){

        User user1 = new User();
        user1.setIdUser(id);
        user1.setUserName(username);
        user1.setPassword(password);
        user1.setName(name);
        user1.setSurname(surname);
        user1.setAdress(adress);
        user1.setImage(image);
        listUsers.add(user1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_add:

                final Dialog dialog_create = new Dialog(MainActivity.this);
                dialog_create.setContentView(R.layout.dialog_create);
                dialog_create.setTitle(R.string.dialog_create);

                Button btn_ok = (Button) dialog_create.findViewById(R.id.btn_dialog_ok);
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        EditText editId = (EditText)dialog_create.findViewById(R.id.id_user);
                        EditText editUsername = (EditText)dialog_create.findViewById(R.id.username_user);
                        EditText editPassword = (EditText)dialog_create.findViewById(R.id.pass_user);
                        EditText editName = (EditText)dialog_create.findViewById(R.id.name_user);
                        EditText editSurname = (EditText)dialog_create.findViewById(R.id.surname_user);
                        EditText editAdress = (EditText)dialog_create.findViewById(R.id.adress_user);
                        EditText editImage = (EditText)dialog_create.findViewById(R.id.image_user);


                        int id = Integer.parseInt(editId.getText().toString());
                        String username = editUsername.getText().toString();
                        String password = editPassword.getText().toString();
                        String name = editName.getText().toString();
                        String surname = editSurname.getText().toString();
                        String adress = editAdress.getText().toString();
                        String image = editImage.getText().toString();


                        addUser(id,username,password,name,surname,adress,image);
                        refresh();

                        dialog_create.dismiss();

                    }
                });

                dialog_create.show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }
}
