package com.baidu.hipicture.module.service;

import com.baidu.hipicture.module.entity.Picture;
import com.baidu.hipicture.module.mapper.PictureMapper;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service
public class PictureService {
    @Resource
    private PictureMapper pictureMapper;

    public Picture getPictureInfoById(BigInteger id) {
        return pictureMapper.getById(id);
    }

    public List<Picture> getAllPictureList() {
        return pictureMapper.getList();
    }

    public int createPicture(String title, String introduce) {
        int timestamp = (int) (System.currentTimeMillis() / 1000);
        Picture picture = new Picture();
        picture.setTitle(title);
        picture.setIntroduce(introduce);
        picture.setCreateTime(timestamp);
        picture.setUpdateTime(timestamp);
        picture.setIsDeleted(0);
        return pictureMapper.insert(picture);
    }

    public int updatePicture(BigInteger id, String title, String introduce) {
        int timestamp = (int) (System.currentTimeMillis() / 1000);
        Picture picture = new Picture();
        picture.setId(id);
        picture.setTitle(title);
        picture.setIntroduce(introduce);
        picture.setUpdateTime(timestamp);
        return pictureMapper.update(picture);
    }

    public int deletePicture(BigInteger id) {
        return pictureMapper.delete(id, (int) System.currentTimeMillis() / 1000);
    }


}
