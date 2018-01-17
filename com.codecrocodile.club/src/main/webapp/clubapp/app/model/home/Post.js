Ext.define('GF.model.home.Post', {
    extend: 'Ext.data.Model',
    requires: 'GF.model.home.PostComment',
    
    idProperty: 'postId',
    
    fields: [
        { name: 'postId', type: 'int' },
        { name: 'accountId', type: 'int' },
        { name: 'userId', type: 'int' },
        { name: 'userName', type: 'string' },
        { name: 'userImage', type: 'string' },
        { name: 'date', type: 'date' },
        { name: 'text', type: 'string' }
    ],
    
    associations: [
        {
            type: 'hasMany', 
            model: 'GF.model.home.PostComment', 
            name: 'getPostComments', 
            associationKey: 'postComments' 
        }
    ],
    
    proxy: {
        type: 'rest',
        url :  GF.util.Services.DASHBOARD_SERVICE + '/post',
        reader: {
            type: 'json'
        }
    }

});