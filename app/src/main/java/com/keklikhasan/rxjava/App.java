package com.keklikhasan.rxjava;

import android.app.Application;
import android.content.Intent;

import com.keklikhasan.rxjava.service.NotificationService;

public class App extends Application {

    public static final RxBus RX_BUS = new RxBus();

    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this, NotificationService.class));
    }
}
