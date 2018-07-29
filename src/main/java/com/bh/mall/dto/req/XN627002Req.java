package com.bh.mall.dto.req;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改代理
 * @author nyc
 *
 */
public class XN627002Req {


	
	
	
	
	// （必填） 首次授权发货金额
    @NotBlank(message = "首次授权发货金额不能为空")
    private String amount;
    
    // （必填）本等级授权单允许自发
    @NotBlank(message = "本等级授权单是否自发")
    private String isSend;
    
    // 本等级是否启用云仓
    @NotBlank(message = "是否启用云仓不能为空")
    private String isWareHouse;
    
    // 等级（必填）
    @NotBlank(message = "等级不能为空")
    private String level;
    
    // (必填) 本等级最低充值金额
    @NotBlank(message = "本等级最低充值金额不能为空")
    private String minChargeAmount;
    
    // （必填）本等级门槛最低余额
    @NotBlank(message = "本等级门槛最低余额不能为空")
    private String minSurplus;
    
    // 等级名称（必填）
    @NotBlank(message = "等级名称不能为空")
    private String name;
    
    // (必填) 红线金额
    @NotBlank(message = "红线金额不能为空")
    private String redAmount;
    
    

    @NotBlank(message = "门槛款不能为空")
    private String impowerAmount;
    @NotBlank(message = "门槛框单次最小充值金额不能为空")
	private String minCharge;

    // （选填） 备注
    private String remark;

    
    // （必填） 更新人
    @NotBlank(message = " 更新人不能为空")
    private String updater;
    
    
    private String code;

    
    

    

	// 本等级升级是否公司审核 （必填）
    private String isCompanyApprove;
    
    // 半门槛推荐人数 （必填）
    @NotBlank
    private String reNumber;

    private String isReset;
    
    // 本等级是否可以被意向 （必填）
    @NotBlank
    private String isIntent;
    
    // 是否可以被介绍 （必填）
    private String isIntro;
    
    // 是否需要实名 （必填）
    private String isRealName;
    
    // 是否需要公司审核 （必填）
    private String isCompanyImpower;
    
    private String impower_amount;
   
    
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIsCompanyApprove() {
		return isCompanyApprove;
	}

	public void setIsCompanyApprove(String isCompanyApprove) {
		this.isCompanyApprove = isCompanyApprove;
	}

	public String getReNumber() {
		return reNumber;
	}

	public void setReNumber(String reNumber) {
		this.reNumber = reNumber;
	}

	public String getIsReset() {
		return isReset;
	}

	public void setIsReset(String isReset) {
		this.isReset = isReset;
	}

	public String getIsIntent() {
		return isIntent;
	}

	public void setIsIntent(String isIntent) {
		this.isIntent = isIntent;
	}

	public String getIsIntro() {
		return isIntro;
	}

	public void setIsIntro(String isIntro) {
		this.isIntro = isIntro;
	}

	public String getIsRealName() {
		return isRealName;
	}

	public void setIsRealName(String isRealName) {
		this.isRealName = isRealName;
	}

	public String getIsCompanyImpower() {
		return isCompanyImpower;
	}

	public void setIsCompanyImpower(String isCompanyImpower) {
		this.isCompanyImpower = isCompanyImpower;
	}

	public String getImpower_amount() {
		return impower_amount;
	}

	public void setImpower_amount(String impower_amount) {
		this.impower_amount = impower_amount;
	}

	public String getMinCharge() {
		return minCharge;
	}

	public void setMinCharge(String minCharge) {
		this.minCharge = minCharge;
	}

	public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMinSurplus() {
        return minSurplus;
    }

    public void setMinSurplus(String minSurplus) {
        this.minSurplus = minSurplus;
    }

    public String getIsWareHouse() {
        return isWareHouse;
    }

    public void setIsWareHouse(String isWareHouse) {
        this.isWareHouse = isWareHouse;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMinChargeAmount() {
        return minChargeAmount;
    }

    public void setMinChargeAmount(String minChargeAmount) {
        this.minChargeAmount = minChargeAmount;
    }

    public String getRedAmount() {
        return redAmount;
    }

    public void setRedAmount(String redAmount) {
        this.redAmount = redAmount;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsSend() {
        return isSend;
    }

    public void setIsSend(String isSend) {
        this.isSend = isSend;
    }
    
    public String getImpowerAmount() {
		return impowerAmount;
	}

	public void setImpowerAmount(String impowerAmount) {
		this.impowerAmount = impowerAmount;
	}


}
