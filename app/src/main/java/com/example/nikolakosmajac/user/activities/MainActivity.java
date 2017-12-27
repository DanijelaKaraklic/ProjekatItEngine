package com.example.nikolakosmajac.user.activities;

import android.app.Dialog;
import android.content.Intent;
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

import static android.R.attr.data;


public class MainActivity extends AppCompatActivity {

    public static int RESULT_CREATE = 1;
    public static int RESULT_UPDATE = 2;


    ArrayList<User> listUsers = new ArrayList<User>();
    private User user = new User();
    private Bundle bundle = new Bundle();


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

        User user1 = new User();
        user1.setIdUser(1);
        user1.setUserName("john@gmail.com");
        user1.setName("John");
        user1.setSurname("Johnson");
        user1.setPassword("something");
        user1.setAdress("Medison Avenue 5");
        user1.setImage("");
        listUsers.add(user1);


        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(listUsers);
        recyclerView.setAdapter(mAdapter);

        recyclerView.setAdapter(new MyAdapter(listUsers,new MyAdapter.OnItemClickListener(){
            public void onItemClick(final User user){

                Intent intentUpdate = new Intent(MainActivity.this,EditActivity.class);
                bundle.getInt("id");
                bundle.getString("name");
                bundle.getString("surname");
                bundle.getString("password");
                bundle.getString("username");
                bundle.getString("image");
                bundle.getString("adress");
                startActivityForResult(intentUpdate,RESULT_UPDATE);

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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_add:

                Intent intentAdd = new Intent(MainActivity.this,CreateActivity.class);
                startActivityForResult(intentAdd,RESULT_CREATE);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_CREATE) {
            if(resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                User user1 = new User();
                user1.setIdUser(bundle.getInt("id"));
                user1.setName(bundle.getString("name"));
                user1.setSurname(bundle.getString("surname"));
                user1.setPassword(bundle.getString("password"));
                user1.setUserName(bundle.getString("username"));
                user1.setImage(bundle.getString("image"));
                user1.setAdress(bundle.getString("adress"));

                listUsers.add(user1);





            }
        }else if (requestCode == RESULT_UPDATE){
            if (resultCode == RESULT_OK){
                Bundle bundle1 = data.getExtras();
                User user1 = new User();
                if (String.valueOf(bundle1.getInt("id")).equals("")){
                    user1.setIdUser(bundle.getInt("id"));
                }else{
                    user1.setIdUser(bundle1.getInt("id"));
                }

                if (bundle1.getString("name").equals("")){
                    user1.setName(bundle.getString("name"));
                }else{
                    user1.setName(bundle1.getString("name"));
                }

                if (bundle1.getString("surname").equals("")){
                    user1.setSurname(bundle.getString("surname"));
                }else{
                    user1.setSurname(bundle1.getString("surname"));
                }

                if (bundle1.getString("password").equals("")){
                    user1.setPassword(bundle.getString("password"));
                }else{
                    user1.setPassword(bundle1.getString("password"));
                }

                if (bundle1.getString("username").equals("")){
                    user1.setUserName(bundle.getString("username"));
                }else{
                    user1.setUserName(bundle1.getString("username"));
                }

                if (bundle1.getString("image").equals("")){
                    user1.setImage(bundle.getString("image"));
                }else{
                    user1.setImage(bundle1.getString("image"));
                }

                if (bundle1.getString("adress").equals("")){
                    user1.setAdress(bundle.getString("adress"));
                }else{
                    user1.setAdress(bundle1.getString("adress"));
                }

                ArrayList<Integer> index = new ArrayList<Integer>();
                for (User u: listUsers) {
                    index.add(u.getIdUser());
                }
                for (Integer i:index) {
                    if (i == user1.getIdUser()){
                        listUsers.add(user1);
                    }
                }

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        refresh();

    }

    private void refresh(){
        MyAdapter adapter = (MyAdapter)recyclerView.getAdapter();
        adapter.addNewList(listUsers);
        adapter.notifyDataSetChanged();
    }

}
