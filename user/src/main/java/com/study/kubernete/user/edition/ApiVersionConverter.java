package com.study.kubernete.user.edition;

import org.springframework.util.StringUtils;

public class ApiVersionConverter {
    public static ApiVersionItem convert(String api) {
        ApiVersionItem apiVersionItem = new ApiVersionItem();
        if (!StringUtils.hasText(api)) {
            return apiVersionItem;
        }

        String[] cells = api.split("\\.");
        Integer high=Integer.parseInt(cells[0]);
//        if(high==null||high.intValue()<=0)
//        {
//            high=1;
//        }
        apiVersionItem.setHigh(high);
        if (cells.length > 1) {
            apiVersionItem.setMid(Integer.parseInt(cells[1]));
        }

        if (cells.length > 2) {
            apiVersionItem.setLow(Integer.parseInt(cells[2]));
        }
        return apiVersionItem;
    }
}
