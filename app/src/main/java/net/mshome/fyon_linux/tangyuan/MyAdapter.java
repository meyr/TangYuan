package net.mshome.fyon_linux.tangyuan;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.nio.Buffer;
import java.util.List;

/**
 * Created by Dean Guo on 10/20/14.
 */
public class MyAdapter
    extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{

    private List<Actor> actors;
    private Context mContext;

    public MyAdapter(Context context , List<Actor> actors)
    {
        this.mContext = context;
        this.actors = actors;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i )
    {
        // 给ViewHolder设置布局文件
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int i )
    {
        // 给ViewHolder设置元素
        Actor p = actors.get(i);
        if(p.inout) {
            viewHolder.mTextView.setText("內");
            viewHolder.mCardView.setBackgroundColor(Color.WHITE);
        }
        else {
            viewHolder.mTextView.setText("外");
            viewHolder.mCardView.setBackgroundColor(Color.YELLOW);
        }

        if(p.num_big_sian != 0 || p.num_small_sian != 0 || p.num_nama_sian != 0)
        {
            String src = "鹹湯圓     ";
            if (p.num_big_sian != 0)
                src += String.format("  %-2s  大", p.num_big_sian);
            else
                src += String.format("           ");

            if (p.num_small_sian != 0)
                  src += String.format("  %-2s  小",p.num_small_sian);
            else
                  src += String.format("          ");

            if (p.num_nama_sian != 0)
                src += String.format("  %-2s  盒",p.num_nama_sian);
            else
                src += String.format("          ");


            viewHolder.mSian.setText(src);
        }

        viewHolder.mTotalPrice.setText("總價："+String.valueOf(p.total_price));

//        viewHolder.mImageView.setImageDrawable(mContext.getDrawable(p.getImageResourceId(mContext)));
    }

    @Override
    public int getItemCount()
    {
        // 返回数据总数
        return actors == null ? 0 : actors.size();
    }

    // 重写的自定义ViewHolder
    public static class ViewHolder
        extends RecyclerView.ViewHolder
    {
        public TextView mTextView,mSian;
        public CardView mCardView;
        public TextView mTotalPrice;

        //public ImageView mImageView;

        public ViewHolder( View v )
        {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.name);
            mCardView = (CardView) v.findViewById(R.id.cardview);
            mSian = (TextView) v.findViewById(R.id.sian);
            mTotalPrice = (TextView) v.findViewById(R.id.cardview_totalprice);
           // mImageView = (ImageView) v.findViewById(R.id.pic);
        }
    }
}
