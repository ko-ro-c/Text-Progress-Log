package com.example.textprogresslog;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
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

        // 1. まずパーツを紐付ける（findViewById）
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.percentText);
        finishButton = findViewById(R.id.finishButton);
        backButton = findViewById(R.id.backButton);

        // 2. 次にデータをロードする！
        loadProgress();

        // 3. ロードした値を使って、画面を最新状態にする
        updateUI();

        // 4. その後、ボタンのクリック設定をする
        // 進捗ボタンの動き
        finishButton.setOnClickListener(v -> {
            if (currentStep < totalSteps) {
                currentStep++;
                updateUI();
                saveProgress();
            }
        });

        // 戻るボタンの動き
        backButton.setOnClickListener(v -> {
            if (currentStep > 0) {
                currentStep--;
                updateUI();
                saveProgress();
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

    // 1. データの読み込み（アプリ起動時に呼ぶ）
    private void loadProgress() {
        // "SaveData" という名前の保存箱を開く
        SharedPreferences pref = getSharedPreferences("SaveData", MODE_PRIVATE);
        // "step" という名前で保存されている数字を取り出す。なければ 0 を返す
        currentStep = pref.getInt("step", 0);
    }

    // 2. データの保存（ボタンを押した時などに呼ぶ）
    private void saveProgress() {
        SharedPreferences pref = getSharedPreferences("SaveData", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        // "step" という名前で現在の進捗を書き込む
        editor.putInt("step", currentStep);
        editor.apply(); // 保存実行！
    }
}