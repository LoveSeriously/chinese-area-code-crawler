package com.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

/**
 * @author lw
 * @date 2019-08-01
 */
public class Category implements Serializable {
    private String id;
    private String createTime;
    private String creatorId;
    private String creatorName;
    private String baseCode;
    private String sysCode;
    private String dataAreaCode;
    private Integer dataSource;

    private String pId;
    private String categoryName;
    private String categoryCode;
    private String pIds;
    private String pNames;
    private Integer isLeaf;
    private Integer categoryType;
    private Integer sortNum;
    private Integer level;

    public Category(CategoryBuilder categoryBuilder) {
        this.id = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
        Date nowTime=new Date();
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = time.format(nowTime);
        this.createTime = categoryBuilder.createTime == null ? format : categoryBuilder.createTime;
        this.creatorId = categoryBuilder.creatorId;
        this.creatorName = categoryBuilder.creatorName;
        this.baseCode = categoryBuilder.baseCode;
        this.sysCode = categoryBuilder.sysCode;
        this.dataAreaCode = categoryBuilder.dataAreaCode;
        this.dataSource = categoryBuilder.dataSource;
        this.pId = categoryBuilder.pId;
        this.categoryName = categoryBuilder.categoryName;
        this.categoryCode = categoryBuilder.categoryCode;
        this.pIds = categoryBuilder.pIds;
        this.pNames = categoryBuilder.pNames;
        this.isLeaf = categoryBuilder.isLeaf;
        this.categoryType = categoryBuilder.categoryType;
        this.sortNum = categoryBuilder.sortNum;
        this.level = categoryBuilder.level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getDataAreaCode() {
        return dataAreaCode;
    }

    public void setDataAreaCode(String dataAreaCode) {
        this.dataAreaCode = dataAreaCode;
    }

    public Integer getDataSource() {
        return dataSource;
    }

    public void setDataSource(Integer dataSource) {
        this.dataSource = dataSource;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getpIds() {
        return pIds;
    }

    public void setpIds(String pIds) {
        this.pIds = pIds;
    }

    public String getpNames() {
        return pNames;
    }

    public void setpNames(String pNames) {
        this.pNames = pNames;
    }

    public Integer getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", creatorId='" + creatorId + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", baseCode='" + baseCode + '\'' +
                ", sysCode='" + sysCode + '\'' +
                ", dataAreaCode='" + dataAreaCode + '\'' +
                ", dataSource=" + dataSource +
                ", pId='" + pId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categoryCode='" + categoryCode + '\'' +
                ", pIds='" + pIds + '\'' +
                ", pNames='" + pNames + '\'' +
                ", isLeaf=" + isLeaf +
                ", categoryType=" + categoryType +
                ", sortNum=" + sortNum +
                '}';
    }

    public static class CategoryBuilder {
        private String id;
        private String createTime;
        private String creatorId;
        private String creatorName;
        private String baseCode;
        private String sysCode;
        private String dataAreaCode;
        private Integer dataSource;

        private String pId;
        private String categoryName;
        private String categoryCode;
        private String pIds;
        private String pNames;
        private Integer isLeaf;
        private Integer categoryType;
        private Integer sortNum;
        private Integer level;

        public CategoryBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public CategoryBuilder setCreateTime(String createTime) {
            this.createTime = createTime;
            return this;
        }

        public CategoryBuilder setCreatorId(String creatorId) {
            this.creatorId = creatorId;
            return this;
        }

        public CategoryBuilder setCreatorName(String creatorName) {
            this.creatorName = creatorName;
            return this;
        }

        public CategoryBuilder setBaseCode(String baseCode) {
            this.baseCode = baseCode;
            return this;
        }

        public CategoryBuilder setSysCode(String sysCode) {
            this.sysCode = sysCode;
            return this;
        }

        public CategoryBuilder setDataAreaCode(String dataAreaCode) {
            this.dataAreaCode = dataAreaCode;
            return this;
        }

        public CategoryBuilder setDataSource(Integer dataSource) {
            this.dataSource = dataSource;
            return this;
        }

        public CategoryBuilder setpId(String pId) {
            this.pId = pId;
            return this;
        }

        public CategoryBuilder setCategoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }

        public CategoryBuilder setCategoryCode(String categoryCode) {
            this.categoryCode = categoryCode;
            return this;
        }

        public CategoryBuilder setpIds(String pIds) {
            this.pIds = pIds;
            return this;
        }

        public CategoryBuilder setpNames(String pNames) {
            this.pNames = pNames;
            return this;
        }

        public CategoryBuilder setIsLeaf(Integer isLeaf) {
            this.isLeaf = isLeaf;
            return this;
        }

        public CategoryBuilder setCategoryType(Integer categoryType) {
            this.categoryType = categoryType;
            return this;
        }

        public CategoryBuilder setSortNum(Integer sortNum) {
            this.sortNum = sortNum;
            return this;
        }

        public Integer getLevel() {
            return level;
        }

        public CategoryBuilder setLevel(Integer level) {
            this.level = level;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }
}
