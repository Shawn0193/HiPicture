package com.baidu.hipicture.app.controller;

import com.baidu.hipicture.app.domain.PictureInfoVO;
import com.baidu.hipicture.module.entity.Picture;
import com.baidu.hipicture.module.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @RequestMapping("/picture/info")
    public PictureInfoVO pictureInfo(@RequestParam(name = "pictureId") BigInteger pictureId) {
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
        PictureInfoVO pictureInfoVO = new PictureInfoVO();
        pictureInfoVO.setCoverPictures(coverPicturesList);
        pictureInfoVO.setTitle(title);
        pictureInfoVO.setIntroduce(introduce);
        pictureInfoVO.setUserName(userName);
        pictureInfoVO.setCategory(category);
        pictureInfoVO.setWidth(width);
        pictureInfoVO.setHeight(height);
        pictureInfoVO.setSize(size);

        return pictureInfoVO;
    }

    @RequestMapping("/picture/list")
    public List<Picture> getAllPictureList() {
        return pictureService.getAllPictureList();
    }

    @RequestMapping("/picture/create")
    public String pictureCreate(@RequestParam(name = "title") String title,
                                @RequestParam(name = "introduce") String introduce) {
        int result = pictureService.createPicture(title, introduce);
        return result == 1 ? "成功" : "失败";
    }

    @RequestMapping("/picture/update")
    public String pictureUpdate(@RequestParam(name = "studentId") BigInteger studentId,
                                @RequestParam(name = "title") String title,
                                @RequestParam(name = "introduce") String introduce) {
        int result = pictureService.updatePicture(studentId, title, introduce);
        return result == 1 ? "成功" : "失败";
    }

    @RequestMapping("/picture/delete")
    public String pictureDelete(@RequestParam(name = "pictureId") BigInteger pictureId) {
        int result = pictureService.deletePicture(pictureId);
        return result == 1 ? "成功" : "失败";
    }

}
