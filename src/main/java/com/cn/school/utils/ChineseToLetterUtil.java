package com.cn.school.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import org.junit.Test;
import org.springframework.stereotype.Component;

@Component
public final class ChineseToLetterUtil {
    /**
     * 得到汉字的首字母
     *
     * @param source
     * @return
     */
    public static String getPinYinHeaderChar(String source) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            char word = source.charAt(i);
            if (Character.toString(word).matches("[\\u4E00-\\u9FA5]")) {
                String[] pinYinArr = PinyinHelper.toHanyuPinyinStringArray(word);
                result.append(pinYinArr[0].charAt(0));
            } else {
                // 非汉字不进行转换，直接添加
                result.append(word);
            }
        }
        return result.toString().toUpperCase();
    }

//    @Test
//    public void testString (){
//        String s = getPinYinHeaderChar("马志军");
//        System.out.println(s);
//    }
}
