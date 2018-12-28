package com.Service;

import com.Common.Common;
import org.omg.CORBA.COMM_FAILURE;
import org.springframework.stereotype.Service;


@Service
public class InitTask {

    //初始化位示图
    public void initTask(){
        for(int i =0;i<Common.width*Common.length;i++){
            Common.list.add(0);
        }
        Common.disk.setoSize(Common.spaceSize);
        Common.disk.setlSize(Common.spaceSize);
    }
}
