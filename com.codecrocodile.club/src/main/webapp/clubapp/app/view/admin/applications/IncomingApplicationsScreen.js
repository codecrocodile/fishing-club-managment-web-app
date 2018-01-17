Ext.define("GF.view.admin.applications.IncomingApplicationsScreen", {
    extend: 'Ext.panel.Panel',
    xtype: 'incoming-applications-screen',
    requires: [
        'GF.view.admin.applications.ApplicationSummaryGrid'
    ],

    layout: {
        type: 'border'
    },
    initComponent: function () {
    	var me = this;

    	Ext.apply(this, me, {
    		
    		items:[
    		    {
    		    	xtype: 'application-summary-grid',
    		    	region: 'north',
    		    	title: 'New Applications',
    	        	split: true,
    	        	collapsible: true,
    	        	minHeight: 150,
    	        	maxHeight: 300
    		    },
    		    {
    		    	xtype: 'panel',
    		    	region: 'center',
    		    	layout: {
    		    		type: 'border'
    		    	},
    		    	autoScroll: true,
    		    	items:[
    		    	    {
    		    	    	xtype: 'panel',
    		    	    	title: 'Application Details',
    		    	    	region: 'center',
//    		    	    	flex: 2,
    		    	    	autoScroll: true,
    	    		    	layout: {
    	    		    		type: 'hbox',
    	    		    		align: 'top'
    	    		    	},
    		    	    	items: [
    		    	    	    {
    		    	    	    	xtype:'user-details',
    		    	    	    	flex: 1
    		    	    	    },
    		    	    	    {
    		    	    	    	xtype:'membership-details',
    		    	    	    	flex: 1
    		    	    	    }
    		    	    	]
    		    	    },
    		    	    {
    		    	    	xtype: 'panel',
    		    	    	title: 'Approval',
    		    	    	region: 'east',
//    		    	    	flex: 1,
    		    	    	bodyStyle: {
    		    	    	    background: '#ddd'
    		    	    	},
    		    	    	items: [
		    	    		    {
		    	    	            xtype      : 'fieldcontainer',
		    	    	            fieldLabel : 'Approved',
		    	    	            labelAlign: 'left', 	
		    	    	            defaultType: 'radiofield',
		    	    	            defaults: {
		    	    	            	padding: '0 10 0 0'
		    	    	            },
		    	    	            layout: 'hbox',
		    	    	            items: [
										{
											boxLabel  : 'No',
										    inputValue: 'false',
										    selected: true
										},
		    	    	                {
		    	    	                    boxLabel  : 'Yes',
		    	    	                    inputValue: 'true'
		    	    	                }
		    	    	            ]
		    	    	        }
    		    	    	]
    		    	    }
    		    	]
    		    }
            ]
    	});
    	
    	this.callParent();
    }
});