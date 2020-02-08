package com.devApp.devApp.util;

import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class UniqueIdGenerator {

    public synchronized String getUniqueId() {
        // There are tons of generators to do this.
        return UUID.randomUUID().toString();
    }
}
