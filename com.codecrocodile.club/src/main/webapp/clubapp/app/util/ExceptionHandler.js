Ext.define('GF.util.ExceptionHandler', {
    singleton:true,
    
    handleException: function(xhr) {
//        var exceptionObject = Ext.JSON.decode(xhr);
        var exceptionObject = xhr;
        
        Ext.MessageBox.show({
            title: 'Error',
            msg: exceptionObject.exceptionMessage,
            buttons: Ext.MessageBox.OK,
            icon: Ext.MessageBox.ERROR
        });
    }
});