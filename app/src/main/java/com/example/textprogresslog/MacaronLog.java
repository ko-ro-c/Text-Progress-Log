package com.example.textprogresslog;

public class MacaronLog {
    private final String title;      // "#1" など
    private final String reflection; // 「メレンゲがゆるかった」などの反省点
    // 後で画像も追加できるように、画像用の変数も準備しておきます
    // private int imageId; 

    // コンストラクタ（設計図から実体を作る時の決まり）
    public MacaronLog(String title, String reflection) {
        this.title = title;
        this.reflection = reflection;
    }

    // データを取り出すためのメソッド（ゲッター）
    public String getTitle() { return title; }
    public String getReflection() { return reflection; }
}
