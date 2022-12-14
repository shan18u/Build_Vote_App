package com.example.buildplan;




import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildplan.ParseItem;
import com.example.buildplan.R;
import com.squareup.picasso.Picasso;



import java.util.ArrayList;

public class ParseAdapter extends RecyclerView.Adapter<ParseAdapter.ViewHolder> {
    private ArrayList<ParseItem> parseItems;
    private Context context;



    public ParseAdapter (ArrayList<ParseItem> parseItems, Context context){
        this.parseItems = parseItems;
        this.context = context;
    }




    @NonNull
    @Override
    public ParseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParseAdapter.ViewHolder holder, int position) {
        ParseItem parseItem = parseItems.get(position);
        holder.textView.setText(parseItem.getTitle());

        try{
            Picasso.get().load(parseItem.getImgurl()).into(holder.imageView);
        }catch (Exception e){
            e.printStackTrace();

            Picasso.get().load(String.valueOf(holder.imageView));
        }







    }

    @Override
    public int getItemCount() {
        return parseItems.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAbsoluteAdapterPosition();
            ParseItem parseItem = parseItems.get(position);

            Intent intent = new Intent(context, Details.class);
            intent.putExtra("title", parseItem.getTitle());
            intent.putExtra("image", parseItem.getImgurl());
           context.startActivity(intent);
        }
    }
}
