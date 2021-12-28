package com.lyugge.alpha_task.ApiResponse;

import java.util.HashMap;

public class SubGifObject {
    private HashMap<String, HashMap<String, String>> images;

    public HashMap<String, HashMap<String, String>> getImages() {
        return images;
    }

    public void setImages(HashMap<String, HashMap<String, String>> images) {
        this.images = images;
    }
}