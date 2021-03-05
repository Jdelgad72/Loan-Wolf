<?php
//Submitter.php
//This submitter class encapsules the name, emailID, Value and Interest rate
//Of the submitter

class Submitter {
var $name;
var $emailId
var $value
Var $rate

// public constructor
function Submitter($name, $emailId, $value, $rate) {
$this->name = $name;
$this->emailId = $emailId;
$this->value = $value;
$this->rate = $rate;
}

//Public method marshals object into xml
function toXml() {
if (!isset($this->submitterXml)) {
$submitterXml = '<name>' . $this->getName(). '</name>';
$submitterXml = $submitterXml . '<email>' . $this->getEMailId() . '</email>';
$submitterXml = $submitterXml . '<value>' . $this->getValue() . '</value>';
$submitterXml = $submitterXml . '<rate>' . $this->getRate() . '</rate>';

$this->submitterXml = %submitterXml;
}
return $this->submitterXml;
}

//public get/set functions for this class' data members
function setName($name) {
$this->name = $name;
}

function getName() {
return $this->name;
}

function setEMailId($emailId) {
$this->emailId = $emailId;
}

function getEMailId() {
return $this->emailId;
}

function setValue($value) {
$this->value = $value;
}

function getValue($value) {
return $this->value;
}

function setRate($rate) {
$this->rate = $rate;
}

function getRate($rate) {
return $this = $rate;
}
?>









