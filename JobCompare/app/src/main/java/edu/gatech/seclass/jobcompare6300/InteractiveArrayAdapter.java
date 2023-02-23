package edu.gatech.seclass.jobcompare6300;

import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class InteractiveArrayAdapter extends ArrayAdapter<RankedJob> {

    private final List<RankedJob> list;
    private final Activity context;

    public InteractiveArrayAdapter(Activity context, List<RankedJob> list) {
        super(context, R.layout.rowbuttonlayout, list);
        this.context = context;
        this.list = list;
    }

    static class ViewHolder {
        protected TextView text;
        protected CheckBox checkbox;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.rowbuttonlayout, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) view.findViewById(R.id.label);
            viewHolder.checkbox = (CheckBox) view.findViewById(R.id.check);
            viewHolder.checkbox
                    .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CompoundButton buttonView,
                                                     boolean isChecked) {
                            RankedJob element = (RankedJob) viewHolder.checkbox
                                    .getTag();
                            System.out.println("ACTIVATED!!!");
                            //element.setSelected(buttonView.isChecked());
                            //RankJobActivity.dataList.get(position).setSelected(buttonView.isChecked());

                            list.get(position).setSelected(buttonView.isChecked());

                        }
                    });
            view.setTag(viewHolder);
            viewHolder.checkbox.setTag(list.get(position));
        } else {
            view = convertView;
            ((ViewHolder) view.getTag()).checkbox.setTag(list.get(position));
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.text.setText(list.get(position).getDisplayName());
        if (list.get(position).currentjob){
            holder.text.setTextColor(Color.GREEN);
        } else {
            holder.text.setTextColor(Color.BLACK);
        }
        holder.checkbox.setChecked(list.get(position).isSelected());
        return view;
    }
}
