package com.demo.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheInspectionService {

    @Autowired
    private CacheManager cacheManager;

    public void printData(String cacheName){
        Cache cache=cacheManager.getCache(cacheName);

      if(cache !=null){
          System.out.println("cached Data is :"+cache.getNativeCache());
      }
      else{
          System.out.println("cached data is not wrt Name:"+cacheName);
      }
  }


}
