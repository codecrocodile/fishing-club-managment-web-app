Ext.define('GF.view.Viewport', {
    extend: 'Ext.container.Viewport',
    layout: {
        type: 'fit'
    },
    items: [
        {
            xtype: 'app-main'
        }
    ]
});
