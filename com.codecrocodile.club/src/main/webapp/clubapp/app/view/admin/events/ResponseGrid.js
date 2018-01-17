Ext.define('GF.view.admin.events.ResponseGrid', {
    extend: 'Ext.panel.Panel',
    xtype: 'response-grid',
    
    initComponent: function() {
        var me = this;
        
        Ext.apply(this, me, {
            items: [

            ]
        });
        
        this.callParent(arguments);
    }
});