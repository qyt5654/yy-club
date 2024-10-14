package com.jingdianyy.subject.infra.basic.es;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "es.cluster")
public class EsConfigProperties {

    private List<EsClusterConfig> esConfig = new ArrayList<>();

    public List<EsClusterConfig> getEsConfig(){
        return esConfig;
    }

    public void setEsConfigs(List<EsClusterConfig> esConfig){
        this.esConfig = esConfig;
    }

}
