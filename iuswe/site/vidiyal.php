<?php
/**
 * @package		flystaff
 * @copyright	Copyright (c) 2015 Sasi varna kumar / Flycart Inc
 * @license		GNU General Public License version 2 or later
 * 
 */

defined('_JEXEC') or die();

require_once JPATH_LIBRARIES.'/fof/include.php';

FOFDispatcher::getTmpInstance('com_vidiyal')->dispatch();
