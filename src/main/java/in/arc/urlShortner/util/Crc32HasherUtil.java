package in.arc.urlShortner.util;

import java.util.zip.CRC32;

public class Crc32HasherUtil {
    public static String crc32Hash(String data){
        CRC32 crc32=new CRC32();
        crc32.update(data.getBytes());
        return Long.toHexString(crc32.getValue());
    }
}
