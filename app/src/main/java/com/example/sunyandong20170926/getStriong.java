package com.example.sunyandong20170926;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 悻 on 2017/9/26.
 */

public class getStriong {

    public static String readFromNetWork(InputStream is){


        try {
            ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
            byte[] buff=new byte[1024];
            int len=0;
            while((len=is.read(buff))!=-1){
                outputStream.write(buff,0,len);

            }
            is.close();
            outputStream.close();
            return outputStream.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
