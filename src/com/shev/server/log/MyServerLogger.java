package com.shev.server.log;

import com.shev.server.data.ConnectionServerData;
import com.shev.server.exeption.IllegalDateParametersException;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MyServerLogger {

    public static void writeLogToTxt (ArrayList<ConnectionServerData> connectionServerData, String LOG_FILE) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            for (ConnectionServerData serverData : connectionServerData) {
                bufferedWriter.write(Long.toString(serverData.getCurrentTime())+" "+
                                         serverData.getSession()+" "+
                                         serverData.getIp());
                bufferedWriter.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void writeLogToTxt (ArrayList<ConnectionServerData> connectionServerData, String LOG_FILE, String threadName) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            for (ConnectionServerData serverData : connectionServerData) {
                bufferedWriter.write(threadName+" "+Long.toString(serverData.getCurrentTime())+" "+
                                         serverData.getSession()+" "+
                                         serverData.getIp());
                bufferedWriter.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static ArrayList<ConnectionServerData> getServerSessionsPeriod(Date from, Date to, String LOG_FILE) throws IllegalDateParametersException{
        if(from.getTime()>to.getTime()){
            throw new IllegalDateParametersException("'to' parameter must be greater then 'from'!");
        }
        ArrayList<ConnectionServerData> connectionServerDataList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(LOG_FILE))) {

            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {
                ConnectionServerData connectionServerData =new ConnectionServerData();
                String[] tokens = currentLine.split(" ");

                for (int cursor = 0; cursor<tokens.length;cursor++){

                    switch (cursor){

                        case 0:connectionServerData.setCurrentTime(Long.parseLong(tokens[cursor]));
                        case 1:connectionServerData.setSession(Long.parseLong(tokens[cursor]));
                        case 2:connectionServerData.setIp(tokens[cursor]);
                    }
                    long sessionDate = connectionServerData.getCurrentTime();
                    if(sessionDate>=from.getTime()&&sessionDate<=to.getTime()){
                        connectionServerDataList.add(connectionServerData);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return connectionServerDataList;
    }

    private static void writeLogToTxt (StringBuffer serverConDataBuff, String LOG_FILE) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(LOG_FILE))) {
            bufferedWriter.write(serverConDataBuff.toString());
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void deleteThreeDaysOldData(String LOG_FILE){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(LOG_FILE))) {

            String currentLine;
            StringBuffer serverConDataBuffer = new StringBuffer("");

            Calendar calendar = Calendar.getInstance();
            long todayMillis = calendar.getTimeInMillis();
            calendar.add(Calendar.DAY_OF_MONTH,-3);
            long threeDaysAfterMillis = calendar.getTimeInMillis();

            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] tokens = currentLine.split(" ");
                long sessionTime = Long.parseLong(tokens[0]);
                if(sessionTime>=threeDaysAfterMillis&&sessionTime<=todayMillis){
                    serverConDataBuffer.append(currentLine);
                    serverConDataBuffer.append("\n");
                }
            }

            if(serverConDataBuffer.length()!=0){
                System.out.println(serverConDataBuffer.toString());
                writeLogToTxt(serverConDataBuffer, LOG_FILE);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
