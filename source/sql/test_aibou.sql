/*変更点
データを増やしました
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
    2,'資格勉強を30分する。'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    2,'金融に関するニュース記事を調べる'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    4,'最安値を調べてから買い物をする '
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    4,'貯金箱に５００円入れる'
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    5,'趣味の時間を10分とる '
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    3,'腹筋を10回する '
);

INSERT INTO mission (theme_id,mission_name) VALUES(
    3,'背筋を10回する '
);
INSERT INTO mission (theme_id,mission_name) VALUES(
    3,'腕立て伏せを10回する '
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
    '猫','ニャー',2
);
SELECT * FROM chara;

/*ユーザテーブル*/
CREATE TABLE user (
    user_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    login_id VARCHAR(50) NOT NULL,
    PW VARCHAR(50) NOT NULL CHECK (CHAR_LENGTH(pw)>=4),
    chara_id INT DEFAULT 1,
    user_nickname VARCHAR(20) DEFAULT 'あなた',
    chara_nickname VARCHAR(20) DEFAULT '僕',
FOREIGN KEY (chara_id) REFERENCES chara(chara_id)
);

INSERT INTO user (login_id,PW,chara_id) VALUES(
    'id',
    'yokoyamadaiki',
    1
);

INSERT INTO user (login_id,PW,chara_id) VALUES(
    'id2',
    'PW22',
    2
);

INSERT INTO user (login_id,PW,chara_id,user_nickname,chara_nickname) VALUES(
    'id3',
    'PW333',
    2,
    '相棒',
    'ニャンちゅう'
);

INSERT INTO user (login_id,PW,chara_id,user_nickname,chara_nickname) VALUES(
    'id4',
    'PW444',
    1,
    'ご主人',
    'ハチ公'
);

INSERT INTO user (login_id,PW) VALUES(
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
