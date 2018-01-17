Ext.define('GF.model.accounting.InvoiceLine', {
    extend: 'Ext.data.Model',
    
    idProperty: 'invoiceLineId',
    
    fields: [
        { name: 'invoiceLineId', type: 'int' },
        { name: 'invoiceId', type: 'int' },
        { name: 'productId', type: 'int' },
        { name: 'productQuantity', type: 'int' },
        { name: 'productName', type: 'string' },
        { name: 'productDescription', type: 'string' },
        { name: 'totalPrice', type: 'float' }
    ]

});