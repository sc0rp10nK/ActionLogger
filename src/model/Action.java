package model;

import java.io.Serializable;

public class Action implements Serializable{
	private String actionId; // id
	private String actionADT; //活動を追加した時の時間
	private String actionDate; //日付
	private String actionSTm; //開始時間
	private String actionETm; //終了時間
	private String actionPlace; //場所
	private String actionReason; //理由
	private String actionRemarks; //備考
	private String actionUserId; //追加する人のID
	
	//getter & setter
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	public String getActionADT() {
		return actionADT;
	}
	public void setActionADT(String actionADT) {
		this.actionADT = actionADT;
	}
	public String getActionDate() {
		return actionDate;
	}
	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}
	public String getActionSTm() {
		return actionSTm;
	}
	public void setActionSTm(String actionSTm) {
		this.actionSTm = actionSTm;
	}
	public String getActionETm() {
		return actionETm;
	}
	public void setActionETm(String actionETm) {
		this.actionETm = actionETm;
	}
	public String getActionPlace() {
		return actionPlace;
	}
	public void setActionPlace(String actionPlace) {
		this.actionPlace = actionPlace;
	}
	public String getActionReason() {
		return actionReason;
	}
	public void setActionReason(String actionReason) {
		this.actionReason = actionReason;
	}
	public String getActionRemarks() {
		return actionRemarks;
	}
	public void setActionRemarks(String actionRemarks) {
		this.actionRemarks = actionRemarks;
	}
	public String getActionUserId() {
		return actionUserId;
	}
	public void setActionUserId(String actionUserId) {
		this.actionUserId = actionUserId;
	}
	
	
}
