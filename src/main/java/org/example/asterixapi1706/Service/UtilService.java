package org.example.asterixapi1706.Service;

import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class UtilService {

    public String returnRandomId(){
        return UUID.randomUUID().toString();
    }
}
