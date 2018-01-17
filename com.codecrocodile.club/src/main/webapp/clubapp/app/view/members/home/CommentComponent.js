Ext.define('GF.view.members.home.CommentComponent', {
    extend: 'Ext.panel.Panel',
    xtype: 'widget.comment-component',
    
    config: {
        cancelCallBack: null,
        cancelCallBackScope: null,
        postCommentText: null
    },
     
    initComponent: function() {
        var me = this;
        
        Ext.apply(this, me, {
            layout: {
                type: 'hbox',
                align: 'stretch'
            },
            padding: '5 5 5 5',
            items: [
                {
                    xtype: 'textareafield',
                    itemId: 'commentTxa',
                    grow: true,
                    rows: 1,
                    flex: 1,
                    padding: '5 5 5 5',
                    value: this.postCommentText,
                    maxLength: 500,
                    enforceMaxLength: true
                }
            ],
            
            dockedItems: [{
                xtype: 'toolbar',
                dock: 'bottom',
                layout: {
                    type: 'hbox',
                    align: 'middle',
                    pack: 'end'
                },
                items: [
                    {
                        xtype: 'button',
                        text: 'Post',
                        handler: function() {
                            var commentComponent = this.up('panel');
                            commentComponent.fireEvent('post', commentComponent.query('#commentTxa')[0].getValue());
                            commentComponent.destroy();
                        }
                    },
                    {
                        xtype: 'button',
                        text: 'Cancel',
                        handler: function() {
                            var commentComponent = this.up('panel');
                            commentComponent.fireEvent('cancel');
                            commentComponent.destroy();
                        }
                    }
                ]
            }]
            
        });
        
        this.callParent(arguments);
    }
    
});
