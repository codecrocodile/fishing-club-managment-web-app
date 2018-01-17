Ext.define('GF.view.users.PaymentHistory', {
    extend: 'Ext.panel.Panel',
    xtype: 'payment-panel',
    
    initComponent: function() {
        var me = this;
        Ext.apply(me, this, {
            items:[
                {
                    xtype: 'gridpanel',
                    store: 'accounting.Invoice',
                    columns: [
                        { 
                            xtype: 'datecolumn',   
                            format: GF.util.AppConfig.DATE_FORMAT, 
                            text: 'Date', 
                            dataIndex: 'date',
                            flex: 1 
                        },
                        { 
                            text: 'Amount', 
                            dataIndex: 'totalPrice',
                            flex: 1 
                        }
                    ]
                }
            ]
        });
        
        this.callParent(arguments);
    }

});