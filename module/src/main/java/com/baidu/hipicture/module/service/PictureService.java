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

    public Picture getPictureInfoById(Long id) {
        return pictureMapper.getById(id);
    }

    public List<Picture> getAllPictureList() {
        return pictureMapper.getList();
    }

    public Long createPicture(String coverPictures, String title, String introduce, String userName,
                             String category, Integer width, Integer height, Integer size) {
        int timestamp = (int) (System.currentTimeMillis() / 1000);
        Picture picture = new Picture();
        picture.setCoverPictures(coverPictures);
        picture.setTitle(title);
        picture.setIntroduce(introduce);
        picture.setUserName(userName);
        picture.setCategory(category);
        picture.setWidth(width);
        picture.setHeight(height);
        picture.setSize(size);
        picture.setCreateTime(timestamp);
        picture.setUpdateTime(timestamp);
        picture.setIsDeleted(0);
        pictureMapper.insert(picture);
        return picture.getId();
    }

    public int updatePicture(Long id, String coverPictures, String title, String introduce, String userName,
                             String category, Integer width, Integer height, Integer size) {
        int timestamp = (int) (System.currentTimeMillis() / 1000);
        Picture picture = new Picture();
        picture.setId(id);
        picture.setCoverPictures(coverPictures);
        picture.setTitle(title);
        picture.setIntroduce(introduce);
        picture.setUserName(userName);
        picture.setCategory(category);
        picture.setWidth(width);
        picture.setHeight(height);
        picture.setSize(size);
        picture.setUpdateTime(timestamp);
        return pictureMapper.update(picture);
    }

    public int deletePicture(Long id) {
        return pictureMapper.delete(id, (int) System.currentTimeMillis() / 1000);
    }

}
