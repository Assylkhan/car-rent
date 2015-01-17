package com.epam.util;

import com.epam.entity.Driver;

import java.util.HashMap;
import java.util.Map;

public class DriverCache {
    Map<String, Driver> drivers = new HashMap<>();

    public Map<String, Driver> getDrivers() {
        return drivers;
    }

    public void put(String key, Driver driver){
        drivers.put(key, driver);
    }

    public Driver get(String key){
        return drivers.get(key);
    }

    public void remove(String key){
        drivers.remove(key);
    }
//
//    public void setDrivers(Map<String, Driver> drivers) {
//        this.drivers = drivers;
//    }

    private DriverCache(){}
    private static class SingletonHolder {
        public static final DriverCache DRIVER_CACHE = new DriverCache();
    }
    public static DriverCache getInstance(){
        return SingletonHolder.DRIVER_CACHE;
    }
}
