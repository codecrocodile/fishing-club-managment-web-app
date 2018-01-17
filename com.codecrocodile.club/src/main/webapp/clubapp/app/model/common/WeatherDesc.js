Ext.define('GF.model.common.WeatherDesc', {
    extend: 'Ext.data.Model',
    
    idProperty: 'weatherDescId',
    
    fields: [
        { name: 'weatherDescId', type: 'int' },
        { name: 'weatherHourlyId', type: 'int' },
        { name: 'value', type: 'string' }
    ]
});
