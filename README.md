# ActinonLogger
授業課題：行動記録システム<br>
コロナウイルス影響下で行う感染拡大対策の一環として、利用者の行動記録を管理する。<br>
■使用言語<br>
 Java EE(Servlet・JSP)
## ■DB
  ### ACTION テーブル (活動)
| ACTION_ID | ACTION_ADD_DATETIME | ACTION_DATE | ACTION_START_TIME | ACTION_END_TIME | ACTION_PLACE | ACTION_REASON | ACTION_REMARKS | ACTION_USERID    | 
| :-------- | ------------------- | ----------- | ----------------- | --------------- | ------------ | ------------- | -------------- | ---------------- | 
| 活動id    | 活動追加日          | 活動日      | 活動開始時刻      | 活動終了時刻    | 活動場所     | 活動内容      | 備考           | 活動ユーザーのid | 
 ### BELONG テーブル (所属)
| BELONG_GROUPID | BELONG_USERID | ADM  | 
| -------------- | ------------- | ---- | 
| グループID     | ユーザーID    | 権限 | 
### MGT_GROUP テーブル (グループ)
| GROUP_ID   | GROUP_NAME | 
| ---------- | ---------- | 
| グループID | グループ名 | 
### USER テーブル (ユーザー)
| USERID     | PWDHASH            | NAME | ADDRESS | TEL      | 	EMAIL         | 
| ---------- | ------------------ | ---- | ------- | -------- | -------------- | 
| ユーザーID | パスワードハッシュ | 名前 | 住所    | 電話番号 | メールアドレス | 
