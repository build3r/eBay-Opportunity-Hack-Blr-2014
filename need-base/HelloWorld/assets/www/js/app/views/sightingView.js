define( [ 'App', 'marionette', 'handlebars', 'models/Model', 'text!templates/sighting.html'],
    function( App, Marionette, Handlebars, Model, template) {
        //ItemView provides some default rendering logic
        return Marionette.ItemView.extend( {
            //Template HTML string
            template: Handlebars.compile(template),
            model: new Model(),
            // View Event Handlers
            events: {
            	'click .uplaod-button' : 'fakeTrigger',
            	'change #realUploadBtn' : 'preview',
            	'submit form' : 'submitForm'
            	
            },
            initialize : function() {
            },
            submitForm : function(e){
            	e.preventDefault();
            	var URL = $('form').attr('action');
            	var formArray  = $( "form" ).serializeArray();
            	var data = {};
            	for(var i=0;i<formArray.length;i++){
            		data[formArray[i].name] = formArray[i].value;
            	}
            	console.log(data);
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
            		}).done(function( msg ) {
            			$('.msg').addClass('show').addClass('alert-success').html('Successfully stored data');
            			$('form')[0].reset();
            			var body = $("html, body");
            			body.animate({scrollTop:0}, '500', 'swing', function() { 
            			});
            	});
            },
            fakeTrigger : function(e){
            	e.preventDefault();
            	this.$el.find('#realUploadBtn').trigger('click');
            },
            preview : function(){
            	var file = document.getElementById('realUploadBtn').files, _this = this;
            	var reader = new FileReader();
                reader.onload = (function (tFile) {
                    return function (evt) {
                        console.log(evt.target.result);
                        _this.$el.find('.photo-container img').attr('src',evt.target.result);
                    };
                }(file));
                reader.readAsDataURL(file[0]);
            }
        });
    });