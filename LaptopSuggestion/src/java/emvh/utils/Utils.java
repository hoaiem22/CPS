/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.utils;

import emvh.constant.Constants;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
public class Utils {

    private static final Logger LOGGER = Logger.getLogger(Utils.class.getName());
//    Return image folder in web project
//    Note that: project structure must be stand right position

    public static String getImageFolder() throws IOException {
        String path = (new File("..").getCanonicalFile().toString());
        path = path.substring(0, path.lastIndexOf("\\")) + "\\WebApp\\LaptopSuggestion\\web\\";

        return path.replace("\\", "/");
    }

    //Return string with no character at 160 in ascii by space and trim them
    public static String trim160(String str) {

        return str.replace(" ", " ").trim();
    }

    public static String getMethod(String method, String caller) {

        return caller + method.toUpperCase().charAt(0) + method.substring(1, method.length());
    }

    public static String filterPrice(String price) {
        if (XMLUtilities.containsIgnoreCase(price, "Liên hệ")) {
            return "0";
        }
        try {
            price = price.replace(",", "");
            Integer.parseInt(price);
        } catch (Exception e) {
            LOGGER.info(price + "is not a number !");
            return "-1";
        }
        return price;
    }

    public static String fixCpuSign(String str, Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (XMLUtilities.containsIgnoreCase(str, entry.getKey())) {
                str = str.replace(entry.getKey(), entry.getValue());
            }
        }

        return str.trim();
    }

    public static Calendar getCalendar(int hour, int min, int second, int millisecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);

        return calendar;
    }

    public static int computeMatchingDensity(String a, String b) {
        int n = a.length();
        int m = b.length();
        int dp[][] = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;

                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);

                }
            }
        }
        return dp[n][m];
    }
}
