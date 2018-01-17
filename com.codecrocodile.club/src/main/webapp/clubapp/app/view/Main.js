Ext.define('GF.view.Main', {
    extend: 'Ext.panel.Panel',
    requires:[
        'Ext.layout.container.Border',
        'Ext.ux.menu.MenuBar',
        'Ext.ux.window.Notification'
    ],
    xtype: 'app-main',
    itemId: 'app-main',

    layout: {
        type: 'border'
    },
    items: [
        {
        	xtype: 'panel',
        	region: 'north',
        	height: 45,
        	layout: {
        	    type: 'hbox',
        	    align: 'stretch'
        	},
            items: [
                {
                    xtype: 'container',
                    layout: {
                        type: 'hbox',
                        align: 'middle',
                        pack: 'left'
                    },
                    style: {
                       backgroundColor: '#D4926A'  
                    },
                    flex: 1,
                    defaults: {
                        margin: '0 15 0 5',
                        width: 40,
                        height: 40
                    },
                    items: [
                        { 
                            xtype: 'button',
                            id: 'viewport.homeScreenBtn',
                            itemId: 'homeScreenBtn',
                            tooltip : 'Home Page',
                            iconCls:  'myimagebutton_home'
                        },
                        { 
                            xtype: 'button',
                            id: 'viewport.fishingLogBtn',
                            itemId: 'fishingLogBtn',
                            tooltip: 'Fishing Log',
                            iconCls: 'myimagebutton_fishinglog'
                        },  
                        { 
                            xtype: 'button',
                            itemId: 'profileScreenBtn',
                            tooltip: 'My Profile',
                            iconCls: 'myimagebutton_profile'
                        },  
                        { 
                            xtype: 'splitbutton',
                            tooltip: 'Hello World',
                            iconCls: 'myimagebutton_settings',
                            width: 70,
                            menu: [
                                {
                                    itemId: 'membersScreenBtn',
                                    text: 'Members'
                                },
                                {
                                    itemId: 'eventsBtn',
                                    text: 'Events'
                                },
                                {
                                    icon: false,
                                    text: 'Asset Management',
                                    menu: [
                                        {
                                            text: 'Bookeeping'
                                        },
                                        {
                                            text: 'Suppliers/Contacts'
                                        },
                                        {
                                            text: 'Repairs'
                                        }                                       
                                    ]
                                },
                                {
                                    text: 'Reports'
                                },
                                {
                                    text: 'Application Settings',
                                    itemId: 'settingsBtn'
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype: 'container',
                    layout: {
                        type: 'hbox',
                        align: 'middle',
                        pack: 'right'
                    },
                    style: {
                        backgroundColor: '#D4926A'  
                    },
                    defaults: {
                        margin: '0 5 0 5',
                        width: 40,
                        height: 40
                    },
                    items: [
                        { 
                            xtype: 'button',
                            itemId: 'saveBtn',
                            tooltip: 'Save changes',
                            iconCls: 'myimagebutton_save'
                        },
                        { 
                            xtype: 'button',
                            itemId: 'cancelBtn',
                            tooltip : 'Cancel changes',
                            iconCls: 'myimagebutton_cancel'
                        }
                    ]
                }
            ]
        },
        {
			xtype: 'container',
			itemId: 'screen-container',
			region: 'center',
			layout: 'card',
			activeItem: 0,
			items: [
	            {
	                xtype: 'home-screen',
	                itemId: 'home-screen'
				},
                {
                    xtype: 'catch-record-screen',
                    itemId: 'catch-record-screen'
                },
                {
                    xtype: 'profile-screen',
                    itemId: 'profile-screen'
                },
                {
                    xtype: 'user-details-screen',
                    itemId: 'user-details-screen'
                },
                {
                    xtype: 'incoming-applications-screen',
                    itemId: 'incoming-applications-screen'
                },
                {
                    xtype: 'event-screen',
                    itemId: 'event-screen'
                },
                {
                    xtype: 'settings-screen',
                    itemId: 'settings-screen'
                }
			]
        }
     ]
});