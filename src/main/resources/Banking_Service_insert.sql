USE `banking_service`;

-- Insert data into customers table
INSERT INTO banking_service.customers (customer_type, first_name, last_name, company_name, tax_id, date_of_birth, registration_date, industry, email, phone_number)
VALUES
('individual', 'John', 'Doe', NULL, NULL, '1985-06-15', '2023-01-10', NULL, 'john.doe@gmail.com', '1234567890'),
('individual', 'Jane', 'Smith', NULL, NULL, '1990-04-22', '2023-03-05', NULL, 'jane.smith@gmail.com', '0987654321'),
('company', NULL, NULL, 'TechCorp', 'TC123456', NULL, '2022-09-30', 'Technology', 'contact@techcorp.com', '1122334455'),
('company', NULL, NULL, 'FinanceInc', 'FI987654', NULL, '2021-06-18', 'Finance', 'support@financeinc.com', '5566778899');

-- Insert data into address table
INSERT INTO banking_service.address (customer_id, type, street, city, country, postal_code)
VALUES
(1, 'Residence', 'al. Jana Pawła II', 'Warsaw', 'Poland', '12345'),
(2, 'Residence', '456 Oak Ave', 'Warsaw', 'Poland', '67890'),
(3, 'Сorespodence', '123 Maple St', 'Warsaw', 'Poland', '12345'),
(4, 'Сorespodence', 'Okopowa 58/72', 'Warsaw', 'Poland', '67890');

-- Insert data into customer_representatives table
INSERT INTO banking_service.customer_representatives (company_id, first_name, last_name, position, email, phone_number, is_primary_contact)
VALUES
(3, 'Alice', 'Brown', 'CEO', 'alice.brown@techcorp.com', '1231231234', 1),
(3, 'Bob', 'Williams', 'CTO', 'bob.williams@techcorp.com', '3213214321', 0),
(4, 'Charlie', 'Davis', 'Manager', 'charlie.davis@financeinc.com', '7897897890', 1),
(4, 'Diana', 'Miller', 'Accountant', 'diana.miller@financeinc.com', '9879879870', 0);

-- Insert data into branches table
INSERT INTO banking_service.branches (branch_name, location, phone_number, open_date)
VALUES
('Central Branch', '123 Main St, Warsaw', '1230001234', '2010-05-15'),
('West Branch', '456 West St, Warsaw', '4560004567', '2012-07-20'),
('East Branch', '789 East Ave, Warsaw', '7890007891', '2015-09-10'),
('South Branch', '321 South Blvd, Warsaw', '3210003212', '2018-11-25');

-- Insert data into employees table
INSERT INTO banking_service.employees (branch_id, first_name, last_name, email, phone_number, job_title, hire_date)
VALUES
(1, 'Emma', 'Johnson', 'emma.johnson@bank.com', '5551112222', 'Manager', '2010-06-01'),
(2, 'Liam', 'Anderson', 'liam.anderson@bank.com', '5553334444', 'Loan Officer', '2012-08-01'),
(3, 'Olivia', 'Martinez', 'olivia.martinez@bank.com', '5555556666', 'Teller', '2015-10-01'),
(4, 'Noah', 'Wilson', 'noah.wilson@bank.com', '5557778888', 'Financial Advisor', '2018-12-01');

-- Insert data into employee_roles table
INSERT INTO banking_service.employee_roles (employee_id, role_name, responsibilities)
VALUES
(1, 'manager', 'Oversees branch operations'),
(2, 'loan officer', 'Approves and manages loans'),
(3, 'teller', 'Handles customer transactions'),
(4, 'financial advisor', 'Provides financial consultation');

-- Insert data into accounts table
INSERT INTO banking_service.accounts (customer_id, account_type, balance, currency, opened_date, status)
VALUES
(1, 'savings', 1000.00, 'EUR', '2023-01-15', 'active'),
(2, 'checking', 500.00, 'EUR', '2023-03-10', 'active'),
(3, 'business', 20000.00, 'EUR', '2022-10-01', 'active'),
(4, 'business', 50000.00, 'EUR', '2021-07-01', 'active');

-- Insert data into transactions table
INSERT INTO banking_service.transactions (from_account_id, to_account_id, transaction_type, amount, transaction_date, description, status)
VALUES
(1, 2, 'transfer', 50.00, '2025-01-15', 'present', 'completed'),
(1, 4, 'card payment', 150.00, '2025-01-15', NULL, 'pending');

-- Insert data into cards table
INSERT INTO banking_service.cards (account_id, card_number, card_type, expiry_date, cvv, is_active)
VALUES
(1, '1234567890123456', 'debit', '2026-01-31', '123', TRUE),
(2, '2345678901234567', 'credit', '2027-05-31', '321', TRUE);

-- Insert data into deposits table
INSERT INTO `banking_service`.`deposits` (`account_id`, `deposit_type`, `amount`, `interest_rate`, `term_months`, `start_date`, `maturity_date`, `status`)
VALUES
(1, 'fixed', 5000.00, 2.0, 12, '2025-01-05', '2026-01-05', 'active'),
(2, 'recurring', 1000.00, 1.5, 24, '2024-12-10', '2026-12-10', 'active');

-- Insert data into loans table
INSERT INTO `banking_service`.`loans` (`account_id`, `loan_type`, `amount`, `interest_rate`, `term_months`, `start_date`, `status`, `payment_date`)
VALUES
(3, 'business', 50000.00, 4.75, 60, '2024-10-15', 'closed', '2025-01-15'),
(4, 'mortgage', 150000.00, 3.25, 240, '2023-05-20', 'active', '2025-03-20');

-- Insert data into service_requests table
INSERT INTO `banking_service`.`service_requests` (`customer_id`, `employee_id`, `request_type`, `status`, `created_at`, `resolved_at`, `notes`)
VALUES
(1, 1, 'card replacement', 'open', '2025-01-20 10:00:00', NULL, 'Customer requested a card replacement due to damage'),
(2, 2, 'loan inquiry', 'resolved', '2025-01-18 09:30:00', '2025-01-19 15:00:00', 'Customer inquired about loan options for a home purchase'),
(3, 1, 'dispute', 'in progress', '2025-01-22 11:00:00', NULL, 'Customer disputes a transaction made on their account');

-- Insert data into appointments table
INSERT INTO `banking_service`.`appointments` (`service_request_id`, `appointment_date`, `status`, `notes`)
VALUES
(1, '2025-01-25 14:00:00', 'scheduled', 'Appointment to process card replacement request'),
(2, '2025-01-21 16:30:00', 'completed', 'Customer discussed loan options and received details on mortgage rates'),
(3, '2025-01-24 11:30:00', 'canceled', 'Customer canceled the appointment for dispute resolution');

-- Insert data into audit_logs table
INSERT INTO `banking_service`.`audit_logs` (`customer_id`, `employee_id`, `action_type`, `action_description`, `date`)
VALUES
(1, NULL, 'login', 'Customer logged in to their account', '2025-01-20 08:45:00'),
(1, 1, 'transaction', 'Employee processed a withdrawal for customer 1', '2025-01-20 10:15:00'),
(2, 2, 'loan approval', 'Employee approved a loan for customer 2', '2025-01-19 17:00:00');