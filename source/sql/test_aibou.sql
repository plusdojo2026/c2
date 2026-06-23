
/*変更点
※0623時点
  ログインidにユニークキーを付け足しました。
※0619時点
キャラidを増やしました。キャラ×タイプの組み合わせを増やしました。
ユーザテーブルのpwを小文字に変更しました。
ミッションの数を増やしました
もし、ミッションを増やしたかったら言ってください

※0616時点
時点データを増やしました
外部制約キーを付け加えました。
ユーザテーブルのキャラクターidをデフォルトで１が入れられるようにしました。（1はすなわち犬です。）
ユーザテーブル→パスワードを四文字以上じゃない登録できないようにした*/

CREATE DATABASE test_aibou;
use test_aibou;

/*テーマテーブル*/

CREATE TABLE theme (
    theme_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    theme_name VARCHAR(20) NOT NULL
);

INSERT INTO theme (theme_name) VALUES (
    '生活')
    ;
    INSERT INTO theme (theme_name) VALUES (
    '勉強')
    ;
    INSERT INTO theme (theme_name) VALUES(
    '運動')
    ;
    INSERT INTO theme (theme_name) VALUES(
    'お金')
    ;
    INSERT INTO theme (theme_name) VALUES(
    '趣味'
);
SELECT * FROM theme;

/*ミッションテーブル*/
CREATE TABLE mission(
    theme_id INT NOT NULL,
    mission_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    mission_name VARCHAR(100),
FOREIGN KEY (theme_id) REFERENCES theme(theme_id)

);
INSERT INTO mission (theme_id,mission_name) VALUES(
    1,'朝食を食べる'
);
INSERT INTO mission (theme_id,mission_name) VALUES(
    1,'昼食を食べる'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    1,'夕食を食べる'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    1,'15分散歩する'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    1,'洗濯をする'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    1,'食器を洗う'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    1,'お風呂につかる'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    1,'部屋の掃除をする'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    1,'風呂掃除をする'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    1,'１食自炊する'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    1,'トイレ掃除をする'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    1,'ゴミ出しする'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    1,'挨拶をする'
);


INSERT INTO mission (theme_id,mission_name) VALUES(
    2,'資格勉強を30分する。'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    2,'金融に関するニュース記事を調べる'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    2,'政治に関するニュース記事を調べる'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    2,'芸能・エンタメに関するニュース記事を調べる'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    2,'IT・テクノロジーに関するニュース記事を調べる'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    2,'国際情勢に関するニュース記事を調べる'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    2,'スポーツに関するニュース記事を調べる'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    2,'本を読む'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    4,'最安値の店を調べてから買い物をする '
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    4,'貯金箱に５００円入れる'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    4,'節電する（不要なコンセントを抜く）'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    4,'家計簿をつける'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    4,'固定費を見直す（光熱費、通信費やサブスクなど）'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    4,'投資、税金、保険について勉強する'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    4,'ふるさと納税について調べる'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    4,'NISAについて調べる'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    4,'クレジットカードの利用額を確認する'
);


INSERT INTO mission (theme_id,mission_name) VALUES(
    5,'趣味の時間を10分とる '
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    5,'身近な人と連絡を取る（会話する）'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    5,'10分以上本を読む'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    5,'グルメ情報を調べる'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    5,'趣味の記録をつける'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    5,'趣味仲間と交流する'
);



INSERT INTO mission (theme_id,mission_name) VALUES(
    3,'腹筋を10回以上する '
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    3,'背筋を10回以上する '
);
INSERT INTO mission (theme_id,mission_name) VALUES(
    3,'腕立て伏せを10回以上する '
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    3,'スクワットを10回以上する '
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    3,'10分以上散歩する'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    3,'ラジオ体操をする'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    3,'ストレッチをする'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    3,'ジョギングする'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    3,'階段を使う'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    3,'肩回しをする'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    3,'座る姿勢や立つ姿勢を意識する'
);



SELECT * FROM mission;

/*タイプテーブル*/

CREATE TABLE type(
    type_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    type_name VARCHAR(20)
);
INSERT INTO type (type_name) VALUES(
    '朝型×晴れ型'
);
INSERT INTO type (type_name) VALUES(
    '朝型×曇り型'
);
INSERT INTO type (type_name) VALUES(
    '夜型×晴れ型'
);
INSERT INTO type (type_name) VALUES(
    '夜型×曇り型'
);

SELECT * FROM type;

/*キャラクターテーブル*/
/*変更点！characterテーブルからcharaテーブルに変更（予約語でテーブルを作成できなかったため。）*/

CREATE TABLE chara(
    chara_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    image VARCHAR(255),
    speak VARCHAR(30),
    type_id INT,
FOREIGN KEY (type_id) REFERENCES type(type_id)

);
INSERT INTO chara (image,speak,type_id) VALUES(
    '犬','ワン',1
);
INSERT INTO chara (image,speak,type_id) VALUES(
    '犬','ワン',2
);

INSERT INTO chara (image,speak,type_id) VALUES(
    '犬','ワン',3
);

INSERT INTO chara (image,speak,type_id) VALUES(
    '犬','ワン',4
);
INSERT INTO chara (image,speak,type_id) VALUES(
    '猫','ニャー',1
);
INSERT INTO chara (image,speak,type_id) VALUES(
    '猫','ニャー',2
);
INSERT INTO chara (image,speak,type_id) VALUES(
    '猫','ニャー',3
);
INSERT INTO chara (image,speak,type_id) VALUES(
    '猫','ニャー',4
);
SELECT * FROM chara;

/*ユーザテーブル*/
CREATE TABLE user (
    user_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    login_id VARCHAR(50) NOT NULL,
    pw VARCHAR(50) NOT NULL CHECK (CHAR_LENGTH(pw)>=4),
    chara_id INT DEFAULT 1,
    user_nickname VARCHAR(20) DEFAULT 'あなた',
    chara_nickname VARCHAR(20) DEFAULT '僕',
FOREIGN KEY (chara_id) REFERENCES chara(chara_id),
UNIQUE (login_id)
);

INSERT INTO user (login_id,pw,chara_id) VALUES(
    'id',
    'yokoyamadaiki',
    1
);

INSERT INTO user (login_id,pw,chara_id) VALUES(
    'id2',
    'PW22',
    2
);

INSERT INTO user (login_id,pw,chara_id,user_nickname,chara_nickname) VALUES(
    'id3',
    'PW333',
    2,
    '相棒',
    'ニャンちゅう'
);

INSERT INTO user (login_id,pw,chara_id,user_nickname,chara_nickname) VALUES(
    'id4',
    'PW444',
    1,
    'ご主人',
    'ハチ公'
);

INSERT INTO user (login_id,pw) VALUES(
    'id5',
    'dendenmushi'
);


SELECT * FROM user;


/*デイリーテーブル、
日付をデフォルトで今日の日付を出す方法がわからない。*/

CREATE TABLE daily_mission(
    daily_mission_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    mission_id INT NOT NULL,
    daily VARCHAR(100),
    complete boolean DEFAULT FALSE,
FOREIGN KEY (user_id) REFERENCES user(user_id),
FOREIGN KEY (mission_id) REFERENCES mission(mission_id)

);
INSERT INTO daily_mission (user_id,mission_id,daily) VALUES(
    1,1,'2026-06-11'
);

INSERT INTO daily_mission (user_id,mission_id,daily,complete) VALUES(
    1,2,'2026-06-11',true
);

SELECT * FROM daily_mission;

