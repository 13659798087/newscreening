package lzgene.newscreening.util;

import lzgene.newscreening.entiry.PageResults;

import java.util.HashMap;
import java.util.Map;

/*
  分页工具类
 */
public class Paging {

    public static Map<String, Object> ajaxGrid(PageResults page) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        if (page == null) {
            dataMap.put("pageSize", 10);
            dataMap.put("page", 1);// 当前页
            dataMap.put("total", 0);// 总计录
            dataMap.put("rows", null);
        } else {
            dataMap.put("pageSize", page.getPageSize());
            dataMap.put("page", page.getPageNo());// 当前页
            dataMap.put("total", page.getTotalCount());// 总计录
            dataMap.put("rows", page.getResult());
        }
        return dataMap;
    }


}
