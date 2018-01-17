Ext.define('GF.model.lookup.Venue', {
    extend: 'Ext.data.Model',
    
    idProperty: 'venueId',
    
    fields: [
        { name: 'venueId', type: 'int' },
        { name: 'description', type: 'string' },
        { name: 'accountId', type: 'int' },
        { name: 'lon', type: 'float' },
        { name: 'lat', type: 'float' },
        { name: 'zoom', type: 'int' }
    ]
});
