package com.example.textprogresslog;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class DiaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_diary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 日記をまとめて入れておくリストを作る
        ArrayList<MacaronLog> diaryList = new ArrayList<>();

// 過去10回分のデータを追加していく
        diaryList.add(new MacaronLog("#1", "メレンゲ失敗。角が立たなかった。"));
        diaryList.add(new MacaronLog("#2", "マカロナージュしすぎた。ピエが出なかった。"));
        diaryList.add(new MacaronLog("#3", "成功！焼き色がきれい。"));
// ...これを10回目まで続ける

        // 1. TextViewを紐付ける
        TextView diaryTextView = findViewById(R.id.diaryTextView);

// 2. リストの内容を1つの大きな「文字」にまとめる
        StringBuilder allLog = new StringBuilder();
        for (MacaronLog log : diaryList) {
            // ここで getTitle() と getReflection() を使う！
            allLog.append(log.getTitle()).append("\n");
            allLog.append(log.getReflection()).append("\n\n");
        }

// 3. 画面に表示する
        diaryTextView.setText(allLog.toString());

        // ボタンを紐付ける
        Button backButton = findViewById(R.id.backToMainButton);

// ボタンを押した時の処理
        backButton.setOnClickListener(v -> {
            // 現在の画面（DiaryActivity）を閉じて、前の画面に戻る
            finish();
        });

    }
}