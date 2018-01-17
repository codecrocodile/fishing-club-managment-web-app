Ext.define('GF.view.users.UserDetailsScreen', {
    extend: 'Ext.panel.Panel',
    requires:[
        'Ext.tab.Panel',
        'GF.view.users.UserDetails',
        'GF.view.users.MembershipDetails',
        'GF.view.users.PaymentHistory'
    ],
    xtype: 'user-details-screen',
    
    layout: {
        type: 'border'
    },
    
    items: [
        {
        	xtype: 'panel',
        	region: 'west',
        	split: true,
        	title: 'Members List',
        	width: 300,
        	layout: 'fit',
            tools: [
                { 
                    type: 'plus',
                    itemId: 'newMemberBtn'
                }
            ],
        	items:[
	        	{
	        		xtype: 'panel',
	                layout: {
	                    type: 'vbox',
	                    align: 'stretch' // required to stretch child items to width of container in fit layout
	                },
	                items: [
	                    {
	                        xtype: 'gridpanel',
	                        itemId: 'membersGrid',
	                        store: 'members.Member',
	                        columns:[
                                {
                                    text: 'Forname',
                                    dataIndex: 'forename',
                                    minWidth: 100,
                                    flex: 1
                                },
                                {
                                    text: 'Surname',
                                    dataIndex: 'surname',
                                    minWidth: 100,
                                    flex: 1
                                }
	                        ]
	                    }
	                ]
	        	}
        	]
        },
        {
        	xtype: 'tabpanel',
        	itemId: 'adminTabPanel',
        	region: 'center',
        	autoScroll: true,
        	overflowY: 'scroll',
            tools:[
                { 
                    type: 'save', 
                    itemId: 'saveMemberBtn'
                },
                { 
                    type: 'close', 
                    itemId: 'deleteMemberBtn',
                },
                { 
                    type: 'refresh',
                    itemId: 'resetMemberBtn'
                }
            ],
            items: [
	            {
            		xtype: 'panel',
            		itemId: 'adminMemberDetails',
            		title: 'Member Details',
            		layout: 'hbox',
            		minWidth: 700,
            		minHeight: 550,
            		items: [
            		    {
        	            	xtype: 'user-details',
        	            	itemId: 'userDetailsAdminForm',
        	            	flex: 1
            		    },
            		    {
        	            	xtype: 'membership-details',
        	            	itemId: 'membershipDetailsAdminForm',
        	            	flex: 1
            		    }
            		]
	            },
	            {
	            	title: 'Payment',
	            	xtype: 'payment-panel',
	            	itemId: 'adminPaymentPanel',
	            	hidden: true,
	            	height: 500
	            }
            ]
        }
    ]
});