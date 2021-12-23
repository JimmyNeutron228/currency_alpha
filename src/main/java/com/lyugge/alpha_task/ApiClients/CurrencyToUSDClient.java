package com.lyugge.alpha_task.ApiClients;

import com.lyugge.alpha_task.ApiResponse.Relation;
import com.lyugge.alpha_task.Config.BaseConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@PropertySource(value = {"classpath:application.properties"})
@FeignClient(name = "currencyClient", url = BaseConfig.currencyApi)
public interface CurrencyToUSDClient {
    @RequestMapping(method = RequestMethod.GET, value = "/historical/{day}.json?app_id=" + BaseConfig.currencyApiKey)
    Relation readRelationByDay(@PathVariable("day") String day);
}
