package xiaona.connect;
import java.util.ArrayList;

import xiaona.datasave.Save;
import xiaona.mynote.R;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;



public class MyAdapter extends BaseAdapter{

    LayoutInflater inflater;
    ArrayList<Save> array;
    public MyAdapter(LayoutInflater inflater1,ArrayList<Save> array1){
        this.inflater=inflater1;
        this.array=array1;
    }

    @Override
    //获取列表长度
    public int getCount() {
        return array.size();
    }

    @Override
    //获取列表位置
    public Object getItem(int position) {
        return array.get(position);
    }

    @Override
    //获取列表ID
    public long getItemId(int position) {
        return position;
    }

    //只需要设定新的数据返回convertView,不必重新创建一个视图。
    // 这样直接使用convertView从而减少了很不不必要view的创建
    @Override

    //加载数据
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewholder1;    //为listview滚动的时候快速设置值，不必每次都重新创建很多对象
        if(convertView==null){
            viewholder1=new ViewHolder();
            convertView=inflater.inflate(R.layout.listview,null);
            viewholder1.textview1=(TextView)convertView.findViewById(R.id.textView1);
            viewholder1.textview2=(TextView)convertView.findViewById(R.id.textView2);
            convertView.setTag(viewholder1);
        }
        viewholder1=(ViewHolder) convertView.getTag();
        viewholder1.textview1.setText(array.get(position).getTitle());
        viewholder1.textview2.setText(array.get(position).getTimes());
        return convertView;
    }
    class ViewHolder{
        TextView textview1,textview2;
    }
}
