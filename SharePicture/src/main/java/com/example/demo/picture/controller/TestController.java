package com.example.demo.picture.controller;


import java.io.File;
import java.util.UUID;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class TestController
{
	@RequestMapping("/pic")
    public String picUpload(){
        return "NewFile";
    }
	
	    @PostMapping("/upload")
	    public void upload(MultipartFile fileUpload){
	        //获取文件名
	        String fileName = fileUpload.getOriginalFilename(); 
	        //指定本地文件夹存储图片
	        String filePath = "E:\\pic\\";
	        try {
	            //将图片保存到static文件夹里
	            fileUpload.transferTo(new File(filePath+fileName));
	        } catch (Exception e) {
	            System.out.println("无法上传图片");
	          
	        } 
	    }
	

}
