package com.example.demo.common.utils;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传图片工具
 * @author Administrator
 *
 */
public class FileUploadUtil
{	
	/**
	 * 判断文件是否为图片
	 * @param file
	 * @return
	 */
	public static boolean isImage(MultipartFile file)
	{
		try
		{
			Image image=ImageIO.read(file.getInputStream());
			if(image!=null&&image.getWidth(null)>0&&image.getHeight(null)>0) 
			{
				return true;
			}
		} 
		catch (IOException e)
		{
			return false;
		}
		return false;
	}

}
