<?php
require_once 'connection.php';

$response = array();
$names = array();
$messages = array();
$dates = array();
$times = array();

$sql ="SELECT CONCAT(u.firstName, " ", u.lastName) AS name, u.email, m.messageContent, m.messageSentTime, m.messageSentDate FROM user AS u, userMessage AS um, message AS m WHERE um.messageID=m.messageID AND ((um.userSent=u.userID AND um.userRecieve=1) OR (um.userRecieve=u.userID AND um.userSent=1)) GROUP BY u.email ORDER BY m.messageSentDate DESC, m.messageSentTime DESC;";
$result = mysqli_query($conn, $sql);

if (mysqli_num_rows($result) > 0) {

  while($row = mysqli_fetch_assoc($result)){
    array_push($borrower_lender, $row["lenderBorrower"]);
    array_push($openLoanID, $row["loanID"]);
    array_push($amount, $row["loanAmount"]);
    array_push($interestRates, $row["interestRate"]);
    array_push($paymentTypes, $row["paymentSchedule"]);
    array_push($startDates, $row["loanDateStart"]);
    array_push($numPayments, $row["numberPayments"]);
  }

  $response["borrowerLender"] = $borrower_lender;
  $response["openLoanID"] = $openLoanID;
  $response["amount"] = $amount;
  $response["interestRate"] = $interestRates;
  $response["paymentType"] = $paymentTypes;
  $response["startDate"] = $startDates;
  $response["numPayments"] = $numPayments;

  $response["error"] = false;
  $response["message"] = "Successfully retrieved all currently open Loans.";
}
else{
  $response['error']=true;
  $response['message']='There are currently No Open Loans. Create an Open Loan.';
}
echo json_encode($response);
?>
