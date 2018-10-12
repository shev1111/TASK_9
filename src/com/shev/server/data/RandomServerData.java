package com.shev.server.data;

import java.util.Random;

public class RandomServerData {

    public  static String getRandomServerIP(){
        StringBuilder ipBuilder = new StringBuilder("");
        for(int cursor = 1; cursor<=3;cursor++){
            ipBuilder.append(getRandomNumberInRange(0,255));
            ipBuilder.append(".");
            if(cursor==3){
                ipBuilder.append(getRandomNumberInRange(0,255));
            }

        }
        return ipBuilder.toString();
    }

    static int getRandomSessionId(){
        return getRandomNumberInRange(100000000, 999999999);
    }

    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
