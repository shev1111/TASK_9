package com.shev.server.service;

import com.shev.server.data.ConnectionServerData;
import com.shev.server.exeption.IllegalDateParametersException;
import com.shev.server.log.MyServerLogger;

import java.util.ArrayList;
import java.util.Date;

public class ServerService {
    private static final String USER_DIR = System.getProperty("user.dir");
    private static final String SEP = System.getProperty("file.separator");
    private static final String LOG_FILE = USER_DIR+SEP+"src"+SEP+"com"+SEP+"shev"+SEP+"server"+SEP+"log"+SEP+"server_logs.txt";

    /*@param int numbers how many instances to return in ArrayList
    * @return ArrayList of ConnectionServerData
    * */
    public static ArrayList<ConnectionServerData> createRandomConnectionServerData(int numberOfInstances){
        ArrayList<ConnectionServerData> connectionServerData = new ArrayList<>(numberOfInstances);
        for(int cursor=0;cursor<numberOfInstances;cursor++){
            connectionServerData.add(new ConnectionServerData("random"));
        }
        return connectionServerData;
    }

    /*@param String from and to dates with pattern format yyyy-MM-dd HH:mm:ss
    * @return ArrayList of ConnectionServerData for defined period
    * */
    public static ArrayList<ConnectionServerData> getConnectionServerDataByPeriod(Date from, Date to) throws IllegalDateParametersException{
        return MyServerLogger.getServerSessionsPeriod(from,to, LOG_FILE);
    }

    /*
    * write ConnectionServerData logs to specific file
    * */
    public static void writeLog(ArrayList<ConnectionServerData> connectionServerData){
        MyServerLogger.writeLogToTxt(connectionServerData, LOG_FILE);
    }

    public static void writeLog(ArrayList<ConnectionServerData> connectionServerData, String threadName){
        MyServerLogger.writeLogToTxt(connectionServerData, LOG_FILE, threadName);
    }

    /*
    * delete three days ald logs from specific file
    * */
    public static void deleteOldData(){
        MyServerLogger.deleteThreeDaysOldData(LOG_FILE);
    }
}
