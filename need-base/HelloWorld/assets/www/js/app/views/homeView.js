define( [ 'App', 'marionette', 'handlebars', 'models/Model', 'text!templates/home.html'],
    function( App, Marionette, Handlebars, Model, template) {
        //ItemView provides some default rendering logic
        return Marionette.ItemView.extend( {
            //Template HTML string
            template: Handlebars.compile(template),
            model: new Model(),
            // View Event Handlers
            events: {
            	
            },
            initialize : function() {
            	
            },
            onBeforeRender: function(){
                // set up final bits just before rendering the view's `el`
             },
            onRender: function(){
                // manipulate the `el` here. it's already
                // been rendered, and is full of the view's
                // HTML, ready to go.
            	var _this = this;
            	$.ajax({
          		  type: "POST",
          		  url: "http://ph-indaily-124.eva.ebay.com:8080/NBIRestService/nbi/common/search",
          		  contentType: "application/json",
          		  data: "{}",
          		  error : function(){
          			  $('.msg').addClass('show').addClass('alert-danger').html('Something went wrong');
          			var body = $("html, body");
          			  body.animate({scrollTop:0}, '500', 'swing', function() { 
            			});
          		  },
          		}).done(function( data ) {
          			_this.generateData(data);
          	});
            },
            generateData : function(data){
            	var html="";
            	$('.feed').html("");
            	for(var i=0;i<data.length;i++){
            		html = html + '<div class="col-md-6">'+
				            		'<div class="list-card">'+
				            			'<div class="row">'+
				            				'<div class="col-md-4">'+
				            					'<img src="'+data[i].subjectPhotoURL+'">'+
				            				'</div>'+
				            				'<div class="feed-details col-md-8">'+
				            					'<div class="name">'+data[i].subjectFirstName+' '+data[i].subjectLastName+'</div>'+
				            					'<div class="values">'+
				            						'<div class="key">Guardian name</div>'+
				            						'<div class="value">'+
				            							data[i].guardianFirstName+
				            						'</div>'+
				            						'<div class="key">Native city</div>'+
				            						'<div class="value">'+
				            							data[i].nativeCity+
				            						'</div>'+
				            						'<div class="key">Languange</div>'+
				            						'<div class="value">'+
				            							data[i].subjectLanguage+
				            						'</div>'+
				            						'<div class="key">Missing city</div>'+
				            						'<div class="value">'+
				            							data[i].subjectMissingCity+
				            						'</div>'+
				            						'<div class="key">Email</div>'+
				            						'<div class="value">'+
				            							data[i].reporterEmail+
				            						'</div>'+
				            					'</div>'+
				            				'</div>'+
				            			'</div>'+
				            			'<div class="row share-wrapper">'+
				            				'<div class="col-md-12">'+
				            					'<div class="btn btn-primary">Share</div>'+
				            				'</div>'+
				            			'</div>'+
				            		'</div>'+
				            	'</div>';
            		
            	}
            	$('.feed').html(html);
            },
            onClose : function(){
            }
        });
    });