Ext.define('GF.model.accounting.Invoice', {
    extend: 'Ext.data.Model',
    requires: ['GF.model.accounting.InvoiceLine'],
    
    idProperty: 'invoiceId',
    
    fields: [
        { name: 'invoiceId', type: 'int' },
        { name: 'orderId', type: 'int' },
        { name: 'date', type: 'date'},
        { name: 'totalPrice', type: 'float'}
    ],

    hasMany: {model: 'accounting.InvoiceLine', name: 'invoiceLines'},
    
    autoLoad: false,
    
    proxy: {
        type : 'rest',
        pageParam: false, //to remove param "page"
        startParam: false, //to remove param "start"
        limitParam: false, //to remove param "limit"
        url: 'rest/accounting-service'
    }
    
});