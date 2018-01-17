Ext.define("GF.view.users.MembershipDetails", {
    extend: 'Ext.form.Panel',
    xtype: 'membership-details',
    requires:[
        'GF.view.users.ImageHolder'          
    ],
    
    hideMemberImage: false,
    
    initComponent: function() {
    	var me = this;

    	var membershipTypesStore = Ext.create('Ext.data.Store', {
    	    fields: ['id', 'name'],
    	    data : [
                {"id": 1, "name": 'Standard Member'},
                {"id": 2, "name": 'Senior Member'},
                {"id": 3, "name": 'Junior Member'}
    	    ]
    	});
    	
    	Ext.apply(this, me, {
            defaults: {
                padding: '0 0 0 5'
            },
    		items:[
    		    {
    		    	xtype: 'combobox',
    		    	name: 'membershipTypeId',
    		    	hiddenName: 'id',
    		    	width: 150,
    		    	fieldLabel: 'Member Type',
    		        labelAlign: 'top',
    		        store: membershipTypesStore,
    		        valueField: 'id',
    		        displayField: 'name',
    		        autoSelect: true,
    		        forceSelection: true,
    		        queryMode: 'local',
                    listeners: {
                        afterrender: function(combo) {
                            var recordSelected = combo.getStore().getAt(0);                     
                            combo.setValue(recordSelected.get('id'));
                        }
                    }
    		    },
    		    {
		            xtype: 'datefield',
		            itemId: 'startDateDf',
		            name: 'startDate',
		            width: 120,
		            fieldLabel: 'Date Joined',
		            labelAlign: 'top',
	                format: GF.util.AppConfig.DATE_FORMAT,
	                invalidText: GF.util.AppConfig.INVALID_DATE_FORMAT_TEXT,
	                allowBlank: false,
	                value: new Date(),
		            maxValue: new Date()  // limited to the current date or prior
    		    },
    		    {
    		    	xtype: 'image-holder',
    		    	itemId: 'imageHolder',
    		    	width: 310,
    		    	hidden: this.hideMemberImage
    		    }
    		]
    	});
    	
    	this.callParent(arguments);
    }

});