package com.Service;


import com.Common.Common;
import com.Entity.Doc;
import com.Exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SpaceService {

    @Autowired
    private MyException myException;

    //新增文件
    public Doc add(String name ,int size) throws Exception {
        //防止输入相同文件名，检索所有文件寻找相同名字的文件，若存在，抛出错误
        for(int i = 0 ;i<Common.docList.size();i++){
            if(Common.docList.get(i).getName().equals(name)){
                myException.reSameName();
            }
        }

        //对文件信息进行存储
        Doc doc = new Doc();
        doc.setName(name);
        doc.setSize(size);

        //判断磁盘的剩余空间小于文件的空间，则抛出内存不够
        if(Common.disk.getlSize()<doc.getSize()){
            myException.noEnoughMenory();
        }
        doc.setEnd(Common.searchEnd(size)+1);

        //判断磁盘的是否有连续的存储空间存放，若没有，则抛出没有连续存储空间的错误
        if(doc.getEnd() != 0){
            doc.setStart((int) (doc.getEnd()-Math.ceil((double) size/Common.blockSize)+1));
            Common.disk.setlSize(Common.disk.getlSize()-size);
        }else {
            myException.noContiMenroy();
        }


        return doc;

    }

    //删除文件
    public void delete(String name) throws Exception {

        int size=0;

        //查找所找文件
        for(int i =0;i<Common.docList.size();i++){
            if(Common.docList.get(i).getName().equals(name)){
                size = Common.docList.get(i).getSize();
                for(int j = Common.docList.get(i).getStart()-1 ;j< Common.docList.get(i).getEnd();j++){
                    Common.list.set(j,0);
                }

                //从作业列表中删除
                Common.docList.remove(Common.docList.get(i));
                break;
            }
            //若找不到，则抛出没有此文件
            if(i==Common.docList.size()-1){
                myException.noDoc();
            }
        }
        //若所用大小为0，则抛出没有任何文件存储
        if(size == 0){
            myException.noAnyDoc();
        }

        Common.disk.setlSize(Common.disk.getlSize()+size);

    }
}
