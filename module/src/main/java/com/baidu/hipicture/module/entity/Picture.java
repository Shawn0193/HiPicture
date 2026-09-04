package com.baidu.hipicture.module.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

@Data
@Accessors(chain=true)
public class Picture {

    private BigInteger id;
    private String coverPictures;
    private String title;
    private String introduce;
    private String userName;
    private String category;
    private Integer width;
    private Integer height;
    private Integer size;
    private Integer updateTime;
    private Integer createTime;
    private Integer isDeleted;


}
