package com.shev.server.service;

import com.shev.server.threads.ServerDataThread;

public class ThreadService {
    public static void createServerConnectionDataInThreads(int threadsCounter){
        for (int cursor = 1;cursor<=threadsCounter; cursor++){
            Thread serverTread = new ServerDataThread();
            serverTread.setName("serverThread_"+cursor);
            serverTread.start();
            try {
                serverTread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
    }


}
