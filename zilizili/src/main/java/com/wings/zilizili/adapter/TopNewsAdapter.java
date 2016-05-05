package com.wings.zilizili.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wings.zilizili.R;
import com.wings.zilizili.domain.TopNewsItem;
import com.wings.zilizili.utils.PicassoImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wing on 2016/4/26.
 */
public class TopNewsAdapter extends PagerAdapter {

    private final Context context;
    private List<TopNewsItem> topNewsList;
    private OnItemClickListener mListener;

    public TopNewsAdapter(Context context, List<TopNewsItem> topNewsList, OnItemClickListener listener) {
        this.context = context;
        this.mListener = listener;
        if (topNewsList == null) {
            this.topNewsList = new ArrayList<>();
        } else {
            this.topNewsList = topNewsList;
        }
    }

    public List<TopNewsItem> getTopNewsList() {
        return topNewsList;
    }

    public void setTopNewsList(List<TopNewsItem> topNewsList) {
        this.topNewsList = topNewsList;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (topNewsList.size() == 0) {
            return null;
        }
        position = position % topNewsList.size();

        View view = View.inflate(context, R.layout.item_top_news, null);
        final ImageView imageView = (ImageView) view.findViewById(R.id.iv_item);
        final TopNewsItem topNewsItem = topNewsList.get(position);
        PicassoImageLoader.getInstance().
                displayTopNewsImage(context, topNewsItem.topimage, imageView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(v, imageView, topNewsItem.topimage);
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}