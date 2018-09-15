/*
package lzgene.newscreening;

import lzgene.newscreening.entiry.Node;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class  NewscreeningApplicationTests {

    //递归
    private List<String> returnList = new ArrayList<String>();

    public String getChildNodes(List<Node> list, String typeId) {
        if(list == null && typeId == null) return "";
        for (Iterator<Node> iterator = list.iterator(); iterator.hasNext();) {
            Node node = (Node) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (node.getParentId().equals("0") && typeId.equals ( node.getId() ) ) {
                recursionFn(list, node);
            }
            // 二、遍历所有的父节点下的所有子节点
                      */
/*if (node.getParentId()==0) {
                          recursionFn(list, node);
                      }*//*

        }
        return returnList.toString();
    }

    private void recursionFn(List<Node> list, Node node) {
        List<Node> childList = getChildList(list, node);// 得到子节点列表
        if (hasChild(list, node)) {// 判断是否有子节点
            returnList.add(node.getId());
            Iterator<Node> it = childList.iterator();
            while (it.hasNext()) {
                Node n = (Node) it.next();
                recursionFn(list, n);
            }
        } else {
            returnList.add(node.getId());
        }
    }

    // 得到子节点列表
    private List<Node> getChildList(List<Node> list, Node node) {
        List<Node> nodeList = new ArrayList<Node>();
        Iterator<Node> it = list.iterator();
        while (it.hasNext()) {
            Node n = (Node) it.next();
            if (n.getParentId() == node.getId()) {
                nodeList.add(n);
            }
        }
        return nodeList;
    }

    // 判断是否有子节点
    private boolean hasChild(List<Node> list, Node node) {
        return getChildList(list, node).size() > 0 ? true : false;
    }

    // 本地模拟数据测试
    @Test
    public void aaa() {
        long start = System.currentTimeMillis();
        List<Node> nodeList = new ArrayList<Node>();
        Node node1 = new Node("1", "宜春市产前筛查中心", "0");
        Node node2 = new Node("2", "赣州市产前筛查中心", "0");
        Node node3 = new Node("3", "宜春市01", "1");
        Node node4 = new Node("4", "宜春市02", "1");
        Node node5 = new Node("5", "赣州区01", "2");
        Node node6 = new Node("6", "赣州区02", "2");

        Node node7 = new Node("7", "宜春市博爱医院", "3");
        Node node8 = new Node("8", "宜春市人民医院", "3");
        Node node9 = new Node("9", "宜春市市妇幼保健院01", "4");
        Node node10 = new Node("10", "宜春市市妇幼保健院02", "4");

        Node node11 = new Node("11", "赣州市浙赣友好医院010", "5");
        Node node12 = new Node("12", "赣州市浙赣友好医院011", "5");
        Node node13 = new Node("13", "赣州市妇幼保健院021", "6");
        Node node14 = new Node("14", "赣州市妇幼保健院021", "6");

        nodeList.add(node1);
        nodeList.add(node2);
        nodeList.add(node3);
        nodeList.add(node4);
        nodeList.add(node5);
        nodeList.add(node6);
        nodeList.add(node7);
        nodeList.add(node8);
        nodeList.add(node9);
        nodeList.add(node10);
        nodeList.add(node11);
        nodeList.add(node12);
        nodeList.add(node13);
        nodeList.add(node14);

        NewscreeningApplicationTests mt = new NewscreeningApplicationTests();
        System.out.println(mt.getChildNodes(nodeList, "2"));
        long end = System.currentTimeMillis();
        System.out.println("用时:" + (end - start) + "ms");
    }
}



*/
