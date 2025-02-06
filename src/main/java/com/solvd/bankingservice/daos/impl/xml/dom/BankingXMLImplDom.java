package com.solvd.bankingservice.daos.impl.xml.dom;

import com.solvd.bankingservice.daos.impl.xml.wrappers.BankingServiceWrapper;
import com.solvd.bankingservice.models.Appointment;
import com.solvd.bankingservice.models.account.Card;
import com.solvd.bankingservice.models.account.Deposit;
import com.solvd.bankingservice.models.account.Loan;
import com.solvd.bankingservice.models.account.Transaction;
import com.solvd.bankingservice.models.account.enums.CardType;
import com.solvd.bankingservice.models.account.enums.deposit_enums.DepositStatus;
import com.solvd.bankingservice.models.account.enums.deposit_enums.DepositType;
import com.solvd.bankingservice.models.account.enums.loan_enums.LoanStatus;
import com.solvd.bankingservice.models.account.enums.loan_enums.LoanType;
import com.solvd.bankingservice.models.account.enums.transaction_enums.TransactionStatus;
import com.solvd.bankingservice.models.account.enums.transaction_enums.TransactionType;
import com.solvd.bankingservice.models.enums.AppointmentStatus;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-06
 */
public class BankingXMLImplDom {

    private static final Dotenv dotenv = Dotenv.configure().directory("src/main/resources/sql").filename("database_config.env").load();
    private static final Logger log = LogManager.getLogger(BankingXMLImplDom.class);
    private static final File READ_FROM_FILE = new File(dotenv.get("XM_FILE_LOCATION"));
    private static final String SCHEMA_FILE_LOCATION = dotenv.get("SCHEMA_FILE_LOCATION");

