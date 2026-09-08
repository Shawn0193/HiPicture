package com.baidu.hipicture.console.controller;

import com.baidu.hipicture.module.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PictureController {
    @Autowired
    private PictureService pictureService;


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
