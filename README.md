# Unusual Spends

![Jacoco Test Coverage Report](TestCoveregeSnapshot.png)
You work at a credit card company and as a value-add they want to start providing alerts to users when their spending in any particular category is higher than usual.

- Compare the total amount paid for the current month, grouped by category with the previous month
- Filter down to the categories for which the user spent at least 50% more this month than last month
- Compose an e-mail message to the user that lists the categories for which spending was unusually high

**Sample Email**

Unusual spending of ₹1076 detected!

Hello Baburao!

We have detected unusually high spending on your card in these categories:

- You spent ₹148 on groceries
- You spent ₹928 on travel

Thanks,

The Credit Card Company

**Extensions**

- Change in Email format
- Change in threshold percentage
- Change in usual spending amount calculation logic
- Adding usual spending amount in email

## OOMD

### Assumptions

- There will be no CreditCard without customer
- There will be no transaction without CreditCard
- Unusual Spend is the extra amount spend than the spend in previous month

### Flow

- First need to have the Customer
- For that customer we can create the credit card
- By using credit card you can spend on different categories
- By analyzing transaction data note down the customers with unusual spend on different categories
- Then send email to them regarding unusual spend as a service

### Package : com.amaap.unusualspends.domain.model.entity

### CreditCard

#### Attributes

- `id`: `long` - The unique identifier of the credit card.
- `customer`: `Customer` - The customer associated with the credit card.

#### Behaviors

- `CreditCard(long id, Customer customer)`: Constructor that initializes a credit card with the given ID and customer.
- `getId(): long`: Method to retrieve the ID of the credit card.
- `getCustomer(): Customer`: Method to retrieve the customer associated with the credit card.
- `create(long cardId, Customer customer): CreditCard`: Static factory method to create a new credit card instance, throwing an `InvalidCreditCardIdException` if the provided ID is not valid.
- `equals(Object o): boolean`: Method to compare if two credit card objects are equal based on their IDs.
- `hashCode(): int`: Method to generate the hash code of the credit card object based on its ID.

### Customer

#### Attributes

- `id`: `int` - The unique identifier of the customer.
- `name`: `String` - The name of the customer.
- `email`: `String` - The email address of the customer.

#### Behaviors

- `create(int customerId, String customerName, String email): Customer`: Static factory method to create a new customer instance, throwing exceptions (`InvalidCustomerNameException`, `InvalidCustomerIdException`, `InvalidCustomerEmailException`) if the provided name, ID, or email is not valid.
- `getId(): int`: Method to retrieve the ID of the customer.
- `getName(): String`: Method to retrieve the name of the customer.
- `getEmail(): String`: Method to retrieve the email address of the customer.
- `equals(Object o): boolean`: Method to compare if two customer objects are equal based on their ID, name, and email.
- `hashCode(): int`: Method to generate the hash code of the customer object based on its ID, name, and email.

### Transaction

#### Attributes

- `id`: `long` - The unique identifier of the transaction.
- `cardId`: `long` - The ID of the credit card associated with the transaction.
- `category`: `Category` - The category of the transaction.
- `amountSpend`: `double` - The amount spent in the transaction.
- `transactionDate`: `LocalDate` - The date of the transaction.

#### Behaviors

- `Transaction(long id, long cardId, Category category, double amountSpend, LocalDate transactionDate)`: Constructor that initializes a transaction with the given ID, card ID, category, amount spent, and transaction date.
- `create(long id, long cardId, Category category, double amountSpend, LocalDate transactionDate): Transaction`: Static factory method to create a new transaction instance, throwing exceptions (`InvalidTransactionCategory`, `InvalidTransactionAmountException`) if the provided category or amount spent is not valid.
- `getId(): long`: Method to retrieve the ID of the transaction.
- `getCardId(): long`: Method to retrieve the ID of the credit card associated with the transaction.
- `getCategory(): Category`: Method to retrieve the category of the transaction.
- `getAmountSpend(): double`: Method to retrieve the amount spent in the transaction.
- `getTransactionDate(): LocalDate`: Method to retrieve the date of the transaction.
- `equals(Object o): boolean`: Method to compare if two transaction objects are equal based on their IDs, card IDs, amount spent, category, and transaction date.
- `hashCode(): int`: Method to generate the hash code of the transaction object based on its ID, card ID, amount spent, category, and transaction date.

### Package: com.amaap.unusualspends.domain.model.entity.exception

#### InvalidCreditCardIdException

#### InvalidCustomerEmailException

#### InvalidCustomerException

#### InvalidCustomerIdException

#### InvalidTransactionAmountException

#### InvalidTransactionCategoryException

#### com.amaap.unusualspends.domain.model.valueobject

### Category Enum

#### Attributes:

- `SHOPPING`
- `GROCERIES`
- `BOOKS`
- `TRAVEL`

### Package com.amaap.unusualspends.domain.service

### EmailSender

#### Behaviours

