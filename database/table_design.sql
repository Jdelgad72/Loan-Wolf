CREATE TABLE user (
userID INT(10) AUTO_INCREMENT,
firstName VARCHAR(64) NOT NULL,
lastName VARCHAR(64) NOT NULL,
dateOfBirth DATE NOT NULL,
streetAddress VARCHAR(128) NOT NULL,
zipAddress INT(5) NOT NULL,
stateAddress VARCHAR(32) NOT NULL,
accountPayPal,
accountGoogle,
gender VARCHAR(16) NOT NULL,
email VARCHAR(64) NOT NULL
PRIMARY KEY (userID)
)
ENGINE=InnoDB;

CREATE TABLE userPhone (
userID INT(10) NOT NULL,
phone INT(11) NOT NULL,
FOREIGN KEY userID REFERENCES user(userID)
)
ENGINE=InnoDB;

CREATE TABLE review (
reviewID INT(10) AUTO_INCREMENT,
reviewTime TIME NOT NULL,
reviewDate DATE NOT NULL,
starRating INT(1) NOT NUll,
comment VARCHAR(256),
PRIMARY KEY (ReviewID)
)
ENGINE=InnoDB;

CREATE TABLE message (
messageID INT(10) AUTO_INCREMENT,
messageTime TIME NOT NULL,
messageDate DATE NOT NULL,
messageContent VARCHAR(1024) NOT NULL,
PRIMARY KEY (messageID)
)
ENGINE=InnoDB;

CREATE TABLE notification (
notificationID INT(10) AUTO_INCREMENT,
notificationTime TIME NOT NULL,
notificationDate DATE NOT NULL,
notificationType VARCHAR(128) NOT NULL,
notificationContent VARCHAR(256) NOT NULL,
PRIMARY KEY (notificationID)
)
ENGINE=InnoDB;

CREATE TABLE report (
reportID INT(10) AUTO_INCREMENT,
reportTime TIME NOT NULL,
reportDate DATE NOT NULL,
reportType VARCHAR(128) NOT NULL,
reportAction VARCHAR(128) NOT NULL,
reportReasoning VARCHAR(128) NOT NULL,
reportInformation VARCHAR(1024) NOT NULL,
PRIMARY KEY (reportID)
)
ENGINE=InnoDB;

CREATE TABLE payment (
paymentID INT(10) AUTO_INCREMENT,
paymentTime TIME NOT NULL,
paymentDate DATE NOT NULL,
paymentAmount INT(6) NOT NULL,
paymentStatus VARCHAR(1),
PRIMARY KEY (paymentID)
)
ENGINE=InnoDB;

CREATE TABLE loan (
loanID INT(10) AUTO_INCREMENT,
loanAmount INT(6) NOT NULL,
interestRate DECIMAL(6, 6) NOT NULL,
loanStatus VARCHAR(32) NOT NULL,
loanDateStart DATE NOT NULL,
paymentSchedule VARCHAR(12) NOT NULL,
dateSent DATE NOT NULL,
timeSent TIME NOT NULL,
dateAccepted DATE,
timeAccepted TIME,
senderSignature VARCHAR(128) NOT NULL,
receiverSignature VARCHAR(128) NOT NUll,
groupLoanID INT(10),
PRIMARY KEY (loanID)
)
ENGINE=InnoDB;
