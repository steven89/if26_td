<?php
if (!defined('DIR')) exit('No direct script access allowed');
$config['host'] = 'localhost';
$config['login'] = 'root';
$config['passwd'] = '';
$config['database'] = 'if26';

try{
	$db = new PDO(
		"mysql:host=$config[host];dbname=$config[database]", 
		$config['login'], 
		$config['passwd']
	);
} catch(Exception $e){
    echo "Erreur ($e->getCode()): $e->getMessage()<br />";
}
