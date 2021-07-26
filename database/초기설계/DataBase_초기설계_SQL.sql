DROP TABLE IF EXISTS `individual`;

CREATE TABLE `individual` (
	`ind_index`	INT	NOT NULL AUTO_INCREMENT,
	`ind_id`	VARCHAR(20)	NULL,
	`ind_password`	VARCHAR(60)	NULL,
	`ind_name`	VARCHAR(20)	NULL,
	`ind_birth`	VARCHAR(8)	NULL,
	`ind_gender`	CHAR(1)	NULL,
	`ind_phone`	VARCHAR(11)	NULL,
	`ind_email`	VARCHAR(60)	NULL,
    PRIMARY KEY(ind_index)
);

DROP TABLE IF EXISTS `enterprise`;

CREATE TABLE `enterprise` (
	`ent_index`	INT	NOT NULL AUTO_INCREMENT,
	`ent_id`	VARCHAR(20)	NULL,
	`ent_password`	VARCHAR(60)	NULL,
	`ent_name`	VARCHAR(20)	NULL,
	`ent_contact`	VARCHAR(11)	NULL,
	`ent_email`	VARCHAR(11)	NULL,
    PRIMARY KEY(ent_index)
);

DROP TABLE IF EXISTS `ind_profile`;

CREATE TABLE `ind_profile` (
	`ind_index`	INT	NOT NULL,
	`ind_resume`	VARCHAR(200)	NULL,
	`ind_video`	VARCHAR(200)	NULL,
	`ind_photo`	VARCHAR(200)	NULL,
	`ind_switch`	BOOLEAN	NULL,
	`ind_introduce`	VARCHAR(200)	NULL
);

DROP TABLE IF EXISTS `ent_profile`;

CREATE TABLE `ent_profile` (
	`ent_index`	INT	NOT NULL,
	`ent_image`	VARCHAR(255)	NULL,
	`ent_ceo`	VARCHAR(20)	NULL,
	`ent_history`	VARCHAR(255)	NULL,
	`ent_address`	VARCHAR(100)	NULL,
	`ent_website`	VARCHAR(100)	NULL,
	`ent_introduce`	VARCHAR(255)	NULL
);

DROP TABLE IF EXISTS `follow`;

CREATE TABLE `follow` (
	`fol_index`	INT	NOT NULL AUTO_INCREMENT,
	`follower`	INT	NULL,
	`following`	INT	NULL,
	`fol_type`	BOOLEAN	NULL,
    Primary KEY(fol_index)
);

DROP TABLE IF EXISTS `interview`;

CREATE TABLE `interview` (
	`int_index`	INT	NOT NULL AUTO_INCREMENT,
	`ind_index`	INT	NOT NULL,
	`ent_index`	INT	NOT NULL,
	`int_start`	DATETIME	NULL,
	`int_end`	DATETIME	NULL,
	`int_url`	VARCHAR(255)	NULL,
	`int_show`	BOOLEAN	NULL,
    PRIMARY KEY(int_index)
);

DROP TABLE IF EXISTS `recruit`;

CREATE TABLE `recruit` (
	`rec_index`	INT	NOT NULL AUTO_INCREMENT,
	`ent_index`	INT	NOT NULL,
	`rec_detail`	VARCHAR(255)	NULL,
	`rec_qualifications`	VARCHAR(255)	NULL,
	`rec_preferention`	VARCHAR(255)	NULL,
	`rec_starttime`	DATETIME	NULL,
	`rec_endtime`	DATETIME	NULL,
	`rec_requiredDoc`	VARCHAR(255)	NULL,
	`rec_deadline`	CHAR(1)	NULL,
    PRIMARY KEY(rec_index)
);

DROP TABLE IF EXISTS `suggestion`;

CREATE TABLE `suggestion` (
	`sug_index`	int	NOT NULL AUTO_INCREMENT,
	`sug_send`	DATETIME	NULL,
	`sug_timeone`	DATETIME	NULL,
	`sug_timetwo`	DATETIME	NULL,
	`sug_timethree`	DATETIME	NULL,
	`ind_index`	INT	NOT NULL,
	`ent_index`	INT	NOT NULL,
	`sug_decision`	DATETIME	NULL,
	`sug_state`	VARCHAR(10)	NULL,
    PRIMARY KEY(sug_index)
);

