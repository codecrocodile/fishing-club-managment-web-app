Ext.define('GF.view.users.RegistrationWizard', {
    extend: 'Ext.window.Window',
    xtype: 'registration-wizard',
    
    title: 'Registration Wizard',
    
    height: 600,
    width: 600,
    modal: true,
    
    layout: 'card',
    
    initComponent: function() {
        var me = this;
        Ext.apply(me, this, {
            items:[
                {
                    xtype: 'user-details',
                    itemId: 'userDetails'
                },
                {
                    xtype: 'membership-details',
                    itemId: 'membershipDetails',
                    hideMemberImage: true
                },
                {
                    xtype: 'container',
                    itemId: 'createContainer',
                    layout: {
                        type: 'vbox',
                        align: 'center',
                        pack: 'center'
                    },
                    items: [
                        {
                            xtype: 'button',
                            itemId: 'createMemberBtn',
                            text: 'Create Member'
                        }
                    ]
                }
            ],
            
            dockedItems: [{
                xtype: 'toolbar',
                dock: 'bottom',
                layout: {
                    type: 'hbox',
                    pack: 'start',
                    align: 'stretch'
                },
                items: [
                    { 
                        xtype: 'button', 
                        itemId: 'backBtn',
                        text: 'Back',
                        disabled: true
                    },
                    '->',
                    { 
                        xtype: 'button',
                        itemId: 'nextBtn',
                        text: 'Next'
                    }
                ]
            }]
        });
        
        this.callParent(arguments);
    }
    
});