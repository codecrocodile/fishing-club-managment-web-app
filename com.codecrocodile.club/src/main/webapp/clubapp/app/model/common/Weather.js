Ext.define('GF.model.common.Weather', {
    extend: 'Ext.data.Model',
    requires: 'GF.model.common.WeatherHourly',
    
    idProperty: 'weatherId',
    
    fields: [
        { name: 'weatherId', type: 'int' },
        { name: 'maxtempC', type: 'int' },
        { name: 'mintempC', type: 'int' }
    ],
    
    associations: [
        {
            type: 'hasMany', 
            model: 'GF.model.common.WeatherHourly', 
            name: 'getHourly', 
            associationKey: 'hourly' 
        }
    ]

});