DROP TABLE IF EXISTS `hashtag`;

CREATE TABLE `hashtag` (
	`tag_index`	INT	NOT NULL AUTO_INCREMENT,
	`taglist_index`	INT	NOT NULL,
	`ind_index`	INT	NOT NULL,
    PRIMARY KEY(tag_index)
);

DROP TABLE IF EXISTS `taglist`;

CREATE TABLE `taglist` (
	`taglist_index`	INT	NOT NULL AUTO_INCREMENT,
	`taglist_name`	VARCHAR(20)	NULL,
    PRIMARY KEY(taglist_index)
);

ALTER TABLE `individual` ADD CONSTRAINT `PK_INDIVIDUAL` PRIMARY KEY (
	`ind_index`
);

ALTER TABLE `enterprise` ADD CONSTRAINT `PK_ENTERPRISE` PRIMARY KEY (
	`ent_index`
);

ALTER TABLE `ind_profile` ADD CONSTRAINT `PK_IND_PROFILE` PRIMARY KEY (
	`ind_index`
);

ALTER TABLE `ent_profile` ADD CONSTRAINT `PK_ENT_PROFILE` PRIMARY KEY (
	`ent_index`
);

ALTER TABLE `follow` ADD CONSTRAINT `PK_FOLLOW` PRIMARY KEY (
	`fol_index`
);

ALTER TABLE `interview` ADD CONSTRAINT `PK_INTERVIEW` PRIMARY KEY (
	`int_index`
);

ALTER TABLE `recruit` ADD CONSTRAINT `PK_RECRUIT` PRIMARY KEY (
	`rec_index`
);

ALTER TABLE `suggestion` ADD CONSTRAINT `PK_SUGGESTION` PRIMARY KEY (
	`sug_index`
);

ALTER TABLE `hashtag` ADD CONSTRAINT `PK_HASHTAG` PRIMARY KEY (
	`tag_index`
);

ALTER TABLE `taglist` ADD CONSTRAINT `PK_TAGLIST` PRIMARY KEY (
	`taglist_index`
);

ALTER TABLE `ind_profile` ADD CONSTRAINT `FK_individual_TO_ind_profile_1` FOREIGN KEY (
	`ind_index`
)
REFERENCES `individual` (
	`ind_index`
);

ALTER TABLE `ent_profile` ADD CONSTRAINT `FK_enterprise_TO_ent_profile_1` FOREIGN KEY (
	`ent_index`
)
REFERENCES `enterprise` (
	`ent_index`
);

ALTER TABLE `interview` ADD CONSTRAINT `FK_individual_TO_interview_1` FOREIGN KEY (
	`ind_index`
)
REFERENCES `individual` (
	`ind_index`
);

ALTER TABLE `interview` ADD CONSTRAINT `FK_enterprise_TO_interview_1` FOREIGN KEY (
	`ent_index`
)
REFERENCES `enterprise` (
	`ent_index`
);

ALTER TABLE `recruit` ADD CONSTRAINT `FK_enterprise_TO_recruit_1` FOREIGN KEY (
	`ent_index`
)
REFERENCES `enterprise` (
	`ent_index`
);

ALTER TABLE `suggestion` ADD CONSTRAINT `FK_individual_TO_suggestion_1` FOREIGN KEY (
	`ind_index`
)
REFERENCES `individual` (
	`ind_index`
);

ALTER TABLE `suggestion` ADD CONSTRAINT `FK_enterprise_TO_suggestion_1` FOREIGN KEY (
	`ent_index`
)
REFERENCES `enterprise` (
	`ent_index`
);

ALTER TABLE `hashtag` ADD CONSTRAINT `FK_taglist_TO_hashtag_1` FOREIGN KEY (
	`taglist_index`
)
REFERENCES `taglist` (
	`taglist_index`
);

ALTER TABLE `hashtag` ADD CONSTRAINT `FK_individual_TO_hashtag_1` FOREIGN KEY (
	`ind_index`
)
REFERENCES `individual` (
	`ind_index`
);

