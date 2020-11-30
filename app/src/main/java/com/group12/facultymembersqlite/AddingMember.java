package com.group12.facultymembersqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddingMember extends AppCompatActivity {

    OpenHelper openHelper;
    String stIDNum;
    String stName;
    String stAdd;
    String stDegree;
    SQLiteDatabase sqLiteDatabase;

    EditText eIDNum;
    EditText eName;
    EditText eAdd;
    EditText eDegree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_member);
        openHelper = new OpenHelper(this);
        sqLiteDatabase = openHelper.getWritableDatabase();

        eIDNum=findViewById(R.id.editIdNum);
        eName=findViewById(R.id.editName);
        eAdd=findViewById(R.id.editAdd);
        eDegree=findViewById(R.id.editDegree);
    }

    public void clickAdd(View view) {
        stIDNum=eIDNum.getText().toString();
        stName=eName.getText().toString();
        stAdd=eAdd.getText().toString();
        stDegree=eDegree.getText().toString();

        if(TextUtils.isEmpty(stIDNum) || TextUtils.isEmpty(stName) || TextUtils.isEmpty(stAdd) || TextUtils.isEmpty(stDegree)){

            Toast.makeText(this, "Check the Empty Fields", Toast.LENGTH_SHORT).show();
        }else {

            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseInfo._ID, (byte[]) null);
            contentValues.put(DatabaseInfo.memberIDNum, stIDNum);
            contentValues.put(DatabaseInfo.memberName, stName);
            contentValues.put(DatabaseInfo.memberAddress, stAdd);
            contentValues.put(DatabaseInfo.memberDegree, stDegree);
            long rowId = sqLiteDatabase.insert(DatabaseInfo.TABLE_NAME, null, contentValues);
            if (rowId != -1) {

                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();

            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }

        }
    }
}