Ext.define('GF.store.admin.events.Event', {
    extend: 'Ext.data.Store',
    model: 'GF.model.admin.events.Event',
    alias: 'store.events',
    
    pageSize: 15 // need to keep in sync with number specified on service
});