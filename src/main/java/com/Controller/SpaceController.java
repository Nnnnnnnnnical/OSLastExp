package com.Controller;


import com.Common.Common;
import com.Entity.Doc;
import com.Response.ListResponse;
import com.Service.InitTask;
import com.Service.SpaceService;
import org.omg.CORBA.COMM_FAILURE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SpaceController {

    @Autowired
    private SpaceService spaceService;

    @Autowired
    private InitTask initTask;

    /**
     * 初始化磁盘
     * @return
     */
    @RequestMapping(value = "space/init",method = RequestMethod.POST)
    public ListResponse spaceInit(){
        initTask.initTask();
        ListResponse listResponse = new ListResponse();
        listResponse.setDisk(Common.disk);
        listResponse.setList(Common.list);
        listResponse.setDocList(Common.docList);
        return listResponse;

    }

    /**
     * 新增文件
     * @param name
     * @param size
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "space/add",method = RequestMethod.POST)
    public ListResponse spaceAdd(@RequestParam("name") String name, @RequestParam("size") int size) throws Exception {

        Doc doc = spaceService.add(name,size);
        Common.docList.add(doc);
        ListResponse addResponse = new ListResponse();
        addResponse.setDocList(Common.docList);
        addResponse.setList(Common.list);
        addResponse.setDisk(Common.disk);
        return addResponse;
    }

    /**
     * 删除文件
     * @param name
     * @return
     */
    @RequestMapping(value = "space/delete",method = RequestMethod.POST)
    public ListResponse spaceDelete(@RequestParam("name") String name) throws Exception {
        spaceService.delete(name);
        ListResponse deleteResponse = new ListResponse();
        deleteResponse.setDocList(Common.docList);
        deleteResponse.setList(Common.list);
        deleteResponse.setDisk(Common.disk);
        return deleteResponse;
    }
}
