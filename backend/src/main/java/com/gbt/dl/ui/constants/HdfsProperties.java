package com.gbt.dl.ui.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.gbt.dl.ui.util.Utils;

@ConfigurationProperties(prefix = "hdfs", ignoreUnknownFields = false)
public class HdfsProperties {

	private String defaultfs;

	private String host1;
	private String host2;

	public String getHost1() {
		return host1;
	}

	public void setHost1(String host1) {
		this.host1 = host1;
	}

	public String getHost2() {
		return host2;
	}

	public void setHost2(String host2) {
		this.host2 = host2;
	}

	private String uploadPath;

	private String appendEmphierPath;

	public String getDefaultfs() {
		return defaultfs;
	}

	public void setDefaultfs(String defaultfs) {
		this.defaultfs = defaultfs;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;

	}

	public String getAppendEmphierPath() {
		return appendEmphierPath;
	}

	public void setAppendEmphierPath(String appendEmphierPath) {
		this.appendEmphierPath = appendEmphierPath;
	}

}
