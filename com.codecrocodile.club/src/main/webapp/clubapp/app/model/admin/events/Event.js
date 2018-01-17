Ext.define('GF.model.admin.events.Event', {
    extend: 'Ext.data.Model',
    requires: ['GF.model.admin.events.EventResponse'],
    
    idProperty: 'eventId',
    
    fields: [
        { name: 'eventId', type: 'int' },
        { name: 'date', type: 'date' },
        { name: 'eventDate', type: 'date' },
        { name: 'eventTime', type: 'string' },
        { name: 'name', type: 'string' },
        { name: 'location', type: 'string' },
        { name: 'description', type: 'string' },
        { name: 'status', type: 'string' },
        { name: 'userId', type: 'int' },
        { name: 'userName', type: 'string' }
    ],
    
    hasMany: {
        model: 'GF.model.admin.events.EventResponse', 
        name: 'eventUserResponses'
    },
    
    proxy: {
        type: 'rest',
        url :  GF.util.Services.EVENT_SERVICE,
        reader: {
            type: 'json',
            root: 'events',
            totalProperty: 'totalCount'
        }
    }
});
