package com.baidu.hipicture.module.domain;

import com.baidu.hipicture.module.domain.PictureListFeedVO;
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
