package com.example.cincin;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;
        import androidx.recyclerview.widget.RecyclerView;
        import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<Coctail> data;

    public static final String ENTRY = "com.example.cincin.ENTRY";

    // create constructor to initialize context and data sent from SearchActivity
    public Adapter(Context context, List<Coctail> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    // Inflate the layout when ViewHolder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.container_coctail, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // Get current position of item in RecyclerView to bind data and assign values from list
        MyHolder myHolder = (MyHolder) holder;
        Coctail current = data.get(position);

        myHolder.textName.setText(current.getName());
        myHolder.textCategory.setText("Category: " + current.getCategory());
        myHolder.textGlass.setText("Glass: " + current.getGlass());
        myHolder.textInstructions.setText("Instructions: " + current.getInstructions());

        new ImageLoad(current.getImgLink(), myHolder.imgLink).execute();
    }

    // return total item from List
    @Override
    public int getItemCount(){
        if (data==null){
            return 0;
        } else{
            return data.size();
        }


    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textName;
        TextView textCategory;
        TextView textGlass;
        TextView textInstructions;

        ImageView imgLink;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textName = (TextView) itemView.findViewById(R.id.textName);
            textCategory = (TextView) itemView.findViewById(R.id.textCategory);
            textGlass = (TextView) itemView.findViewById(R.id.textGlass);
            textInstructions = (TextView) itemView.findViewById(R.id.textInstructions);
            imgLink = (ImageView) itemView.findViewById(R.id.imgLink);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "You clicked an item", Toast.LENGTH_SHORT).show();
        }
    }
}










