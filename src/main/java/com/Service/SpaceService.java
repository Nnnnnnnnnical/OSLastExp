package com.Service;


import com.Common.Common;
import com.Entity.Doc;
import org.omg.CORBA.COMM_FAILURE;
import org.springframework.stereotype.Service;

import java.rmi.server.ExportException;

@Service
public class SpaceService {

    //新增文件
    public Doc add(String name ,int size) throws Exception {
        //防止输入相同文件名
        for(int i = 0 ;i<Common.docList.size();i++){
            if(Common.docList.get(i).getName().equals(name)){
                throw new Exception("不能重复名字");
            }
        }
        //对文件信息进行存储
        Doc doc = new Doc();
        doc.setName(name);
        doc.setSize(size);

        //判断磁盘的剩余空间小于文件的空间，则抛出内存不够
        if(Common.disk.getlSize()<doc.getSize()){
            throw new Exception("内存不够");
        }
        doc.setEnd(Common.searchEnd(size)+1);
        if(doc.getEnd() != 0){
            doc.setStart((int) (doc.getEnd()-Math.ceil((double) size/Common.blockSize)+1));
            Common.disk.setlSize(Common.disk.getlSize()-size);
        }else {
            throw new Exception("无法分配连续空间");
        }


        return doc;

    }

    //删除文件
    public void delete(String name){

        int size=0;
        for(int i =0;i<Common.docList.size();i++){
            if(Common.docList.get(i).getName().equals(name)){
                size = Common.docList.get(i).getSize();
                for(int j = Common.docList.get(i).getStart()-1 ;j< Common.docList.get(i).getEnd();j++){
                    Common.list.set(j,0);
                }

                Common.docList.remove(Common.docList.get(i));
                break;
            }
        }

        Common.disk.setlSize(Common.disk.getlSize()+size);

    }
}
