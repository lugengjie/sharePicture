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
	
	/**
	 * 上传文件
	 * 1.获取文件名后缀，用UUID生成唯一标识，拼接成新文件名
	 * 2.根据文件名的hashCode%1000为存放文件夹
	 * 3.转存文件到指定路径
	 * 4.返回文件路径和名字
	 * @param multipartFile
	 * @param uploadPath
	 * @return
	 */
	public static String upLoad(MultipartFile multipartFile,String localAbsolutePath)
	{
		int fileHashCode=Math.abs(multipartFile.hashCode());
		String packageName="/"+(fileHashCode%1000);
		localAbsolutePath+=packageName;
		File newFilePath=new File(localAbsolutePath);
		if(!newFilePath.exists())
		{
			newFilePath.mkdirs();
		}
		String suffixName="jpeg";
		String newFileName=UuidUtil.getUUID()+"."+suffixName;
		try
		{
			multipartFile.transferTo(new File(newFilePath,newFileName));
		}
		catch (IOException e)
		{
			return null;
		}
		return packageName+"/"+newFileName;
	}

}
