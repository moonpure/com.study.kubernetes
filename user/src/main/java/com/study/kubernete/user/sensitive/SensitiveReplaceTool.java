package com.study.kubernete.user.sensitive;


import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SensitiveReplaceTool {
    public static String replace(SensitiveTypeEnum sensitiveType, String value) {
        return replace(sensitiveType, null, 0, '*', value);
    }

    public static String replace(SensitiveTypeEnum sensitiveType, String regex, int index, char replacedCharacter, String value) {
        if (!StringUtils.hasText(value) || sensitiveType == null) {
            return value;
        }
        if (sensitiveType == SensitiveTypeEnum.NOSENSTIVE) {
            return value;
        }
        if (sensitiveType != SensitiveTypeEnum.CUSTOM) {
            if (sensitiveType == SensitiveTypeEnum.EMAIL) {
                int idx = value.indexOf("@");
                String startStr = value.substring(0, idx);
                return defaultReplace(startStr,sensitiveType.getStartLen(),sensitiveType.getEndLen(), replacedCharacter).concat(value.substring(idx));
            }
            return defaultReplace(value,sensitiveType.getStartLen(),sensitiveType.getEndLen(), replacedCharacter);

        }
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(value);
        if (!matcher.find()) {
            return null;
        }
        String group = matcher.group(index);
        StringBuilder replaceStr = new StringBuilder();
        for (int x = 0; x < group.length(); ++x) {
            replaceStr.append(replacedCharacter);
        }
        return value.replace(group, replaceStr.toString());
    }


    public static String defaultReplace(String sourceValue, Integer startLen, Integer endLen, char replacedChar) {
        if (!StringUtils.hasText(sourceValue)) {
            return sourceValue;
        }
        final Integer strLength = sourceValue.length();
        if ( strLength<startLen ) {
         return    defaultReplace(sourceValue, replacedChar);
            //return sourceValue;
        }
      Integer endpoint=startLen; //从start...endpoint为要脱敏的点，异常后面全脱
        if (strLength<=startLen + endLen ) {
            endpoint=strLength;
        }
        else
        {
            endpoint=strLength-endLen;
        }
    //取出要缺钱的字符串
        String replStr = sourceValue.substring(startLen, endpoint);
        if(!StringUtils.hasText(replStr))
        {
            return sourceValue;
        }
        return sourceValue.substring(0, startLen).concat(defaultReplace(replStr,replacedChar)).concat(sourceValue.substring(endpoint));
    }

    public  static String defaultReplace(String sourceValue, char replacedChar)
    {
        if(!StringUtils.hasText(sourceValue))
        {
            return sourceValue;
        }
        final char[] chars = new char[sourceValue.length()];
        Arrays.fill(chars, replacedChar);
       return new String(chars);
    }
}
