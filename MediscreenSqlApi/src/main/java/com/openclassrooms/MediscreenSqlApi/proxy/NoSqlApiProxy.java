package com.openclassrooms.MediscreenSqlApi.proxy;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "microservice-NoSqlApi", url = "http://localhost:8082")
public interface NoSqlApiProxy {


    @PostMapping(value = "/notes/triggers")
    Integer getTriggersIteration(@RequestParam Long idpatient);

}
