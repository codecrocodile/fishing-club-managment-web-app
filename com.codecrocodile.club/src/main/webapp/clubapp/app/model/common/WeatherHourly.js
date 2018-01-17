Ext.define('GF.model.common.WeatherHourly', {
    extend: 'Ext.data.Model',
    requires: [
        'GF.model.common.WeatherDesc',
        'GF.model.common.WeatherIconUrl'
    ],
    
    idProperty: 'weatherHourlyId',
    
    fields: [
        { name: 'weatherHourlyId', type: 'int' },
        { name: 'weatherId', type: 'int' },
        { name: 'cloudcover', type: 'int' },
        { name: 'humidity', type: 'int' },
        { name: 'precipMM', type: 'float' },
        { name: 'winddir16Point', type: 'string' },
        { name: 'windspeedMiles', type: 'int' },
        { name: 'visibility', type: 'int' },
        { name: 'tempC', type: 'int' }
    ],
    
    associations: [
        {
            type: 'hasMany', 
            model: 'GF.model.common.WeatherDesc', 
            name: 'getWeatherDesc', 
            associationKey: 'weatherDesc' 
        },
        {
            type: 'hasMany', 
            model: 'GF.model.common.WeatherIconUrl', 
            name: 'getWeatherIconUrl', 
            associationKey: 'weatherIconUrl' 
        }
    ]
});
