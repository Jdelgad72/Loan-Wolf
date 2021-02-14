<?php
require_once '../../../vendor/autoload.php';

date_default_timezone_set("America/Indianapolis");
//echo date_default_timezone_get();
$CLIENT_ID = "381871578603-kq43dlf32e1boghdcja60rh92umulg2m.apps.googleusercontent.com";

$id_token = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImZkMjg1ZWQ0ZmViY2IxYWVhZmU3ODA0NjJiYzU2OWQyMzhjNTA2ZDkiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiIzODE4NzE1Nzg2MDMtcm1zb2locmdlaDM1cXZrMmphc2doZHNyYzZ1azY1ZmQuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiIzODE4NzE1Nzg2MDMta3E0M2RsZjMyZTFib2doZGNqYTYwcmg5MnVtdWxnMm0uYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMTc5ODg1MzgwNTg0NzE4MzA5MjQiLCJlbWFpbCI6InRlc3RlcnRlc3Rpbmd0ZXN0Nzc3QGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJuYW1lIjoiVGVzdCBUZXN0IiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS8tV0tLSWl4a0F0YncvQUFBQUFBQUFBQUkvQUFBQUFBQUFBQUEvQU1adXVjbVVrWmExVzYyZHhZM1R3b0hQRlBiSU1jMGY4dy9zOTYtYy9waG90by5qcGciLCJnaXZlbl9uYW1lIjoiVGVzdCIsImZhbWlseV9uYW1lIjoiVGVzdCIsImxvY2FsZSI6ImVuIiwiaWF0IjoxNjEzMjU3MjMwLCJleHAiOjE2MTMyNjA4MzB9.xAUKTcAiODR9AtbPHp6Iy_tJ9Z3GTMXMetE4sjpLtNcB-EbnTfUeeProTYn5xgWVFyJvkHBnYehjyc9m2GZ1QkpFNHtYbTGRorqpkgLSXYo6wgv8NkWYy2yNj5T8W7Xh-Jypa2MmNTD2v-lc_voAqdlYGegMnX3QiKDZKsl6rC-ETLmLFlmUgtrBMnH6_0c76-9xNwyFEN05nmYKuRrmFHkWEC9xzYwTCcrOLts5_R59P1wRhgvYv9-E4ankUADpe7U5zsVcWOvJU04SyGV7YnulKBV7bmzrlIx4IsDWAlsIiX8R-yXEktIS428TTe9BW6Rq0dXe7qU675Qjmh9G5g";

$client = new Google_Client(['client_id' => $CLIENT_ID]);  // Specify the CLIENT_ID of the app that accesses the backend
$payload = $client->verifyIdToken($id_token);
if ($payload) {
  $userid = $payload['sub'];
$firstName = $payload['given_name'];
    $lastName = $payload['family_name'];
    $email = $payload['email'];
  echo $lastName;
  // If request specified a G Suite domain:
  //$domain = $payload['hd'];
} else {
  echo "Invalid ID token";
}
?>
