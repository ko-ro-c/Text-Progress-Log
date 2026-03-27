package com.example.textprogresslog;

public class MacaronLog {
    private final String title;
    private final String reflection;
    private final int imageResId; // ★追加：画像のリソースID

    // ★修正：コンストラクタ（3つ受け取るように）
    public MacaronLog(String title, String reflection, int imageResId) {
        this.title = title;
        this.reflection = reflection;
        this.imageResId = imageResId;
    }

    public String getTitle() { return title; }
    public String getReflection() { return reflection; }
    public int getImageResId() { return imageResId; } // ★追加
}