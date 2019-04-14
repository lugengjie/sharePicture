package com.example.demo.picture.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.account.entity.UserDTO;
import com.example.demo.account.service.AccountService;
import com.example.demo.account.service.IAccountService;
import com.example.demo.album.entity.AlbumDTO;
import com.example.demo.album.service.AlbumService;
import com.example.demo.album.service.IAlbumService;
import com.example.demo.personalCenter.service.IPersonalCenterService;
import com.example.demo.personalCenter.service.PersonalCenterService;
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
	@Autowired
	IPersonalCenterService personalCenterService;
	@Autowired
	IAccountService accountService;
	
	/**
	 * 展示相册中已有的图片或添加图片
	 * pictures为某个相册中的所有图片
	 * albums为某个用户的所有相册
	 * @param albumDTO
	 * @param model
	 * @return
	 */
	@RequestMapping("/showPictureOfAlbum")
    public String showPictureOfAlbum(HttpSession session,AlbumDTO albumDTO,Model model)
	{
		Long userId = 1L;
		Long albumId = albumDTO.getId();
		for(int i=0;i<10;i++) {System.out.println(albumId);}
		
		List<Long> albumIds = new ArrayList<Long>();
		if(albumId!=null && albumId!=0) 
		{
			albumIds.add(albumId);	
		}
		AlbumDTO albumDTOTemp = albumService.findAlbumDtoByUserIdAndAlbumId(userId, albumId);
		List<UserDTO> userDTOs = accountService.findUserDTOByAlbumId(userId, albumId);
		List<PictureDTO> pictureDTOs = personalCenterService.findPictureDTOsOfUserByAlbumIds(albumIds, userId);
		List<AlbumDTO> albums = albumService.showAlbum(1L);
		model.addAttribute("userDTOs",userDTOs);
		model.addAttribute("pictureDTOs", pictureDTOs);
		model.addAttribute("albumDTO", albumDTOTemp);
		model.addAttribute("albums", albums);
        return "showPictureOfAlbum";
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
		/*picture.getAlbumId();*/
		if(pictureService.uploadPicture(multipartFile, picture))
		{
			return "上传图片成功";
		}
		return "上传图片失败";
	}
	
	/**
	 * 轮播
	 * 不同页面的轮播要返回不同的：：*
	 * @param picture
	 * @param model
	 * @return
	 */
	@RequestMapping("/carouselPicture")
	public String pictureCarousel(PictureDTO picture ,Model model)
	{
		AlbumDTO album=pictureService.pictureCarousel(picture.getPictureId());
		System.out.println(album);
		model.addAttribute("littleAlbum", album);
		return "homePage::carouselMask";
	}
	
	/**
	 * 收藏图片
	 */
	@RequestMapping("/collectPicture")
	public @ResponseBody String collectPicture(HttpSession session,PictureDTO pictureDTO)
	{
		String email = "xueyuancpt@163.com";
		if(pictureService.collectPicture(session, pictureDTO, email)) {
			return "收藏图片成功";
		}
		return "收藏图片失败";
	}
	
	/**
	 * 快速收藏图片
	 */
	@RequestMapping("/quickCollectPicture")
	public @ResponseBody String quickCollectPicture(HttpSession session,PictureDTO pictureDTO)
	{
		if(pictureService.quickCollectPicture(session, pictureDTO))
		{
			return "收藏图片成功";
		}
		return "收藏图片失败";
	}
	
	/**
	 * 快速收藏图片
	 */
	@RequestMapping("/likePicture")
	public @ResponseBody String likePicture(HttpSession session,PictureDTO pictureDTO)
	{
		String email = "xueyuancpt@163.com";
		Long pictureId = pictureDTO.getPictureId();
		if(pictureId != null && pictureId != 0) 
		{
			if(pictureService.likePicture(pictureId, email))
			{
				return "喜欢图片成功";
			}
		}
		return "喜欢图片失败";
	}
	
	/**
	 * 取消喜欢图片
	 */
	@RequestMapping("/cancelLikePicture")
	public @ResponseBody String cancelLikePicture(HttpSession session,PictureDTO pictureDTO)
	{
		System.out.println(pictureDTO);
		String email = "xueyuancpt@163.com";
		Long pictureId = pictureDTO.getPictureId();
		if(pictureId != null && pictureId != 0) 
		{
			if(pictureService.cancelLikePicture(pictureId, email))
			{
				return "取消喜欢图片成功";
			}
		}
		return "取消喜欢图片失败";
	}
	
	/**
	 * 编辑图片
	 * @param session
	 * @param pictureDTO
	 * @return
	 */
	@RequestMapping("/editPicture")
	public @ResponseBody String editPicture(HttpSession session, PictureDTO pictureDTO)
	{
		
		Long userId =1L;
		if(pictureService.editPicture(userId, pictureDTO))
		{
			return "编辑图片成功";
		}
		return "编辑图片失败";
	}
	
	/**
	 * 删除图片
	 * @param session
	 * @param pictureDTO
	 * @return
	 */
	@RequestMapping("/deletePicture")
	public @ResponseBody String deletePicture(HttpSession session, PictureDTO pictureDTO)
	{
		Long userId =1L;
		if(pictureService.deletePicture(userId, pictureDTO))
		{
			return "删除图片成功";
		}
		return "删除图片失败";
	}

}
