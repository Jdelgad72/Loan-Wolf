<?php
require_once 'connection.php';

$response = array();
$ids = array();
$names = array();
$emails = array();
$ratings = array();
$num = array();

$sql ="select userID, CONCAT(firstName, ' ', LastName) AS name, email, SUM(starRating)/COUNT(starRating) AS averageRating, COUNT(starRating) AS numReviews From user, review WHERE userID = userReviewing GROUP BY userID ORDER BY averageRating DESC, numReviews DESC LIMIT 3;";
$result = mysqli_query($conn, $sql);

if (mysqli_num_rows($result) > 0) {

  while($row = mysqli_fetch_assoc($result)){
    array_push($ids, $row["userID"]);
    array_push($names, $row["name"]);
    array_push($emails, $row["email"]);
    array_push($ratings, $row["averageRating"]);
    array_push($num, $row["numReviews"]);
  }

  $response["ids"] = $ids;
  $response["names"] = $names;
  $response["emails"] = $emails;
  $response["ratings"] = $ratings;
  $response["num"] = $num;

  $response["error"] = false;
  $response["message"] = "Successfully created Leaderboard.";
}
else{
  $response['error']=true;
  $response['message']='There are currently No rankings to make leaderboards.';
}
echo json_encode($response);
?>
