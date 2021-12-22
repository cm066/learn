//import org.springframework.web.server.ServerWebExchange;

import org.springframework.lang.Nullable;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Test {
    /**
     * domain长度判断
     */
    private static int LEN = 3;
    public static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss SSS z", Locale.ENGLISH);
    public static final long EXPIRE_TIME = 300000;
    private static char DELIMITER = ':';
    public static void main(String[] args) {
//        String s = "/captcha-code/";
//        String domainName = getDomainName(s);
//        System.out.println(domainName);
        String s = "/data/portal/system/login/v1/*";
        boolean pattern = isPattern(s);
        System.out.println(pattern);
        System.out.println(System.currentTimeMillis());
        String timestamp ="Thu, 28 Oct 2021 16:16:32 200 GMT";

        ZonedDateTime dateTime = ZonedDateTime.parse(timestamp, DATE_TIME_FORMATTER);
        long parseTime = dateTime.toInstant().toEpochMilli();
        long currentTimeMillis = Instant.now().toEpochMilli();
        System.out.println(parseTime);
        System.out.println(currentTimeMillis + 30 * 60 * 1000);
        //允许用户日期比服务日期晚30分钟
        System.out.println(dateTime);
        System.out.println(Instant.now());
//        _method=POST&_uri=/data/portal/system/login/v1&_timestamp=Thu, 28 Oct 2021 08:26:32 200 GMT&_body={"account":"jingjiu","password":"YKbGDPI7SAcifRk3aSScBvNrPB0KbXGeewY6nb6ZuYWYLkVd045tHLhrJno6u85jNR/i2jVMIDZfsej2VkjJeSinN+bc1Qb2rWXZgL/FHu7i9a95I+qPq+1jbCut6mo95Nc1A5z7AnYrDUoWXbWijkdP6Zav1KDKcJZeaM/nMyA="}
//        String digest = DigestUtils.md5DigestAsHex(singContent.getBytes(StandardCharsets.UTF_8));
//        String signature = sign(digest, secretKey);
    }
    /**
     * 得到cookie的域名
     */
    public static final String getDomainName(String s) {
        String domainName = null;
        String serverName = s;
        if (serverName == null || serverName.equals("")) {
            domainName = "";
        } else {
            serverName = serverName.toLowerCase();
            serverName = serverName.substring(7);
            final int end = serverName.indexOf("/");
            serverName = serverName.substring(0, end);
            final String[] domains = serverName.split("\\.");
            int len = domains.length;

            if (len > LEN) {
                domainName = domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
            } else if (len <= LEN && len > 1) {
                domainName = domains[len - 2] + "." + domains[len - 1];
            } else {
                domainName = serverName;
            }
        }

        if (domainName != null && domainName.indexOf(DELIMITER) > 0) {
            String[] ary = domainName.split("\\:");
            domainName = ary[0];
        }
        return domainName;
    }

    public static boolean isPattern(@Nullable String path) {
        if (path == null) {
            return false;
        } else {
            boolean uriVar = false;

            for(int i = 0; i < path.length(); ++i) {
                char c = path.charAt(i);
                if (c == '*' || c == '?') {
                    return true;
                }

                if (c == '{') {
                    uriVar = true;
                } else if (c == '}' && uriVar) {
                    return true;
                }
            }

            return false;
        }
    }

//    private static String digestAsHexString(String algorithm, byte[] bytes) {
//        char[] hexDigest = digestAsHexChars(algorithm, bytes);
//        return new String(hexDigest);
//    }

}
