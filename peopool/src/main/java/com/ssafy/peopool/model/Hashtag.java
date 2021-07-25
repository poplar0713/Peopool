package com.ssafy.peopool.model;

public class Hashtag {
	int tag_index;
	int tag_type;
	int tag_target;
	int taglist_index;

	public Hashtag() {
	}

	public int getTag_index() {
		return tag_index;
	}

	public void setTag_index(int tag_index) {
		this.tag_index = tag_index;
	}

	public int getTag_type() {
		return tag_type;
	}

	public void setTag_type(int tag_type) {
		this.tag_type = tag_type;
	}

	public int getTag_target() {
		return tag_target;
	}

	public void setTag_target(int tag_target) {
		this.tag_target = tag_target;
	}

	public int getTaglist_index() {
		return taglist_index;
	}

	public void setTaglist_index(int taglist_index) {
		this.taglist_index = taglist_index;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Hashtag [tag_index=").append(tag_index).append(", tag_type=").append(tag_type)
				.append(", tag_target=").append(tag_target).append(", taglist_index=").append(taglist_index)
				.append("]");
		return builder.toString();
	}
}
