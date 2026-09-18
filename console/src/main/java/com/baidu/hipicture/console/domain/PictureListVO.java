package com.baidu.hipicture.console.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain=true)
public class PictureListVO {

    private List<PictureListFeedVO> list;
    private Long total;
    private Integer pageSize;
}
