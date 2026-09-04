package com.baidu.hipicture.app.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class PictureListFeedVO {

    private String pictureUrl;
    private String pictureName;
    private Long pictureId;

}
