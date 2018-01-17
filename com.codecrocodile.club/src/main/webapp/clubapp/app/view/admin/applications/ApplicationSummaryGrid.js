Ext.define('GF.view.admin.applications.ApplicationSummaryGrid', {
	extend: 'Ext.panel.Panel',
	xtype: 'application-summary-grid',
	requires: [
//	    'GF.store.admin.applications.MembershipApplication'
	],
	
	initComponent: function() {
		var me = this;
		
		Ext.apply(this, me, {

			layout: {
				type: 'vbox',
				align: 'stretch' // required to stretch child items to width of container in fit layout
			},
			autoScroll: true,
			
			items: [
			    {
			    	xtype: 'gridpanel',
			    	store: 'admin.applications.MembershipApplication',
			    	columns:[
			    	        {
			    	        	text: 'Application Date',
			    	        	dataIndex: 'dateOfApplication',
			    	        	minWidth: 100,
			    	 	    	flex: 1
			    	        },
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
			    	 	    },
			    	 	    {
			    	 	    	text: 'Member Type',
			    	 	    	dataIndex: 'membershipType',
			    	 	    	minWidth: 100,
			    	 	    	flex: 1
			    	 	    }
			        ]
			    }
			]
		});
		this.callParent(arguments);
	}
});