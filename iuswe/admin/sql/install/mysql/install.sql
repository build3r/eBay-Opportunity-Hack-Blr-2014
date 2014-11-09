CREATE TABLE IF NOT EXISTS `#__vidiyal_donors` (
  `vidiyal_donor_id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(35) NOT NULL COMMENT 'Donors name.',
  `Amount` int(11) NOT NULL DEFAULT 0 COMMENT 'Paied Amount.',
  `Currency` varchar(10) NOT NULL DEFAULT 'INR' COMMENT 'Currency.',
  `EMail` varchar(30) COMMENT 'Donors E-Mail ID.',
  `Note` varchar(250) COMMENT 'Doners Notes.',
  `DateOfBirth` datetime  COMMENT 'Date of Birth.',
  `DateOfPayment` datetime  COMMENT 'Date of Payment.',
  `enabled` tinyint(3) NOT NULL DEFAULT '1',
  `token` char(32) DEFAULT NULL,
  `created_on` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `created_by` bigint(20) NOT NULL DEFAULT '0',
  `modified_on` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `modified_by` bigint(20) NOT NULL DEFAULT '0',
  `locked_on` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `locked_by` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`vidiyal_donor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

 

CREATE TABLE IF NOT EXISTS `#__vidiyal_receivers` (
  `vidiyal_receiver_id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(35) NOT NULL COMMENT 'Receiver name.',
  `InstituteName` varchar(35) NOT NULL COMMENT 'Institute Name.',
  `Amount` int(11) NOT NULL DEFAULT 0 COMMENT 'Paied Amount.',
  `Currency` varchar(10) NOT NULL DEFAULT 'INR' COMMENT 'Currency.',
  `Course` varchar(30) COMMENT 'Course details.',
  `Note` varchar(250) COMMENT 'Doners Notes.',
  `Fees` varchar(30) COMMENT 'Fee details.',
  `DateOfIssue` datetime  COMMENT 'Date of Issue.',  
  `Longitude` varchar(30) COMMENT 'Longitude details.',
  `Latitude` varchar(30) COMMENT 'Latitude details.',
  `enabled` tinyint(3) NOT NULL DEFAULT '1',
  `token` char(32) DEFAULT NULL,
  `created_on` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `created_by` bigint(20) NOT NULL DEFAULT '0',
  `modified_on` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `modified_by` bigint(20) NOT NULL DEFAULT '0',
  `locked_on` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `locked_by` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`vidiyal_receiver_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
