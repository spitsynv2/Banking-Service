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