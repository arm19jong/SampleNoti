package com.jongzazaal.samplenoti.notification;

import android.support.v4.app.NotificationCompat;
public class GCMItemHttpRequestInterface{}

//public interface HttpRequestInterface {
//    void onHttpRequestSuccess(int from, HttpBaseRequest request, String result);
//    void onHttpRequestFail(int from, HttpBaseRequest request, Exception exception);
//    void onHttpRequestRetry(HttpBaseRequest request, int retryLeft, Exception exception);
//}
//public class GCMItemHttpRequestInterface implements HttpRequestInterface {
//    public interface GCMLoadItemInterface {
//        void showNotification(NotificationMessageEvent msgEvent, NotificationCompat.BigPictureStyle notiStyle);
//        void showBigPicStyleNotification(NotificationMessageEvent msgEvent, GCMItemModel detailObj);
//    }
//
//    private GCMLoadItemInterface mCallback;
//    private NotificationMessageEvent msgEvent;
//    private final String TAG = "GCMLoadItemCallback";
//
//    public GCMItemHttpRequestInterface(GCMLoadItemInterface mCallback, NotificationMessageEvent msgEvent) {
//        this.mCallback = mCallback;
//        this.msgEvent = msgEvent;
//    }
//
//    @Override
//    public void onHttpRequestSuccess(int from, HttpBaseRequest request, String result) {
//        GCMItemModel detailObj = GsonConstant.getInstance().getGsonBuilder().create().fromJson(result, GCMItemModel.class);
//        if (detailObj.getError_code().equals(CodeProvider.CODE_OK)) {
//            mCallback.showBigPicStyleNotification(msgEvent, detailObj);
//        } else {
//            mCallback.showNotification(msgEvent, null);
//        }
//    }
//
//    @Override
//    public void onHttpRequestFail(int from, HttpBaseRequest request, Exception exception) {
//        mCallback.showNotification(msgEvent, null);
//    }
//
//    @Override
//    public void onHttpRequestRetry(HttpBaseRequest request, int retryLeft, Exception exception) {
//
//    }
//}