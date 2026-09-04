package com.baidu.hipicture.app.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain=true)
public class PictureListVO {

    private List<PictureListFeedVo> pictureListFeedVos;
}
