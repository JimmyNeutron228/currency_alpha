package com.lyugge.alpha_task.ApiResponse;

import lombok.Data;

import java.util.List;

@Data
public class GifObject {
    private List<SubGifObject> data;
}
