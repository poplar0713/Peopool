package com.ssafy.peopool.model;

public class FileInfo {
	
	private String saveFolder;
	private String originFile;
	private String saveFile;
	public String getSaveFolder() {
		return saveFolder;
	}
	public void setSaveFolder(String saveFolder) {
		this.saveFolder = saveFolder;
	}
	public String getOriginFile() {
		return originFile;
	}
	public void setOriginFile(String originFile) {
		this.originFile = originFile;
	}
	public String getSaveFile() {
		return saveFile;
	}
	public void setSaveFile(String saveFile) {
		this.saveFile = saveFile;
	}
	@Override
	public String toString() {
		return "FileInfo [saveFolder=" + saveFolder + ", originFile=" + originFile + ", saveFile=" + saveFile + "]";
	}
	
	

}
