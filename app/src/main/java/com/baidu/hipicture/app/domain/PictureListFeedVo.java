package com.baidu.hipicture.app.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

@Data
@Accessors(chain=true)
public class PictureListFeedVo {

    private String pictureUrl;
    private String pictureName;
    private BigInteger pictureId;

}
