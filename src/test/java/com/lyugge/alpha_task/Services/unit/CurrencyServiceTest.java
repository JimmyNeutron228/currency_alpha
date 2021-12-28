package com.lyugge.alpha_task.Services.unit;

import com.lyugge.alpha_task.ApiClients.CurrencyToUSDClient;
import com.lyugge.alpha_task.ApiClients.GifClient;
import com.lyugge.alpha_task.ApiResponse.GifObject;
import com.lyugge.alpha_task.ApiResponse.Relation;
import com.lyugge.alpha_task.ApiResponse.SubGifObject;
import com.lyugge.alpha_task.Config.BaseConfig;
import com.lyugge.alpha_task.Services.CurrencyService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CurrencyServiceTest extends UnitTest {
    @Mock
    private CurrencyToUSDClient currencyClient;

    @Mock
    private GifClient gifClient;

    @InjectMocks
    private CurrencyService currencyService;

    @Test
    void shouldCorrectlyReturnGifUrlTest() {
        String brokeGifUrl = "https://media2.giphy.com/media/" +
                "ZGH8VtTZMmnwzsYYMf/giphy.gif?" +
                "cid=4799edfbrmdpy0qwce9eskvfkgwt18o1ehj" +
                "k6ka9e2bsvmbq&rid=giphy.gif&ct=g";
        String currency = "rub";
        Relation prevRelation = relationGenerate(currency, 73.6);
        Relation nowRelation = relationGenerate(currency, 71.6);
        Date nowDate = new Date();
        Date prevDate = new Date(nowDate.getTime() - 86400000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        when(currencyClient.readRelationByDay(dateFormat.format(prevDate))).thenReturn(prevRelation);
        when(currencyClient.readRelationByDay(dateFormat.format(nowDate))).thenReturn(nowRelation);
        when(gifClient.readBrokeGif()).thenReturn(gifObjectGenerate(brokeGifUrl));

        final var result = currencyService.necessaryGif(currency);
        assertEquals(brokeGifUrl, result);
        verify(currencyClient, times(2)).readRelationByDay(any());
        verify(gifClient, times(1)).readBrokeGif();
        verify(gifClient, times(0)).readRichGif();
    }

    @Test
    void shouldCorrectlyReturnGifUrlTest1() {
        String richGifUrl = "https://media1.giphy.com/media/lptjRBxFKCJmFoibP3/giphy.gif?" +
                "cid=4799edfboxk06wb9emf9gaizlaz0h70x3za6y9vm0frgwxgy&rid=giphy.gif&ct=g";
        String currency = "rub";
        Relation prevRelation = relationGenerate(currency, 71.6);
        Relation nowRelation = relationGenerate(currency, 72.6);
        Date nowDate = new Date();
        Date prevDate = new Date(nowDate.getTime() - 86400000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        when(currencyClient.readRelationByDay(dateFormat.format(prevDate))).thenReturn(prevRelation);
        when(currencyClient.readRelationByDay(dateFormat.format(nowDate))).thenReturn(nowRelation);
        when(gifClient.readRichGif()).thenReturn(gifObjectGenerate(richGifUrl));

        final var result = currencyService.necessaryGif(currency);
        assertEquals(richGifUrl, result);
        verify(currencyClient, times(2)).readRelationByDay(any());
        verify(gifClient, times(0)).readBrokeGif();
        verify(gifClient, times(1)).readRichGif();
    }

    @Test
    void shouldCorrectlyReturnGifUrlTest2() {
        String currency = "aaa";
        Relation prevRelation = relationGenerate(currency, 71.6);
        Relation nowRelation = errorRelationGenerate();
        Date nowDate = new Date();
        Date prevDate = new Date(nowDate.getTime() - 86400000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        when(currencyClient.readRelationByDay(dateFormat.format(prevDate))).thenReturn(prevRelation);
        when(currencyClient.readRelationByDay(dateFormat.format(nowDate))).thenReturn(nowRelation);

        final var result = currencyService.necessaryGif(currency);
        assertEquals(BaseConfig.errorGif, result);
        verify(currencyClient, times(2)).readRelationByDay(any());
        verify(gifClient, times(0)).readRichGif();
        verify(gifClient, times(0)).readBrokeGif();
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

    private Relation errorRelationGenerate() {
        Relation relation = new Relation();
        relation.setBase("usd");
        relation.setDisclaimer("1");
        relation.setTimestamp(1L);
        relation.setLicense("1");
        return relation;
    }

    private GifObject gifObjectGenerate(String url) {
        GifObject gifObject = new GifObject();
        SubGifObject subGifObject = new SubGifObject();
        subGifObject.setImages(
                new HashMap<>() {{
                    put("original", new HashMap<>() {{
                        put("url", url);
                    }});
                }}
        );
        gifObject.setData(new ArrayList<>() {{
            for (int i = 0; i < 50; i++)
                add(subGifObject);
        }});
        return gifObject;
    }
}