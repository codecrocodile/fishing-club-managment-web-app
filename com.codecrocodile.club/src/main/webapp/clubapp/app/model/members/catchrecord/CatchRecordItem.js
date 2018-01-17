Ext.define('GF.model.members.catchrecord.CatchRecordItem', {
    extend: 'Ext.data.Model',
        
    fields: [
        { name: 'id', type: 'int' },
        { name: 'catchRecordId', type: 'int' },
        { name: 'venueId', type: 'int' },
        { name: 'lon', type: 'float' },
        { name: 'lat', type: 'float' },
        { name: 'timePeriod', type: 'string' },
        { name: 'weight', type: 'float' },
        { name: 'killed', type: 'boolean' },
        { name: 'olMarker', type: 'auto' }
    ]

});