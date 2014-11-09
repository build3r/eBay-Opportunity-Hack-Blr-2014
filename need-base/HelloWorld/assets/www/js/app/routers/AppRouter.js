define(['marionette', 'controllers/Controller'], function(Marionette, Controller) {
   return Marionette.AppRouter.extend({
       //"index" must be a method in AppRouter's controller
       appRoutes: {
    	   "/":"home",
    	   "home":"home",
    	   "report": "report",
    	   "sighting": "sighting",
    	   "quickSearch" : "quickSearch"
       }
   });
});