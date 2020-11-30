package com.group12.facultymembersqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    OpenHelper dbHelper;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter userAdapter;
    private RecyclerView.LayoutManager layoutManager;
    Button btnRegister;

    List<POJO> memberDetails;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new OpenHelper(this);
        sqLiteDatabase= dbHelper.getReadableDatabase();
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        memberDetails = new ArrayList<POJO>();
        memberDetails.clear();
        Cursor c1 = sqLiteDatabase.query(DatabaseInfo.TABLE_NAME, null, null, null, null, null, null);

        if (c1 != null && c1.getCount() != 0) {
            memberDetails.clear();
            while (c1.moveToNext()) {
                POJO prodDetails = new POJO();

                prodDetails.setP_id(c1.getInt(c1.getColumnIndex(DatabaseInfo._ID)));
                prodDetails.setP_ID_Num(c1.getString(c1.getColumnIndex(DatabaseInfo.memberIDNum)));
                prodDetails.setP_name(c1.getString(c1.getColumnIndex(DatabaseInfo.memberName)));
                prodDetails.setP_address(c1.getString(c1.getColumnIndex(DatabaseInfo.memberAddress)));
                prodDetails.setP_degree(c1.getString(c1.getColumnIndex(DatabaseInfo.memberDegree)));
                memberDetails.add(prodDetails);
            }
        }

        c1.close();
        layoutManager = new LinearLayoutManager(this);
        userAdapter = new RecycleAdapter(memberDetails);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(userAdapter);
    }

    public void clickAddMember(View view) {startActivity(new Intent(this,AddingMember.class));
    }
}