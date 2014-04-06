<?php

//Site root dir
define('DIR_ROOT',		$_SERVER['DOCUMENT_ROOT']);
//Images dir (root relative)
define('DIR_IMAGES',	'/uploads/images');
//Files dir (root relative)
define('DIR_FILES',		'/uploads/images');


//Width and height of resized image
define('WIDTH_TO_LINK', 20000);
define('HEIGHT_TO_LINK', 20000);

//Additional attributes class and rel
define('CLASS_LINK', 'lightview');
define('REL_LINK', 'lightbox');

date_default_timezone_set('Europe/London');
?>
