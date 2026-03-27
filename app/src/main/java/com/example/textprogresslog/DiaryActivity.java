package com.example.textprogresslog;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DiaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_diary);
        // (ViewCompatのリスナーはそのまま残してください)

        // 日記リスト（第3引数に画像IDを入れる。ない場合は 0 にする）
        ArrayList<MacaronLog> diaryList = new ArrayList<>();
        diaryList.add(new MacaronLog("#1", "メレンゲ失敗。角が立たなかった。", R.drawable.diary1));
        diaryList.add(new MacaronLog("#2", "マカロナージュしすぎた。ピエが出なかった。", 0)); // 写真なし
        diaryList.add(new MacaronLog("#3", "成功！焼き色がきれい。", R.drawable.diary3));

        // ★重要：コンテナを取得
        LinearLayout container = findViewById(R.id.diaryContainer);

        // リストをループして画面を作っていく
        for (MacaronLog log : diaryList) {
            // 1. タイトルのTextViewを作る
            TextView titleTv = new TextView(this);
            titleTv.setText(log.getTitle());
            titleTv.setTextSize(20);
            titleTv.setPadding(0, 16, 0, 8);
            titleTv.setTextColor(getResources().getColor(android.R.color.black));
            container.addView(titleTv);

            // 2. 画像がある場合だけImageViewを作る
            if (log.getImageResId() != 0) {
                ImageView iv = new ImageView(this);
                iv.setImageResource(log.getImageResId());
                iv.setAdjustViewBounds(true); // 画像の比率を保つ
                iv.setPadding(0, 8, 0, 8);
                container.addView(iv);
            }

            // 3. 反省点のTextViewを作る
            TextView reflectionTv = new TextView(this);
            reflectionTv.setText(log.getReflection());
            reflectionTv.setPadding(0, 0, 0, 32);
            container.addView(reflectionTv);
        }

        // 戻るボタンの処理はそのまま
        Button backButton = findViewById(R.id.backToMainButton);
        backButton.setOnClickListener(v -> finish());
    }
}