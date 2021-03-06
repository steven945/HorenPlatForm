package com.horen.partner.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.horen.base.util.DisplayUtil;
import com.horen.base.util.ImageLoader;
import com.horen.base.util.PlatformUtil;
import com.horen.partner.bean.Plan;
import com.horen.partner.ui.activity.PlatformWebViewActivity;

/**
 * Created by BuZhiheng on 2017/12/13.
 * <p>
 * 封装方案item viewholder
 * (布局文件大小可能不一样，需要继承此viewholder 构造方法绑定view即可)
 * <p>
 * 首页热门方案 全部方案 全部热门方案 共用此viewholder
 * <p>
 * 调用setData方法 绑定数据
 */
public class OneImgBaseHolder extends RecyclerView.ViewHolder {
    ImageView ivBgSingle; // 单个包装图片

    public OneImgBaseHolder(View itemView) {
        super(itemView);
    }

    public void setData(final Context context, final Plan plan) {
        int screenWidth = DisplayUtil.getScreenWidth(context); // 屏幕宽度
        int ivWidth = screenWidth - DisplayUtil.dip2px(15) * 2;  // 屏幕宽度减去两边的间隙，得到ImageView的真实宽度
        CardView.LayoutParams layoutParams = (CardView.LayoutParams) ivBgSingle.getLayoutParams();
        layoutParams.height = (int) (ivWidth / 2.2f); // 根据设计图上的比例，宽高比为2.2 : 1 求出ImageView的高，屏幕适配
        ivBgSingle.setLayoutParams(layoutParams); // 设置给Imageview
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //  5.0以上不需要加载圆角图片，防止出现cardView和imageview有间隙的情况
            ImageLoader.load(context, plan.getSolutions().get(0).getSolution_bglogo(), ivBgSingle);
        } else { // 5.0以下图片加载圆角Glide
            ImageLoader.loadRoundIV(context, plan.getSolutions().get(0).getSolution_bglogo(), ivBgSingle, 6);
        }
        ivBgSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url;
                if ("2".equals(plan.getSolutions().get(0).getH5_mode())) {
                    url = plan.getSolutions().get(0).getSolution_pc_url();//海外版URL不需要拼接
                } else {
                    url = PlatformUtil.getPlanUrlH5(plan.getSolutions().get(0).getSolution_h5_url());//国内版URL需要拼接
                }
                Intent intent = new Intent(context, PlatformWebViewActivity.class);
                intent.putExtra(PlatformWebViewActivity.WEBVIEW_URL, url);
                context.startActivity(intent);
            }
        });
    }
}