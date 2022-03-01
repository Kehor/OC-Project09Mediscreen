package com.openclassrooms.MediscreenSqlApi.microservice;



import com.openclassrooms.MediscreenSqlApi.proxy.NoSqlApiProxy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class NoSqlApiMicroserviceImpl implements NoSqlApiMicroservice {
    @Autowired
    private NoSqlApiProxy noSqlApiProxy;

    @Override
    public Integer getTriggersIteration(Long idpatient){
        int trigger = noSqlApiProxy.getTriggersIteration(idpatient);
        return trigger;
    }

}
