package model;

import java.io.Serializable;

//フォームの正当性確認キー
public class ValidationKey implements Serializable {
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
