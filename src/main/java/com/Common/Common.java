package com.Common;

import com.Entity.Disk;
import com.Entity.Doc;

import java.util.ArrayList;
import java.util.List;

public class Common {

    public static int spaceSize = 100*1024;  //磁盘总空间
    public static int blockSize = 4;        //物理块大小
    public static int width = 50;           //位示图的列数
    public static int length = 512;         //位示图的行数
    public static Disk disk = new Disk();

    public static List<Integer> list = new ArrayList<>();   //位示图是否空闲
    public static List<Doc> docList = new ArrayList<>();    //文件list


    //查找物理块截至位置
    public static int searchEnd(int size){
        int count = 0,k=0;
        int end = 0;

        for(int i =0;i<list.size();i++){
            if(list.get(i).equals(0)){
                //count用来计数判断是否有足够的物理块存放文件
                count++;
                //如果count等于文件所需要的物理块，返回当前所在的位置为结束位置，并将之前计数的物理块记成1
                if (count == Math.ceil((double) size / blockSize)) {
                    end = i;
                    for(int j = i;k<count;j--,k++){
                        list.set(j,1);
                    }
                    return end;
                }
                //如果检索到最后一个物理块，则返回-1告知没有连续的存储空间存储
                if(i==list.size()-1){
                    return -1;
                }
            }
            //如果检索到物理块标识为1，则说明该物理块已被占用，count需要重新计数
            if(list.get(i).equals(1)){
                count=0;
            }

        }
        return end;
    }
}
