package com.example.studytimer;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

class Data{
    private Drawable icon;
    private String text;

    public Data(String text, Drawable icon) {
        this.icon = icon;
        this.text = text;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setText(String text) {
        this.text = text;
    }
    public Drawable getIcon(){
        return this.icon;
    }
    public String getText(){
        return this.text;
    }
}
public class CustomAdapter extends ArrayAdapter<Data> {
    private LayoutInflater layoutInflater;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Data data = (Data)getItem(position);

        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.list_item,null);
        }

        ImageView imageView;
        imageView = (ImageView) convertView.findViewById(R.id.img);
        imageView.setImageDrawable(data.getIcon());

        TextView textView;
        textView = (TextView)convertView.findViewById(R.id.text);
        textView.setText(data.getText());
        return convertView;
    }

    public CustomAdapter(Context context, int textViewResourceId, List<Data> objects) {
        super(context, textViewResourceId,objects);
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);


    }
}
