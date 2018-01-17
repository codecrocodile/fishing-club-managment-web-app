Ext.define("GF.view.external.Login", {
    extend: 'Ext.form.Panel',
    alias: 'widget.external-login',
    
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
                            name: 'passwordTxt',
                            allowBlank: false,
                            fieldLabel: 'Password',
                            labelAlign: 'top',
                            emptyText: 'password',
                            inputType: 'password'
                        },
                        {
                            xtype: 'label',
                            id: 'forgotPasswordLbl',
                            html: '<a href="#">forgot password?</a>',
                            padding: '5 0 5 0',
                            style :{
                                textAlign:'right'
                            },
                            listeners: {
                                render: function(c){
                                    console.log(c);
                                  c.getEl().on('click', function(){
                                    Ext.Msg.alert('Hello', 'World');
                                    console.log(this);
                                  }, c);
                                }
                            }
                        }
                    ],
                    buttons: [
                        { text:'Login' }
                    ]
                }
            ]
        });
        
        this.callParent(arguments);
    }
});