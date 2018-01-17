Ext.define('GF.model.home.PostComment', {
    extend: 'Ext.data.Model',
    
    idProperty: 'postCommentId',
    
    fields: [
        { name: 'postCommentId', type: 'int' },
        { name: 'postId', type: 'int' },
        { name: 'userId', type: 'int' },
        { name: 'userName', type: 'string' },
        { name: 'userImage', type: 'string' },
        { name: 'date', type: 'date' },
        { name: 'text', type: 'string' }
    ],
    
    proxy: {
        type: 'rest',
        url :  GF.util.Services.DASHBOARD_SERVICE + '/post-comment',
        reader: {
            type: 'json'
        },
        listeners: {
            exception: function (proxy, response, operation) {
                debugger;
                
//                var json = Ext.decode(response.responseText);
//                if (json) {
//                    detl.getForm().markInvalid(json.errors);
//                    Ext.MessageBox.show({
//                        title: 'Save Failed',
//                        msg: json.message,
//                        icon: Ext.MessageBox.ERROR,
//                        buttons: Ext.Msg.OK
//                    });
//                } else
//                Ext.MessageBox.show({
//                    title: 'EXCEPTION',
//                    msg: operation.getError(),
//                    icon: Ext.MessageBox.ERROR,
//                    buttons: Ext.Msg.OK
//                });
            }
        }
    }
    

});