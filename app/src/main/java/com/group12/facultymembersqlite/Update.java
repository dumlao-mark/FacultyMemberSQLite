package com.group12.facultymembersqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Update extends AppCompatActivity {

    EditText eIDNum;
    EditText eName;
    EditText eAddress;
    EditText eDegree;

    String stIDNum;
    String stName;
    String stAddress;
    String stDegree;

    List<POJO> memberDetails;
    OpenHelper openHelper;
    SQLiteDatabase sqLiteDatabase;

    int rowId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        openHelper = new OpenHelper(this);
        sqLiteDatabase=openHelper.getWritableDatabase();

        eIDNum=findViewById(R.id.editIdUpdate);
        eName=findViewById(R.id.editNameUpdate);
        eAddress=findViewById(R.id.editAddressUpdate);
        eDegree=findViewById(R.id.editDegreeUpdate);

        rowId = getIntent().getIntExtra("prodId", -1);

        Cursor cursor = sqLiteDatabase.query(DatabaseInfo.TABLE_NAME, null, DatabaseInfo._ID + " = " + rowId, null, null,null, null);
        memberDetails= new ArrayList<POJO>();
        memberDetails.clear();

        if(cursor != null && cursor.getCount() != 0){
            while (cursor.moveToNext()) {
                eIDNum.setText(cursor.getString(cursor.getColumnIndex(DatabaseInfo.memberIDNum)));
                eName.setText(cursor.getString(cursor.getColumnIndex(DatabaseInfo.memberName)));
                eAddress.setText(cursor.getString(cursor.getColumnIndex(DatabaseInfo.memberAddress)));
                eDegree.setText(cursor.getString(cursor.getColumnIndex(DatabaseInfo.memberDegree)));
            }
        }else{
            Toast.makeText(this, "No Data Found!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickUpdate(View view){
        stIDNum=eIDNum.getText().toString();
        stName=eName.getText().toString();
        stAddress=eAddress.getText().toString();
        stDegree=eDegree.getText().toString();

        if(TextUtils.isEmpty(stIDNum) || TextUtils.isEmpty(stName) || TextUtils.isEmpty(stAddress) || TextUtils.isEmpty(stDegree)){
            Toast.makeText(this, "Check the Empty Fields", Toast.LENGTH_SHORT).show();
        }else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseInfo.memberIDNum, stIDNum);
            contentValues.put(DatabaseInfo.memberDegree, stAddress);
            contentValues.put(DatabaseInfo.memberName, stName);
            contentValues.put(DatabaseInfo.memberDegree, stDegree);

            int updateId = sqLiteDatabase.update(DatabaseInfo.TABLE_NAME, contentValues, DatabaseInfo._ID + " = " + rowId, null);
            if (updateId != -1) {
                Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Something Wrong!!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onDestroy() {
        sqLiteDatabase.close();
        super.onDestroy();
    }
}