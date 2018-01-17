Ext.define("GF.view.external.ResendLogin", {
    extend: 'Ext.form.Panel',
    alias: 'widget.external-resend-login',
    
    initComponent: function() {
        var me = this;
        
        Ext.apply(this, me, {
            
            layout: {
                type: 'vbox',
                align: 'center',
                pack: 'center'
            },
            
            items: [
                {
                    xtype: 'panel',
                    minWidth: 250,
                    minheight: 250,
                    layout: {
                        type: 'vbox',
                        align: 'stretch'
                    },
                    defaultType: 'textfield',
                    items: [
                        {
                            name: 'emailAddressTxt',
                            allowBlank: false,
                            fieldLabel: 'Email Address',
                            labelAlign: 'top',
                            emptyText: 'email address'
                        },
                        {
                            name: 'dobDtf', //TODO, bug in firefox 29, doesn't show months and years properly
                            allowBlank: false,
                            xtype: 'datefield',
                            fieldLabel: 'Date of Birth',
                            labelAlign: 'top',
                            format: 'm-D-Y',
                            showToday: false,
                            maxValue: new Date()
                        } 
                    ],
                    buttons: [
                        { text:'Resend Login' }
                    ]
                }
            ]
        });
        
        this.callParent(arguments);
    }
});