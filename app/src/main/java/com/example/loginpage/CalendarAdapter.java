package com.example.loginpage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter for displaying calendar dates in a RecyclerView.
 * It shows each date, handles click events, and highlights dates with events.
 */
public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {

    //List of dates to display in the calendar
    private List<DateModel> dateList;
    //Listener to handle clicks on calendar dates
    private OnItemListener onItemListener;
    //Color used to highlight dates that have events
    private int eventColor;

    /**
     * Interface for handling clicks on calendar dates.
     */
    public interface OnItemListener {
        void onItemClick(int day);
    }
    /**
     * Creates a CalendarAdapter.
     * @param dateList list of dates to display
     * @param onItemListener listener for date clicks
     * @param eventColor color to highlight dates with events
     */
    public CalendarAdapter(List<DateModel> dateList, OnItemListener onItemListener, int eventColor) {
        this.dateList = dateList;
        this.onItemListener = onItemListener;
        this.eventColor = eventColor;
    }
    /**
     * Creates a new ViewHolder for a date item.
     * @param parent the parent ViewGroup
     * @param viewType the type of the new view
     * @return a new ViewHolder instance
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_date, parent, false);
        return new ViewHolder(view, onItemListener);
    }
    /**
     * Binds the data for a date at the given position to the ViewHolder.
     * @param holder the ViewHolder to bind data to
     * @param position the position of the date in the list
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DateModel model = dateList.get(position);

        if (model.day == 0) {
            holder.dateText.setText("");
            holder.dateText.setAlpha(0f);
            holder.itemView.setClickable(false);
        } else {
            holder.dateText.setText(String.valueOf(model.day));
            holder.dateText.setAlpha(model.isCurrentMonth ? 1.0f : 0.3f);

            // Highlight event days (optional)
            if(model.hasEvent) {
                holder.dateText.setTextColor(eventColor);
            } else {
                holder.dateText.setTextColor(holder.itemView.getResources()
                        .getColor(android.R.color.black));
            }

            holder.itemView.setClickable(true);
        }
    }
    /**
     * Returns the total number of date items.
     * @return number of dates in the list
     */
    @Override
    public int getItemCount() {
        return dateList.size();
    }

    /**
     * ViewHolder class for calendar date items.
     * Handles displaying the date and detecting clicks.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView dateText;
        OnItemListener onItemListener;

        /**
         * Creates a ViewHolder and sets up click listener.
         * @param itemView the view for this item
         * @param onItemListener listener for date clicks
         */
        public ViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
            super(itemView);
            dateText = itemView.findViewById(R.id.dateText);
            this.onItemListener = onItemListener;
            itemView.setOnClickListener(this);
        }
        /**
         * Handles clicks on the date item.
         * Calls the listener if the date is valid (non-zero).
         * @param v the clicked view
         */
        @Override
        public void onClick(View v) {
            int day = 0;
            try {
                day = Integer.parseInt(dateText.getText().toString());
            } catch (NumberFormatException e) {
                // ignore invalid clicks on empty dates
            }
            if(day != 0) {
                onItemListener.onItemClick(day);
            }
        }
    }
}
