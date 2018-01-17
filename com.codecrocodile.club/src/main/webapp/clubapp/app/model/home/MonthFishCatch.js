Ext.define('GF.model.home.MonthFishCatch', {
    extend: 'Ext.data.Model',
    
    fields: [
        { name: 'month', type: 'auto' },
        { name: 'catchNumber', type: 'int' }
    ]
});