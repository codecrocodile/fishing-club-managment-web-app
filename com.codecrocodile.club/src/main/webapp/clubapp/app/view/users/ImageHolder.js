Ext.define("GF.view.users.ImageHolder", {
    extend: 'Ext.panel.Panel',
    xtype: 'image-holder',
    itemId: 'image-holder',
    
    padding: '10 0 0 0',
    
    initComponent: function() {
    	
    	var me = this;

    	Ext.apply(me, this, {
    		items:[
    		    {
    		    	xtype: 'image',
    		    	itemId: 'memberPhoto',
    		    	src: './resources/images/placeholder-photo.png',
    		        autoEl: 'div', // wrap in a div,
    		        alt: 'Member Photo',
    		        imgCls: 'member-photo' 
    		    }
    		]
    	});
    	
    	this.callParent(arguments);
    },
    
    setImage: function(src) {
        /*
         * When using the image the image url we must append the timestamp
         * so that the browser does not cache the image we are working with.
         * This way we can edit and reuse the same image name/url
         */
        if (src) {
            this.queryById('memberPhoto').setSrc('rest/image/' + src + '?dc=' + new Date().getTime());
        } else {
            this.clearImage();
        }
    },
    
    clearImage: function() {
        this.queryById('memberPhoto').setSrc('./resources/images/placeholder-photo.png');
    }
    
});