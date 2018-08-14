package com.example.programing.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.programing.myapplication.Main2Activity.NEW_WORD_ACTIVITY_REQUEST_CODE;

public class MainActivity extends AppCompatActivity {

    private EditText mEditWordView;
    public static final String EXTRA_REPLY = "com.example.programing.myapplication.REPLY";
    public static final String EXTRA_MODEL_EDIT = "com.example.programing.myapplication.MODEL.EDIT";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditWordView = findViewById(R.id.edit_word);

        final Button button = findViewById(R.id.button_save);
        final Button edit_button = findViewById(R.id.button_edit);

        SharedPreferences g = getSharedPreferences("Edited",MODE_PRIVATE);
        String editedData = g.getString("word",null);
        if(editedData != null){
            mEditWordView.setText(editedData);
            SharedPreferences.Editor editor = g.edit();
            editor.clear();
            editor.apply();
            button.setVisibility(View.GONE);
            edit_button.setVisibility(View.VISIBLE);
        }

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String word = mEditWordView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    replyIntent.putExtra(EXTRA_MODEL_EDIT, "false");
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });

        edit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String word = mEditWordView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    replyIntent.putExtra(EXTRA_MODEL_EDIT, "true");
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
