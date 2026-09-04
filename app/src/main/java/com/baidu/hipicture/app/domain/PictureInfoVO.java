package com.baidu.hipicture.app.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;
import java.util.List;

@Data
@Accessors(chain=true)
public class PictureInfoVO {

    private List<String> coverPictures;
    private String title;
    private String introduce;
    private String userName;
    private String category;
    private Integer width;
    private Integer height;
    private Integer size;

}
