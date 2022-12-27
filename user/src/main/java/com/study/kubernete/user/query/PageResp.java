package com.study.kubernete.user.query;


import lombok.Data;

import java.util.List;

@Data
/*("分页对象【Page】")*/
public class PageResp<T> {

   /* @ApiModelProperty(value = "当前分页页码")*/
    private Long index;

   /* @ApiModelProperty(value = "每页展示条数")*/
    private Long size;

    /*@ApiModelProperty(value = "总条数")*/
    private Long total;

    /*@ApiModelProperty(value = "总页数")*/
    private Long pages;
   /* @ApiModelProperty(value = "响应数据")*/
   private List<T> data;
}
