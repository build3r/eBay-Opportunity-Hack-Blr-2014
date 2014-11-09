define([ 'marionette', 'handlebars', 'text!templates/header.html'],
    function (Marionette, Handlebars, template) {
        //ItemView provides some default rendering logic
        return Marionette.ItemView.extend({
            template:Handlebars.compile(template),
            events: {
            	'mouseenter .nav>li' : 'showFlyout',
            	'mouseleave .nav>li' : 'hideFlyout',
            	'click .nav>li' 	 : 'toggleActive',
            	'click .burger'		 : 'toggleBurgerActive',
            },
            showFlyout :  function(e){
            	e.preventDefault();
            	var flyout = $(e.target).next('.flyout');
            	if(flyout.length){
            		flyout.addClass('show');            	
            	}
            },
            toggleBurgerActive : function(e){
            	e.preventDefault();
            	var target = $('.menu-drawer');
            	$('.burger').toggleClass('active');
            	target.toggleClass('active');
            	$('html').toggleClass('overflow');
            	$('.mask').toggleClass('show');
            },
            hideFlyout :  function(e){
            	e.preventDefault();
            	var flyout = $(e.target).closest('.flyout').length ? $(e.target).closest('.flyout') : $(e.target).next('.flyout');
            	if(flyout.length){
            		flyout.removeClass('show');            	
            	}
            },
            toggleActive : function(e){
            	$('.nav>li').removeClass('active');
            	$(e.target).closest('li').addClass('active');
            },
            onRender: function(){
                // manipulate the `el` here. it's already
                // been rendered, and is full of the view's
                // HTML, ready to go.
            	setTimeout(function(){
            		$('.nav>li a[href="#'+Backbone.history.fragment+'"]').closest('li').addClass('active');
            			if($('.menu-drawer').is(':visible')){
            				$('.nav>li').on('click',function(){
            					$('.menu-drawer').toggleClass('active');
            					$('.burger').toggleClass('active');
            					$('html').toggleClass('overflow');
            					$('.mask').toggleClass('show');
            				});
            				$('.mask').on('click',function(e){
            					e.preventDefault();
            					$('.menu-drawer').removeClass('active');
            					$('.burger').removeClass('active');
            					$('html').removeClass('overflow');
            					$('.mask').removeClass('show');
            				});
                    	}
            	},500);
            	
            },
          
        });
    });