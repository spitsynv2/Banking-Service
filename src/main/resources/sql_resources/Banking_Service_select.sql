SELECT *
FROM banking_service.customers
LEFT JOIN banking_service.addresses ON customers.Id = addresses.customer_id
LEFT JOIN banking_service.customer_representatives ON customers.Id = customer_representatives.company_id
LEFT JOIN banking_service.accounts ON customers.Id = accounts.customer_id
LEFT JOIN banking_service.transactions ON accounts.Id = transactions.from_account_id OR accounts.Id = transactions.to_account_id
LEFT JOIN banking_service.cards ON accounts.Id = cards.account_id
LEFT JOIN banking_service.loans ON accounts.Id = loans.account_id
LEFT JOIN banking_service.deposits ON accounts.Id = deposits.account_id
LEFT JOIN banking_service.service_requests ON customers.Id = service_requests.customer_id
LEFT JOIN banking_service.appointments ON service_requests.Id = appointments.service_request_id
LEFT JOIN banking_service.audit_logs ON customers.Id = audit_logs.customer_id

USE banking_service;

SELECT customers.Id, customers.first_name, customers.last_name, customers.company_name, accounts.Id AS account_id, accounts.balance
FROM customers
LEFT JOIN accounts ON customers.Id = accounts.customer_id;

SELECT DISTINCT customers.Id, customers.first_name, customers.last_name, customers.company_name, transactions.id
FROM customers
INNER JOIN accounts ON customers.Id = accounts.customer_id
INNER JOIN transactions ON accounts.Id = transactions.from_account_id OR accounts.Id = transactions.to_account_id;

SELECT account_type, AVG(balance) AS average_balance
FROM accounts
GROUP BY account_type;

SELECT accounts.customer_id, COUNT(*) AS transaction_count
FROM transactions
INNER JOIN accounts ON transactions.from_account_id = accounts.Id OR transactions.to_account_id = accounts.Id
GROUP BY accounts.customer_id
HAVING transaction_count > 1;

-- Can have same result ↑↓ (without HAVING) --

SELECT account_id, COUNT(*) AS total_transactions
FROM (
    SELECT from_account_id AS account_id FROM transactions
    UNION ALL
    SELECT to_account_id AS account_id FROM transactions
) AS combined_accounts
GROUP BY account_id;

SELECT customers.Id AS customer_id, SUM(accounts.balance) AS total_balance, COUNT(accounts.Id) as total_accounts
FROM banking_service.customers
INNER JOIN accounts ON customers.Id = accounts.customer_id
GROUP BY customers.Id
HAVING total_balance > 501;

SELECT audit.log_id AS audit_id, audit.customer_id, audit.employee_id,
CASE WHEN customers.customer_type = 'COMPANY' THEN customers.company_name
ELSE CONCAT(customers.first_name, ' ', customers.last_name)
END AS customer_name,
CONCAT(employees.first_name, ' ', employees.last_name) AS employee_name,
audit.action_type,
audit.action_description,
audit.date
FROM audit_logs AS audit
LEFT JOIN customers ON audit.customer_id = customers.id
LEFT JOIN employees ON audit.employee_id = employees.id
ORDER BY audit.date DESC;





