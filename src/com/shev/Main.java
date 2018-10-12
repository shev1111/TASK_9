package com.shev;

import com.shev.server.service.ThreadService;

public class Main {
    public static void main(String[] args) {
        for (int cursor=1;cursor<=3;cursor++){
            ThreadService.createServerConnectionDataInThreads(3);
            try {
                Thread.currentThread().sleep(180_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }






    }

}
