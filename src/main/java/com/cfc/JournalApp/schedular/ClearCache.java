package com.cfc.JournalApp.schedular;

import com.cfc.JournalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClearCache {
    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 * * * * *")
    public void clearAppCache() {
        appCache.init();

    }
}
