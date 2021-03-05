
<?php
require_once '../../../vendor/autoload.php';
require_once 'connection.php';


$response = array();
if(isset($_POST['idToken'])){


      //inserts the id, name, value and rate.
      $stmt = $conn->prepare("INSERT INTO user(firstName, lastName, email, accountGoogle, amount) VALUES (?, ?, ?, ?, 0)");
      $stmt->bind_param("ssss", $firstName, $lastName, $email, $userid);
      if($stmt->execute()){
	$response['error'] = false;
        $response['newUser'] = true;
        $response['message'] = 'User registered successfully';
        $response['user'] = $user;

      );

      

}else{
  $response['error']=true;
  $response['message']='Missing Id Token';
}  
echo json_encode($response);
?>




