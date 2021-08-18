package com.ssafy.peopool.model;

public class TagList {
	int taglist_index;
	String taglist_name;
	int cat_index;

	public int getCat_index() {
		return cat_index;
	}

	public void setCat_index(int cat_index) {
		this.cat_index = cat_index;
	}

	public int getTaglist_index() {
		return taglist_index;
	}

	public void setTaglist_index(int taglist_index) {
		this.taglist_index = taglist_index;
	}

	public String getTaglist_name() {
		return taglist_name;
	}

	public void setTaglist_name(String taglist_name) {
		this.taglist_name = taglist_name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tag [taglist_index=").append(taglist_index).append(", taglist_name=").append(taglist_name)
				.append("]");
		return builder.toString();
	}

}
