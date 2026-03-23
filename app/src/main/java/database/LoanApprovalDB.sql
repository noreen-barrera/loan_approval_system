-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 31, 2025 at 02:21 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `LoanApprovalDB`
--

-- --------------------------------------------------------

--
-- Table structure for table `application_documents`
--

CREATE TABLE `application_documents` (
  `doc_id` int(11) NOT NULL,
  `application_id` int(11) DEFAULT NULL,
  `doc_type` enum('ID','Payslip','Co-maker') DEFAULT NULL,
  `submitted` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `application_documents`
--

INSERT INTO `application_documents` (`doc_id`, `application_id`, `doc_type`, `submitted`) VALUES
(1, 1, 'ID', 1),
(2, 1, 'Payslip', 1),
(3, 1, 'Co-maker', 0),
(4, 2, 'ID', 0),
(5, 2, 'Payslip', 0),
(6, 2, 'Co-maker', 0),
(7, 3, 'ID', 1),
(8, 3, 'Payslip', 1),
(9, 3, 'Co-maker', 1);

-- --------------------------------------------------------

--
-- Stand-in structure for view `dashboard`
-- (See below for the actual view)
--
CREATE TABLE `dashboard` (
`application_id` int(11)
,`full_name` varchar(100)
,`product` varchar(100)
,`amount` decimal(12,2)
,`purpose` varchar(50)
,`status` enum('In Review','Approved','Rejected','Auto-Approved','Auto-Rejected')
,`priority_level` enum('Platinum','Gold','Silver','Red')
);

-- --------------------------------------------------------

--
-- Table structure for table `evaluation_scores`
--

CREATE TABLE `evaluation_scores` (
  `evaluation_id` int(11) NOT NULL,
  `application_id` int(11) DEFAULT NULL,
  `evaluated_by` int(11) DEFAULT NULL,
  `credit_score` int(11) DEFAULT NULL,
  `income_score` int(11) DEFAULT NULL,
  `purpose_score` int(11) DEFAULT NULL,
  `debt_score` int(11) DEFAULT NULL,
  `client_score` int(11) DEFAULT NULL,
  `total_score` decimal(5,2) DEFAULT NULL,
  `evaluation_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `is_evaluated` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `evaluation_scores`
--

INSERT INTO `evaluation_scores` (`evaluation_id`, `application_id`, `evaluated_by`, `credit_score`, `income_score`, `purpose_score`, `debt_score`, `client_score`, `total_score`, `evaluation_date`, `is_evaluated`) VALUES
(1, 1, 2, 20, 18, 12, -5, 15, 75.00, '2025-07-14 08:43:49', 0),
(2, 3, 2, 18, 15, 10, -2, 20, 61.00, '2025-07-14 08:43:49', 1),
(15, 28, NULL, 30, 25, 15, 10, 10, 90.00, '2025-07-30 11:36:43', 0),
(16, 29, NULL, 30, 4, 15, 10, 10, 69.00, '2025-07-30 12:11:19', 1),
(18, 30, NULL, 30, 24, 15, 10, 10, 89.00, '2025-07-31 11:33:06', 0),
(19, 31, NULL, 30, 20, 7, 10, 10, 77.00, '2025-07-31 11:34:17', 1),
(20, 32, NULL, 30, 4, 15, 10, 10, 69.00, '2025-07-31 12:15:50', 0);

-- --------------------------------------------------------

--
-- Table structure for table `loan_applications`
--

CREATE TABLE `loan_applications` (
  `application_id` int(11) NOT NULL,
  `applicant_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `amount` decimal(12,2) DEFAULT NULL,
  `term_months` int(11) DEFAULT NULL,
  `purpose` varchar(50) DEFAULT NULL,
  `status` enum('In Review','Approved','Rejected','Auto-Approved','Auto-Rejected') DEFAULT 'In Review',
  `priority_level` enum('Platinum','Gold','Silver','Red') DEFAULT NULL,
  `reasons_id` int(11) DEFAULT NULL,
  `is_flagged` tinyint(1) DEFAULT 0,
  `flagged_reason` varchar(255) DEFAULT NULL,
  `submission_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `loan_applications`
--

INSERT INTO `loan_applications` (`application_id`, `applicant_id`, `product_id`, `amount`, `term_months`, `purpose`, `status`, `priority_level`, `reasons_id`, `is_flagged`, `flagged_reason`, `submission_date`) VALUES
(1, 1, 1, 20000.00, 12, 'Business Expansion', 'Approved', 'Gold', 1, 0, NULL, '2025-07-14 08:31:46'),
(2, 1, 2, 15000.00, 6, 'Tuition fee / School Fees', 'Auto-Rejected', 'Red', 2, 0, NULL, '2025-07-14 08:31:46'),
(3, 1, 3, 5000.00, 3, 'Emergency Medical', 'Approved', 'Silver', 3, 0, NULL, '2025-07-14 08:31:46'),
(28, 1, 1, 20000.00, 6, 'Business Expansion', 'Auto-Approved', 'Platinum', NULL, 0, NULL, '2025-07-30 11:36:43'),
(29, 1, 2, 10000.00, 6, 'Tuition / School Fees', 'In Review', 'Silver', NULL, 0, NULL, '2025-07-30 12:11:19'),
(30, 1, 2, 30000.00, 6, 'Tuition / School Fees', 'Auto-Approved', 'Platinum', NULL, 0, NULL, '2025-07-31 11:33:06'),
(31, 1, 3, 20000.00, 3, 'Emergency Medical', 'Approved', 'Gold', NULL, 0, NULL, '2025-07-31 11:34:17'),
(32, 1, 2, 11000.00, 6, 'Tuition / School Fees', 'In Review', 'Silver', NULL, 0, NULL, '2025-07-31 12:15:50');

-- --------------------------------------------------------

--
-- Table structure for table `loan_history`
--

CREATE TABLE `loan_history` (
  `loan_id` int(11) NOT NULL,
  `application_id` int(11) DEFAULT NULL,
  `disbursed_date` date DEFAULT NULL,
  `repaid` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `loan_history`
--

INSERT INTO `loan_history` (`loan_id`, `application_id`, `disbursed_date`, `repaid`) VALUES
(1, 3, '2025-06-05', 0);

-- --------------------------------------------------------

--
-- Table structure for table `loan_products`
--

CREATE TABLE `loan_products` (
  `product_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `interest_rate` decimal(5,2) DEFAULT NULL,
  `min_amount` decimal(12,2) DEFAULT NULL,
  `max_amount` decimal(12,2) DEFAULT NULL,
  `min_term` int(11) DEFAULT NULL,
  `max_term` int(11) DEFAULT NULL,
  `allow_vip` tinyint(1) DEFAULT 1,
  `allow_regular` tinyint(1) DEFAULT 1,
  `allow_new` tinyint(1) DEFAULT 1,
  `require_clean_history` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `loan_products`
--

INSERT INTO `loan_products` (`product_id`, `name`, `interest_rate`, `min_amount`, `max_amount`, `min_term`, `max_term`, `allow_vip`, `allow_regular`, `allow_new`, `require_clean_history`) VALUES
(1, 'Business Loan', 5.00, 10000.00, 100000.00, 6, 24, 1, 1, 1, 0),
(2, 'Education Loan', 3.50, 5000.00, 50000.00, 6, 12, 1, 1, 1, 0),
(3, 'Emergency Loan', 2.00, 1000.00, 20000.00, 3, 6, 1, 1, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `rejection_reasons`
--

CREATE TABLE `rejection_reasons` (
  `reason_id` int(11) NOT NULL,
  `application_id` int(11) DEFAULT NULL,
  `reason` enum('Missing Documents','Low Credit Score','Suspicious Information') DEFAULT NULL,
  `notes` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `rules_config`
--

CREATE TABLE `rules_config` (
  `rule_id` int(11) NOT NULL,
  `credit_weight` int(11) DEFAULT 30,
  `income_weight` int(11) DEFAULT 25,
  `purpose_weight` int(11) DEFAULT 15,
  `debt_weight` int(11) DEFAULT 10,
  `client_weight` int(11) DEFAULT 20,
  `auto_approve_threshold` decimal(5,2) DEFAULT 85.00,
  `auto_reject_threshold` decimal(5,2) DEFAULT 60.00,
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rules_config`
--

INSERT INTO `rules_config` (`rule_id`, `credit_weight`, `income_weight`, `purpose_weight`, `debt_weight`, `client_weight`, `auto_approve_threshold`, `auto_reject_threshold`, `updated_at`) VALUES
(1, 30, 25, 15, 10, 20, 85.00, 60.00, '2025-07-14 08:43:22');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('applicant','officer','admin') NOT NULL,
  `client_type` enum('VIP','Regular','New') DEFAULT 'New',
  `date_registered` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `full_name`, `email`, `password`, `role`, `client_type`, `date_registered`) VALUES
(1, 'John Doe', 'john@gmail.com', 'password123', 'applicant', 'Regular', '2025-07-14 08:12:25'),
(2, 'Anna Officer', 'officer@gmail.com', 'officerpass', 'officer', 'Regular', '2025-07-14 08:12:25'),
(3, 'Carla Admin', 'admin@gmail.com', 'adminpass', 'admin', 'VIP', '2025-07-14 08:12:25');

-- --------------------------------------------------------

--
-- Structure for view `dashboard`
--
DROP TABLE IF EXISTS `dashboard`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `loanapprovaldb`.`dashboard`  AS SELECT `la`.`application_id` AS `application_id`, `u`.`full_name` AS `full_name`, `p`.`name` AS `product`, `la`.`amount` AS `amount`, `la`.`purpose` AS `purpose`, `la`.`status` AS `status`, `la`.`priority_level` AS `priority_level` FROM ((`loanapprovaldb`.`loan_applications` `la` join `loanapprovaldb`.`users` `u` on(`u`.`user_id` = `la`.`applicant_id` and `u`.`role` = 'applicant')) join `loanapprovaldb`.`loan_products` `p` on(`p`.`product_id` = `la`.`product_id`)) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `application_documents`
--
ALTER TABLE `application_documents`
  ADD PRIMARY KEY (`doc_id`),
  ADD KEY `application_id` (`application_id`);

--
-- Indexes for table `evaluation_scores`
--
ALTER TABLE `evaluation_scores`
  ADD PRIMARY KEY (`evaluation_id`),
  ADD KEY `evaluated_by` (`evaluated_by`),
  ADD KEY `evaluation_scores_ibfk_1` (`application_id`);

--
-- Indexes for table `loan_applications`
--
ALTER TABLE `loan_applications`
  ADD PRIMARY KEY (`application_id`),
  ADD KEY `applicant_id` (`applicant_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `loan_history`
--
ALTER TABLE `loan_history`
  ADD PRIMARY KEY (`loan_id`),
  ADD KEY `application_id` (`application_id`);

--
-- Indexes for table `loan_products`
--
ALTER TABLE `loan_products`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `rejection_reasons`
--
ALTER TABLE `rejection_reasons`
  ADD PRIMARY KEY (`reason_id`),
  ADD KEY `application_id` (`application_id`);

--
-- Indexes for table `rules_config`
--
ALTER TABLE `rules_config`
  ADD PRIMARY KEY (`rule_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `application_documents`
--
ALTER TABLE `application_documents`
  MODIFY `doc_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `evaluation_scores`
--
ALTER TABLE `evaluation_scores`
  MODIFY `evaluation_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `loan_applications`
--
ALTER TABLE `loan_applications`
  MODIFY `application_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `loan_history`
--
ALTER TABLE `loan_history`
  MODIFY `loan_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `loan_products`
--
ALTER TABLE `loan_products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `rejection_reasons`
--
ALTER TABLE `rejection_reasons`
  MODIFY `reason_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `rules_config`
--
ALTER TABLE `rules_config`
  MODIFY `rule_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `application_documents`
--
ALTER TABLE `application_documents`
  ADD CONSTRAINT `application_documents_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `loan_applications` (`application_id`);

--
-- Constraints for table `evaluation_scores`
--
ALTER TABLE `evaluation_scores`
  ADD CONSTRAINT `evaluation_scores_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `loan_applications` (`application_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `evaluation_scores_ibfk_2` FOREIGN KEY (`evaluated_by`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `loan_applications`
--
ALTER TABLE `loan_applications`
  ADD CONSTRAINT `loan_applications_ibfk_1` FOREIGN KEY (`applicant_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `loan_applications_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `loan_products` (`product_id`);

--
-- Constraints for table `loan_history`
--
ALTER TABLE `loan_history`
  ADD CONSTRAINT `loan_history_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `loan_applications` (`application_id`);

--
-- Constraints for table `rejection_reasons`
--
ALTER TABLE `rejection_reasons`
  ADD CONSTRAINT `rejection_reasons_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `loan_applications` (`application_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
