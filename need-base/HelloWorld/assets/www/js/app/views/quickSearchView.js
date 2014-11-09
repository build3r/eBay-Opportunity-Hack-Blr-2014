define( [ 'App', 'marionette', 'handlebars', 'models/Model', 'text!templates/quickSearch.html'],
    function( App, Marionette, Handlebars, Model, template) {
        //ItemView provides some default rendering logic
        return Marionette.ItemView.extend( {
            //Template HTML string
            template: Handlebars.compile(template),
            model: new Model(),
            // View Event Handlers
            events: {
            	'submit form' : 'submitForm'
            },
            submitForm : function(e){
            	e.preventDefault();
            	var _this = this;
            	var URL = $('form').attr('action');
            	var formArray  = $( "form" ).serializeArray();
            	var data = {};
            	for(var i=0;i<formArray.length;i++){
            		if(formArray[i].value)
            		data[formArray[i].name] = formArray[i].value;
            	}
            	$.ajax({
            		  type: "POST",
            		  url: URL,
            		  contentType: "application/json",
            		  data: JSON.stringify(data),
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
            	var html ="";
            	$('.search-results').html(""); 
            	for(var i=0;i<data.length;i++){
            		html = html + '<div class="col-md-3 kid">'+
										'<div class="list-card">'+
											'<div class="pic">'+
												'<img src="'+data[i].subjectPhotoURL+'">'+
											'</div>'+
											'<div class="details">'+
												'<div class="name">'+
												data[i].subjectFirstName+' '+data[i].subjectLastName+
												'</div>'+
												'<div class="values clearfix">'+
													'<div class="key">Email</div>'+
													'<div class="value">'+
													data[i].contactEmail+
													'</div>'+
												'</div>'+
												'<div class="values clearfix">'+
													'<div class="key">Found City</div>'+
													'<div class="value">'+
													data[i].subjectFoundCity+
													'</div>'+
												'</div>'+
												'<div class="values clearfix">'+
													'<div class="key">Contact</div>'+
													'<div class="value">'+
													data[i].reporterPhoneNumber+
													'</div>'+
												'</div>'+
											'</div>'+
									'</div>'+
								'</div>';
            	}
            	$('.search-results').html(html);
            },
            initialize : function() {
            }
        });
    });