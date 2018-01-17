Ext.define("GF.view.members.home.HomeScreen", {
    extend: 'Ext.panel.Panel',
    xtype: 'home-screen',
    requires: [
        'GF.view.members.home.NoticeBoard'           
    ],
    
    layout: 'border',
    
    initComponent: function() {
        var me = this;

        Ext.apply(this, me, {
            items:[
               // center panel    
               {
                   xtype: 'panel',
                   title: 'Message Board',
                   layout: {
                       type: 'vbox',
                       align: 'center',
                       pack: 'start'    
                   },
                   region: 'center',
                   autoScroll: true,
                   defaults: {
                       width: 600    
                   },
                   items: [
                       {
                           xtype: 'panel',
                           layout: 'fit',
                           items: [
                               {
                                   xtype: 'textareafield',
                                   itemId: 'postEntryTxa',
                                   grow: true,
                                   name: 'message',
                                   emptyText: 'Whats on your mind?',
                                   maxLength: 500,
                                   enforceMaxLength: true,
                                   flex: 1
                               }
                           ],
                           dockedItems: [
                               {
                                   xtype: 'toolbar',
                                   dock: 'bottom',
                                   layout: {
                                       type: 'hbox',
                                       pack: 'end'
                                   },
                                   style: {
                                       backgroundColor: '#848484'
                                   },
                                   items: [
                                       {
                                           xtype: 'button',
                                           itemId: 'postBtn',
                                           text: 'Post'
                                       }
                                   ]
                               }
                           ]
                       },
                       {
                           xtype: 'noticeboard',
                           itemId: 'noticeboard',
                           store: 'home.Post',
                           padding: '0 0 100 0'
                       }
                   ]
               },
               
               // east panel
               {
                   xtype: 'panel',
                   region: 'east',   
                   split: true,
                   width: 500, // must give width or layout will fail
                   layout: {
                       type: 'vbox',
                       align: 'stretch'
                   },
                   items: [
                       {
                           xtype: 'panel',
                           title: 'Alerts, Events and Messages',
                           items: [
                               {
                                   html: '<div>Alerts: 0</div><div>Events: 0</div><div>Messages: 0</div>'
                               }
                           ],
                           height: 200
                       },
                       {
                            xtype: 'gridpanel',
                            title: 'Club Fishing Log',
                            cls: 'eventSummaryGrid',
                            store: 'members.catchrecord.CatchLog',
                            autoScroll: true,
                            flex: 1,
                            columns:[
                                {
                                    text: 'Name',
                                    dataIndex: 'memberName',
                                    minWidth: 100,
                                    flex: 1,
                                    renderer: this.formatTitle
                                },
                                {
                                    xtype: 'datecolumn',   
                                    format: GF.util.AppConfig.DATE_FORMAT,
                                    text: 'Date',
                                    dataIndex: 'date',
                                    minWidth: 130,
                                    width: 130
                                },
                                {
                                    xtype: 'numbercolumn', 
                                    format:'0,000',
                                    text: 'Fish Caught',
                                    dataIndex: 'numberOfFish',
                                    minWidth: 130,
                                    width: 130
                                }
                            ],
                            features: [
                                {
                                    ftype: 'rowbody',
                                    setupRowData: function(record, rowIndex, rowValues) {
                                        var headerCt = this.view.headerCt,
                                            colspan = headerCt.getColumnCount();
                                        
                                        if (record.get("notes") == '') {
                                            record.set("notes", "No notes entered"); 
                                        }
            
                                        // Usually you would style the my-body-class in CSS file
                                        Ext.apply(rowValues, {
                                            rowBody: '<div>'+ record.get("notes") + '</div> <div style="margin-top: 10px; color: #333333;"><i>' + record.get("waters") + '</i></div>'  ,
                                            rowBodyCls: this.rowBodyCls,
                                            rowBodyColspan: colspan
                                        });
                                    }
                                },
                                {
                                    ftype: 'rowwrap'
                                } 
                            ],
                            bbar: Ext.create('Ext.PagingToolbar', {
                                store: 'members.catchrecord.CatchLog',
                                displayInfo: true,
                                displayMsg: 'Displaying {0} - {1} of {2}',
                                emptyMsg: "No catch records to display"
                            })
                        }
                   ]
               }
            ]
        });
        
        this.callParent(arguments);
    },
   
    /**
     * Title renderer
     * @private
     */
    formatTitle: function(value, p, record){
        return Ext.String.format('<div class="topic"><b>{0}</b></div>', value);
    }
});


