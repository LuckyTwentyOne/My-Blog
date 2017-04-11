package ua.kh.butov.blog.entity;

import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;

public class Article extends AbstractEntity<Long> {
	private static final long serialVersionUID = -161085554582629931L;
	private String title;
	private String url;
	private String logo;
	private String desc;
	private String content;
	private int idCategory;
	private Timestamp created;
	private long views;
	private int comments;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public long getViews() {
		return views;
	}
	public void setViews(long views) {
		this.views = views;
	}
	public int getComments() {
		return comments;
	}
	public void setComments(int comments) {
		this.comments = comments;
	}
	public String getArticleLink(){
		return "/MyBlog/article/"+getId()+url;
	}
	public String getShortTitle(){
		if(StringUtils.length(title) > 20) {
			return title.substring(0, 17)+"...";
		}
		else{
			return title;
		}
	}
}
