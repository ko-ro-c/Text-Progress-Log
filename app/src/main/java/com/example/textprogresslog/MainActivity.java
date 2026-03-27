package com.example.textprogresslog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int currentStep = 0;

    private final String[] macaronSteps = {
            "材料：粉糖・卵白・アーモンドプードル準備",
            "材料：グラニュー糖・着色料の準備",
            "メレンゲ：強めパワーで泡立て開始",
            "メレンゲ：グラニュー糖を3回に分けて投入",
            "メレンゲ：低速でキメを整える",
            "メレンゲ：角が立つまで（固すぎるくらい！）",
            "マカロナージュ：粉類を一気に入れる",
            "マカロナージュ：最初は切るように混ぜる",
            "マカロナージュ：リボン状になるまで",
            "絞り：生地が広がり、跡が消えるか確認",
            "乾燥：表面が触れるようになるまで待機",
            "乾燥：暖房の有無で速度が変わるので注意",
            "焼成：180度で3分（ピエが出る！）",
            "焼成：130度で12分（アルミで色付き防止）",
            "完成！理想のマカロンの出来上がり！"
    };

    // ★追加：工程に対応する画像の配列（ご自身のファイル名に書き換えてください）
    // 15個のステップと1対1で対応させています
    private final int[] macaronStepImages = {
            R.drawable.macaron_step0,  // 材料1
            R.drawable.macaron_step0,  // 材料2
            R.drawable.macaron_step2,  // メレンゲ1
            R.drawable.macaron_step2,  // メレンゲ2
            R.drawable.macaron_step2,  // メレンゲ3
            R.drawable.macaron_step5,  // メレンゲ完成
            R.drawable.macaron_step6,  // マカロナージュ1
            R.drawable.macaron_step6,  // マカロナージュ2
            R.drawable.macaron_step8,  // マカロナージュ完成
            R.drawable.macaron_step9,  // 絞り
            R.drawable.macaron_step10, // 乾燥1
            R.drawable.macaron_step10, // 乾燥2
            R.drawable.macaron_step12, // 焼成1
            R.drawable.macaron_step12, // 焼成2
            R.drawable.macaron_step14  // 完成！
    };

    private final int totalSteps = 15;

    private ProgressBar progressBar;
    private TextView textView;
    private Button finishButton;
    private Button backButton;
    private TextView stepDescriptionText;
    private ImageView macaronImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.percentText);
        finishButton = findViewById(R.id.finishButton);
        backButton = findViewById(R.id.backButton);
        stepDescriptionText = findViewById(R.id.stepDescriptionText);
        macaronImageView = findViewById(R.id.macaronImageView);
        Button diaryButton = findViewById(R.id.diaryButton);

        loadProgress();
        updateUI();

        finishButton.setOnClickListener(v -> {
            if (currentStep < totalSteps) {
                currentStep++;
                updateUI();
                saveProgress();
            }
        });

        backButton.setOnClickListener(v -> {
            if (currentStep > 0) {
                currentStep--;
                updateUI();
                saveProgress();
            }
        });

        diaryButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DiaryActivity.class);
            startActivity(intent);
        });
    }

    @SuppressLint("SetTextI18n")
    private void updateUI() {
        int percent = (int) (((double) currentStep / totalSteps) * 100);
        progressBar.setProgress(percent);
        textView.setText(percent + "% 完了！");

        if (currentStep < totalSteps) {
            stepDescriptionText.setText(macaronSteps[currentStep]);

            // ★追加：画像を表示する（現在のステップの画像をセット）
            macaronImageView.setImageResource(macaronStepImages[currentStep]);

            finishButton.setText("次へ");
            finishButton.setEnabled(true);
        } else {
            stepDescriptionText.setText("理想のマカロンが完成しました！");

            // ★追加：全行程終了時の画像を表示（最後の画像を表示）
            macaronImageView.setImageResource(macaronStepImages[totalSteps - 1]);

            finishButton.setText("完了");
            finishButton.setEnabled(false);
        }

        backButton.setEnabled(currentStep > 0);
    }

    private void loadProgress() {
        SharedPreferences pref = getSharedPreferences("SaveData", MODE_PRIVATE);
        currentStep = pref.getInt("step", 0);
    }

    private void saveProgress() {
        SharedPreferences pref = getSharedPreferences("SaveData", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("step", currentStep);
        editor.apply();
    }
}