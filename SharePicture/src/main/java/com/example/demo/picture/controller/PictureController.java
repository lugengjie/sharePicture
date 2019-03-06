package com.example.demo.picture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.picture.entity.PictureDTO;
import com.example.demo.picture.service.PictureService;

@Controller
public class PictureController
{
	
	@Autowired
	PictureService pictureService;
	@RequestMapping("/pic")
    public String picUpload(){
        return "NewFile";
    }
	
	/**
	 * 上传图片
	 * @param multipartFile
	 * @param picture
	 * @return
	 */
	@RequestMapping("/upp")
	public @ResponseBody String uploadPicture(MultipartFile multipartFile,PictureDTO picture)
	{
		if(pictureService.uploadPicture(multipartFile, picture))
		{
			return "上传图片成功";
		}
		return "上传图片失败";
	}
}
