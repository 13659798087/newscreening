package lzgene.newscreening.util;
import java.util.UUID;
/**
 * Create GUID
 *
 */
public class Guid {
	 /**
	  * @生成uuid

	  */
	public static final String GenerateGUID(){
		  UUID uuid = UUID.randomUUID();
		  return uuid.toString();
	}
		
}
