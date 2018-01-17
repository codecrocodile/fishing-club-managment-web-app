Ext.define('GF.model.admin.events.EventResponse', {
    extend: 'Ext.data.Model',
    
    fields: [
        { name: 'id', type: 'int' },
        { name: 'date', type: 'date' },
        { name: 'response', type: 'string' },
        { name: 'comment', type: 'string' },
        { name: 'userId', type: 'int' },
        { name: 'userName', type: 'string' }
    ]
});