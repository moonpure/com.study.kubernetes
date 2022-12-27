package com.study.kubernete.user.edition;

import lombok.Data;

@Data
public class ApiVersionItem implements Comparable<ApiVersionItem> {

    private int high = 1;

    private int mid = 0;

    private int low = 0;

    public ApiVersionItem() {
    }

    @Override
    public int compareTo(ApiVersionItem right) {
        if(this.getHigh()==0)
        {
            this.setHigh(1);
            this.setMid(0);
            this.setLow(0);
        }
        if (this.getHigh() > right.getHigh()) {
            return 1;
        } else if (this.getHigh() < right.getHigh()) {
            return -1;
        }

        if (this.getMid() > right.getMid()) {
            return 1;
        } else if (this.getMid() < right.getMid()) {
            return -1;
        }

        if (this.getLow() > right.getLow()) {
            return 1;
        } else if (this.getLow() < right.getLow()) {
            return -1;
        }
        return 0;
    }
}
