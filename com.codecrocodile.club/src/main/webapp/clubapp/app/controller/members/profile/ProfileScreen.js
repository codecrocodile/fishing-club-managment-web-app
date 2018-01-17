Ext.define('GF.controller.members.profile.ProfileScreen', {
    extend : 'Ext.app.Controller',
    
    views: [
        'members.profile.ProfileScreen'
    ],
    stores: [
        'members.Member'
    ],
    
    refs : [
       { ref: 'userDetailsForm', selector: '#userDetailsForm' }
    ],
        
    init : function() {
        
    },
    
    /**
     * Called on entering this screen. Common to all screen controllers.
     */
    enteringScreen: function() {
        var membersMemberStore = this.getMembersMemberStore();
        membersMemberStore.load({    
            scope: this,
            url: 'rest/member-service/authenticated',
            callback: function(records, operation, success) {
                var model = records[0];
                this.getUserDetailsForm().loadRecord(model);
                
                this.getController('members.profile.PhotoScreen').setMember(model);
            }
        });
    },
    
    save: function() {
        var membersMemberStore = this.getMembersMemberStore();
        var model = membersMemberStore.first();
        this.getUserDetailsForm().updateRecord();
        model.save({
            url: 'rest/member-service/authenticated'
        });
    },
    
    cancel: function() {
        var membersMemberStore = this.getMembersMemberStore();
        var model = membersMemberStore.first();
        this.getUserDetailsForm().loadRecord(model);
    }

});