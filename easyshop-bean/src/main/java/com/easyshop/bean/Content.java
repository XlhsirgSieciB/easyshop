package com.easyshop.bean;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author pursuelin
 * @since 2018-12-06
 */
@TableName("tb_content")
public class Content implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 内容类目ID
     */
    private Long categoryId;
    /**
     * 内容标题
     */
    private String title;
    /**
     * 子标题
     */
    private String subTitle;
    /**
     * 标题描述
     */
    private String titleDesc;
    /**
     * 链接
     */
    private String url;
    /**
     * 图片绝对路径
     */
    private String pic;
    /**
     * 图片2
     */
    private String pic2;
    /**
     * 内容
     */
    private String content;
    private Date created;
    private Date updated;

    //图片尺寸
    private Integer width;
    private Integer height;
    private Integer status;
    private Integer del;
    
    @TableField(exist=false)
    private ContentCategory contentCategory;

	public Integer getDel() {
		return del;
	}

	public void setDel(Integer del) {
		this.del = del;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public ContentCategory getContentCategory() {
		return contentCategory;
	}

	public void setContentCategory(ContentCategory contentCategory) {
		this.contentCategory = contentCategory;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getTitleDesc() {
        return titleDesc;
    }

    public void setTitleDesc(String titleDesc) {
        this.titleDesc = titleDesc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Content{" +
        ", id=" + id +
        ", categoryId=" + categoryId +
        ", title=" + title +
        ", subTitle=" + subTitle +
        ", titleDesc=" + titleDesc +
        ", url=" + url +
        ", pic=" + pic +
        ", pic2=" + pic2 +
        ", content=" + content +
        ", created=" + created +
        ", updated=" + updated +
        "}";
    }
}