- `public void sendEmail(String subject, String body, String email) throws InvalidCustomerEmailException, InvalidEmailBodyException, InvalidEmailSubjectException

### SpendAnalyzer

#### Attributes:

- `Map<Long, List<SpendsDto>>`

#### Behaviors:

- ` Map<Long, List<SpendsDto>> analyzeSpend(List<Transaction> currentMonthTransactions, List<Transaction> previousMonthTransactions, double thresholdPercentage): Map<Long, List<SpendsDto>>`:

### Package : com.amaap.unusualspends.domain.model.entity.validator

#### CreditCardValidator

##### Behaviors:

- `boolean isValidId(long cardId)`

#### CustomerEmailIdValidator

##### Behaviors:

- `boolean isValidEmail(String customerEmail)`

#### CustomerIdValidator

##### Behaviors:

- `boolean isValidId(int customerId)`

#### CustomerNameValidator

##### Behaviors:

- `boolean isValidName(String customerName)`

#### TransactionValidator

##### Behaviors:

- `boolean isValidCategory(Category category)`
- `boolean isValidSpend(Double spend) `

### Package : com.amaap.unusualspends.controller

#### CreditCardCompanyController Class

##### Attributes:

- `creditCardCompanyService`: Instance of `CreditCardCompanyService`.

##### Behaviors:

- `Map<Long, List<SpendsDto>> analyzeSpend(List<Transaction> currentMonthTransactions, List<Transaction> previousMonthTransactions, double thresholdPercentage)`
- `Response sendEmail(Map<Long, List<SpendsDto>> spendRecord)`

#### CreditCardController Class

##### Attributes:

- `creditCardService`: Instance of `CreditCardService`.

##### Behaviors:

- `Response createCreditCardFor(Customer customer)` throws `InvalidCreditCardIdException`:
- `getCreditCardBy(long id): CreditCard`

#### CustomerController Class

##### Attributes:

- `customerService`: Instance of `CustomerService`.

##### Behaviors:

- `Response createCustomer(String customerName, String email)` throws `InvalidCustomerException`, `CustomerAlreadyExistsException`:

- `Customer findCustomerBy(int id)`

#### TransactionController Class

##### Attributes:

- `transactionService`: Instance of `TransactionService`.

##### Behaviors:

- `Response createTransaction(long cardId, Category category, double amountSpend, LocalDate transactionDate) ` throws `InvalidTransactionCategoryException`, `InvalidTransactionAmountException`
- `List<Transaction> getAllTransactions() `
- `List<Transaction> getTransactionsByMonth(Month month)`

### Package : com.amaap.unusualspends.repository

#### CreditCardRepository Interface

##### Behaviors:

- `CreditCard addCreditCard(CreditCard creditCardToAdd)`
- `CreditCard getCreditCardBy(long id): CreditCard`

#### CustomerRepository Interface

##### Behaviors:

- `Customer addCustomer(Customer customer)`
- `Customer findCustomerBy(int customerId)`

#### TransactionRepository Interface

##### Behaviors:

- `Transaction addTransaction(Transaction transactionToAdd)`
- `List<Transaction> getAllTransactions(): `

### Package : com.amaap.unusualspends.repository.impl

#### InMemoryCreditCardRepository Class

##### Behaviors:

- `CreditCard addCreditCard(CreditCard creditCardToAdd)`
- `CreditCard getCreditCardBy(long id)`

#### InMemoryCustomerRepository Class

##### Behaviors:

- `Customer addCustomer(Customer customer)`
- `Customer findCustomerBy(int customerId)`

#### InMemoryTransactionRepository Class

##### Behaviors:

- `Transaction addTransaction(Transaction transaction)`
- `List<Transaction> getAllTransactions()`

### Package : com.amaap.unusualspends.repository.db

#### InMemoryDatabase Interface

##### Behaviors:

- `Customer addCustomer(Customer customer) throws CustomerAlreadyExistsException`
- `CreditCard addCreditCard(CreditCard creditCardToAdd)`
- `Transaction addTransaction(Transaction transactionToAdd)`
- `Customer findCustomerBy(int customerId)`
- `CreditCard getCreditCardBy(long id)`
- `List<Transaction> getAllTransactions()`

### Package : com.amaap.unusualspends.repository.db.impl

#### FakeInMemoryDatabase Class

##### Behaviors:

- `Customer addCustomer(Customer customer) throws CustomerAlreadyExistsException`
- `CreditCard addCreditCard(CreditCard creditCardToAdd)`
- `Transaction addTransaction(Transaction transaction)`
- `Customer findCustomerBy(int customerId)`
- `CreditCard getCreditCardBy(long id)`
- `List<Transaction> getAllTransactions()`

### Package : com.amaap.unusualspends.repository.db.exception

#### CustomerAlreadyExistsException Class

##### Constructors:

- `CustomerAlreadyExistsException(String message)`: Constructs a new CustomerAlreadyExistsException with the specified detail message.
