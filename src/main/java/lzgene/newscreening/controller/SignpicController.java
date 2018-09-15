package lzgene.newscreening.controller;

import lzgene.newscreening.entiry.PageResults;
import lzgene.newscreening.model.Signpic;
import lzgene.newscreening.services.SignpicServices;
import lzgene.newscreening.util.Guid;
import lzgene.newscreening.util.Paging;
import lzgene.newscreening.util.SSO;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/signpic")
public class SignpicController {
    @Resource
    private SignpicServices signpicServices;

    private String view = "signpic/";

    @RequestMapping("/signpic")
    public String signpic(){
        return view + "signpic";
    }

    @ResponseBody
    @RequestMapping("/getSignpic")
    public Map<String,Object> getUserRole(String pageNumber, String rowNumber, String sortName,
                                          String sortOrder, String sp_name, HttpServletRequest request){

        List<Signpic> signpic = new ArrayList<Signpic>();

        int pageNo = Integer.parseInt((pageNumber == null || pageNumber =="0") ? "1":pageNumber);
        int pageSize = Integer.parseInt((rowNumber == null || rowNumber =="0") ? "10":rowNumber);
        String orderBy = sortName == null  ? "updaterTime":sortName;
        String order = sortOrder == null  ? PageResults.DESC:sortOrder;

        PageResults<Signpic> pageResults = new PageResults<Signpic>();

        pageResults.setPageNo(pageNo);
        pageResults.setPageSize(pageSize);
        pageResults.setOrderBy(orderBy);
        pageResults.setOrder(order);

        signpic = signpicServices.getSignpic(sp_name,(pageNo-1)*pageSize+1, pageNo*pageSize);

        long totalCount = signpicServices.getSignCount(sp_name,(pageNo-1)*pageSize+1, pageNo*pageSize);
        pageResults.setResult(signpic);
        pageResults.setTotalCount(totalCount);

        return Paging.ajaxGrid(pageResults);

    }

    @ResponseBody
    @RequestMapping(value="uploadPicture",headers="content-type=multipart/*" ,method= RequestMethod.POST)
    public Map uploadPicture(@RequestParam("file")MultipartFile file,String sp_name,String id, HttpServletRequest request)throws Exception{
        String sign = "";
        Map map=new HashMap<>();

        Signpic signpic = new Signpic();

        //int YGID001 = (int)request.getSession().getAttribute("ygid001");
        String name = file.getOriginalFilename();
        long filesize  = file.getSize();

        //新增
        if(StringUtils.isEmpty(id)){
            id = Guid.GenerateGUID();
            signpic.setId(id);
            signpic.setSp_name(sp_name);
            signpic.setSp_pic(file.getBytes());
            signpicServices.uploadPicture(signpic);
            sign = "add";
        }else{//修改
            signpicServices.updateSignpic(id,sp_name,file.getBytes());
            sign = "edit";
        }

        map.put("id",id);
        map.put("sign",sign);

        return map;
    }


    @RequestMapping(value = "Pictureshows")
    public ResponseEntity<byte[]> Pictureshow(HttpServletRequest request,String id) {
       // int YGID001 = (int)request.getSession().getAttribute("ygid001");
        Signpic list=signpicServices.Pictureshows(id);//根据员工号查找头像

        byte[] bytes=null;
        if(list!=null){
            bytes=list.getSp_pic();
        }
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentDispositionFormData("attachment", new String("头像".getBytes("GBK"),"ISO8859-1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(bytes,headers, HttpStatus.OK);
    }

    @RequestMapping("/addSignpic")
    public String addSignpic(Model model, String id,String sp_name, String sp_pic,String type){

        model.addAttribute("id",id);
        model.addAttribute("sp_name",sp_name);
        model.addAttribute("sp_pic",sp_pic);
        model.addAttribute("type",type);
        return view+"addSignpic";

    }

    @ResponseBody
    @RequestMapping("/validateSign")
    public Integer validateSign(String sp_name){
        int i = signpicServices.validateSign(sp_name);//查询组别信息
        if(i>0)
            i=1;
        else
            i=0;
        return i;
    }

    //根据id删除电子签名
    @ResponseBody
    @RequestMapping("/deleteSignpic")
    public void deleteSignpic(String id){
        signpicServices.deleteSignpic(id);//查询组别信息
    }


}
