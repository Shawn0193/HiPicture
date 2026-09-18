package com.baidu.hipicture.console.controller;

import com.baidu.hipicture.console.domain.PictureInfoVO;
import com.baidu.hipicture.console.domain.PictureListFeedVO;
import com.baidu.hipicture.console.domain.PictureListVO;
import com.baidu.hipicture.module.entity.Picture;
import com.baidu.hipicture.module.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @RequestMapping("/picture/list")
    public PictureListVO getAllPictureList(@RequestParam(name = "page") Integer page) {
       Integer pageSize = 10;
       List<Picture> pictureList = pictureService.getPicturePage(page, pageSize);
       Long total = pictureService.getTotal();
       PictureListVO pictureListVO = new PictureListVO();
       pictureListVO.setTotal(total);
       pictureListVO.setPageSize(pageSize);
       List<PictureListFeedVO> pictureListFeedVOList = new ArrayList<>();
       for (Picture picture : pictureList) {
           PictureListFeedVO pictureListFeedVO = new PictureListFeedVO();
           pictureListFeedVO.setPictureId(picture.getId());
           pictureListFeedVO.setPictureUrl(picture.getCoverPictures());
           pictureListFeedVO.setPictureName(picture.getTitle().split("\\$")[0]);
           pictureListFeedVOList.add(pictureListFeedVO);
       }
       pictureListVO.setList(pictureListFeedVOList);

       return pictureListVO;
    }

    @RequestMapping("/picture/info")
    public PictureInfoVO getPictureInfo(@RequestParam(name = "pictureId") Long pictureId) {
        Picture picture = pictureService.getPictureInfoById(pictureId);
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
        String createTimeString = LocalDateTime.ofInstant(
                Instant.ofEpochSecond(createTime),
                ZoneId.of("Asia/Shanghai")
        ).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String updateTimeString = LocalDateTime.ofInstant(
                Instant.ofEpochSecond(updateTime),
                ZoneId.of("Asia/Shanghai")
        ).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        PictureInfoVO pictureInfoVO = new PictureInfoVO();
        pictureInfoVO.setCoverPictures(coverPicturesList);
        pictureInfoVO.setTitle(title);
        pictureInfoVO.setIntroduce(introduce);
        pictureInfoVO.setUserName(userName);
        pictureInfoVO.setCategory(category);
        pictureInfoVO.setWidth(width);
        pictureInfoVO.setHeight(height);
        pictureInfoVO.setSize(size);
        pictureInfoVO.setCreateTime(createTimeString);
        pictureInfoVO.setUpdateTime(updateTimeString);

        return pictureInfoVO;
    }

    @RequestMapping("/picture/create")
    public String studentCreate(@RequestParam(name = "coverPictures", required = false) String coverPictures,
                                @RequestParam(name = "title", required = false) String title,
                                @RequestParam(name = "introduce", required = false) String introduce,
                                @RequestParam(name = "userName", required = false) String userName,
                                @RequestParam(name = "category", required = false) String category,
                                @RequestParam(name = "width", required = false) Integer width,
                                @RequestParam(name = "height", required = false) Integer height,
                                @RequestParam(name = "size", required = false) Integer size
    ) {
        Long result = pictureService.createPicture(coverPictures, title, introduce, userName, category, width, height, size);
        return result > 0 ? Long.toString(result) : "失败";
    }

    @RequestMapping("/picture/update")
    public String studentUpdate(@RequestParam(name = "pictureId") Long pictureId,
                                @RequestParam(name = "coverPictures", required = false) String coverPictures,
                                @RequestParam(name = "title", required = false) String title,
                                @RequestParam(name = "introduce", required = false) String introduce,
                                @RequestParam(name = "userName", required = false) String userName,
                                @RequestParam(name = "category", required = false) String category,
                                @RequestParam(name = "width", required = false) Integer width,
                                @RequestParam(name = "height", required = false) Integer height,
                                @RequestParam(name = "size", required = false) Integer size) {
        int result = pictureService.updatePicture(pictureId, coverPictures, title, introduce, userName, category, width, height, size);
        return result == 1 ? "成功" : "失败";
    }

    @RequestMapping("/picture/delete")
    public String studentDelete(@RequestParam(name = "pictureId") Long pictureId) {
        int result = pictureService.deletePicture(pictureId);
        return result == 1 ? "成功" : "失败";
    }

}
