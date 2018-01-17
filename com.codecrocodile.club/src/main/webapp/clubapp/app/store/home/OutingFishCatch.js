Ext.define('GF.store.home.OutingFishCatch', {
    extend: 'Ext.data.Store',
    model: 'GF.model.home.OutingFishCatch',
    alias: 'outingfishcatch',
    data:[
          {outings: 5, catchNumber: 0},
          {outings: 10, catchNumber: 1},
          {outings: 15, catchNumber: 2},
          {outings: 60, catchNumber: 3}
    ]
});