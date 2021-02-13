package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ReviewDBHelper(context: Context?):SQLiteOpenHelper(
    context,Constants_Review.DB_NAME, null, Constants_Review.DB_VERSION
) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Constants_Review.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants_Review.TABLE_NAME)
        onCreate(db)
    }

    fun insertReview(
        name : String?,
        image: String?,
        contentt: String?,
        ratingstar: String?,
        addedTime: String?
    ): Long {
        //get writeable database because we want write data
        val db = this.writableDatabase
        val values = ContentValues()
        //insert data
        values.put(Constants_Review.R_NAME, name)
        values.put(Constants_Review.R_IMAGE, image)
        values.put(Constants_Review.R_CONTENT, contentt)
        values.put(Constants_Review.R_RATING_STAR, ratingstar)
        values.put(Constants_Review.R_ADDED_TIME_STAMP, addedTime)

        //insert row, it will return record id of saved record
        val id = db.insert(Constants_Review.TABLE_NAME, null, values)
        db.close()
        return id

    }

    fun getAllRecords(orderBy: String): ArrayList<ModelReview> {

        val recordList = ArrayList<ModelReview>()
        val selectQuery = "SELECT * FROM ${Constants_Review.TABLE_NAME} ORDER BY $orderBy"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val modelReview = ModelReview(
                    ""+ cursor.getString(cursor.getColumnIndex(Constants_Review.R_ID)),
                    "" + cursor.getString(cursor.getColumnIndex(Constants_Review.R_NAME)),
                    "" + cursor.getString(cursor.getColumnIndex(Constants_Review.R_IMAGE)),
                    "" + cursor.getString(cursor.getColumnIndex(Constants_Review.R_CONTENT)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants_Review.R_RATING_STAR)),
                    "" + cursor.getString(cursor.getColumnIndex(Constants_Review.R_ADDED_TIME_STAMP))
                )
                recordList.add(modelReview)
            } while (cursor.moveToNext())
        }
        db.close()
        return recordList
    }
}
