package com.lyugge.alpha_task.ApiClients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "instantwebtools-api", url = "")
public interface CurrencyToUSDClient {
}
