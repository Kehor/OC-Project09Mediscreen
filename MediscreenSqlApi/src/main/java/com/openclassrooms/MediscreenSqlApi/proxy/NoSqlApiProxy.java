package com.openclassrooms.MediscreenSqlApi.proxy;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "microservice-NoSqlApi", url = "${proxy.nosql}")
public interface NoSqlApiProxy {


    @PostMapping(value = "/notes/triggers")
    Integer getTriggersIteration(@RequestParam Long idpatient);

}
