package com.group12.facultymembersqlite;

import android.provider.BaseColumns;

public class DatabaseInfo implements BaseColumns{


    private DatabaseInfo() {
    }
    public static final String TABLE_NAME = "MemberTable";
    public static final String memberIDNum = "MemberIDNum";
    public static final String memberName = "MemberName";
    public static final String memberAddress = "MemberAddress";
    public static final String memberDegree = "MemberDegree";

}