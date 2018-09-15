package lzgene.newscreening;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class time {

    //@Test
    public void time() {
/*
        dbNameMap.put("ycfy", "lzcqwsycfy");
        dbNameMap.put("gzfy", "lzcqwsgzfy");
        dbIPMap.put("ycfy", "127.0.0.1");
        dbIPMap.put("gzfy", "127.0.0.1");*/

        Map<String, String> dbNameMap = new HashMap<String, String>();
        Map<String, String> dbIPMap = new HashMap<String, String>();

        String a = "ycfy,gzfy";
        String b = "lzcqwsycfy,lzcqwsgzfy";
        String c = "127.0.0.1";

        String[] aa = a.split(",");
        String[] bb = b.split(",");

        for(int i=0;i<aa.length;i++ ){
            dbNameMap.put(aa[i],bb[i]);
            dbIPMap.put(aa[i],c);
        }


        /*String key=(String)propertyNames.nextElement();
        String value=properties.getProperty(key);
        map.put(key,value);*/


       /* System.out.println(System.currentTimeMillis());
        String s="2018-07-18 09:31:00";
        String res="";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);

        System.out.println(res);
        System.out.println(System.currentTimeMillis() - ts);*/
        /*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        System.out.println(format.format(date));*/


        //System.out.println("这是："+ Calendar.getInstance().get(Calendar.YEAR));

    }

    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }

        System.out.println(hexValue);
        return hexValue.toString();

    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr){

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }

    // 测试主函数
    @Test
    public void test() {


        String s = new String("gzadmin");
        System.out.println("原始：" + s);
        System.out.println("MD5后：" + string2MD5(s));
        System.out.println("加密的：" + convertMD5(s));
        System.out.println("解密的：" + convertMD5(convertMD5(s)));

    }

}
