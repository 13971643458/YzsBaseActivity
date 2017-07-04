package com.yzs.yzsbaseactivitylib.util;

import android.app.Activity;
import android.util.Log;

import com.yzs.yzsbaseactivitylib.dialog.ProgressDialog;
import com.yzs.yzsbaseactivitylib.dialog.YzsLoadingDialog;


/**
 * author 姚智胜
 * version V1.0
 * Description: 加载数据时显示的对话框
 * date 2016/11/17 21:33
 */
public class LoadingDialogUtils {
    private static final String TAG = "LoadingDialogUtils";

    private static ProgressDialog progressDialog;
    private static YzsLoadingDialog yzsLoadingDialog;
    /**
     * 默认载入loading
     */
    public static final int PROGRESS_LOADING = 0;
    /**
     * yzsLoading动画
     */
    public static final int YZS_LOADING = 1;

    /**
     * 载入loading，使用默认提示词
     */
    public static void showLoadingDialog(Activity activity, int type) {

        showLoadingDialog(activity, type, null, 0);

    }

    /**
     * 载入loading，使用自定义提示词
     */
    public static void showLoadingDialog(Activity activity, int type, String message) {

        showLoadingDialog(activity, type, message, 0);

    }

    /**
     * 载入loading，使用默认提示词，自定义图片
     */
    public static void showLoadingDialog(Activity activity, int type, int drawableId) {

        showLoadingDialog(activity, type, null, drawableId);

    }

    /**
     * 载入loading，使用自定义提示词,自定义图片(只对YzsLoadingDialog有效果)
     */
    public static void showLoadingDialog(Activity activity, int type, String message, int drawableId) {

        switch (type) {
            case PROGRESS_LOADING:
                showProgressLoading(activity, message);
                break;

            case YZS_LOADING:
                showYzsLoading(activity, message, drawableId);
                break;

            default:
                showProgressLoading(activity, message);
                break;
        }
    }


    private static void showProgressLoading(Activity activity) {
        try {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(activity, "请稍候");
            }
            progressDialog.setCancelable(false);

            progressDialog.show();
        } catch (Exception e) {
            Log.e(TAG, "progressDialog启动失败");
        }
    }

    private static void showProgressLoading(Activity activity, String message) {
        try {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(activity, null == message ? "请稍候" : message);
            }
            progressDialog.setCancelable(false);

            progressDialog.show();
        } catch (Exception e) {
            Log.e(TAG, "progressDialog启动失败");
        }
    }

    private static void showYzsLoading(Activity activity) {
        try {
            if (yzsLoadingDialog == null) {
                yzsLoadingDialog = new YzsLoadingDialog(activity, "请稍候");
            }
            yzsLoadingDialog.setCancelable(false);
            yzsLoadingDialog.show();
        } catch (Exception e) {
            Log.e(TAG, "yzsLoadingDialog启动失败");
        }
    }

    private static void showYzsLoading(Activity activity, String message) {
        try {
            if (yzsLoadingDialog == null) {
                yzsLoadingDialog = new YzsLoadingDialog(activity, message);
            }
            yzsLoadingDialog.setCancelable(false);
            yzsLoadingDialog.show();
        } catch (Exception e) {
            Log.e(TAG, "yzsLoadingDialog启动失败");
        }
    }

    private static void showYzsLoading(Activity activity, int drawableId) {
        try {
            if (yzsLoadingDialog == null) {
                yzsLoadingDialog = new YzsLoadingDialog(activity,activity.getResources().getDrawable(drawableId));
                Log.e(TAG, "初始化YzsLoading");
            }
            yzsLoadingDialog.setCancelable(false);

            yzsLoadingDialog.show();
        } catch (Exception e) {
            Log.e(TAG, "yzsLoadingDialog启动失败");
        }
    }

    private static void showYzsLoading(Activity activity, String message, int drawableId) {
        try {
            if (yzsLoadingDialog == null && drawableId != 0) {
                yzsLoadingDialog = new YzsLoadingDialog(activity, message, activity.getResources().
                        getDrawable(drawableId));
            } else {
                yzsLoadingDialog = new YzsLoadingDialog(activity, message);
            }
            yzsLoadingDialog.setCancelable(false);
            yzsLoadingDialog.show();
        } catch (Exception e) {
            Log.e(TAG, "yzsLoadingDialog启动失败");
        }
    }


    /**
     * 默认载入loading
     */
    public static void showLoadingDialog(Activity activity) {
        showLoadingDialog(activity, PROGRESS_LOADING);
    }


    /**
     * 载入默认loading 自定义message
     *
     * @param message
     */
    public static void showLoadingDialog(Activity activity, String message) {

        showLoadingDialog(activity, PROGRESS_LOADING, message);
    }

    /**
     * 取消loading
     */
    public static void cancelLoadingDialog() {

        if (progressDialog != null && progressDialog.isShowing()) {
            try {
                progressDialog.dismiss();
            } catch (Exception e) {
                Log.e(TAG, "progressDialog销毁失败");
            }
        }
        progressDialog = null;

        if (yzsLoadingDialog != null && yzsLoadingDialog.isShowing()) {
            try {
                yzsLoadingDialog.dismiss();
            } catch (Exception e) {
                Log.e(TAG, "yzsLoadingDialog销毁失败");
            }
        }
        yzsLoadingDialog = null;
    }


}
