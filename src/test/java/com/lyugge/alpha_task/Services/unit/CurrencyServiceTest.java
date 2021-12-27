package com.lyugge.alpha_task.Services.unit;

import com.lyugge.alpha_task.ApiClients.CurrencyToUSDClient;
import com.lyugge.alpha_task.ApiResponse.Relation;
import com.lyugge.alpha_task.Enums.ExchangeRateRelation;
import com.lyugge.alpha_task.Services.CurrencyService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CurrencyServiceTest extends UnitTest {
    @Mock
    private CurrencyToUSDClient currencyClient;

    @InjectMocks
    private CurrencyService currencyService;

    @Test
    void shouldCorrectlyCheckingCurrencyTest() {
        String currency = "rub";
        Relation prevRelation = relationGenerate(currency, 73.6);
        Relation nowRelation = relationGenerate(currency, 71.6);
        Date nowDate = new Date();
        Date prevDate = new Date(nowDate.getTime() - 86400000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        when(currencyClient.readRelationByDay(dateFormat.format(prevDate))).thenReturn(prevRelation);
        when(currencyClient.readRelationByDay(dateFormat.format(nowDate))).thenReturn(nowRelation);

        final var result = currencyService.currencySituation(currency);
        assertEquals(ExchangeRateRelation.LESS_THAN, result);
        verify(currencyClient, times(2)).readRelationByDay(any());
    }

    private Relation relationGenerate(String currency, Double sale) {
        Relation relation = new Relation();
        relation.setBase("usd");
        relation.setDisclaimer("1");
        relation.setTimestamp(1L);
        relation.setLicense("1");
        HashMap<String, Double>map = new HashMap<>();
        map.put(currency, sale);
        relation.setRates(map);
        return relation;
    }
}