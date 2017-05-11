package xiaona.mynote;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.Toast;

import java.util.ArrayList;

import xiaona.connect.MyAdapter;
import xiaona.connect.MyDataBase;
import xiaona.datasave.Save;

public class First extends Activity {

    Button button;
    ListView listview;
    LayoutInflater inflater; //布局管理器，将list.xml文件转化为view对象
    ArrayList<Save> arraylist;
    MyDataBase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        button = (Button)findViewById(R.id.button1);
        listview = (ListView)findViewById(R.id.listview1);
        inflater = getLayoutInflater();

        mydatabase = new MyDataBase(this);
        arraylist=mydatabase.getArray();
        MyAdapter myadapter = new MyAdapter(inflater,arraylist);
        listview.setAdapter(myadapter);

       //短按获取list
        listview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),Second.class);
                intent.putExtra("ids",arraylist.get(position).getIds());
                startActivity(intent);
                First.this.finish();
            }
        });
        //长按
        listview.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(First.this)
                .setTitle("温馨提示")
                .setMessage("确定要删除吗")
                .setNegativeButton("取消",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("确定",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mydatabase.toDelete(arraylist.get(position).getIds());
                        arraylist = mydatabase.getArray();
                        MyAdapter myadapter = new MyAdapter(inflater, arraylist);
                        listview.setAdapter(myadapter);
                        Toast.makeText(getApplicationContext(),"删除成功", Toast.LENGTH_SHORT).show();

                    }
                })
                 .create().show();
                return true;
            }
        });

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Second.class);
                startActivity(intent);
                First.this.finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.newout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item1:
                Intent intent = new Intent(getApplicationContext(),Second.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.item2:
                this.finish();
                break;
            default:
                break;
        }
        return true;
    }
}
