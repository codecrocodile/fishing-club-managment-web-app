Ext.define("GF.view.admin.events.EventScreen", {
    extend: 'Ext.panel.Panel',
    xtype: 'event-screen',
    requires: [
        'GF.view.admin.events.EventSummaryGrid',
        'GF.view.admin.events.ResponseGrid'
    ],
    layout: {
        type: 'border'
    },
    
    initComponent: function () {
        var me = this;

        Ext.apply(this, me, {
            items:[
                {
                    xtype: 'panel',
                    region: 'west',
                    split: true,
                    title: 'Events',
                    width: 500,
                    layout: 'fit',
                    tools: [
                            { 
                                xtype:'button', 
                                itemId: 'newEventBtn',
                                text: 'New Event' 
                            }
                        ],
                    items:[
                        {
                            xtype: 'gridpanel',
                            itemId: 'eventSummaryGrid',
                            store: 'admin.events.Event',
                            cls: 'eventSummaryGrid',
                            autoScroll: true,
                            viewConfig: {
                                emptyText: 'No events entered',
                                deferEmptyText: false,  
                                  
                                trackOver: false,
                                stripeRows: false,
                                plugins: [
                                    {
                                        ptype: 'preview',
                                        bodyField: 'description',
                                        expanded: true,
                                        pluginId: 'eventSummaryGridPreview'
                                    }
                                ]
                           },
                           columns:[
                               {
                                   text: 'Event',
                                   dataIndex: 'name',
                                   minWidth: 100,
                                   flex: 1,
                                   renderer: this.formatTitle
                               },
                               {
                                   text: 'Status',
                                   dataIndex: 'status',
                                   minWidth: 100,
                                   width: 100
                               },
                               {
                                   xtype: 'datecolumn',   
                                   format: GF.util.AppConfig.DATE_FORMAT,
                                   text: 'Date',
                                   dataIndex: 'eventDate',
                                   minWidth: 100,
                                   width: 100
                               },
                               {
                                   text: 'Time',
                                   dataIndex: 'eventTime',
                                   minWidth: 100,
                                   width: 100
                               }
                           ],
                           bbar: Ext.create('Ext.PagingToolbar', {
                               store: 'admin.events.Event',
                               displayInfo: true,
                               displayMsg: 'Displaying {0} - {1} of {2}',
                               emptyMsg: "No events to display"
                           })
                       }
                   ]
               },
               {
                   xtype: 'panel',
                   region: 'center',
                   layout: {
                       type: 'border'
                   },
                   items:[
                       {
                           xtype: 'form',
                           itemId: 'eventDetailsForm',
                           title: 'Event Details',
                           tools:[
                               { 
                                   xtype: 'button', 
                                   itemId: 'saveEventBtn',
                                   text: 'Save' 
                               },
                               { 
                                   xtype: 'button', 
                                   itemId: 'deleteEventBtn',
                                   text: 'Delete' 
                               },
                               { 
                                   xtype: 'button',
                                   itemId: 'resetEventBtn',
                                   text: 'Reset' 
                               }
                           ],
                           region: 'north',
                           items: [
                               {
                                   xtype: 'container',
                                   layout: {
                                       type: 'hbox',
                                       align: 'stretch'
                                   },
                                   padding: '5 5 5 5',
                                   defaults: {
                                      
                                   },
                                   items: [
                                       {
                                           xtype: 'datepicker',
                                           itemId: 'eventDataPicker',
                                           minDate: new Date(),
                                           showToday: false,
                                           name: 'eventDate'
                                       },
                                       {
                                           xtype: 'container',
                                           padding: '5 5 5 5',
                                           items: [
                                               {
                                                   xtype: 'combobox',
                                                   fieldLabel: 'Status',
                                                   labelAlign: 'top',
                                                   store:['Proposed','Scheduled', 'Canceled'],
                                                   queryMode: 'local',
                                                   name: 'status'
                                               },
                                               {
                                                   xtype: 'textfield',
                                                   name: 'eventTime',
                                                   fieldLabel: 'Time',
                                                   labelAlign: 'top',
                                                   allowBlank: false,
                                                   maxLength: 20,
                                                   enforceMaxLength: true,
                                                   regex: /^\d{1,2}:\d{2}([ap]m)?$/,
                                                   regexText: 'Must be time format e.g 15:55'
                                               },
                                               {
                                                   xtype: 'textfield',
                                                   name: 'name',
                                                   fieldLabel: 'Title',
                                                   labelAlign: 'top',
                                                   allowBlank: false,
                                                   maxLength: 20,
                                                   enforceMaxLength: true
                                               },  
                                               {
                                                   xtype: 'textfield',
                                                   name: 'location',
                                                   fieldLabel: 'Location',
                                                   labelAlign: 'top',
                                                   allowBlank: false,
                                                   maxLength: 200,
                                                   enforceMaxLength: true
                                               }
                                           ]
                                       },
                                       {
                                           xtype: 'textarea',
                                           name: 'description',
                                           fieldLabel: 'Description',
                                           labelAlign: 'top',
                                           allowBlank: false,
                                           padding: '5 5 5 5',
                                           flex: 1,
                                           maxLength: 500,
                                           enforceMaxLength: true
                                       }
                                   ]
                               }
                           ]
                       },
                       {
                           xtype: 'panel',
                           title: 'Responses',
                           region: 'center',
                           split: true,
                           layout: 'fit',
                           items:[
                               {
                                    xtype: 'gridpanel',
                                   itemId: 'eventResponseGrid',
                                   viewConfig: {
                                       emptyText: 'No responses recieved',
                                       deferEmptyText: false
                                   },
                                   columns:[
                                       {
                                           text: 'Name',
                                           dataIndex: 'userName',
                                           minWidth: 100,
                                           width: 200
                                       },
                                       {
                                           text: 'Response',
                                           dataIndex: 'response',
                                           minWidth: 100,
                                           width: 200
                                       },
                                       {
                                           text: 'Comment',
                                           dataIndex: 'comment',
                                           minWidth: 100,
                                           flex: 1
                                       }
                                   ]
                               }
                           ]
                       }
                   ]
               }
            ]
        });
        
        this.callParent(arguments);
    },
    
    /*
     * Title renderer
     * @private
     */
    formatTitle: function(value, p, record){
        return Ext.String.format('<div class="topic"><b>{0}</b><span class="event">{1}</span></div>', value, record.get('userName'));
    }
});