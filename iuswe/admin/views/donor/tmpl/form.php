<?php
JHtml::_('jquery.framework');
JHtml::_('bootstrap.framework');

$viewTemplate = $this->getRenderedForm();
	    ?>
	    <ul class="nav nav-tabs">
			<li class="active">
		    	<a href="#personal" data-toggle="tab"><?php echo JText::_('Donor details')?></a>
		    </li>
		 </ul>
		  
		 <div class="tab-content">
			<?php echo $viewTemplate; ?>
		 </div>
		 
		 <?php
JFactory::getDocument()->addScriptDeclaration(
'jQuery(document).ready(function(){
jQuery("form#adminForm").addClass("tab-content")
});');
	
	?>
