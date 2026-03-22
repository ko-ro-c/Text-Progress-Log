package com.example.textprogresslog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int currentStep = 0; // 0から32まで

    // Javaの基礎：配列を使って、16個のステップを準備
    private final String[] macaronSteps = {
            "材料：粉糖・卵白・アーモンドプードル準備", // Step 0
            "材料：グラニュー糖・着色料の準備",          // Step 1
            "メレンゲ：強めパワーで泡立て開始",          // Step 2
            "メレンゲ：グラニュー糖を3回に分けて投入",    // Step 3
            "メレンゲ：低速でキメを整える",             // Step 4
            "メレンゲ：角が立つまで（固すぎるくらい！）", // Step 5
            "マカロナージュ：粉類を一気に入れる",        // Step 6
            "マカロナージュ：最初は切るように混ぜる",     // Step 7
            "マカロナージュ：リボン状になるまで",        // Step 8
            "絞り：生地が広がり、跡が消えるか確認",      // Step 9
            "乾燥：表面が触れるようになるまで待機",      // Step 10
            "乾燥：暖房の有無で速度が変わるので注意",     // Step 11
            "焼成：180度で3分（ピエが出る！）",         // Step 12
            "焼成：130度で12分（アルミで色付き防止）",   // Step 13
            "完成！理想のマカロンの出来上がり！"         // Step 14
    };

    // 全ステップ数を、配列の数に合わせる
    private final int totalSteps = 15;
    //private final int totalSteps = 32; // 16章 × 2ステップ

    private ProgressBar progressBar;
    private TextView textView;
    private Button finishButton;
    private Button backButton;

    private TextView stepDescriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. まずパーツを紐付ける（findViewById）
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.percentText);
        finishButton = findViewById(R.id.finishButton);
        backButton = findViewById(R.id.backButton);
        stepDescriptionText = findViewById(R.id.stepDescriptionText);
        // ボタンを紐付ける
        Button diaryButton = findViewById(R.id.diaryButton);

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

        // ボタンを押した時の処理
        diaryButton.setOnClickListener(v -> {
            // Intent(今の画面, 次の画面のクラス) を作る
            Intent intent = new Intent(MainActivity.this, DiaryActivity.class);
            // 画面移動を実行！
            startActivity(intent);
        });

        updateUI(); // 起動時にも表示を整える
    }

    // 表示を更新する命令をまとめた「メソッド」
    @SuppressLint("SetTextI18n")
    private void updateUI() {
        // 1. ％の計算と表示
        int percent = (int) (((double) currentStep / totalSteps) * 100);
        progressBar.setProgress(percent);
        textView.setText(percent + "% 完了！");

        // 2. 工程テキストとボタンの制御
        if (currentStep < totalSteps) {
            // 工程テキストに、配列の文字を表示する
            stepDescriptionText.setText(macaronSteps[currentStep]);

            // ボタンの文字は「次へ」で固定
            finishButton.setText("次へ");
            finishButton.setEnabled(true);
        } else {
            // 全行程終了時
            stepDescriptionText.setText("理想のマカロンが完成しました！");
            finishButton.setText("完了");
            finishButton.setEnabled(false);
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