/*
package lzgene.newscreening;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhoneTest {

    //@Test
	public void encryption() {
		String tel = "13659798087";
		String strs = "";
		String[] ts = tel.split("");
		System.out.println(ts);
		for(int i=0;i<ts.length;i++){
			String caseNum = "";
			switch (  Integer.valueOf(ts[i])  ){
				case 0:
					caseNum="zk";
					break;
				case 1:
					caseNum="cn";
					break;
				case 2:
					caseNum="tg";
					break;
				case 3:
					caseNum="wu";
					break;
				case 4:
					caseNum="fv";
					break;
				case 5:
					caseNum="qp";
					break;
				case 6:
					caseNum="ui";
					break;
				case 7:
					caseNum="ko";
					break;
				case 8:
					caseNum="dl";
					break;
				case 9:
					caseNum="xr";
					break;
			}
			strs+=caseNum;
		}
		System.out.println(strs);
		String s1 = strs.substring(strs.length()-2 ,strs.length());//最后两位
		String s2 = strs.substring(strs.length()-4 ,strs.length()-2);
		String s3 = strs.substring(0,strs.length()-4);
		String s4 = s3+s1+s2;
		System.out.println(s4);
	}


	@Test
	public void Decrypt() {
		String tel="";
		//String s = "cnwuuiqpxrkoxrdlzkdlko";

		String s = "cnwuuiqpxrkoxrdlzkkodl";
		String sa = s.substring(0,s.length()-4);
		String sb = s.substring(s.length()-2 ,s.length());//最后两位
		String sc = s.substring(s.length()-4 ,s.length()-2);

		String sd =sa+sb+sc;

		StringBuffer s1 = new StringBuffer(sd);
		int i;
		for (i = 2; i < s1.length(); i += 3) {
			s1.insert(i, ',');
		}
		String[] array = s1.toString().split(",");
		String caseNum = "";
		for (String string : array) {
			switch ( string ){
				case "zk":
					caseNum="0";
					break;
				case "cn":
					caseNum="1";
					break;
				case "tg":
					caseNum="2";
					break;
				case "wu":
					caseNum="3";
					break;
				case "fv":
					caseNum="4";
					break;
				case "qp":
					caseNum="5";
					break;
				case "ui":
					caseNum="6";
					break;
				case "ko":
					caseNum="7";
					break;
				case "dl":
					caseNum="8";
					break;
				case "xr":
					caseNum="9";
					break;
			}
			tel+=caseNum;
		}
		System.out.println("-------------"+tel);

	}

}
*/
