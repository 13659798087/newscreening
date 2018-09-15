package lzgene.newscreening.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VersionUtil {

    @Value("${system.version}")
    private String version;

    public String getVersion() {
        return version;
    }


}
