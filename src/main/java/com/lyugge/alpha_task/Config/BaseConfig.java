package com.lyugge.alpha_task.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class BaseConfig {
    public final static String currencyApi = "https://openexchangerates.org/api/";

    public final static String gifApi = "api.giphy.com/v1/gifs/search";

    public final static String currencyApiKey = "d6a21609b17c4afba2b7953518b5e4f2";

    public final static String gifApiKey = "fc56hSF9CWB01ll9FhcdV9KpydezWmKC";

    public final static String errorGif = "https://c.tenor.com/DiUjye_MGoAAAAAd/not-found-404error.gif";
}