Ext.define('GF.model.admin.applications.MembershipApplication', {
    extend: 'Ext.data.Model',
    
    fields: [
        { name: 'id', type: 'int' },
        { name: 'dateOfApplication', type: 'auto' },
        
        { name: 'forename', type: 'auto' },
        { name: 'surname', type: 'auto' },
        { name: 'gender', type: 'auto' },
        
        { name: 'membershipType', type: 'auto' },
        { name: 'applicationMessage', type: 'auto' }
    ]
});
