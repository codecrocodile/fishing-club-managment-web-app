Ext.define('GF.store.home.MonthFishCatch', {
    extend: 'Ext.data.Store',
    model: 'GF.model.home.MonthFishCatch',
    data:[
          {month: 'Jan', catchNumber: 0},
          {month: 'Feb', catchNumber: 1},
          {month: 'Mar', catchNumber: 30},
          {month: 'Apr', catchNumber: 20},
          {month: 'May', catchNumber: 10},
          {month: 'Jun', catchNumber: 50},
          {month: 'Jul', catchNumber: 55},
          {month: 'Aug', catchNumber: 33},
          {month: 'Sep', catchNumber: 20},
          {month: 'Oct', catchNumber: 2},
          {month: 'Nov', catchNumber: 8},
          {month: 'Dec', catchNumber: 70}
    ]
});