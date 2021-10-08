package com.youyouxi.github.creater.Utils;

public class Convert {

    /**
     * 字段转换 驼峰格式 大驼峰
     *
     * @param s 字符串
     * @return 字符串
     */
    public static String convertUpperCamelCase(String s) {
        String split = "_";
        String[] names = s.split(split);
        StringBuilder stringBuilder = new StringBuilder("");
        for (String str : names) {
            String a = str.substring(0, 1);
            String A = a.toUpperCase();
            str = A + str.substring(1);
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    /**
     * 字段转换 驼峰格式 小驼峰
     *
     * @param s 字符串
     * @return 字符串
     */
    public static String convertLowerCamelCase(String s) {
        String str = convertUpperCamelCase(s);
        String a = str.substring(0, 1);
        String A = a.toLowerCase();
        return A + str.substring(1);
    }

}
