package model;

public class Group {
	private String groupId; // id
	private String groupName; //名前
	private String groupUserId; //ユーザーid
	private boolean groupAdm; //管理者判定

	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupUserId() {
		return groupUserId;
	}
	public void setGroupUserId(String groupUserId) {
		this.groupUserId = groupUserId;
	}
	public boolean isGroupAdm() {
		return groupAdm;
	}
	public void setGroupAdm(boolean groupAdm) {
		this.groupAdm = groupAdm;
	}

}
