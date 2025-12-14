package in.arc.urlShortner.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class Crc32HasherUtilTest {

    @Test
    void getHashGivenData() {
        String data="String to be hashed";
        String hash= Crc32HasherUtil.crc32Hash(data);
        String expectedHash="e7b5ac8a";
        assertEquals(expectedHash, hash);
    }
}