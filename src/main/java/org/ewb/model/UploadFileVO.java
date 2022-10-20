package org.ewb.model;

public class UploadFileVO {
	private String path;
	private String fileName;
	private String uuid;
	private String fullPath;
	private boolean checkI;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getFullPath() {
		return fullPath;
	}
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
	public boolean isCheckI() {
		return checkI;
	}
	public void setCheckI(boolean checkI) {
		this.checkI = checkI;
	}
	@Override
	public String toString() {
		return "UploadFileVO [path=" + path + ", fileName=" + fileName + ", uuid=" + uuid + ", fullPath=" + fullPath
				+ ", checkI=" + checkI + "]";
	}
	
	
}
