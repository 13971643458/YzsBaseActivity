package com.yzs.yzsbaseactivitylib.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzs.yzsbaseactivitylib.entity.EventCenter;
import com.yzs.yzsbaseactivitylib.loading.LoadingDialog;
import com.yzs.yzsbaseactivitylib.util.ToastUtils;

import org.greenrobot.eventbus.Subscribe;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2016/10/24
 */
public abstract class YzsBaseFragment extends SupportFragment {
    private static final String TAG = "YzsBaseFragment";
    protected android.view.View rootView;

    public Toolbar mToolbar;
    public TextView title;
    public ImageView back;
    public TextView tv_menu;
    public ImageView iv_menu;

    public YzsBaseFragment() { /* compiled code */ }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (null != getArguments()) {
            getBundleExtras(getArguments());
        }
        View view = initContentView(inflater, container, savedInstanceState);
        initView(view);
        initLogic(view);
        return view;
    }

    protected View createFragmentView(LayoutInflater inflater, @Nullable ViewGroup container, int resId) {
        View view = null;
        if (null != inflater) {
            view = inflater.inflate(resId, container, false);
        }

        // TODO
        if (null == view) {
            Log.e(TAG, "fragment view is not created！");
        }

        return view;
    }

    // 初始化UI setContentView
    protected abstract View initContentView(LayoutInflater inflater, @Nullable ViewGroup container,
                                            @Nullable Bundle savedInstanceState);

    // 初始化控件
    protected abstract void initView(View view);

    // 逻辑处理
    protected abstract void initLogic(View view);


    /**
     * 获取bundle信息
     *
     * @param bundle
     */
    protected abstract void getBundleExtras(Bundle bundle);

    /**
     * EventBus接收消息
     *
     * @param center 消息接收
     */
    @Subscribe
    public void onEventMainThread(EventCenter center) {

        if (null != center) {
            onEventComing(center);
        }

    }

    /**
     * EventBus接收消息
     *
     * @param center 获取事件总线信息
     */
    protected abstract void onEventComing(EventCenter center);

    /**
     * 显示默认加载动画 默认加载文字
     */
    protected void showLoadingDialog() {
        LoadingDialog.showLoadingDialog(getActivity());
    }

    /**
     * 显示加载动画 默认加载文字
     *
     * @param type
     */
    protected void showLoadingDialog(int type) {
        LoadingDialog.showLoadingDialog(getActivity(), type);
    }

    /**
     * 显示加载动画 默认加载文字，自定义图片
     *
     * @param type
     */
    protected void showLoadingDialog(int type, int drawableId) {
        LoadingDialog.showLoadingDialog(getActivity(), type, drawableId);
    }

    /**
     * 显示默认加载动画 自定义加载文字
     *
     * @param str
     */
    protected void showLoadingDialog(String str) {
        LoadingDialog.showLoadingDialog(getActivity(), str);
    }

    /**
     * 显示加载动画 自定义加载文字
     *
     * @param type
     * @param str
     */
    protected void showLoadingDialog(int type, String str) {
        LoadingDialog.showLoadingDialog(getActivity(), type, str);
    }

    /**
     * 显示加载动画 自定义加载文字 自定义图片(只对YzsDialog有效果)
     *
     * @param type
     * @param str
     */
    protected void showLoadingDialog(int type, String str, int drawable) {
        LoadingDialog.showLoadingDialog(getActivity(), type, str, drawable);
    }

    /**
     * 取消加载动画
     */
    protected void cancelLoadingDialog() {
        LoadingDialog.cancelLoadingDialog();
    }

    //Toast显示
    protected void showShortToast(String string) {
        ToastUtils.showShortToast(getActivity(), string);
    }

    protected void showShortToast(int stringId) {
        ToastUtils.showShortToast(getActivity(), stringId);
    }

    protected void showLongToast(String string) {
        ToastUtils.showShortToast(getActivity(), string);
    }

    protected void showLongToast(int stringId) {
        ToastUtils.showShortToast(getActivity(), stringId);
    }

    /**
     * startActivity
     *
     * @param clazz 目标Activity
     */
    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }

    /**
     * startActivity with bundle
     *
     * @param clazz  目标Activity
     * @param bundle 数据
     */
    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * @param clazz 目标Activity
     */
    protected void readyGoThenKill(Class<?> clazz) {
        readyGoThenKill(clazz, null);
    }

    /**
     * @param clazz  目标Activity
     * @param bundle 数据
     */
    protected void readyGoThenKill(Class<?> clazz, Bundle bundle) {
        readyGo(clazz, bundle);
        getActivity().finish();
    }

    /**
     * startActivityForResult
     *
     * @param clazz       目标Activity
     * @param requestCode 发送判断值
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz       目标Activity
     * @param requestCode 发送判断值
     * @param bundle      数据
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }


}
