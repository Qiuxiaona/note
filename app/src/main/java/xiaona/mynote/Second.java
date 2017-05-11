package xiaona.mynote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import xiaona.connect.MyDataBase;
import xiaona.datasave.Save;


public class Second extends Activity{

    EditText edittext1,edittext2;
    Button button1;
    MyDataBase mydatabase;
    Save save;
    int ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        edittext1=(EditText) findViewById(R.id.editText1);
        edittext2=(EditText) findViewById(R.id.editText2);
        button1=(Button) findViewById(R.id.button1);
        mydatabase=new MyDataBase(this);

        Intent intent=this.getIntent();
        ids=intent.getIntExtra("ids",0);
        if(ids!=0){
            save=mydatabase.getTiandCon(ids);
            edittext1.setText(save.getTitle());
            edittext2.setText(save.getContent());
        }
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                isSave();
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        SimpleDateFormat dateformat=new SimpleDateFormat("yyy.MM.dd  HH:mm:ss", Locale.CHINA);
        Date curDate=new Date(System.currentTimeMillis());
        String times = dateformat.format(curDate);
        String title =edittext1.getText().toString();
        String content=edittext2.getText().toString();

        if(ids!=0){
            save = new Save(title,ids,content,times);
            mydatabase.toUpdata(save);
            Intent intent=new Intent(Second.this,First.class);
            startActivity(intent);
            Second.this.finish();
        }else{
            if(title.equals("") && content.equals("")){
                Intent intent=new Intent(Second.this,First.class);
                startActivity(intent);
                Second.this.finish();
            }else {
                save=new Save(title,content,times);
                mydatabase.toInsert(save);
                Intent intent=new Intent(Second.this,First.class);
                startActivity(intent);
                Second.this.finish();
            }
        }
    }
    private void isSave(){
        SimpleDateFormat datefoemat = new SimpleDateFormat("yyy.MM.dd  HH:mm:ss",Locale.CHINA);
        Date curDate=new Date(System.currentTimeMillis());
        String times = datefoemat.format(curDate);
        String title=edittext1.getText().toString();
        String content=edittext2.getText().toString();

        if(ids!=0){
            save =new Save(title,ids,content,times);
            mydatabase.toUpdata(save);
            Intent intent = new Intent(Second.this,First.class);
            startActivity(intent);
            Second.this.finish();
        }else if(title.equals("")){
            Toast.makeText(getApplicationContext(),"标题不能为空", Toast.LENGTH_SHORT).show();
        }else if(content.equals("")){
            Toast.makeText(getApplicationContext(),"内容不能为空", Toast.LENGTH_SHORT).show();
        }else{
            save = new Save(title,content,times);
            mydatabase.toInsert(save);
            Intent intent =new Intent(Second.this,First.class);
            startActivity(intent);
            Second.this.finish();
            Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share,menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,"主题："+edittext1.getText().toString()+"内容："+edittext2.getText().toString());
                startActivity(intent);
                break;
            default:
                break;
        }
        return false;
    }
}
