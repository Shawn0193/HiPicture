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

    @RequestMapping("/picture/info")
    public Picture studentInfo(@RequestParam(name = "pictureId") BigInteger pictureId) {
        return pictureService.getPictureInfoById(pictureId);
    }

    @RequestMapping("/picture/all")
    public List<Picture> getAllStudentInfo() {
        return pictureService.getAllPictureInfo();
    }

    @RequestMapping("/picture/create")
    public String studentCreate(@RequestParam(name = "title") String title,
                                @RequestParam(name = "introduce") String introduce) {
        int result = pictureService.createPicture(title, introduce);
        return result == 1 ? "成功" : "失败";
    }

    @RequestMapping("/picture/update")
    public String studentUpdate(@RequestParam(name = "studentId") BigInteger studentId,
                                @RequestParam(name = "title") String title,
                                @RequestParam(name = "introduce") String introduce) {
        int result = pictureService.updatePicture(studentId, title, introduce);
        return result == 1 ? "成功" : "失败";
    }

    @RequestMapping("/picture/delete")
    public String studentDelete(@RequestParam(name = "pictureId") BigInteger pictureId) {
        int result = pictureService.deletePicture(pictureId);
        return result == 1 ? "成功" : "失败";
    }

}
