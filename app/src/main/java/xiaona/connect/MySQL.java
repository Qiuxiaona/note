package xiaona.connect;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class MySQL extends SQLiteOpenHelper{

    public MySQL(Context context) {
        super(context,"mydata", null, 1);
    }

    @Override
    //建表
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table mynote(ids integer PRIMARY KEY autoincrement,title text,content text,times text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
