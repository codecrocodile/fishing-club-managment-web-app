Ext.define('GF.store.home.PostComment', {
    extend: 'Ext.data.Store',
    model: 'GF.model.home.PostComment',
    alias: 'postcomment',
    
    autoLoad: false,
    defaultSortDirection: 'DESC'

});