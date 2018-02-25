package az.plainpiedemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import az.plainpie.PieView;
import az.plainpie.animation.PieAngleAnimation;
import az.plainpiedemo.R;

/**
 * @author alejandro.zurcher
 */

public class ChartDataAdapter extends RecyclerView.Adapter<ChartDataAdapter.DataViewHolder> {

    class DataViewHolder extends RecyclerView.ViewHolder {

        private final PieView pieView;

        DataViewHolder(View itemView) {
            super(itemView);
            pieView = (PieView) itemView.findViewById(R.id.pie_view);

            PieAngleAnimation animation1 = new PieAngleAnimation(pieView);
            animation1.setDuration(3000);
            pieView.startAnimation(animation1);
        }
    }

    private final float[] chartsData;

    public ChartDataAdapter(float[] chartsData) {
        this.chartsData = chartsData;
    }

    @Override
    public ChartDataAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChartDataAdapter.DataViewHolder holder, int position) {
        float data = chartsData[position];
        holder.pieView.setPercentage(data);
    }

    @Override
    public int getItemCount() {
        return chartsData.length;
    }

}
