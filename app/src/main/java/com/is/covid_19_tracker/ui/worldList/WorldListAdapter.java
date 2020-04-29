package com.is.covid_19_tracker.ui.worldList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.is.covid_19_tracker.R;
import com.is.covid_19_tracker.data.local.entity.Corona;
import com.is.covid_19_tracker.databinding.ItemWorldListAdapterBinding;
import com.is.covid_19_tracker.ui.base.DataBoundListAdapter;
import com.is.covid_19_tracker.util.Objects;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Islam Elshnawey
 * @date 2020-03-31
 * <p>
 * is.elshnawey@gmail.com
 **/
public class WorldListAdapter extends DataBoundListAdapter<Corona, ItemWorldListAdapterBinding> implements Filterable {

    private final androidx.databinding.DataBindingComponent dataBindingComponent;
    private BankClickCallback callback;
    private DataBoundListAdapter.DiffUtilDispatchedInterface diffUtilDispatchedInterface;
    private Context mContext;
    private List<Corona> contactList;
    private List<Corona> contactListFiltered;

    public WorldListAdapter(androidx.databinding.DataBindingComponent dataBindingComponent,
                            BankClickCallback callback,
                            DataBoundListAdapter.DiffUtilDispatchedInterface diffUtilDispatchedInterface) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = callback;
        this.diffUtilDispatchedInterface = diffUtilDispatchedInterface;
    }

    @Override
    protected ItemWorldListAdapterBinding createBinding(ViewGroup parent) {

        ItemWorldListAdapterBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_world_list_adapter, parent, false, dataBindingComponent);
        mContext = parent.getContext();
        binding.getRoot().setOnClickListener(v -> {
            Corona notification = binding.getCorona();
            if (notification != null && callback != null) {
                callback.onClick(notification);
            }
        });
        return binding;
    }

    @Override
    protected void dispatched() {
        if (diffUtilDispatchedInterface != null) {
            diffUtilDispatchedInterface.onDispatched();
        }
    }

    @Override
    protected void bind(ItemWorldListAdapterBinding binding, Corona item) {
        binding.setCorona(item);

        binding.tvCountry.setText(item.getCountry());
        binding.tvCases.setText("Cases : " + item.getCases());
        binding.tvDeaths.setText("Deaths : " + item.getDeaths());

        if (item.getCountryInfo().flag != null) {

            Glide.with(mContext)
                    .load(item.CountryInfo.flag)
                    .thumbnail(Glide.with(mContext)
                            .load(item.CountryInfo.flag)).into(binding.imgFlag);

        } else {

            if (binding.imgFlag != null) {
                binding.imgFlag.setImageResource(R.drawable.placeholder_image);
            }

        }

    }

    @Override
    protected boolean areItemsTheSame(Corona oldItem, Corona newItem) {
        return Objects.equals(oldItem.getId(), newItem.getId())
                && oldItem.getCases() == (newItem.getCases())
                && oldItem.getDeaths() == (newItem.getDeaths());
    }

    @Override
    protected boolean areContentsTheSame(Corona oldItem, Corona newItem) {
        return Objects.equals(oldItem.getId(), newItem.getId())
                && oldItem.getCases() == (newItem.getCases())
                && oldItem.getDeaths() == (newItem.getDeaths());
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    contactListFiltered = contactList;
                } else {
                    List<Corona> filteredList = new ArrayList<>();
                    for (Corona row : contactList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getCountry().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    contactListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                contactListFiltered = (ArrayList<Corona>) filterResults.values;

                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }

    public interface BankClickCallback {
        void onClick(Corona notification);
    }
}
