package com.Service;

import com.Common.Common;
import org.omg.CORBA.COMM_FAILURE;
import org.springframework.stereotype.Service;


@Service
public class InitTask {

    //初始化位示图
    public void initTask(){
        //设置所有物理块的标识为0，标识未被占用
        for(int i =0;i<Common.width*Common.length;i++){
            Common.list.add(0);
        }
        //初始化磁盘，设置原始大小和剩余大小为磁盘大小
        Common.disk.setoSize(Common.spaceSize);
        Common.disk.setlSize(Common.spaceSize);
    }
}
