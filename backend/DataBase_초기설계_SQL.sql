DROP TABLE IF EXISTS `individual`;

CREATE TABLE `individual` (
	`ind_index`	INT	NOT NULL,
	`ind_id`	VARCHAR(20)	NULL,
	`ind_password`	VARCHAR(60)	NULL,
	`ind_name`	VARCHAR(20)	NULL,
	`ind_birth`	VARCHAR(8)	NULL,
	`ind_gender`	CHAR(1)	NULL,
	`ind_phone`	VARCHAR(11)	NULL,
	`ind_email`	VARCHAR(60)	NULL
);

DROP TABLE IF EXISTS `enterprise`;

CREATE TABLE `enterprise` (
	`ent_index`	INT	NOT NULL,
	`ent_id`	VARCHAR(20)	NULL,
	`ent_password`	VARCHAR(60)	NULL,
	`ent_name`	VARCHAR(20)	NULL,
	`ent_contact`	VARCHAR(11)	NULL,
	`ent_email`	VARCHAR(11)	NULL
);

DROP TABLE IF EXISTS `ind_profile`;

CREATE TABLE `ind_profile` (
	`ind_index`	INT	NOT NULL,
	`ind_resume`	VARCHAR(200)	NULL,
	`ind_video`	VARCHAR(200)	NULL,
	`ind_photo`	VARCHAR(200)	NULL
);

DROP TABLE IF EXISTS `ent_profile`;

CREATE TABLE `ent_profile` (
	`ent_index`	INT	NOT NULL,
	`ent_image`	VARCHAR(255)	NULL,
	`ent_ceo`	VARCHAR(20)	NULL,
	`ent_history`	VARCHAR(255)	NULL,
	`ent_address`	VARHCHAR(100)	NULL,
	`ent_website`	VARCHAR(100)	NULL,
	`ent_introduce`	VARCHAR(255)	NULL
);

DROP TABLE IF EXISTS `follow`;

CREATE TABLE `follow` (
	`fol_index`	INT	NOT NULL,
	`target`	INT	NULL,
	`ent_index`	INT	NOT NULL,
	`ind_index`	INT	NOT NULL
);

DROP TABLE IF EXISTS `history`;

CREATE TABLE `history` (
	`his_index`	INT	NOT NULL,
	`ind_index`	INT	NOT NULL,
	`ent_index`	INT	NOT NULL,
	`his_start`	DATETIME	NULL,
	`his_end`	DATETIME	NULL,
	`his_url`	VARCHAR(255)	NULL,
	`his_show`	VARCHAR(20)	NULL
);

DROP TABLE IF EXISTS `recruit`;

CREATE TABLE `recruit` (
	`rec_index`	INT	NOT NULL,
	`ent_index`	INT	NOT NULL,
	`rec_detail`	VARCHAR(255)	NULL,
	`rec_qualifications`	VARCHAR(255)	NULL,
	`rec_preferention`	VARCHAR(255)	NULL,
	`rec_starttime`	DATETIME	NULL,
	`rec_endtime`	DATETIME	NULL,
	`rec_requiredDoc`	VARCHAR(255)	NULL,
	`rec_deadline`	CHAR(1)	NULL
);

DROP TABLE IF EXISTS `turning`;

CREATE TABLE `turning` (
	`tur_index`	int	NOT NULL,
	`tur_send`	DATETIME	NULL,
	`tur_one`	DATETIME	NULL,
	`tur_two`	DATETIME	NULL,
	`tur_three`	DATETIME	NULL,
	`ind_index`	INT	NOT NULL,
	`ent_index`	INT	NOT NULL,
	`tur_decision`	DATETIME	NULL
);

DROP TABLE IF EXISTS `document`;

CREATE TABLE `document` (
	`doc_index`	INT	NOT NULL,
	`ind_index`	INT	NOT NULL,
	`rec_index`	INT	NOT NULL,
	`doc_filepath`	VARCHAR(255)	NULL
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

ALTER TABLE `history` ADD CONSTRAINT `PK_HISTORY` PRIMARY KEY (
	`his_index`
);

ALTER TABLE `recruit` ADD CONSTRAINT `PK_RECRUIT` PRIMARY KEY (
	`rec_index`
);

ALTER TABLE `turning` ADD CONSTRAINT `PK_TURNING` PRIMARY KEY (
	`tur_index`
);

ALTER TABLE `document` ADD CONSTRAINT `PK_DOCUMENT` PRIMARY KEY (
	`doc_index`
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

ALTER TABLE `follow` ADD CONSTRAINT `FK_enterprise_TO_follow_1` FOREIGN KEY (
	`ent_index`
)
REFERENCES `enterprise` (
	`ent_index`
);

ALTER TABLE `follow` ADD CONSTRAINT `FK_individual_TO_follow_1` FOREIGN KEY (
	`ind_index`
)
REFERENCES `individual` (
	`ind_index`
);

ALTER TABLE `history` ADD CONSTRAINT `FK_individual_TO_history_1` FOREIGN KEY (
	`ind_index`
)
REFERENCES `individual` (
	`ind_index`
);

ALTER TABLE `history` ADD CONSTRAINT `FK_enterprise_TO_history_1` FOREIGN KEY (
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

ALTER TABLE `turning` ADD CONSTRAINT `FK_individual_TO_turning_1` FOREIGN KEY (
	`ind_index`
)
REFERENCES `individual` (
	`ind_index`
);

ALTER TABLE `turning` ADD CONSTRAINT `FK_enterprise_TO_turning_1` FOREIGN KEY (
	`ent_index`
)
REFERENCES `enterprise` (
	`ent_index`
);

ALTER TABLE `document` ADD CONSTRAINT `FK_individual_TO_document_1` FOREIGN KEY (
	`ind_index`
)
REFERENCES `individual` (
	`ind_index`
);

ALTER TABLE `document` ADD CONSTRAINT `FK_recruit_TO_document_1` FOREIGN KEY (
	`rec_index`
)
REFERENCES `recruit` (
	`rec_index`
);

