Ext.define('GF.util.AppMask', {
    singleton:true,

    callCount: 0,
    
    show: function(maskMessage) {
        if (this.callCount === 0) {
            if (!maskMessage) {
                Ext.getBody().mask('loading...');
            } else {
                Ext.getBody().mask(maskMessage);
            }
        }
        this.callCount = this.callCount + 1;
    },
    
    hide: function() {
        this.callCount = this.callCount - 1;
        
        if (this.callCount === 0) {
            Ext.getBody().unmask();
        }
    }
    
});