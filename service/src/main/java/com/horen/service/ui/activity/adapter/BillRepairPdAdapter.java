package com.horen.service.ui.activity.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.base.widget.HRTabView;
import com.horen.service.R;
import com.horen.service.bean.BillDetailBean;

import java.util.List;

/**
 * @author :ChenYangYi
 * @date :2018/09/04/10:10
 * @description :账单中心 耗材产品Adapter
 * @github :https://github.com/chenyy0708
 */
public class BillRepairPdAdapter extends BaseQuickAdapter<BillDetailBean.BillListBean, BaseViewHolder> {
    public BillRepairPdAdapter(@Nullable List<BillDetailBean.BillListBean> data) {
        super(R.layout.service_item_bill_detail_product, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BillDetailBean.BillListBean item) {
        if (helper.getLayoutPosition() % 2 == 0) {
            helper.setBackgroundColor(R.id.tab_view, ContextCompat.getColor(mContext, R.color.color_f5));
        } else {
            helper.setBackgroundColor(R.id.tab_view, ContextCompat.getColor(mContext, R.color.white));
        }
        HRTabView tabView = helper.getView(R.id.tab_view);
        tabView.setData(new String[]{item.getProduct_name(), item.getProduct_type(), String.valueOf(item.getStorage_qty())}, new String[]{"1", "1", "1"});
    }
}
