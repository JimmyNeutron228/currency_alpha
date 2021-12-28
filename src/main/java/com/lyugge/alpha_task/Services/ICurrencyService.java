package com.lyugge.alpha_task.Services;

import org.springframework.lang.NonNull;


public interface ICurrencyService {
    @NonNull
    String necessaryGif(@NonNull String currency);
}
