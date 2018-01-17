Ext.define('GF.view.members.profile.ImageCropWindow', {
    requires : [ 'Ext.Window' ],
    extend : 'Ext.Window',
    title : 'Image Crop Utility',
    modal : true,
    width: 500,
    height: 500,
    minWidth: 200,
    minHeight: 200, 
    maxWidth: 1000,
    maxHeight: 600, 
    autoScroll: true,
    
    cropData : null,
    imageUrl : '',

    initComponent : function() {
        var me = this;

        // footer bar with save and cancel
        me.fbar = {
            xtype : 'toolbar',
            items : [ 
                {
                    xtype : 'button',
                    itemId : 'cancelBtn',
                    text : 'cancel',
                    listeners: {
                        click: function() {
                            me.close();
                        }
                    }
                }, 
                {
                    xtype : 'button',
                    itemId : 'saveBtn',
                    text : 'save',
                    listeners: {
                        click: function() {
                            me.saveCrop();
                        }
                    }
                } 
            ]
        };
        
        Ext.apply(this, me, {
            items: [
                {
                    xtype: 'image', 
                    src: me.imageUrl,
                    listeners: {
                        afterrender: function ( comp, eOpts ){
                            $(this.getEl().dom).Jcrop({
                                aspectRatio: 1,
                                onSelect: me.setCropData,
                                onChange: me.setCropData
                            });
                        }
                    }
                }
            ]
        });
        
        this.callParent(arguments);
    },
    
    setCropData: function(coords) {
        var photoScreenController  = GF.app.getController('members.profile.PhotoScreen');
        photoScreenController.cropData = coords;
    },

    saveCrop : function() {
        if (this.fireEvent('save', this) === false) {
            return this;
        }
        this.close();
    }
});