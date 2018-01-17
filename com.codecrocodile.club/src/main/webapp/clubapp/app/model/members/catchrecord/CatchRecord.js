Ext.define('GF.model.members.catchrecord.CatchRecord', {
    extend: 'Ext.data.Model',
    
    fields: [
        { name: 'id', type: 'int' },
        { name: 'userId', type: 'int' },
        { name: 'date', type: 'date' },
        { name: 'fishingNotes', type: 'string' }
    ],
    
    hasMany: {
        model: 'GF.model.members.catchrecord.CatchRecordItem', 
        name: 'catchRecordItems'
    }

});