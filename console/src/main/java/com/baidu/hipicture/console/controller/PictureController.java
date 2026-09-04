package com.baidu.hipicture.console.controller;

import com.baidu.hipicture.module.entity.Picture;
import com.baidu.hipicture.module.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
public class PictureController {
    @Autowired
    private PictureService pictureService;


    @RequestMapping("/picture/create")
    public String studentCreate(@RequestParam(name = "coverPictures") String coverPictures,
                                @RequestParam(name = "title") String title,
                                @RequestParam(name = "introduce") String introduce,
                                @RequestParam(name = "userName") String userName,
                                @RequestParam(name = "category") String category,
                                @RequestParam(name = "width") Integer width,
                                @RequestParam(name = "height") Integer height,
                                @RequestParam(name = "size") Integer size
    ) {
        int result = pictureService.createPicture(coverPictures, title, introduce, userName, category, width, height, size);
        return result == 1 ? "成功" : "失败";
    }

    @RequestMapping("/picture/update")
    public String studentUpdate(@RequestParam(name = "pictureId") BigInteger pictureId,
                                @RequestParam(name = "coverPictures") String coverPictures,
                                @RequestParam(name = "title") String title,
                                @RequestParam(name = "introduce") String introduce,
                                @RequestParam(name = "userName") String userName,
                                @RequestParam(name = "category") String category,
                                @RequestParam(name = "width") Integer width,
                                @RequestParam(name = "height") Integer height,
                                @RequestParam(name = "size") Integer size) {
        int result = pictureService.updatePicture(pictureId, coverPictures, title, introduce, userName, category, width, height, size);
        return result == 1 ? "成功" : "失败";
    }

    @RequestMapping("/picture/delete")
    public String studentDelete(@RequestParam(name = "pictureId") BigInteger pictureId) {
        int result = pictureService.deletePicture(pictureId);
        return result == 1 ? "成功" : "失败";
    }

}
