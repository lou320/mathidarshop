package com.example.mathidarshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String WORKER_NAME = "WORKER_NAME";
    public static final String ATONE_AMOUNT = "ATONE_AMOUNT";
    public static final String GIN_AMMOUNT = "GIN_AMMOUNT";
    public static final String MOUSETEETH_AMMOUNT = "MOUSETEETH_AMMOUNT";
    public static final String TOTAL = "TOTAL";
    public static final String DATE = "DATE";
    public static final String SHEET_AMOUNT = "SHEET_AMOUNT";
    public static final String WORKER_TABLE = "WORKER_table";
    public static final String KUNAR = "KUNAR";
    public static final String ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "worker.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatements = "CREATE TABLE " + WORKER_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + WORKER_NAME + " TEXT," + ATONE_AMOUNT + " REAL, " + GIN_AMMOUNT + " REAL, " + MOUSETEETH_AMMOUNT + " REAL, " + SHEET_AMOUNT + " REAL, "+ KUNAR + " REAL, " + TOTAL + " REAL, " + DATE + " TEXT )";
        sqLiteDatabase.execSQL(createTableStatements);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addWorkerValue(WorkerValue workerValue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(WORKER_NAME, workerValue.getName());
        cv.put(ATONE_AMOUNT,workerValue.getAtoneAmount());
        cv.put(GIN_AMMOUNT, workerValue.getGin());
        cv.put(MOUSETEETH_AMMOUNT,workerValue.getMouseteeth());
        cv.put(SHEET_AMOUNT,workerValue.getSheet());
        cv.put(KUNAR,workerValue.getKunar());
        cv.put(TOTAL,workerValue.caculate());
        LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Yangon"));
        cv.put(DATE, localDate.toString());

        long insert = db.insert(WORKER_TABLE, null,cv);
        if(insert == -1){
            return false;
        }else {
            return true;
        }
    }
    public List<String> getEveryOne(){
        List<String> retunList = new ArrayList<>();
        String sqlString = "SELECT * FROM " + WORKER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlString,null);

        if(cursor.moveToLast()){
            do {
                int workID = cursor.getInt(0);
                String workerName = cursor.getString(1);
                float ATONE_AMOUNT = cursor.getFloat(2);
                float GIN_AMMOUNT = cursor.getFloat(3);
                float MOUSETEETH_AMMOUNT = cursor.getFloat(4);
                float SHEET_AMOUNT = cursor.getFloat(5);
                float KUNAR = cursor.getFloat(6);
                float total = cursor.getFloat(7);
                String date = cursor.getString(8);
                String workValue = (workID +". "+workerName+ " Date: "+ date +"\n"+"350:" + ATONE_AMOUNT+", 400:"+ GIN_AMMOUNT + ", 450:" + MOUSETEETH_AMMOUNT + ", 500:"+SHEET_AMOUNT + ", 700:" + KUNAR+ "\nTotal:"+total);

//                WorkerValue newWork = new WorkerValue(workID,workerName,ATONE_AMOUNT,GIN_AMMOUNT,MOUSETEETH_AMMOUNT,SHEET_AMOUNT,KUNAR,total,date);
                retunList.add(workValue);

            } while (cursor.moveToPrevious());
        }
        cursor.close();
        db.close();
        return  retunList;
    }
    public boolean deleteOneValue(String workerValue){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM "+ WORKER_TABLE +" WHERE " + ID + " = " + workerValue.substring(0, workerValue.indexOf("."));
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()){
            return  true;
        }else {
            return false;
        }
    }

}
