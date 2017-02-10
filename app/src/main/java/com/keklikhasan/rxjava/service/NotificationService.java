package com.keklikhasan.rxjava.service;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.keklikhasan.rxjava.App;
import com.keklikhasan.rxjava.R;
import com.keklikhasan.rxjava.model.NotificationMessage;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class NotificationService extends Service {

    private Disposable disposable;

    @Override
    public void onCreate() {
        super.onCreate();
        App.RX_BUS.observe(NotificationMessage.class)
                .subscribe(new Observer<NotificationMessage>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(NotificationMessage notificationMessage) {
                        showNotification(notificationMessage);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "RxBus exception");
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void showNotification(NotificationMessage notificationMessage) {
        NotificationManager manager
                = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification
                = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_chat_bubble_black_24dp)
                .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .setContentTitle(notificationMessage.title)
                .setContentText(notificationMessage.message)
                .setDefaults(Notification.DEFAULT_ALL)
                .build();
        manager.notify(1, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
