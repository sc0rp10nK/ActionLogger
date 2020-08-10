package model;

import java.io.Serializable;

public class Member extends User implements Serializable{
	private String gpName; //グループ名
	private String adm; //権限
	private String gpId;

	public String getGpId() {
		return gpId;
	}

	public void setGpId(String gpId) {
		this.gpId = gpId;
	}

	public String getGpName() {
		return gpName;
	}

	public void setGpName(String gpName) {
		this.gpName = gpName;
	}

	public String getAdm() {
		return adm;
	}

	public void setAdm(String adm) {
		this.adm = adm;
	}
}
