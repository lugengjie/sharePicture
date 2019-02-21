package com.example.demo.common.utils;

import java.util.UUID;
/**
 * 生成通用唯一识别码工具
 * @author Administrator
 *
 */
public class UuidUtil {
	public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
   }
}
