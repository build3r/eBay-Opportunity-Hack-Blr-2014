define(['App', 'backbone', 'marionette', 'views/HeaderView','views/reportView', 'views/sightingView', "views/quickSearchView", "views/homeView"],
    function (App, Backbone, Marionette, HeaderView, reportView, sightingView, quickSearchView, homeView) {
    return Backbone.Marionette.Controller.extend({
        initialize : function (options) {
            App.headerRegion.show(new HeaderView());
           
        },
        //gets mapped to in AppRouter's appRoutes
        report : function () {
           App.mainRegion.show(new reportView());
        },
        sighting : function () {
            App.mainRegion.show(new sightingView());
        },
        quickSearch : function(){
        	App.mainRegion.show(new quickSearchView());
        },
        home : function(){
        	App.mainRegion.show(new homeView());
        }
    });
});