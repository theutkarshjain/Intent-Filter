package com.example.uj.intentfilter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView textView;
    ImageView picView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        picView = findViewById(R.id.imageView);
        textView = (TextView)findViewById(R.id.textView);

        Intent receivedIntent = getIntent();
        String receiveAction = receivedIntent.getAction();
        String receiveType = receivedIntent.getType();

        if(receiveAction.equals(Intent.ACTION_SEND))
        {
            if(receiveType.startsWith("text/"))
            {
                picView.setVisibility(View.GONE);
                String receiverText = receivedIntent.getStringExtra(Intent.EXTRA_TEXT);
                if(receiveType != null)
                {
                    textView.setText(receiverText);
                }
            }
            else if(receiveType.startsWith("image/"))
            {
                textView.setVisibility(View.GONE);
                Uri receivedUri = receivedIntent.getParcelableExtra(Intent.EXTRA_STREAM);
                if(receivedUri != null)
                {
                    picView.setImageURI(receivedUri);
                }
            }
        }
    }

}

