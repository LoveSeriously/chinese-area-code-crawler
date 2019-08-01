package com.bean;

import java.util.Date;
import java.util.UUID;

/**
 * 新的数据区域
 *
 * @author lw
 * @date 2019-08-01
 */
public class AreaCode {
    private String id;
    private Date createTime;
    private String creatorId;
    private String creatorName;
    private String baseCode;
    private String sysCode;
    private String dataAreaCode;
    private Integer dataSource;

    private String pId;
    private String areaName;
    private String areaCode;
    private String pIds;
    private String pNames;
    private Integer isLeaf;
    private Integer state;
    private Integer isGrid;
    private Integer sortNum;

    public AreaCode(AreaCodeBuilder areaCodeBuilder) {
        this.id = areaCodeBuilder.id.equals(null) ? UUID.randomUUID().toString().replaceAll("-", "") : areaCodeBuilder.id;
        this.createTime = areaCodeBuilder.createTime == null ? new Date() : areaCodeBuilder.createTime;
        this.creatorId = areaCodeBuilder.creatorId;
        this.creatorName = areaCodeBuilder.creatorName;
        this.baseCode = areaCodeBuilder.baseCode;
        this.sysCode = areaCodeBuilder.sysCode;
        this.dataAreaCode = areaCodeBuilder.dataAreaCode;
        this.dataSource = areaCodeBuilder.dataSource;
        this.pId = areaCodeBuilder.pId;
        this.areaName = areaCodeBuilder.areaName;
        this.areaCode = areaCodeBuilder.areaCode;
        this.pIds = areaCodeBuilder.pIds;
        this.pNames = areaCodeBuilder.pNames;
        this.isLeaf = areaCodeBuilder.isLeaf;
        this.state = areaCodeBuilder.state;
        this.isGrid = areaCodeBuilder.isGrid;
        this.sortNum = areaCodeBuilder.sortNum;
    }

    public String getId() {
        return id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public String getSysCode() {
        return sysCode;
    }

    public String getDataAreaCode() {
        return dataAreaCode;
    }

    public Integer getDataSource() {
        return dataSource;
    }

    public String getpId() {
        return pId;
    }

    public String getAreaName() {
        return areaName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public String getpIds() {
        return pIds;
    }

    public String getpNames() {
        return pNames;
    }

    public Integer getIsLeaf() {
        return isLeaf;
    }

    public Integer getState() {
        return state;
    }

    public Integer getIsGrid() {
        return isGrid;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    @Override
    public String toString() {
        return "AreaCode{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", creatorId='" + creatorId + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", baseCode='" + baseCode + '\'' +
                ", sysCode='" + sysCode + '\'' +
                ", dataAreaCode='" + dataAreaCode + '\'' +
                ", dataSource=" + dataSource +
                ", pId='" + pId + '\'' +
                ", areaName='" + areaName + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", pIds='" + pIds + '\'' +
                ", pNames='" + pNames + '\'' +
                ", isLeaf=" + isLeaf +
                ", state=" + state +
                ", isGrid=" + isGrid +
                ", sortNum=" + sortNum +
                '}';
    }

    public static class AreaCodeBuilder {
        private String id;
        private Date createTime;
        private String creatorId;
        private String creatorName;
        private String baseCode;
        private String sysCode;
        private String dataAreaCode;
        private Integer dataSource;

        private String pId;
        private String areaName;
        private String areaCode;
        private String pIds;
        private String pNames;
        private Integer isLeaf;
        private Integer state;
        private Integer isGrid;
        private Integer sortNum;

        public AreaCodeBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public AreaCodeBuilder setCreateTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        public AreaCodeBuilder setCreatorId(String creatorId) {
            this.creatorId = creatorId;
            return this;
        }

        public AreaCodeBuilder setCreatorName(String creatorName) {
            this.creatorName = creatorName;
            return this;
        }

        public AreaCodeBuilder setBaseCode(String baseCode) {
            this.baseCode = baseCode;
            return this;
        }

        public AreaCodeBuilder setSysCode(String sysCode) {
            this.sysCode = sysCode;
            return this;
        }

        public AreaCodeBuilder setDataAreaCode(String dataAreaCode) {
            this.dataAreaCode = dataAreaCode;
            return this;
        }

        public AreaCodeBuilder setDataSource(Integer dataSource) {
            this.dataSource = dataSource;
            return this;
        }

        public AreaCodeBuilder setpId(String pId) {
            this.pId = pId;
            return this;
        }

        public AreaCodeBuilder setAreaName(String areaName) {
            this.areaName = areaName;
            return this;
        }

        public AreaCodeBuilder setAreaCode(String areaCode) {
            this.areaCode = areaCode;
            return this;
        }

        public AreaCodeBuilder setpIds(String pIds) {
            this.pIds = pIds;
            return this;
        }

        public AreaCodeBuilder setpNames(String pNames) {
            this.pNames = pNames;
            return this;
        }

        public AreaCodeBuilder setIsLeaf(Integer isLeaf) {
            this.isLeaf = isLeaf;
            return this;
        }

        public AreaCodeBuilder setState(Integer state) {
            this.state = state;
            return this;
        }

        public AreaCodeBuilder setIsGrid(Integer isGrid) {
            this.isGrid = isGrid;
            return this;
        }

        public AreaCodeBuilder setSortNum(Integer sortNum) {
            this.sortNum = sortNum;
            return this;
        }

        public AreaCode build() {
            return new AreaCode(this);
        }
    }
}
