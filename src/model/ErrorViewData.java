package model;

import java.io.Serializable;

//エラービューの表示内容を伝達するためのJavaBeans
public class ErrorViewData implements Serializable {
	private String message; //表示メッセージ
	private String linkStr;	//リンク表示文字列
	private String link;  	//リンク先アドレス
	
	//コンストラクタ
	public ErrorViewData(String message, String linkStr, String link) {
		this.message = message;
		this.linkStr = linkStr;
		this.link = link;
	}

	//setterとgetter
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getLinkStr() {
		return linkStr;
	}
	public void setLinkStr(String linkStr) {
		this.linkStr = linkStr;
	}
}
