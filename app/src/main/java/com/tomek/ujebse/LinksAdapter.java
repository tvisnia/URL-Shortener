package com.tomek.ujebse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
/**
 * Created by tomek on 30.07.15.
 */
public class LinksAdapter extends BaseAdapter {

    private List<Links> links = Links.listAll(Links.class);
    private Context context;
    public static LayoutInflater inflater = null;

    public LinksAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return links.size();
    }

    @Override
    public Object getItem(int position) {
        Links links = Links.findById(Links.class, (long) (position+1));
        return links;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        //BaseAdapter iterate starting from 0
        //SugarORM first item index start from 1
        rowView = inflater.inflate(R.layout.list_item, null);
        holder.originalLink=(TextView) rowView.findViewById(R.id.originalLink);
        holder.shortcutLink=(TextView) rowView.findViewById(R.id.shortcutLink);
        Links linksList = links.get(position);

        holder.originalLink.setText(linksList.original);
        holder.shortcutLink.setText(linksList.shortcut);
        rowView.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //item click action code here
            }
        });
        return rowView;
    }

    private class Holder {
        TextView originalLink;
        TextView shortcutLink;

        public Holder() {
        }
    }

}
