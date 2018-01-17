Ext.define('GF.view.members.profile.ProfileScreen', {
    extend: 'Ext.panel.Panel',
    xtype: 'profile-screen',
    itemId: 'profileScreen',
    requires:[
        'Ext.tab.Panel',
        'GF.view.members.profile.PhotoScreen'
    ],
    title: 'My Profile',
    layout: 'fit',
    items: [
        {
            xtype: 'tabpanel',
            autoScroll: true,
            overflowY: 'scroll',
            items: [
                {
                    xtype: 'panel',
                    title: 'Details',
                    layout: {
                        type: 'hbox',
                        align: 'middle',
                        pack: 'center'
                    },
                    items: [
                        {
                            xtype: 'user-details',
                            itemId: 'userDetailsForm'
                        }
                    ]
                },
                {
                    xtype: 'photo-screen',
                    title: 'Photo'
                },
                {
                    title: 'Payment History',
                    xtype: 'payment-panel',
                    hidden: true
                }
            ]
        }
    ]
});