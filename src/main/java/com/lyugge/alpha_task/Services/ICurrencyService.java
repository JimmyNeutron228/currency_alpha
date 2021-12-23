package com.lyugge.alpha_task.Services;

import com.lyugge.alpha_task.ApiClients.CurrencyToUSDClient;
import com.lyugge.alpha_task.Enums.ExchangeRateRelation;
import org.springframework.lang.NonNull;


public interface ICurrencyService {
    @NonNull
    ExchangeRateRelation currencySituation(@NonNull String currency);
    @NonNull
    String necessaryGif(@NonNull String currency);
}
