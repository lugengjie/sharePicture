package com.example.demo.recommend.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.album.entity.AlbumDTO;
import com.example.demo.album.service.AlbumService;
import com.example.demo.picture.entity.PictureDTO;
import com.example.demo.recommend.service.RecommendService;

@RequestMapping("/recommend")
@Controller
public class RecommendController
{	
	@Autowired
	RecommendService recommendService;
	@Autowired
	AlbumService albumService;
	
	@RequestMapping("/toRecommendPage")
	public String toRecommendPage(HttpSession session,Model model)
	{
		Long myUserId = (Long)session.getAttribute("userId");
		List<PictureDTO> pictureDTOs = recommendService.RecommendPageOfPictureDTO(myUserId);
		List<AlbumDTO> albums=albumService.showAlbum(myUserId);
		model.addAttribute("pictureDTOs", pictureDTOs);
		model.addAttribute("albums", albums);
		return "recommendPage";
	}
	
	@RequestMapping("/toRecommendPageOfSortPage")
	public String toRecommendPage(HttpSession session,Model model,String interestName)
	{
		Long myUserId = (Long)session.getAttribute("userId");
		List<PictureDTO> pictureDTOs = recommendService.findSortPictureByClassification(myUserId, interestName);
		List<AlbumDTO> albums=albumService.showAlbum(myUserId);
		model.addAttribute("pictureDTOs", pictureDTOs);
		model.addAttribute("albums", albums);
		return "recommendPage";
	}
}
