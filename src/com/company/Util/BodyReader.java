package com.company.Util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class BodyReader {
    public static String readBody(InputStream is) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[4096];
        for (int n = is.read(buf); n > 0; n = is.read(buf)) {
            out.write(buf, 0, n);
        }
        return new String(out.toByteArray());
    }
}
