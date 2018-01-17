/**
 * This is a summary of catch recorded by a member in one day's fishing.
 */
Ext.define('GF.model.members.catchrecord.CatchLog', {
    extend: 'Ext.data.Model',
    
    idProperty: 'catchRecordId',
    
    fields: [
        { name: 'catchRecordId', type: 'int' },
        { name: 'date', type: 'date' },
        { name: 'waters', type: 'string', defaultValue: '' },
        { name: 'notes', type: 'string', defaultValue: 'No notes entered' },
        { name: 'numberOfFish', type: 'int', defaultValue: 0 },
        { name: 'memberName', type: 'string', defaultValue: 'Unknown' }
    ],
    
    proxy: {
        type: 'rest',
        url :  GF.util.Services.CATCH_RECORD + '/catchlog',
        reader: {
            type: 'json',
            root: 'catchLogs',
            totalProperty: 'totalCount'
        }
    }
});