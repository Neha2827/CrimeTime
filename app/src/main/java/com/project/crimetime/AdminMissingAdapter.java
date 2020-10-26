package com.project.crimetime;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.project.crimetime.Classes.MissingcomplainClass;
import java.util.ArrayList;
public class AdminMissingAdapter extends RecyclerView.Adapter<AdminMissingAdapter.AdminHolder> {
    Context context;
    ArrayList<MissingcomplainClass> missingcomplainClasses;
    public AdminMissingAdapter(Context context, ArrayList<MissingcomplainClass> missingcomplainClasses) {
        this.context = context;
        this.missingcomplainClasses = missingcomplainClasses;
    }
    @NonNull
    @Override
    public AdminHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final AdminHolder adminHolder = new AdminHolder(LayoutInflater.from(context).inflate(R.layout.card_missing_complain,parent,
                false));
        return adminHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull AdminHolder holder, int position) {
        MissingcomplainClass model = missingcomplainClasses.get(position);
        Glide.with(context).load(model.getImage()).into(holder.mIvmissing);
        holder.mtvcomplainDate.setText(model.getComplaintdate());
        holder.mtvcomplainNo.setText(model.getComplainNo());
        holder.mtvmissingname.setText(model.getMissingname());
        holder.mtvmissingage.setText(model.getMissingage());
        holder.mtvmissingcomplexion.setText(model.getMissingskin());
        holder.mtvmissingheight.setText(model.getMissingheight());
        holder.mtvmissingplace.setText(model.getMissingplace());
        holder.mtvmissinghair.setText(model.getMissinghair());
        holder.mtvcomplainername.setText(model.getComplainername());
        holder.mtvcomplainerphone.setText(model.getComplainerphone());
        holder.mtvcomplainerpin.setText(model.getComplainerpin());
    }

    @Override
    public int getItemCount() {
        return missingcomplainClasses.size();
    }

    class AdminHolder extends RecyclerView.ViewHolder {
        ImageView mIvmissing;
        TextView mtvmissingname, mtvmissingplace, mtvmissingcomplexion, mtvmissingheight, mtvmissingage,
                mtvcomplainNo, mtvcomplainDate,
                mtvmissinghair, mtvcomplainername, mtvcomplainerpin, mtvcomplainerphone;

        public AdminHolder(@NonNull View itemView) {
            super(itemView);
            mIvmissing = itemView.findViewById(R.id.img_view_missing_person);
            mtvcomplainDate = itemView.findViewById(R.id.tv_complain_date);
            mtvcomplainNo = itemView.findViewById(R.id.tv_missing_complain_no);
            mtvmissingname = itemView.findViewById(R.id.tv_missing_person_name);
            mtvmissingage = itemView.findViewById(R.id.tv_missing_age);
            mtvmissingcomplexion = itemView.findViewById(R.id.tv_missing_complexion);
            mtvmissingheight = itemView.findViewById(R.id.tv_missing_height);
            mtvmissingplace = itemView.findViewById(R.id.tv_missing_place);
            mtvmissinghair = itemView.findViewById(R.id.tv_missing_hair);
            mtvcomplainername = itemView.findViewById(R.id.tv_complainer_name);
            mtvcomplainerphone = itemView.findViewById(R.id.tv_complainer_phone);
            mtvcomplainerpin = itemView.findViewById(R.id.tv_complainer_pin);
        }
    }
}