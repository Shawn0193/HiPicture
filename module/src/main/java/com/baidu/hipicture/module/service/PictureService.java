package com.baidu.hipicture.module.service;

import com.baidu.hipicture.module.domain.PictureInfoVO;
import com.baidu.hipicture.module.domain.PictureListFeedVO;
import com.baidu.hipicture.module.domain.PictureListVO;
import com.baidu.hipicture.module.entity.Picture;
import com.baidu.hipicture.module.mapper.PictureMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PictureService {
    @Resource
    private PictureMapper pictureMapper;

    public Picture getPictureInfoById(Long id) {
        return pictureMapper.getById(id);
    }

    public List<Picture> getAllPictureList(Integer page) {
        Long total = pictureMapper.getTotal();

        return  pictureMapper.getList();
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

    public PictureListVO getPicturePage(Integer page, Integer pageSize) {
        long total = pictureMapper.getTotal();
        Integer offset = (page - 1) * pageSize;
        List<Picture> pictureList = pictureMapper.getPage(offset, pageSize);
        List<PictureListFeedVO> pictureListFeedVOS = new ArrayList<>();
        for (Picture picture : pictureList) {
            PictureListFeedVO pictureListFeedVO = new PictureListFeedVO();
            pictureListFeedVO.setPictureId(picture.getId());
            pictureListFeedVO.setPictureUrl(picture.getCoverPictures().split("\\$")[0]);
            pictureListFeedVO.setPictureName(picture.getTitle());
            pictureListFeedVOS.add(pictureListFeedVO);
        }
        PictureListVO  pictureListVO = new PictureListVO();
        pictureListVO.setTotal(total);
        pictureListVO.setList(pictureListFeedVOS);
        pictureListVO.setPageSize(pageSize);

        return pictureListVO;
    }

    public PictureInfoVO getPictureInfo(Long id) {
        Picture picture = pictureMapper.getById(id);
        String coverPictures = picture.getCoverPictures();
        List<String> coverPicturesList = new ArrayList<>(Arrays.asList(coverPictures.split("\\$")));
        String title = picture.getTitle();
        String introduce = picture.getIntroduce();
        String userName = picture.getUserName();
        String category = picture.getCategory();
        Integer width = picture.getWidth();
        Integer height = picture.getHeight();
        Integer size = picture.getSize();
        Integer createTime = picture.getCreateTime();
        Integer updateTime = picture.getUpdateTime();
        PictureInfoVO pictureInfoVO = new PictureInfoVO();
        pictureInfoVO.setCoverPictures(coverPicturesList);
        pictureInfoVO.setTitle(title);
        pictureInfoVO.setIntroduce(introduce);
        pictureInfoVO.setUserName(userName);
        pictureInfoVO.setCategory(category);
        pictureInfoVO.setWidth(width);
        pictureInfoVO.setHeight(height);
        pictureInfoVO.setSize(size);
        String createTimeString = LocalDateTime.ofInstant(
                Instant.ofEpochSecond(createTime),
                ZoneId.of("Asia/Shanghai")
        ).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String updateTimeString = LocalDateTime.ofInstant(
                Instant.ofEpochSecond(createTime),
                ZoneId.of("Asia/Shanghai")
        ).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        pictureInfoVO.setCreateTime(createTimeString);
        pictureInfoVO.setUpdateTime(updateTimeString);

        return pictureInfoVO;
    }


}
