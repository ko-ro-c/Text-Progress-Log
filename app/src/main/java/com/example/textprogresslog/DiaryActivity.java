package com.example.textprogresslog;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat; // ★追加
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

        // システムバーの余白設定（これがあると画面が崩れません）
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayList<MacaronLog> diaryList = new ArrayList<>();
        diaryList.add(new MacaronLog("#1", "中に空洞ができてしまった。バリバリ", R.drawable.diary1));
        diaryList.add(new MacaronLog("#2", "生地は柔らかくなったけど、空洞あり", R.drawable.diary2));
        diaryList.add(new MacaronLog("#3", "膨らみよし、少し割れた", R.drawable.diary3));
        diaryList.add(new MacaronLog("#4", "成功", R.drawable.diary4));
        diaryList.add(new MacaronLog("#5", "成功", 0));
        diaryList.add(new MacaronLog("#6", "ココアパウダーは調整が難しい", R.drawable.diary6));
        diaryList.add(new MacaronLog("#7", "マカロナージュしすぎ、ココアパウダーは減らして正解", R.drawable.diary7));
        diaryList.add(new MacaronLog("#8", "一番いい感じ", R.drawable.diary8));
        diaryList.add(new MacaronLog("#9", "成功！焼き色がきれい。", R.drawable.diary9));
        diaryList.add(new MacaronLog("#10", "成功！", R.drawable.diary10));


        // ★修正：IDを diaryButton から diaryContainer に変更
        LinearLayout container = findViewById(R.id.diaryContainer);

        for (MacaronLog log : diaryList) {
            TextView titleTv = new TextView(this);
            titleTv.setText(log.getTitle());
            titleTv.setTextSize(20);
            titleTv.setPadding(0, 16, 0, 8);

            // ★修正：色の取得方法を最新版に変更
            titleTv.setTextColor(ContextCompat.getColor(this, android.R.color.black));
            container.addView(titleTv);

            if (log.getImageResId() != 0) {
                ImageView iv = new ImageView(this);
                iv.setImageResource(log.getImageResId());
                iv.setAdjustViewBounds(true);
                iv.setPadding(0, 8, 0, 8);
                container.addView(iv);
            }

            TextView reflectionTv = new TextView(this);
            reflectionTv.setText(log.getReflection());
            reflectionTv.setPadding(0, 0, 0, 32);
            container.addView(reflectionTv);
        }

        Button backButton = findViewById(R.id.backToMainButton);
        backButton.setOnClickListener(v -> finish());
    }
}