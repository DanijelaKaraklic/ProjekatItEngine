package com.example.nikolakosmajac.user.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nikolakosmajac.user.R;
import com.example.nikolakosmajac.user.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private  List<User> values;
    private OnItemClickListener listenerShort;
    private OnItemLongClickListener listenerLong;



    public interface OnItemClickListener{
        void onItemClick(User user);
    }

    public interface OnItemLongClickListener{
        void onItemClick(User user);
    }

    public MyAdapter(List<User>users,OnItemClickListener listener){
        values = users;
        listenerShort = listener;
    }

    public MyAdapter(List<User>users,OnItemLongClickListener listener){
        values = users;
        listenerLong = listener;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener,View.OnLongClickListener*/{

        // each data item is just a string in this case
        public TextView txtName;
        public TextView txtSurname;
        public ImageView imgImage;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtName = (TextView) v.findViewById(R.id.name);
            txtSurname = (TextView) v.findViewById(R.id.surname);
            imgImage = (ImageView) v.findViewById(R.id.image);
        }

        public void bind(final User user,final OnItemClickListener listener){
            txtName.setText(user.getName());
            txtSurname.setText(user.getSurname());
            if (user.getImage().equals("")){
                //Picasso.with(itemView.getContext()).load(user.getImage()).placeholder(R.drawable.ic_action_add).into(imgImage);
                imgImage.setImageResource(R.drawable.ic_action_add);

            }else{
                Picasso.with(itemView.getContext()).load(user.getImage()).into(imgImage);

            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(user);
                }
            });






        }


        public void bind(final User user,final OnItemLongClickListener listenerLong){
            txtName.setText(user.getName());
            txtSurname.setText(user.getSurname());
            if (user.getImage().equals("")){
               // Picasso.with(itemView.getContext()).load(user.getImage()).placeholder(R.drawable.ic_action_add).into(imgImage);
                imgImage.setImageResource(R.drawable.ic_action_add);
            }else{
                //Picasso.with(itemView.getContext()).load(user.getImage()).into(imgImage);
                imgImage.setImageResource(R.drawable.ic_action_add);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerLong.onItemClick(user);
                }
            });






        }

    }

    public void addNewList(List<User> newList){
        values.clear();
        values.addAll(newList);
    }

    public void add(int position, User user) {
        values.add(position, user);
        notifyItemInserted(position);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<User> myDataset) {
        values = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);

        vh.txtName = (TextView) v.findViewById(R.id.name);
        vh.txtSurname = (TextView) v.findViewById(R.id.surname);
        vh.imgImage = (ImageView) v.findViewById(R.id.image);

        return vh;



    }





    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element


        holder.txtName.setText(values.get(position).getName().toString());
        holder.txtSurname.setText(values.get(position).getSurname().toString());
        holder.imgImage.setImageResource(R.drawable.ic_action_add);


        holder.bind(values.get(position),listenerShort);
        holder.bind(values.get(position),listenerLong);
    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}


