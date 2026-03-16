package com.example.textprogresslog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int currentStep = 0; // 0から32まで
    private final int totalSteps = 32; // 16章 × 2ステップ

    private ProgressBar progressBar;
    private TextView textView;
    private Button finishButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.percentText);
        finishButton = findViewById(R.id.finishButton);
        backButton = findViewById(R.id.backButton);

        // 進捗ボタンの動き
        finishButton.setOnClickListener(v -> {
            if (currentStep < totalSteps) {
                currentStep++;
                updateUI();
            }
        });

        // 戻るボタンの動き
        backButton.setOnClickListener(v -> {
            if (currentStep > 0) {
                currentStep--;
                updateUI();
            }
        });

        updateUI(); // 起動時にも表示を整える
    }

    // 表示を更新する命令をまとめた「メソッド」
    @SuppressLint("SetTextI18n")
    private void updateUI() {
        // 1. ％の計算
        int percent = (int) (((double) currentStep / totalSteps) * 100);
        progressBar.setProgress(percent);
        textView.setText(percent + "% 完了！");

        // 2. ボタンの文字を動的に変える
        int chapter = (currentStep / 2) + 1;
        if (currentStep >= totalSteps) {
            finishButton.setText("全行程クリア！");
            finishButton.setEnabled(false); // 押せなくする
        } else if (currentStep % 2 == 0) {
            // 偶数のときは「読破」
            finishButton.setText("第" + chapter + "章 読破");
        } else {
            // 奇数のときは「課題クリア」
            finishButton.setText("第" + chapter + "章 課題完了");
        }

        // 3. 0のときは「戻る」を押せなくする
        backButton.setEnabled(currentStep > 0);
    }
}