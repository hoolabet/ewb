package org.ewb.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.ewb.model.UploadFileVO;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnailator;

@Controller
public class UploadFileController {
	
	private String getFolder() {
		Date d = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		return dateFormat.format(d).replace("-", "\\");
	}

	private boolean checkImage(File file) {
		try {
			String fileType = Files.probeContentType(file.toPath());
			return fileType.startsWith("image");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	@RequestMapping(value = "/uploadAjaxAction", method = RequestMethod.POST)
	public ResponseEntity<ArrayList<UploadFileVO>> uploadAjaxAction(ArrayList<MultipartFile> uploadFile) {

		ArrayList<UploadFileVO> uvoList = new ArrayList<>();

		String uploadFolder = "D:\\01-STUDY\\upload";
		File uploadPath = new File(uploadFolder, getFolder());
		if(!uploadPath.exists()) {
			uploadPath.mkdirs();
		}

		for(MultipartFile u : uploadFile) {
			UUID uuid = UUID.randomUUID();

			File saveFile = new File(uploadPath,uuid+"_"+u.getOriginalFilename());

			UploadFileVO uvo = new UploadFileVO();

			uvo.setPath(getFolder());
			uvo.setFileName(u.getOriginalFilename());
			uvo.setUuid(uuid.toString());
			uvo.setCheckI(checkImage(saveFile));

			try {
				u.transferTo(saveFile);
//				if(checkImage(saveFile)) {
//					File thumb = new File(uploadPath,"s_"+uuid+"_"+u.getOriginalFilename());
//					FileOutputStream fos = new FileOutputStream(thumb);
//					Thumbnailator.createThumbnail(u.getInputStream(),fos,100,100);
//					fos.close();
//				}
				uvoList.add(uvo);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return new ResponseEntity<>(uvoList,HttpStatus.OK);
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getFile(String fileName){
		File file = new File("D:\\01-STUDY\\upload\\"+ fileName);
		ResponseEntity<byte[]> result = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(
					FileCopyUtils.copyToByteArray(file),
					headers,
					HttpStatus.OK
					);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ResponseEntity<Resource> fileDownload(String fileName) {
		Resource res = new FileSystemResource("D:\\01-STUDY\\upload\\"+ fileName);
		String resName = res.getFilename();
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.add(
					"Content-Disposition", 
					"attachment;filename=" 
							+ new String(resName.getBytes("utf-8"),"ISO-8859-1"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(res, headers,HttpStatus.OK);
	}
}
