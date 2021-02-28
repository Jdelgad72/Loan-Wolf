<?php
require_once 'connection.php';
date_default_timezone_set("America/Indianapolis");

$response = array();
if(isset($_POST['searchEmail'], $_POST['requesterID'])){
  $searchEmail = $_POST['searchEmail'];
  $requesterID = $_POST['requesterID'];

  $stmt = $conn->prepare("select userID, dateJoined, SUM(starRating)/COUNT(starRating) AS averageRating From user, review WHERE userID = userReviewing AND email=? GROUP BY userID;");
  $stmt->bind_param("s",$searchEmail);
  $stmt->execute();
  $stmt->bind_result($id, $dateJoined, $averageRating);
  $stmt->fetch(); 
  $stmt->close();
  
  $response['dateJoined'] = date("F Y", strtotime($dateJoined));
  $response["rating"] = $averageRating;

  $sql = "select * FROM user, userLoan WHERE (userSender = $requesterID OR userReciever = $requesterID) AND email = '$searchEmail';";
  $result = mysqli_query($conn, $sql);
  if (mysqli_num_rows($result) > 0){
    $response['pastCurrentInteraction'] = true;
  }else{
    $response['pastCurrentInteraction'] = false;
  }

  $stmt2 = $conn->prepare("select Concat(firstName, ' ', lastName) AS name, starRating, comment from user, review WHERE email=? and userID = userReviewing ORDER BY reviewDate DESC LIMIT 1;");
  $stmt2->bind_param("s",$searchEmail);
  $stmt2->execute();
  $stmt2->bind_result($recentReviewer, $recentStarRating, $recentComment);
  $stmt2->fetch();
  $stmt2->close();

  $response["recentReviewer"] = $recentReviewer;
  $response["recentStarRating"] = $recentStarRating;
  $response["recentComment"] = $recentComment;
  
  $stmt3 = $conn->prepare("Select COUNT(p.paymentID) FROM user AS u, userPayment AS up, payment AS p WHERE email = ? AND u.userID = up.userFrom AND up.paymentID = p.paymentID;");
  $stmt3->bind_param("s",$searchEmail);
  $stmt3->execute();
  $stmt3->bind_result($numOfPayments);
  $stmt3->fetch(); 
  $stmt3->close();
  
  $response["numOfPayments"] = $numOfPayments;
 
  $stmt4 = $conn->prepare("Select COUNT(p.paymentID)/(Select COUNT(p.paymentID) FROM user AS u, userPayment AS up, payment AS p WHERE email = ? AND u.userID = up.userFrom AND up.paymentID = p.paymentID) AS defaultRate FROM user AS u, userPayment AS up, payment AS p WHERE email = ? AND u.userID = up.userFrom AND up.paymentID = p.paymentID AND paymentStatus = 'Failed';");
  $stmt4->bind_param("ss",$searchEmail, $searchEmail);
  $stmt4->execute();
  $stmt4->bind_result($defaultRate);
  $stmt4->fetch();
  $stmt4->close();

  $response["defaultRate"] = $defaultRate;

  $response['error'] =false;
  $response['message'] ="Successfully ran both queries"; 
}else{
  $response['error'] = true;
  $response['message'] = "Error with parameters set.";
}
echo json_encode($response);
?>
