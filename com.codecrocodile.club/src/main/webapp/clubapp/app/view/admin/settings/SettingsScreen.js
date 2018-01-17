Ext.define('GF.view.admin.settings.SettingsScreen', {
    extend: 'Ext.tab.Panel',
    xtype: 'settings-screen',
    title: 'Application Settings',
    
    initComponent: function() {
        var me = this;
        
        Ext.create('Ext.data.Store', {
            storeId:'simpsonsStore',
            fields:['name', 'email', 'phone'],
            data:{'items':[
                { 'name': 'Lisa',  "email":"lisa@simpsons.com",  "phone":"555-111-1224"  },
                { 'name': 'Bart',  "email":"bart@simpsons.com",  "phone":"555-222-1234" },
                { 'name': 'Homer', "email":"home@simpsons.com",  "phone":"555-222-1244"  },
                { 'name': 'Marge', "email":"marge@simpsons.com", "phone":"555-222-1254"  }
            ]},
            proxy: {
                type: 'memory',
                reader: {
                    type: 'json',
                    root: 'items'
                }
            }
        });
        
        Ext.apply(me, this, {
            tools:[
                { type:'save' },
                { type:'close' },
                { type:'refresh' }
            ],
               
            items:[
                {
                    xtype: 'container',
                    title: 'Club Details',
                    
                    layout: {
                        type: 'hbox',
                        align: 'middle',
                        pack: 'center'
                    },
                    items: [
                        {
                            xtype: 'container',
                            layout: {
                                type: 'hbox'
                            },
                            items: [
                                {
                                    xtype: 'form',
                                    title: 'Contact',
                                    defaults: {
                                        padding: '0 0 0 5',    
                                    },
                                    items: [
                                        {
                                            xtype: 'textfield',
                                            width: 310,
                                            colspan: 2,
                                            name: 'name',
                                            fieldLabel: 'Club Name',
                                            labelAlign: 'top'
                                        },
                                        {
                                            xtype: 'textfield',
                                            width: 310,
                                            colspan: 2,
                                            name: 'address1',
                                            fieldLabel: 'Address Line 1',
                                            labelAlign: 'top'
                                        },
                                        {
                                            xtype: 'textfield',
                                            width: 310,
                                            colspan: 2,
                                            name: 'address2',
                                            fieldLabel: 'Address Line 2',
                                            labelAlign: 'top'
                                        },
                                        {
                                            xtype: 'textfield',
                                            width: 310,
                                            colspan: 2,
                                            name: 'address3',
                                            fieldLabel: 'Address Line 3',
                                            labelAlign: 'top'
                                        },
                                        {
                                            xtype: 'textfield',
                                            width: 100,
                                            colspan: 2,
                                            name: 'postcode',
                                            fieldLabel: 'Postcode',
                                            labelAlign: 'top'
                                        },
                                        {
                                            xtype: 'textfield',
                                            itemId: 'emailTxt',
                                            width: 310,
                                            colspan: 2,
                                            name: 'email',
                                            fieldLabel: 'Email Address',
                                            labelAlign: 'top',
                                            vtype: 'email'
                                        }
                                    ]
                                },
                                {
                                    xtype: 'gridpanel',
                                    title: 'Fishing Venues',
                                    padding: '0 0 0 10',
                                    width: 350,
                                    tools: [
                                        { type: 'plus' }
                                    ],
                                    store: Ext.data.StoreManager.lookup('simpsonsStore'),
                                    columns: [
                                        { text: 'Name',  dataIndex: 'name' },
                                        { text: 'Email', dataIndex: 'email', flex: 1 },
                                        { text: 'Phone', dataIndex: 'phone' }
                                    ]
                                }
                            ]
                        }
                    ]
                },
                {
                    title: 'User Permissions',
                    layout: {
                        type: 'hbox',
                        align: 'middle',
                        pack: 'center'
                    },
                    items: [
                        {
                            xtype: 'container',
                            items: [
                                {
                                    xtype: 'gridpanel',
                                    title: 'Special Permissions',
                                    padding: '0 0 0 10',
                                    width: 350,
                                    height: 350,
                                    tools: [
                                        { type: 'plus' }
                                    ],
                                    store: Ext.data.StoreManager.lookup('simpsonsStore'),
                                    columns: [
                                        { text: 'Name',  dataIndex: 'name' },
                                        { text: 'Email', dataIndex: 'email', flex: 1 },
                                        { text: 'Phone', dataIndex: 'phone' }
                                    ]
                                }
                            ]
                        }       
                    ]
                }

            ]
        });
        
        this.callParent(arguments);
    }
    
});