package com.Response;

import com.Entity.Disk;
import com.Entity.Doc;

import java.util.List;

public class ListResponse {

    private List<Integer> list;
    private List<Doc> docList;
    private Disk disk;

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public List<Doc> getDocList() {
        return docList;
    }

    public void setDocList(List<Doc> docList) {
        this.docList = docList;
    }

    public Disk getDisk() {
        return disk;
    }

    public void setDisk(Disk disk) {
        this.disk = disk;
    }
}
