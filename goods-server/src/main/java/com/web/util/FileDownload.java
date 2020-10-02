package com.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * 下载文件 创建人：wlh 创建时间：2014年12月23日
 * 
 * @version
 */
public class FileDownload {

	/**
	 * @param response
	 * @param filePath
	 *            //文件完整路径(包括文件名和扩展名)
	 * @param fileName
	 *            //下载后看到的文件名
	 * @return 文件名
	 */
	public static void fileDownload(final HttpServletResponse response, String filePath, String fileName)
			throws Exception {

		byte[] data = FileUtil.toByteArray2(filePath + fileName);
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream;charset=UTF-8");
		// 激活下载操作
		OutputStream os = response.getOutputStream();
		BufferedOutputStream outputStream = new BufferedOutputStream(os);
		outputStream.write(data);
		outputStream.flush();
		outputStream.close();
		os.close();
		response.flushBuffer();
	}

	/**
	 * @param response
	 * @param request
	 * @param filePath
	 *            //文件完整路径(包括文件名和扩展名)
	 * @param fileName
	 *            //下载后看到的文件名
	 * @return 文件名
	 */
	public static void fileDownload(final HttpServletResponse response, HttpServletRequest request, String filePath,
                                    String fileName) throws Exception {

		byte[] data =FileUtil.toByteArray2(filePath + fileName);
		String formFileName = fileName;
		String userAgent = request.getHeader("User-Agent");
		// 针对IE或者以IE为内核的浏览器：
		if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
			formFileName = URLEncoder.encode(formFileName, "UTF-8");
		} else {
			// 非IE浏览器的处理：
			formFileName = new String(formFileName.getBytes("UTF-8"), "ISO-8859-1");
		}
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"" + formFileName + "\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream;charset=UTF-8");
		// 激活下载操作
		OutputStream os = response.getOutputStream();
		BufferedOutputStream outputStream = new BufferedOutputStream(os);
		outputStream.write(data);
		outputStream.flush();
		outputStream.close();
		os.close();
		response.flushBuffer();
	}

}
