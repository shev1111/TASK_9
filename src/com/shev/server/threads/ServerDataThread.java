package com.shev.server.threads;

import com.shev.server.data.ConnectionServerData;
import com.shev.server.service.ServerService;

import java.util.ArrayList;

public class ServerDataThread extends Thread {
    @Override
    public void run() {
        ArrayList<ConnectionServerData> connectionServerData = ServerService.createRandomConnectionServerData(10);
        ServerService.writeLog(connectionServerData);
        sleepThread();

    }

    private void sleepThread(){
        try {
            this.sleep(180_000);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
