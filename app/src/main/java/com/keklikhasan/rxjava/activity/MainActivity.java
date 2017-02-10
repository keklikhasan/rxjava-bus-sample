package com.keklikhasan.rxjava.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.keklikhasan.rxjava.App;
import com.keklikhasan.rxjava.R;
import com.keklikhasan.rxjava.model.NotificationMessage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.text)
    protected EditText textEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.send)
    protected void onClickSend() {
        NotificationMessage notificationMessage = new NotificationMessage();
        notificationMessage.message = textEditText.getText().toString();
        notificationMessage.title = "App NotificationMessage Test";
        App.RX_BUS.send(notificationMessage);
    }


}
