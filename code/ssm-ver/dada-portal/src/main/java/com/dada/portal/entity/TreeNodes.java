package com.dada.portal.entity;

/**
 * <p>项目名称:dada-manager-web</p>
 * <p>包名称:com.dada.manager.controller.TreeNodes</p>
 * <p>文件名称:TreeNodes.java</p>
 * <p>功能描述:ZTree节点实体</p>
 * <p>其他说明:    </p>
 * <p>@author:thanos<p>   
 * <p> date:2019年3月24日上午10:43:23 </p>
 * <p>@version  jdk1.8</p>
 * <p>Copyright (c) 2019, 892944741@qq.com All Rights Reserved. </p>
 */
public class TreeNodes {
	
	 private String id ;

	 private String pid ;

	 private String name;

	 private String url ;

	 private int open ;

	 private int ishidden ;

	 private String title ;

	 private String target;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getOpen() {
		return open;
	}

	public void setOpen(int open) {
		this.open = open;
	}

	public int getIshidden() {
		return ishidden;
	}

	public void setIshidden(int ishidden) {
		this.ishidden = ishidden;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "TreeNodes [id=" + id + ", pid=" + pid + ", name=" + name + ", url=" + url + ", open=" + open
				+ ", ishidden=" + ishidden + ", title=" + title + ", target=" + target + "]";
	}


}
