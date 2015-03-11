CREATE TABLE `journal_entry` (
  `id` VARCHAR(100) NOT NULL,
  `month` INT NOT NULL,
  `day` INT NOT NULL,
  `year` INT NOT NULL,
  `category` VARCHAR(100) NOT NULL,
  `value` FLOAT NOT NULL,
  `type` VARCHAR(10) NOT NULL,
  `type_modifier` TINYINT(1) NOT NULL,
  `created_by` VARCHAR(100) NOT NULL,
  `created_date` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`));