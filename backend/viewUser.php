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

  $response["recentReviewer"] = $recentReviewer;
  $response["recentStarRating"] = $recentStarRating;
  $response["recentComment"] = $recentComment;
  $response['error'] =false;
  $response['message'] ="Successfully ran both queries"; 
}else{
  $response['error'] = true;
  $response['message'] = "Error with parameters set.";
}
echo json_encode($response);
?>
