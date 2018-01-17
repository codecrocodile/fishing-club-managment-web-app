Ext.define('GF.view.members.home.CatchLogGrid', {
    extend: 'Ext.panel.Panel',
    xtype: 'catch-log-grid',
    requires: [
        'Ext.ux.PreviewPlugin'
    ],
    
    initComponent: function() {
        var me = this;
        
        Ext.apply(this, me, {
            cls: 'eventSummaryGrid',
            
          layout: {
          type: 'vbox',
          align : 'stretch',
          pack  : 'start'
      },
      autoScroll: true,
            items: [
                {
                    xtype: 'gridpanel',
                    store: 'members.catchrecord.CatchLog',
                    
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
                            minWidth: 100,
                            flex: 1
                        },
                        {
                            xtype: 'numbercolumn', 
                            format:'0,000',
                            text: 'Fish Caught',
                            dataIndex: 'numberOfFish',
                            minWidth: 100,
                            flex: 1
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
                                    rowBody: '<div>'+ record.get("notes") + '</div> <div><i>' + record.get("waters") + '</i></div>'  ,
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