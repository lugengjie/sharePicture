package com.example.demo.picture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.album.entity.AlbumDTO;
import com.example.demo.album.service.AlbumService;
import com.example.demo.album.service.IAlbumService;
import com.example.demo.picture.entity.PictureDTO;
import com.example.demo.picture.service.IPictureService;
import com.example.demo.picture.service.PictureService;

@RequestMapping("/picture")
@Controller
public class PictureController
{
	
	@Autowired
	IPictureService pictureService;
	@Autowired
	IAlbumService albumService;
	
	@RequestMapping("/addPicture")
    public String addPicture(Model model){
		List<AlbumDTO> albums=albumService.showAlbum(1L);
		model.addAttribute("albums", albums);
        return "addPicture";
    }
	
	/**
	 * 上传图片
	 * @param multipartFile
	 * @param picture
	 * @return
	 */
	@RequestMapping("/uploadPicture")
	public @ResponseBody String uploadPicture(MultipartFile multipartFile,PictureDTO picture)
	{
		/**
		 * 临时设AlbumId为1L，用session以后恢复
		 */
		picture.setAlbumId(1L);
		if(pictureService.uploadPicture(multipartFile, picture))
		{
			return "上传图片成功";
		}
		return "上传图片失败";
	}
}
