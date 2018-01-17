Ext.define('GF.view.members.profile.PhotoScreen', {
    extend: 'Ext.panel.Panel',
    xtype: 'photo-screen',
    requires: ['Ext.ux.ImageCrop'],
    itemId: 'photoScreen',
    layout: {
        type: 'vbox',
        align: 'center',
        pack: 'center'
    },
    
    initComponent: function() {
        var me = this;
        
        Ext.apply(this, me, {
            items: [
                {
                    id: 'members-profile-imageForm',
                    itemId: 'members-profile-imageForm',
                    xtype: 'form',
                    items: [{
                        xtype: 'filefield',
                        name: 'photo',
                        width: 265,
                        fieldLabel: 'Photo',
                        labelAlign: 'top',
                        labelWidth: 50,
                        allowBlank: false,
                        anchor: '100%',
                        buttonText: 'Select Photo...'
                    }],
                    buttons: [{
                        id: 'members-profile-uploadbtn',
                        itemId: 'members-profile-uploadbtn',
                        text: 'Upload'
                    }]
                },
                {
                    xtype: 'panel',
                    items: [
                        {
                            xtype: 'image',
                            id: 'memberImg',
                            itemId: 'memberImg',
                            width: 265,
                            height : 265,
                            src: './resources/images/placeholder-photo.png'
                        }
                    ],
                    buttons: [{
                        id: 'members-profile-editphoto',
                        itemId: 'members-profile-editphoto',
                        text: 'Edit Photo'
                    }]
                }
            ]
            
        });
        
        this.callParent(arguments);
    }
});

