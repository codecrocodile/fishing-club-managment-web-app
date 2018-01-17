Ext.define("GF.view.external.ResetPassword", {
    extend: 'Ext.form.Panel',
    alias: 'widget.external-reset-password',
    
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
                            name: 'passwordTxt',
                            allowBlank: false,
                            fieldLabel: 'Password',
                            labelAlign: 'top',
                            emptyText: 'password',
                            inputType: 'password'
                        },
                        {
                            name: 'confirmedPasswordTxt',
                            allowBlank: false,
                            fieldLabel: 'Confirm Password',
                            labelAlign: 'top',
                            emptyText: 'password',
                            inputType: 'password'
                        }
                    ],
                    buttons: [
                        { text:'Reset Password' }
                    ]
                }
            ]
        });
        
        this.callParent(arguments);
    }
});