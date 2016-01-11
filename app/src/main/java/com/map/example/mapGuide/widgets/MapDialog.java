package com.map.example.mapGuide.widgets;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.map.example.R;
import com.map.example.mapGuide.utils.ToastUtils;
import com.map.example.mapGuide.utils.Utils;

import java.net.URISyntaxException;

/**
 * Created by liuliuchen on 15/12/7.
 */
public class MapDialog extends Dialog {
    private String addStr;
    private Context mContext;

    public MapDialog(Context context, int themeResId, String address) {
        super(context, themeResId);
        this.addStr = address;
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_map);
        findViewById(R.id.btn_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapDialog.this.dismiss();
            }
        });
        findViewById(R.id.map_baidu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isAvilible(mContext, "com.baidu.BaiduMap")) {
                    try {
                        String uu = "intent://map/geocoder?address=" + addStr + "&src=hanhe|hanhe#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end";
                        mContext.startActivity(Intent.getIntent(uu));
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                } else {
                    ToastUtils.toast(mContext, mContext.getString(R.string.string_map_not_install_notice), ToastUtils.LENGTH_SHORT);
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse("http://api.map.baidu.com/geocoder?address=" + addStr + "&output=html&src=hanhe|hanhe");
                    intent.setData(content_url);
                    mContext.startActivity(intent);

                }
                MapDialog.this.dismiss();
            }
        });
        findViewById(R.id.map_gaode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isAvilible(mContext, "com.autonavi.minimap")) {
                    Intent intent = new Intent("android.intent.action.VIEW",
                            Uri.parse("androidamap://keywordNavi?sourceApplication=softname&keyword=" + addStr + "&style=2"));
                    intent.setPackage("com.autonavi.minimap");
                    mContext.startActivity(intent);
                } else {
                    ToastUtils.toast(mContext, mContext.getString(R.string.string_map_not_install_notice), ToastUtils.LENGTH_SHORT);
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse("http://m.amap.com/?k=" + addStr);
                    intent.setData(content_url);
                    mContext.startActivity(intent);
                }
                MapDialog.this.dismiss();
            }
        });
        findViewById(R.id.map_tengxun).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isAvilible(mContext, "com.tencent.map")) {
                    Intent intent = new Intent("android.intent.action.VIEW",
                            Uri.parse("qqmap://map/search?keyword=" + addStr + "&referer=nonghuobang"));
                    intent.setPackage("com.tencent.map");
                    mContext.startActivity(intent);
                } else {
                    ToastUtils.toast(mContext, mContext.getString(R.string.string_map_not_install_notice), ToastUtils.LENGTH_SHORT);
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse("http://apis.map.qq.com/uri/v1/routeplan?type=drive&to=" + addStr + "&policy=1&referer=hanhe");
                    intent.setData(content_url);
                    mContext.startActivity(intent);
                }
                MapDialog.this.dismiss();

            }
        });
    }
}
