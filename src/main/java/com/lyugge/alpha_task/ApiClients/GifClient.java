package com.lyugge.alpha_task.ApiClients;

import com.lyugge.alpha_task.Config.BaseConfig;
import com.lyugge.alpha_task.ApiResponse.GifObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@PropertySource(value = {"classpath:application.properties"})
@FeignClient(name = "gifClient", url = BaseConfig.gifApi)
public interface GifClient {
    @RequestMapping(method = RequestMethod.GET,
            value = "?api_key=" + BaseConfig.gifApiKey + "&q=riche&limit=" + 50)
    GifObject readRichGif();

    @RequestMapping(method = RequestMethod.GET,
            value = "?api_key=" + BaseConfig.gifApiKey + "&q=broke&limit=" + 50)
    GifObject readBrokeGif();
}
