package com.shev.server.data;

public class ConnectionServerData {

    private long currentTime;
    private long session;
    private String ip;

    public ConnectionServerData(String random) {
        if(random.contentEquals("random")){
            currentTime = System.currentTimeMillis();
            session = RandomServerData.getRandomSessionId();
            ip = RandomServerData.getRandomServerIP();
        }
    }

    public ConnectionServerData(long currentTime, long session, String ip) {
        this.currentTime = currentTime;
        this.session = session;
        this.ip = ip;
    }

    public ConnectionServerData() {

    }

    public long getCurrentTime() {
        return currentTime;
    }

    public long getSession() {
        return session;
    }

    public String getIp() {
        return ip;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public void setSession(long session) {
        this.session = session;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return this.currentTime+" "+this.session+" "+this.ip;
    }
}