    public BankingServiceWrapper readAllFromXmlViaDom() {

        BankingServiceWrapper wrapper = new BankingServiceWrapper();

        List<Transaction> transactions = new ArrayList<>();
        List<Loan> loans = new ArrayList<>();
        List<Deposit> deposits = new ArrayList<>();
        List<Card> cards = new ArrayList<>();
        List<Appointment> appointments = new ArrayList<>();

        try {
            File xsdFile = new File(SCHEMA_FILE_LOCATION);
            if (!verifyXmlBySchema(xsdFile)) {
                log.error("XML validation failed, aborting parsing.");
                return null;
            }else {
                log.info("XML validation passed, parsing xml file.");
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(READ_FROM_FILE);
            document.getDocumentElement().normalize();

            Element root = document.getDocumentElement();

            NodeList transactionNodes = root.getElementsByTagName("transaction");
            for (int i = 0; i < transactionNodes.getLength(); i++) {
                Node node = transactionNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    Transaction transaction = new Transaction();
                    transaction.setId(Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent()));
                    transaction.setFromAccountId(Long.parseLong(element.getElementsByTagName("fromAccountId").item(0).getTextContent()));
                    transaction.setToAccountId(Long.parseLong(element.getElementsByTagName("toAccountId").item(0).getTextContent()));
                    transaction.setTransactionType(TransactionType.valueOf(element.getElementsByTagName("transactionType").item(0).getTextContent()));
                    transaction.setAmount(Double.parseDouble(element.getElementsByTagName("amount").item(0).getTextContent()));
                    transaction.setTransactionDate(LocalDateTime.parse(element.getElementsByTagName("transactionDate").item(0).getTextContent()));
                    transaction.setDescription(element.getElementsByTagName("description").item(0).getTextContent());
                    transaction.setTransactionStatus(TransactionStatus.valueOf(element.getElementsByTagName("transactionStatus").item(0).getTextContent()));

                    transactions.add(transaction);
                }
            }

            NodeList loanNodes = root.getElementsByTagName("loan");
            for (int i = 0; i < loanNodes.getLength(); i++) {
                Node node = loanNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    Loan loan = new Loan();
                    loan.setId(Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent()));
                    loan.setLoanType(LoanType.valueOf(element.getElementsByTagName("loanType").item(0).getTextContent()));
                    loan.setAmount(Double.parseDouble(element.getElementsByTagName("amount").item(0).getTextContent()));
                    loan.setInterestRate(Double.parseDouble(element.getElementsByTagName("interestRate").item(0).getTextContent()));
                    loan.setTermMonths(Integer.parseInt(element.getElementsByTagName("termMonths").item(0).getTextContent()));
                    loan.setStartDate(LocalDate.parse(element.getElementsByTagName("startDate").item(0).getTextContent()));
                    loan.setPaymentDate(LocalDate.parse(element.getElementsByTagName("paymentDate").item(0).getTextContent()));
                    loan.setLoanStatus(LoanStatus.valueOf(element.getElementsByTagName("loanStatus").item(0).getTextContent()));

                    loans.add(loan);
                }
            }

            NodeList depositNodes = root.getElementsByTagName("deposit");
            for (int i = 0; i < depositNodes.getLength(); i++) {
                Node node = depositNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    Deposit deposit = new Deposit();
                    deposit.setId(Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent()));
                    deposit.setDepositType(DepositType.valueOf(element.getElementsByTagName("depositType").item(0).getTextContent()));
                    deposit.setAmount(Double.parseDouble(element.getElementsByTagName("amount").item(0).getTextContent()));
                    deposit.setInterestRate(Double.parseDouble(element.getElementsByTagName("interestRate").item(0).getTextContent()));
                    deposit.setTermMonths(Integer.parseInt(element.getElementsByTagName("termMonths").item(0).getTextContent()));
                    deposit.setStartDate(LocalDate.parse(element.getElementsByTagName("startDate").item(0).getTextContent()));
                    deposit.setMaturityDate(LocalDate.parse(element.getElementsByTagName("maturityDate").item(0).getTextContent()));
                    deposit.setDepositStatus(DepositStatus.valueOf(element.getElementsByTagName("depositStatus").item(0).getTextContent()));

                    deposits.add(deposit);
                }
            }

            NodeList cardNodes = root.getElementsByTagName("card");
            for (int i = 0; i < cardNodes.getLength(); i++) {
                Node node = cardNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    Card card = new Card();
                    card.setId(Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent()));
                    card.setCardNumber(element.getElementsByTagName("cardNumber").item(0).getTextContent());
                    card.setCardType(CardType.valueOf(element.getElementsByTagName("cardType").item(0).getTextContent()));
                    card.setExpiryDate(LocalDate.parse(element.getElementsByTagName("expiryDate").item(0).getTextContent()));
                    card.setCvv(element.getElementsByTagName("cvv").item(0).getTextContent());
                    card.setActive(Boolean.parseBoolean(element.getElementsByTagName("isActive").item(0).getTextContent()));

                    cards.add(card);
                }
            }

            NodeList appointmentNodes = root.getElementsByTagName("appointment");
            for (int i = 0; i < appointmentNodes.getLength(); i++) {
                Node node = appointmentNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    Appointment appointment = new Appointment();
                    appointment.setId(Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent()));
                    appointment.setAppointmentDate(LocalDateTime.parse(element.getElementsByTagName("appointmentDate").item(0).getTextContent()));
                    appointment.setAppointmentStatus(AppointmentStatus.valueOf(element.getElementsByTagName("appointmentStatus").item(0).getTextContent()));
                    appointment.setNotes(element.getElementsByTagName("notes").item(0).getTextContent());

                    appointments.add(appointment);
                }
            }

            wrapper.setTransactions(transactions);
            wrapper.setLoans(loans);
            wrapper.setDeposits(deposits);
            wrapper.setCards(cards);
            wrapper.setAppointments(appointments);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            log.error("Error parsing XML", e);
        }

        return wrapper;
    }

    public boolean verifyXmlBySchema(File xsdFile) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdFile);

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(BankingXMLImplDom.READ_FROM_FILE));
            return true;
        } catch (SAXException | IOException e) {
            log.error("XML validation failed: {}", e.getMessage(), e);
            return false;
        }
    }
}
