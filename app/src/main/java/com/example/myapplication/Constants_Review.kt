package com.example.myapplication


object Constants_Review {
    //db name
    const val DB_NAME = "MY_REVIEW_DB"
    const val DB_VERSION = 1
    const val TABLE_NAME = "MY_REVIEW_TABLE"
    //coulumns field of table
    const val R_ID = "ID"
    const val R_NAME = "NAME"
    const val R_IMAGE = "IMAGE"
    const val R_CONTENT = "CONTENT"
    const val R_RATING_STAR = "RATING_STAR"
    const val R_ADDED_TIME_STAMP = "ADDED_TIME_STAMP"
    const val R_UPDATED_TIME_STAMP = "UPDATED_TIME_STAMP"

    //create table query
    const val CREATE_TABLE = (
            "CREATE TABLE " + TABLE_NAME + "("
                    + R_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + R_NAME + " TEXT, "
                    + R_IMAGE + " TEXT, "
                    + R_CONTENT + " TEXT, "
                    + R_RATING_STAR + " TEXT, "
                    + R_ADDED_TIME_STAMP + " TEXT, "
                    + R_UPDATED_TIME_STAMP + " TEXT"
                    + ")"
            )
}