Ext.define('GF.store.admin.applications.MembershipApplication', {
	extend: 'Ext.data.Store',
	model: 'GF.model.admin.applications.MembershipApplication',
	data:[
	      {
	    	  id: 1, 
	    	  dateOfApplication: '31/05/2014', 
	    	  forename: 'Chris', 
	    	  surname: 'Hatton', 
	    	  gender: 'Male', 
	    	  membershipType: 1, 
	    	  applicationMessage: 'This is a message from the user'
	      },
	      {id: 2, dateOfApplication: '31/05/2014', forename: 'Mark', surname: 'Hatton', gender: 'Male', membershipType: 1, applicationMessage: 'This is a message from the user'},
	      {id: 3, dateOfApplication: '31/05/2014', forename: 'Jen', surname: 'Williams', gender: 'Female', membershipType: 1, applicationMessage: 'This is a message from the user'},
	      {id: 4, dateOfApplication: '31/05/2014', forename: 'Alan', surname: 'Kerr', gender: 'Male', membershipType: 1, applicationMessage: 'This is a message from the user'}
	]
});