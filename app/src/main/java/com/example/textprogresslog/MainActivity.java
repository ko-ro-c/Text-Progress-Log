package com.example.textprogresslog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Javaの基礎：進捗を管理する「変数」を用意する
    private int currentProgress = 0; // 現在の進捗率
    private final int totalChapters = 15; // 教科書の全章数

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // パーツをJavaの世界に連れてくる
        ProgressBar progressBar = findViewById(R.id.progressBar);
        TextView textView = findViewById(R.id.percentText);
        Button button = findViewById(R.id.finishButton);

        // ボタンを押した時の「動き」を決める
        button.setOnClickListener(v -> {
            // 進捗を1増やす
            currentProgress++;

            // Javaの算術演算でパーセントを計算
            // (double)で型変換（キャスト）するのがポイント！
            int percent = (int) (((double) currentProgress / totalChapters) * 100);

            // 見た目に反映させる
            progressBar.setProgress(percent);
            textView.setText(percent + "% 完了！");

            if (percent >= 100) {
                textView.setText("祝！教科書読了！");
            }
        });
    }
}