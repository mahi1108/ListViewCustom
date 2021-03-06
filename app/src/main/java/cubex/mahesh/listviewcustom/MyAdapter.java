package cubex.mahesh.listviewcustom;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class MyAdapter extends BaseAdapter {

    String path;
    File f;
    File[ ] files;
    MainActivity mActivity;
    MyAdapter(MainActivity mActivity )
    {
        path = "/storage/sdcard0/WhatsApp/Media/WhatsApp Images/";
        f = new File(path);
        if(!f.exists()){
            path = "/storage/emulated/0/WhatsApp/Media/WhatsApp Images/";
            f = new File(path);
        }
        files = f.listFiles();
        this.mActivity = mActivity;
    }


    @Override
    public int getCount() {
        return files.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView
            (int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mActivity);

        View v = inflater.inflate(R.layout.indiview,null);

        ImageView iv = v.findViewById(R.id.iview);
        TextView tv1 = v.findViewById(R.id.tv1);
        TextView tv2 = v.findViewById(R.id.tv2);
        ImageView del_view = v.findViewById(R.id.del);

        final File f1 = files[position];
        tv1.setText(f1.getName());
        tv2.setText(String.valueOf(f1.length()));
        Bitmap bmp = BitmapFactory.decodeFile(f1.getPath());

     Bitmap bmp1 =   ThumbnailUtils.extractThumbnail(bmp,
             40,40);
        iv.setImageBitmap(bmp1);
        del_view.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               f1.delete();
                files = f.listFiles();
                MyAdapter.this.notifyDataSetChanged();
            }
        });
        return v;
     }
}
