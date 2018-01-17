Ext.define('GF.view.admin.events.EventSummaryGrid', {
    extend: 'Ext.panel.Panel',
    xtype: 'event-summary-grid',
    requires: [
        'Ext.ux.PreviewPlugin'
    ],
    
    initComponent: function() {
        var me = this;
        
        Ext.apply(this, me, {
            
            
            layout: {
                type: 'vbox',
                align: 'stretch' // required to stretch child items to width of container in fit layout
            },
           
            items: [

            ]
        });
        
        this.callParent(arguments);
    }


});