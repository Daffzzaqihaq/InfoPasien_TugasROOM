package com.daffzzaqihaq.infopasien.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.daffzzaqihaq.infopasien.R;
import com.daffzzaqihaq.infopasien.db.Constant;
import com.daffzzaqihaq.infopasien.db.PasienDatabase;
import com.daffzzaqihaq.infopasien.model.PasienModel;
import com.daffzzaqihaq.infopasien.ui.MainActivity;
import com.daffzzaqihaq.infopasien.ui.UpdatePasienActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PasienAdapter extends RecyclerView.Adapter<PasienAdapter.ViewHolder> {

    private final Context context;
    private final List<PasienModel> pasienModelList;
    private PasienAdapter pasienAdapter;
    private PasienDatabase pasienDatabase;
    private Bundle bundle;
    private AlertDialog alertDialog;

    public PasienAdapter(Context context, List<PasienModel> pasienModelList) {
        this.context = context;
        this.pasienModelList = pasienModelList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pasien, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final PasienModel pasienModel = pasienModelList.get(i);

        viewHolder.txtPasien.setText(pasienModel.getNama_pasien());
        viewHolder.txtPenyakit.setText(pasienModel.getNama_penyakit());

        viewHolder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pasienDatabase = pasienDatabase.createDatabase(context);
                final PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                popupMenu.inflate(R.menu.popupmenu);
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.delete:

                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                                alertDialogBuilder.setMessage("Are you sure ?");
                                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        pasienDatabase.kelasDao().delete(pasienModel);

                                        pasienModelList.remove(i);
                                        notifyItemRemoved(i);
                                        notifyItemRangeChanged(0, pasienModelList.size());
                                        Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });

                                AlertDialog alertDialog = alertDialogBuilder.create();
                                alertDialog.show();

                                break;
                            case R.id.edit:
                                bundle = new Bundle();

                                bundle.putInt(Constant.KEY_ID_PASIEN, pasienModel.getId_pasien());
                                bundle.putString(Constant.KEY_NAMA_PASIEN, pasienModel.getNama_pasien());
                                bundle.putString(Constant.KEY_NAMA_PENYAKIT, pasienModel.getNama_penyakit());

                                context.startActivity(new Intent(context, UpdatePasienActivity.class).putExtras(bundle));
                                break;
                        }
                        return true;
                    }
                });

            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putInt(Constant.KEY_ID_PASIEN, pasienModel.getId_pasien());
                bundle.putString(Constant.KEY_NAMA_PASIEN, pasienModel.getNama_pasien());
                context.startActivity(new Intent(context, MainActivity.class).putExtras(bundle));
            }
        });

    }

    @Override
    public int getItemCount() {
        return pasienModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtPenyakit)
        TextView txtPenyakit;
        @BindView(R.id.txtPasien)
        TextView txtPasien;
        @BindView(R.id.overflow)
        ImageButton overflow;
        @BindView(R.id.cvPasien)
        CardView cvPasien;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
