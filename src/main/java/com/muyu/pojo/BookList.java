package com.muyu.pojo;

import java.util.HashMap;

public class BookList {
	private String sectionName;
	private String sectionHref;
	private String sectionContent;

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getSectionHref() {
		return sectionHref;
	}

	public void setSectionHref(String sectionHref) {
		this.sectionHref = sectionHref;
	}

	public String getSectionContent() {
		return sectionContent;
	}

	public void setSectionContent(String sectionContent) {
		this.sectionContent = sectionContent;
	}

	@Override
	public String toString() {
		return "BookList{" +
				"sectionName='" + sectionName + '\'' +
				", sectionHref='" + sectionHref + '\'' +
				", sectionContent='" + sectionContent + '\'' +
				'}';
	}
}