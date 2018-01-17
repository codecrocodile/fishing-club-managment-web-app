Ext.define('GF.model.common.WeatherIconUrl', {
    extend: 'Ext.data.Model',
    
    idProperty: 'weatherIconUrlId',
    
    fields: [
        { name: 'weatherIconUrlId', type: 'int' },
        { name: 'weatherHourlyId', type: 'int' },
        { name: 'value', type: 'string' }
    ]
});
