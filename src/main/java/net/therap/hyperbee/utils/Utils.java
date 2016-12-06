package net.therap.hyperbee.utils;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;

/**
 * @author zoha
 * @author rayed
 * @since 11/22/16
 */
@Component
public class Utils {

    public static long getCurrentTimeMills() {
        return System.currentTimeMillis();
    }

    public static String redirectTo(String url) {
        return "redirect:" + url;
    }

    public static String hashMd5(String pass) {
        try {
            byte[] passBytes = pass.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            byte[] digested = md.digest(passBytes);

            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : digested) {
                stringBuilder.append(Integer.toHexString(0xff & b));
            }

            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {

            return null;
        }
    }

    public static String formatActivityLogMessage(String message, Object placeholder) {
        return MessageFormat.format(message, placeholder);
    }

    public static String convertQueryStringForCount(String query) {
        return query.replaceAll("\\*", "COUNT(*)");
    }
}
