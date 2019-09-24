package com.xxl.job.executor.entity.mysql;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author jiangtingfeng
 * @description
 * @data 2019/9/24
 */
@Data
@Table(name = "SysCorp")
@Entity
public class SysCorp implements Serializable {

    @Id
    private int corpID;

    private String corpName;
    private String corpCode;
    private String manager;
    private String managerPhone;
    private String phone;
    private String address;
    private String email;
    private String postCode;
    private String fax;
    private String webSite;
    private String regionCode;
    private String longitude;
    private String latitude;
    private Integer mallTemplateID;
    private String mallLogo;
    private String mallName;
    private String mallInfo;
    private String licenseImg;
    private String organizeImg;
    private String taxImg;
    private String weiXinAppId;
    private String weiXinAPPSecret;
    private String WeiXinPaySignKey;
    private String alipayID;
    private Integer alipayIDStatus;
    private String qualification;
    private Integer status;
    private String auditInfo;
    private Date auditSubmitTime;
    private Date auditPassTime;
    private String note;
    //注意
    private String createBy;
    private Date createTime;
    //
    private String lastUpdateBy;
    private Date lastUpdateTime;
    private Integer corpType;
    private Integer operatorsID;
    private Date expiryDate;
    private String taobaoID;
    private Integer Source;
    private String province;
    private String city;
    private String district;
    private String zoom;
    private String systemSettingID;
    //
    private String industryTypeID;
    private Integer addToSubSysLanding;
    private Integer pageThemeID;
    private String logoImage;
    private String backImage;
    private String logoTitle;
    private Integer optionEditPower;
    private String selectedSystems;
    private String isUseDefaultSystemSetting;
}
