Ext.Loader.setConfig({
    enabled: true,
    disableCaching: false,
    paths: {
        GeoExt: "./geoext2-2.0.1/src/GeoExt"
//        GeoExt: "http://geoext.github.com/geoext2/src/GeoExt/",
    }
});

Ext.Loader.setPath('Ext.ux', './ux');
//Ext.Loader.setPath('GeoExt', './geoext2-2.0.1/src/GeoExt');


Ext.define('GF.Application', {
    name: 'GF',
    extend: 'Ext.app.Application',
    requires:[
        'Ext.form.*',
        'Ext.layout.*',
        'Ext.panel.*',
        'Ext.picker.*',
        'Ext.chart.*',
        'Ext.grid.plugin.CellEditing',
        'Ext.grid.Panel',
        'Ext.grid.column.Action',
        'Ext.grid.column.CheckColumn',
        'Ext.grid.column.Date',
        'Ext.grid.column.Number',
        'GF.util.AppConfig',
        'GF.util.Services',
        'GF.util.AppMask',,
        'GF.util.ExceptionHandler',
        'GF.view.Viewport',
        'Ext.PagingToolbar',
        'GF.model.admin.events.Event',
        'GF.view.users.RegistrationWizard'
    ],
    
    models: [
        'GF.model.admin.applications.MembershipApplication'
    ],

    controllers: [
        'Main',
        'members.home.HomeScreen',
        'members.catchrecord.CatchRecordScreen',
        'members.profile.ProfileScreen',
        'members.profile.PhotoScreen',
        'users.UsersDetailsScreen',
        'admin.events.EventScreen',
        'admin.applications.IncomingApplicationsScreen',
        'admin.settings.SettingsScreen',
        'users.RegistrationWizard'
    ],

    stores: [
        'admin.applications.MembershipApplication',
        'lookup.Venue'
    ],
    
    launch: function() {
        GF.util.AppMask.show('loading application...');
        
        Ext.Ajax.request({
            url : GF.util.Services.LOOKUP + '/allLookups',
            success : function(xhr) {
                var jsonData = Ext.decode(xhr.responseText);
                var venueStore = Ext.data.StoreManager.lookup('lookup.Venue');
                venueStore.loadData(jsonData.venues, false);
                
                Ext.create('GF.view.Viewport');
                GF.util.AppMask.hide();
            },
            failure : function(xhr) {
                console.log("Error: " + xhr.statusText);
                
                GF.util.AppMask.hide();
            }
        });
        
        
//        // to sort problem with tab indexes because same form is used more than once
//        // https://www.sencha.com/forum/showthread.php?250586-Can-someone-please-confirm-tabIndex-behavior-with-Ext.form.field.Base&p=918558#post918558
//        Ext.override(Ext.form.Panel, {
//            removeTabIndexes: function(){
//                var me = this;
//                if(!me.tabIndexesAreCreated){
//                    return;
//                }
//                Ext.each(me.query('field:not(hiddenfield):not(displayfield)'),function(field){
//                    field.inputEl.dom.tabIndex = -1;
//                });
//                me.tabIndexesAreCreated=false;
//            },
//            createTabIndexes: function(){
//                var me = this;
//                if(me.tabIndexesAreCreated){
//                    return;
//                }
//                var idx = 1;
//                Ext.each(me.query('field:not(hiddenfield):not(displayfield)'),function(field){
//                    field.inputEl.dom.tabIndex = field.initialConfig.tabIndex || idx++;
//                });
//                me.tabIndexesAreCreated=true;
//            }
//        });
    }

});
