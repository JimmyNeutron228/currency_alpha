package com.lyugge.alpha_task.Services;

import com.lyugge.alpha_task.ApiClients.CurrencyToUSDClient;
import com.lyugge.alpha_task.ApiClients.GifClient;
import com.lyugge.alpha_task.ApiResponse.GifObject;
import com.lyugge.alpha_task.ApiResponse.Relation;
import com.lyugge.alpha_task.Enums.ExchangeRateRelation;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class CurrencyService implements ICurrencyService {
    private final CurrencyToUSDClient currencyClient;
    private final GifClient gifClient;

    @Override
    @NonNull
    public ExchangeRateRelation currencySituation(@NonNull String currency) {
        Date nowDate = new Date();
        Date prevDate = new Date(nowDate.getTime() - 86400000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowDay = dateFormat.format(nowDate);
        String prevDay = dateFormat.format(prevDate);
        Relation nowRelation = currencyClient.readRelationByDay(nowDay);
        Relation prevRelation = currencyClient.readRelationByDay(prevDay);
        if (nowRelation.curRelation(currency) > prevRelation.curRelation(currency)) {
            return ExchangeRateRelation.MORE_THAN;
        }
        return ExchangeRateRelation.LESS_THAN;
    }

    @Override
    @NonNull
    public String necessaryGif(@NonNull String currency) {
        GifObject object;
        ExchangeRateRelation relation = currencySituation(currency);
        if (relation.equals(ExchangeRateRelation.MORE_THAN)) {
            object = gifClient.readRichGif();
        } else {
            object = gifClient.readBrokeGif();
        }
        return object
                .getData()
                .get((int) (Math.random() * 49))
                .getImages()
                .get("original")
                .get("url");
    }
}
