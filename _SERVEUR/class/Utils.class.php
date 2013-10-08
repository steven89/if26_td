<?php
if (!defined('DIR')) exit('No direct script access allowed');

class Utils {
	
	public static function require_class($name){
		$name = ucfirst($name);
		require_once("class/$name.class.php");
	}
}