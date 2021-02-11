<?php
require_once '../../../vendor/autoload.php';
require_once 'connection.php';

$CLIENT_ID = "381871578603-rmsoihrgeh35qvk2jasghdsrc6uk65fd.apps.googleusercontent.com";

$response = array();
if(isset($_GET['idToken'])){

  // Get $id_token via HTTPS POST.

  $client = new Google_Client(['client_id' => $CLIENT_ID]);  // Specify the CLIENT_ID of the app that accesses the backend
  $payload = $client->verifyIdToken($id_token);
  if ($payload) {
    $userid = $payload['sub'];
    // If request specified a G Suite domain:
    //$domain = $payload['hd'];
  } else {
    // Invalid ID token
  }

}else{
  $response['error']=true;
  $response['message']='Missing Id Token';
}
echo json_encode($response);
?>
