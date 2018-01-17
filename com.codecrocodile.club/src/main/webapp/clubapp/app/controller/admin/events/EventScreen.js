Ext.define('GF.controller.admin.events.EventScreen', {
    extend: 'Ext.app.Controller',
    
    stores: [
        'admin.events.Event' 
    ],
    
    views: [
        'admin.events.EventScreen'
    ],
    
    refs : [
        { ref : "eventSummaryGrid", selector : '#eventSummaryGrid' },
        { ref : "eventDetailsForm", selector : '#eventDetailsForm' },
        { ref : "eventDataPicker", selector : '#eventDataPicker' },
        { ref : "eventResponseGrid", selector : '#eventResponseGrid' }
    ],
    
    currentSelectedEvent: Ext.create('GF.model.admin.events.Event'),
    emptyStore: Ext.create('Ext.data.Store', {model: 'GF.model.admin.events.EventResponse'}),
        
    init: function() {
        this.control({
            '#eventSummaryGrid': {
                select: function ( eventSummaryGrid, record, index, eOpts ) {
                    this.currentSelectedEvent = record;
                    this.setData();
                }
            },
            '#newEventBtn': {
                click: function() {
                    this.currentSelectedEvent = Ext.create('GF.model.admin.events.Event');
                    this.clearData();
                }
            },
            '#saveEventBtn': {
                click: function() {
                    this.saveEvent();
                }
            },
            '#deleteEventBtn': {
                click: function() {
                    this.deleteEvent();
                }
            },
            '#resetEventBtn': {
                click: function() {
                    if (this.currentSelectedEvent.getId() > 0) {
                        this.setData();
                    } else {
                        this.clearData();
                    }
                }
            }
        });
    },
    
    /**
     * Life cycle method
     */
    enteringScreen: function() {
        this.getAdminEventsEventStore().loadPage(1);
    },
    
    /**
     * Life cycle method
     */
    exitingScreen: function() { },
    
    setData: function() {
        this.getEventDetailsForm().loadRecord(this.currentSelectedEvent);
        this.getEventDataPicker().setValue(this.currentSelectedEvent.get('eventDate'));
        
        var store = this.currentSelectedEvent.eventUserResponses();
        this.getEventResponseGrid().reconfigure(store);
    },
    
    clearData: function() {
        this.getEventDetailsForm().getForm().reset();
        this.getEventDataPicker().setValue(new Date());
        this.getEventSummaryGrid().getSelectionModel().deselectAll(true);
        
        this.getEventResponseGrid().reconfigure(this.emptyStore);
    },
    
    saveEvent: function() {
        var validForm = this.getEventDetailsForm().isValid();
        if (validForm === false) {
            return;
        }
        
        GF.util.AppMask.show('Saving event...');
        
        this.getEventDetailsForm().updateRecord(this.currentSelectedEvent);
        this.currentSelectedEvent.set('eventDate', this.getEventDataPicker().getValue());
        
        this.currentSelectedEvent.save({
            scope: this,
            success: function(newEventFromServer) {
                
                if (this.getAdminEventsEventStore().getById(newEventFromServer.getId()) === null) {
                    this.getAdminEventsEventStore().insert(0, newEventFromServer);
                }
                
                this.getEventSummaryGrid().getSelectionModel().select(newEventFromServer);
                GF.util.AppMask.hide();
            },
            failure: function (xhr) {
                GF.util.ExceptionHandler.handleException(xhr);
                GF.util.AppMask.hide();
            }
        });
    },
    
    deleteEvent: function() {
        if (this.currentSelectedEvent.getId() === 0) {
            this.clearData();
            return;
        } else {
            
        }
        
        GF.util.AppMask.show('Deleting event...');
        
        // bug with extjs model destroy(). it doesn't call the callback methods so direct ajax request
        Ext.Ajax.request({
            url :  GF.util.Services.EVENT_SERVICE + '/' + this.currentSelectedEvent.getId(),
            method: 'DELETE', // because params are being sent
            success : function(xhr) {
                
                this.getAdminEventsEventStore().remove(this.currentSelectedEvent);
                this.currentSelectedEvent = Ext.create('GF.model.admin.events.Event');
                this.getEventDetailsForm().getForm().reset();
                this.getEventDataPicker().setValue(new Date());
                
                GF.util.AppMask.hide();
            },
            failure : function(xhr) {
                GF.util.ExceptionHandler.handleException(xhr);
                GF.util.AppMask.hide();
            },
            scope: this
        });
    }
    
});
