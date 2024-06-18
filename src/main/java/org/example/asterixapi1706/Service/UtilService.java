package org.example.asterixapi1706.Service;

import java.util.UUID;

public class UtilService {

    public String returnRandomId(){
        return UUID.randomUUID().toString();
    }
}
