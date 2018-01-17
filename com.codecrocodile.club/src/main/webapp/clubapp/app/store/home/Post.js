Ext.define('GF.store.home.Post', {
    extend: 'Ext.data.Store',
    model: 'GF.model.home.Post',
    alias: 'post',
    
    autoLoad: false,
    defaultSortDirection: 'DESC',
    pageSize: 8, // must keep in sync with value on server
    
    proxy: {
        type: 'rest',
        url :  GF.util.Services.DASHBOARD_SERVICE + '/post',
        reader: {
            type: 'json',
            root: 'posts',
            totalProperty: 'totalCount'
        }
    }
});