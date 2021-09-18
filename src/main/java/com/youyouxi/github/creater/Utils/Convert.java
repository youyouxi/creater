package com.youyouxi.github.creater.Utils;

public class Convert {

    /**
     * 字段转换 a_b -> AB 驼峰格式
     *
     * @param s 字符串
     * @return 字符串
     */
    public static String convert_AB(String s) {
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
     * 字段转换 a_b -> aB 驼峰格式
     *
     * @param s 字符串
     * @return 字符串
     */
    public static String convert_ab(String s) {
        String str = convert_AB(s);
        String a = str.substring(0, 1);
        String A = a.toLowerCase();
        return A + str.substring(1);
    }

}
