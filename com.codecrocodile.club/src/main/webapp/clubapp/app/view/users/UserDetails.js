Ext.define('GF.view.users.UserDetails', {
    extend: 'Ext.form.Panel',
    xtype: 'user-details',
    
    layout: {
        type: 'table',
        columns: 2
    },
    
    initComponent: function() {
    	var me = this;
    	
    	Ext.apply(me, this, {
    		defaults: {
    			padding: '0 0 0 5'
    		},
    		items:[
    		    {
    		    	xtype: 'combobox',
    		    	itemId: 'titleCbo',
    		    	colspan: 2,
    		    	width: 100,
    		    	name: 'title',
    		    	fieldLabel: 'Title',
    		        labelAlign: 'top',
    		        forceSelection: true,
    		        store:['Mr','Mrs', 'Miss', 'Dr'],
    		        queryMode: 'local',
    		        listeners: {
    		            afterrender: function(combo) {
    		                var recordSelected = combo.getStore().getAt(0);                     
    		                combo.setValue(recordSelected.get('field1'));
    		            }
    		        }
    		    },
    		    {
    		        xtype: 'textfield',
    		        itemId: 'forenameTxt',
    		        width: 150,
    		        name: 'forename',
    		        fieldLabel: 'Forename',
    		        labelAlign: 'top',
    		        allowBlank: false
    		    },
    		    {
    		        xtype: 'textfield',
    		        itemId: 'surnameTxt',
    		        width: 150,
    		        name: 'surname',
    		        fieldLabel: 'Surname',
    		        labelAlign: 'top',
    		        allowBlank: false
    		    },
    		    {
		            xtype: 'datefield',
		            itemId: 'dobDf',
		            colspan: 2,
		            width: 120,
		            name: 'dob',
		            fieldLabel: 'DOB',
		            labelAlign: 'top',
		            format: GF.util.AppConfig.DATE_FORMAT,
		            invalidText: GF.util.AppConfig.INVALID_DATE_FORMAT_TEXT,
		            allowBlank: false,
		            maxValue: new Date()  // limited to the current date or prior
    		    },
    	        {
    	            xtype: 'radiogroup',
    	            colspan: 2,
    	            width: 150,
    	            fieldLabel: 'Gender',
    	            labelAlign: 'top', 
    	            columns: 2,
    	            vertical: true,
    	            items: [
    	                { boxLabel: 'Male', name: 'gender', inputValue: 'male', checked: true},
    	                { boxLabel: 'Female', name: 'gender', inputValue: 'female'}
    	            ]
    	        },
    		    {
    		        xtype: 'textfield',
    		        width: 310,
    		        colspan: 2,
    		        name: 'address1',
    		        fieldLabel: 'Address Line 1',
    		        labelAlign: 'top'
    		    },
    		    {
    		        xtype: 'textfield',
    		        width: 310,
    		        colspan: 2,
    		        name: 'address2',
    		        fieldLabel: 'Address Line 2',
    		        labelAlign: 'top'
    		    },
    		    {
    		        xtype: 'textfield',
    		        width: 310,
    		        colspan: 2,
    		        name: 'address3',
    		        fieldLabel: 'Address Line 3',
    		        labelAlign: 'top'
    		    },
    		    {
    		        xtype: 'textfield',
    		        width: 100,
    		        colspan: 2,
    		        name: 'postcode',
    		        fieldLabel: 'Postcode',
    		        labelAlign: 'top'
    		    },
			    {
			        xtype: 'textfield',
			        width: 150,
			        name: 'homePhone',
			        fieldLabel: 'Home Phone Number',
			        labelAlign: 'top'
			    },
			    {
			        xtype: 'textfield',
			        width: 150,
			        name: 'mobilePhone',
			        fieldLabel: 'Mobile Phone Number',
			        labelAlign: 'top'
			    },
    		    {
    		        xtype: 'textfield',
    		        itemId: 'emailTxt',
    		        width: 310,
    		        colspan: 2,
    		        name: 'email',
    		        fieldLabel: 'Email Address',
    		        labelAlign: 'top',
    		        vtype: 'email'
    		    }
    		]
    	});
    	
    	this.callParent(arguments);
    }
    
});