package com.group12.facultymembersqlite;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ProductViewer>{
    List<POJO> memberDetails;
    Context context;
    OpenHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    public RecycleAdapter(List<POJO> productDetails) {
        this.memberDetails = productDetails;
    }

    @NonNull
    @Override
    public ProductViewer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View iteView = inflater.inflate(R.layout.activity_item, parent, false);
        ProductViewer viewHolder = new ProductViewer(iteView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewer holder, final int position) {
        final POJO pojo=memberDetails.get(position);

        holder.pId.setText("ID: " + pojo.getP_id());
        holder.pIDNum.setText("ID Number :"+ pojo.getP_ID_Num());
        holder.pAddress.setText("Address: "+ pojo.getP_address());
        holder.pName.setText("Name: "+ pojo.getP_name());
        holder.pDegree.setText("Highest Degree: "+ pojo.getP_degree());
        holder.txtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int pId = pojo.getP_id();
                dbHelper = new OpenHelper(context);
                sqLiteDatabase = dbHelper.getWritableDatabase();
                sqLiteDatabase.delete(DatabaseInfo.TABLE_NAME,DatabaseInfo._ID+ " = " + pId,null);
                notifyItemRangeChanged(position,memberDetails.size());
                memberDetails.remove(position);
                notifyItemRemoved(position);
                sqLiteDatabase.close();
            }
        });

        holder.txtUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, Update.class);
                intent.putExtra("prodId", pojo.getP_id());
                context.startActivity(intent);

            }
        });
    }//onBindViewHolder

    @Override
    public int getItemCount() {
        return memberDetails.size();
    }

    public class ProductViewer extends RecyclerView.ViewHolder {


        TextView pId;
        TextView pIDNum;
        TextView pName;
        TextView pAddress;
        TextView pDegree;
        TextView txtDelete;
        TextView txtUpdate;


        public ProductViewer(View itemView) {
            super(itemView);

            pId = itemView.findViewById(R.id.txtId);
            pIDNum =  itemView.findViewById(R.id.txtCode);
            pName = itemView.findViewById(R.id.txtName);
            pAddress = itemView.findViewById(R.id.txtDes);
            pDegree = itemView.findViewById(R.id.txtQuantity);
            txtDelete=itemView.findViewById(R.id.txtDelete);
            txtUpdate=itemView.findViewById(R.id.txtUpdate);

        }


    }

    public void deleteProduct(){

    }

}
