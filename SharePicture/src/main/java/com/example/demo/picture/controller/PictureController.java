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
	
	/**
	 * 展示相册中已有的图片或添加图片
	 * pictures为某个相册中的所有图片
	 * albums为某个用户的所有相册
	 * @param albumDTO
	 * @param model
	 * @return
	 */
	@RequestMapping("/addPicture")
    public String addPicture(AlbumDTO albumDTO,Model model){
		albumDTO.setId(1L);
		List<AlbumDTO> albums = albumService.showAlbum(1L);
		AlbumDTO album = pictureService.showAllPictureOfAlbum(albumDTO);
		model.addAttribute("pictures", album);
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
	
	@RequestMapping("/carouselPicture")
	public AlbumDTO pictureCarousel(PictureDTO picture)
	{
		

	}
}
