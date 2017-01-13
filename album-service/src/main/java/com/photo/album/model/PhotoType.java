package com.photo.album.model;

public class PhotoType {

	private Integer id;
	private String TypeName;
	private String TypeCover;
	private Integer TypeContent;
	private String typeRemark;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeName() {
		return TypeName;
	}
	public void setTypeName(String typeName) {
		TypeName = typeName;
	}
	public String getTypeCover() {
		return TypeCover;
	}
	public void setTypeCover(String typeCover) {
		TypeCover = typeCover;
	}
	public Integer getTypeContent() {
		return TypeContent;
	}
	public void setTypeContent(Integer typeContent) {
		TypeContent = typeContent;
	}
	public String getTypeRemark() {
		return typeRemark;
	}
	public void setTypeRemark(String typeRemark) {
		this.typeRemark = typeRemark;
	}
	
	
}
