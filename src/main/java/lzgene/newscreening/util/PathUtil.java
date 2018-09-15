package lzgene.newscreening.util;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

public class PathUtil {
    public String getWebInfPath() {
        URL url = getClass().getProtectionDomain().getCodeSource()
                .getLocation();
        String path = url.toString();

        int index = path.indexOf("WEB-INF");

        if (index == -1) {
            index = path.indexOf("classes");
        }
        if (index == -1) {
            index = path.indexOf("bin");
        }

        path = path.substring(0, index);

        if (path.startsWith("zip")) {
            path = path.substring(4);
        } else if (path.startsWith("file")) {
            path = path.substring(6);
        } else if (path.startsWith("jar")) {
            path = path.substring(10);
        }
        try {
            path = URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (path.indexOf(":") < 0) {
            path = System.getProperty("file.separator") + path;
        }
        return path;
    }

    public static String getWebPath() {
        return new PathUtil().getWebInfPath();
    }

}

