package com.example.mckagabo.mckagaboapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mckagabo on 23-Sep-16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ObjectViewHolder> {
    List<TheObject> anotherObjectList;

    public RecyclerViewAdapter(List<TheObject> anotherObjectList) {
        this.anotherObjectList = anotherObjectList;
    }

    @Override
    public ObjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.mylistview,parent,false);
        ObjectViewHolder viewHolder=new ObjectViewHolder(v);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(ObjectViewHolder holder, int position) {
        holder.theTitle.setText(anotherObjectList.get(position).getTitle());

       String imageUrl=anotherObjectList.get(position).getImagUrl();
       // holder.aka.setText(imageUrl);
       // loadImage(imgageUrl,holder.imageView);
        loadImage("https://visit.gent.be/sites/default/files/images-event/bisschopshuis_-_theorizz_-_horta_-_beeld.jpg",holder.imageView);
       // holder.imageView.setImageBitmap(getBitmapFromURL("https://visit.gent.be/sites/default/files/images-article/spelvandeslotenmaker_gent1.jpg"));
       /* Pattern p = Pattern.compile("\"([^\"]*)\"");
        Matcher m = p.matcher(imageUrl);
        while(m.find())
        {
            try {
                URL url=new URL(m.group(1));
                String newUrl=url.getPath();
               holder.aka.setText(newUrl) ;
                loadImage(newUrl,holder.imageView);
                Bitmap myBitmap=getBitmapFromURL(newUrl);
               // holder.imageView.setImageBitmap(myBitmap);
            } catch (MalformedURLException e) {
                Toast.makeText(holder.itemView.getContext(),"le probleme est:"+e.getMessage(), Toast.LENGTH_LONG).show();
            }


        }*/

    }
    public void loadImage(String url, ImageView imageView) {
        Picasso.with(imageView.getContext()).load(url).into(imageView);
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }
    @Override
    public int getItemCount() {
        return anotherObjectList.size();
    }

    static class ObjectViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView theTitle,aka;
        public ObjectViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.imageView);
            theTitle=(TextView)itemView.findViewById(R.id.title);
            aka=(TextView)itemView.findViewById(R.id.description);
        }
    }
}
