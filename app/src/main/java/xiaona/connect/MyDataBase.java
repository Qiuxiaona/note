package xiaona.connect;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import xiaona.datasave.Save;


public class MyDataBase {
    Context context;
    MySQL mySQL;
    SQLiteDatabase mydatabase;

    public MyDataBase(Context context1){
        this.context=context1;
        mySQL=new MySQL(context);
    }

    public ArrayList<Save> getArray(){
        ArrayList<Save> arraylist1 = new ArrayList<>();
        ArrayList<Save> arraylist2 = new ArrayList<>();
        mydatabase=mySQL.getWritableDatabase();
        Cursor cursor = mydatabase.rawQuery("select ids,title,times from mynote" , null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            int id=cursor.getInt(cursor.getColumnIndex("ids"));
            String title=cursor.getString(cursor.getColumnIndex("title"));
            String times=cursor.getString(cursor.getColumnIndex("times"));
            Save save = new Save(id,title,times);
            arraylist1.add(save);
            cursor.moveToNext();
        }
        mydatabase.close();
        for(int i = arraylist1.size();i>0;i--){
            arraylist2.add(arraylist1.get(i-1));
        }
        return arraylist2;
    }


    //通过SQLiteOpenHelper的getWritableDatabase()获得一个SQLiteDatabase数据库，以后的操作都是对SQLiteDatabase进行操作。
    //3. 对得到的SQLiteDatabase对象进行增，改，删，查等操作。
    public Save getTiandCon(int id){
        mydatabase = mySQL.getWritableDatabase();
        Cursor cursor = mydatabase.rawQuery("select title,content from mynote where ids='"+id+"'" , null);
        cursor.moveToFirst();
        String title=cursor.getString(cursor.getColumnIndex("title"));
        String content=cursor.getString(cursor.getColumnIndex("content"));
        Save save= new Save(title,content);
        mydatabase.close();
        return save;
    }
    public void toUpdata(Save save){
        mydatabase=mySQL.getWritableDatabase();
        mydatabase.execSQL("update mynote set title='"+ save.getTitle()+"',times='"+save.getTimes()+"',content='"+save.getContent() +"' where ids='"+ save.getIds()+"'");
        mydatabase.close();
    }
    public void toInsert(Save save){
        mydatabase=mySQL.getWritableDatabase();
        mydatabase.execSQL("insert into mynote(title,content,times)values('"+ save.getTitle()+"','"+save.getContent()+"','"+save.getTimes()+"')");
        mydatabase.close();
    }
    public void toDelete(int ids){
        mydatabase=mySQL.getWritableDatabase();
        mydatabase.execSQL("delete from mynote where ids="+ids+"");
        mydatabase.close();
    }

}
