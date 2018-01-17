Ext.define('GF.controller.users.UsersDetailsScreen', {
    extend : 'Ext.app.Controller',
    requires : [ 'GF.util.Services' ],

    stores : [ 'members.Member', 'accounting.Invoice' ],

    views : [ 'users.UserDetailsScreen' ],

    refs : [
        { ref : 'membersGrid', selector : '#membersGrid' }, 
        { ref : 'userDetailsAdminForm', selector : '#userDetailsAdminForm' }, 
        { ref : 'membershipDetailsAdminForm', selector : '#membershipDetailsAdminForm' }, 
        { ref : 'adminTabPanel', selector : '#adminTabPanel'} 
    ],

    currentSelectedMember : null,

    init : function() {

        this.control({
            '#newMemberBtn': {
                click : function(source, event, eOpts) {
                    this.registerNewMember();
                }   
            },
            '#membersGrid' : {
                select : function(membersGrid, record, index, eOpts) {
                    this.currentSelectedMember = record;
                    this.setMemberData(this.currentSelectedMember);
                }
            },
            '#saveMemberBtn' : {
                click : function(source, event, eOpts) {
                    if (this.currentSelectedMember) {
                        this.saveMember();
                    }
                }
            },
            '#deleteMemberBtn' : {
                click : function(source, event, eOpts) {
                    if (this.currentSelectedMember) {
                        Ext.Msg.show({
                            title      : 'Delete Member',
                            msg        : 'Are you sure you want to delete ' + this.currentSelectedMember.get('forename') + ' ' + this.currentSelectedMember.get('surname') + '?',
                            width      : 400,
                            buttons    : Ext.MessageBox.YESNO,
                            fn         : function(buttonId) {
                                            if (buttonId === 'yes') {
                                                this.deleteMember();
                                            }
                                         },
                            icon       : Ext.MessageBox.QUESTION,
                            scope: this
                         });
                    }
                }
            },
            '#resetMemberBtn' : {
                click : function(source, event, eOpts) {
                    if (this.currentSelectedMember) {
                        this.setMemberData(this.currentSelectedMember);
                    } else {
                        this.clearData();
                    }
                }
            },
            '#adminTabPanel' : {
                tabchange : function(tabPanel, newCard, oldCard, eOpts) {
                    if (this.currentSelectedMember) {
                        this.tabChange(tabPanel, newCard, oldCard, eOpts);
                    }
                }
            }

        });
    },

    /**
     * Life cycle method
     */
    enteringScreen : function() {
        // load members store with the proxy setup of the Member model
        this.getMembersMemberStore().load();
    },

    /**
     * Life cycle method
     */
    exitingScreen : function() {
    },
    
    clearData: function() {
        this.getUserDetailsAdminForm().getForm().reset(true);
        this.getMembershipDetailsAdminForm().getForm().reset(true);
    },

    setMemberData : function(currentSelectedMember) {
        var memberImg = this.getMembershipDetailsAdminForm().queryById('imageHolder');
        
        this.getAdminTabPanel().setActiveTab(0);
        this.getUserDetailsAdminForm().loadRecord(currentSelectedMember);
        this.getMembershipDetailsAdminForm().loadRecord(currentSelectedMember);
        
        memberImg.setImage(currentSelectedMember.get('photo'));
    },

    tabChange : function(tabPanel, newCard, oldCard, eOpts) {
        var itemId = newCard.getItemId();
        if (itemId === 'adminPaymentPanel') {
            this.getAccountingInvoiceStore().load({
                url : 'rest/accounting-service',
                params : {
                    userId : this.currentSelectedMember.getId()
                }
            });
        }
    },

    saveMember : function() {
        var validUserDetailsForm = this.getUserDetailsAdminForm().isValid();
            validMembershipDetailsForm = this.getMembershipDetailsAdminForm().isValid();
        if (validUserDetailsForm === false || validMembershipDetailsForm === false) {
            return;
        }
        
        GF.util.AppMask.show('Saving member...');
        
        this.getUserDetailsAdminForm().updateRecord();
        this.getMembershipDetailsAdminForm().updateRecord();
        
        this.currentSelectedMember.save({
            scope: this,
            success: function(newEventFromServer) {
                GF.util.AppMask.hide();
            },
            failure: function (xhr) {
                GF.util.ExceptionHandler.handleException(xhr);
                GF.util.AppMask.hide();
            }
        });
    },
    
    deleteMember: function() {
        GF.util.AppMask.show('Deleting member...');
        
        // bug with extjs model destroy(). it doesn't call the callback methods so direct ajax request
        Ext.Ajax.request({
            url :  GF.util.Services.MEMBER_SERVICE + '/' + this.currentSelectedMember.getId(),
            method: 'DELETE', // because params are being sent
            success : function(xhr) {
                this.getMembersMemberStore().remove(this.currentSelectedMember);
                this.currentSelectedMember = null;
                this.clearData();
                
                GF.util.AppMask.hide();
            },
            failure : function(xhr) {
                GF.util.ExceptionHandler.handleException(xhr);
                GF.util.AppMask.hide();
            },
            scope: this
        });
    },
    
    
    registerNewMember: function() {
        var regWizard = Ext.create('GF.view.users.RegistrationWizard', {
            itemId: 'registrationWizard'
        });
        regWizard.show();
    },
    
    addNewMember: function(newMember) {
        this.getMembersMemberStore().add(newMember);
        this.getMembersGrid().getSelectionModel().select(newMember);
        this.getAdminTabPanel().setActiveTab(0);
    }

});