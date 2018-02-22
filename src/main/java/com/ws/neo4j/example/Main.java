/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws.neo4j.example;

import com.ws.neo4j.example.graph.persistence.ForecastGenerator;
import org.apache.logging.log4j.core.config.Configurator;

/**
 *
 * @author dante.basso
 */
public class Main {
    
    //REGULAR FORECAST
    private static final Integer NUMBER_OF_COMPANYS = 1; //4
    private static final Integer NUMBER_OF_PROFESSIONALS = 10; //20
    private static final Integer NUMBER_OF_CUSTOMERS = 1000; //1000
    private static final Integer NUMBER_OF_PRODUCTS = 700; //1200
    private static final boolean USE_THREAD_POOL = true;
    
    //VERY SMALL FORECAST
//    private static final Integer NUMBER_OF_COMPANYS = 1; //4
//    private static final Integer NUMBER_OF_PROFESSIONALS = 10; //20
//    private static final Integer NUMBER_OF_CUSTOMERS = 200; //1000
//    private static final Integer NUMBER_OF_PRODUCTS = 100; //1200
//    private static final boolean USE_THREAD_POOL = true;
    
    public static void main (String[] args) {
        ForecastGenerator generator = new ForecastGenerator();
        generator.generateForecast(NUMBER_OF_COMPANYS, NUMBER_OF_PROFESSIONALS, NUMBER_OF_CUSTOMERS, NUMBER_OF_PRODUCTS, USE_THREAD_POOL);
    }
    
}
