/**
 * Details for a club member.
 */
Ext.define('GF.model.members.Member', {
    extend: 'Ext.data.Model',
    
    idProperty: 'userId',
    
    fields: [
        { name: 'userId', type: 'int', useNull: true }, // if when inserting we want to use proper POST then put null as id so that it isn't included as path param
        { name: 'title', type: 'string', useNull: true },
        { name: 'forename', type: 'string', useNull: true },
        { name: 'surname', type: 'string', useNull: true },
        { name: 'dob', type: 'date', useNull: true },
        { name: 'gender', type: 'string', useNull: true },
        { name: 'address1', type: 'string', useNull: true },
        { name: 'address2', type: 'string', useNull: true },
        { name: 'address3', type: 'string', useNull: true },
        { name: 'postcode', type: 'string', useNull: true },
        { name: 'homePhone', type: 'string', useNull: true },
        { name: 'mobilePhone', type: 'string', useNull: true },
        { name: 'email', type: 'string', useNull: true },
        { name: 'photo', type: 'string', useNull: true },
        { name: 'startDate', type: 'date', useNull: true},
        { name: 'membershipTypeId', type: 'int', useNull: true }
    ],

    validations: [
        {field: 'forename', type: 'presence'},
        {field: 'forename', type: 'length', min: 2},
        {field: 'surname', type: 'presence'},
        {field: 'surname', type: 'length', min: 2},
        {field: 'dob', type: 'presence'},
        {field: 'email', type: 'presence'},
        {field: 'email', type: 'email'}
    ],
    
    autoLoad: false,
    
    proxy: {
        type : "rest",
        url: "rest/member-service"
    }
    
});