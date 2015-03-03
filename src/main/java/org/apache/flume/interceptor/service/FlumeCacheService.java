package org.apache.flume.interceptor.service;

import java.io.IOException;
import java.io.FileNotFoundException;
import org.apache.flume.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import org.apache.flume.interceptor.EnrichedEventBody;

import java.util.*;

import org.apache.flume.serialization.JSONStringSerializer;

import java.io.FileReader;
 
import com.opencsv.CSVReader;

@Component
public class FlumeCacheService implements ICacheService<Event> {

    private static final Logger logger = LoggerFactory.getLogger(FlumeCacheService.class);
    private String externalFile;

    @Override
    @Cacheable(value = "FlumeCachedEvent")
    public Event intercept(Event d) {
        byte[] payload = d.getBody();
        try {
         EnrichedEventBody enrichedEventBody =  JSONStringSerializer.fromJSONString(new String(payload), EnrichedEventBody.class);
         Map<String, String> exData = enrichedEventBody.getExtraData();
         enrichedEventBody.setExtraData(enrichMap(externalFile, exData));
         byte[] newPayload = enrichedEventBody.toByteArray();
         d.setBody(newPayload);
        } catch (IOException e) {
            logger.error("IOexception" ,e);
        }
        logger.info("Flume cache Service intercepting: " + d.toString());
        return d;
    }
    
    
    /*
    @return Map
    @param String to enrich data according key named 'hostname'.
    @param data to be enriched
    */
    public Map<String, String> enrichMap(String filename, Map<String,String> data){
        String newValue = null;
        try {
        CSVReader reader = new CSVReader(new FileReader(filename)); //default constructor uses comma separator
        List<String[]> allRows = reader.readAll();
         for(String[] row : allRows){
             newValue += Arrays.toString(row);
        }
         data.put("hostname", newValue);
        } catch (FileNotFoundException e ) {
            logger.error("FileNotFoundException", e);
        } catch (IOException e){
            logger.error("IOException", e);
        }
        
        return  data;
    }
    
    
     
}
