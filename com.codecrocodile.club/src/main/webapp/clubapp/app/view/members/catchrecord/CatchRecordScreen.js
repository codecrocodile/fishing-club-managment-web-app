Ext.define("GF.view.members.catchrecord.CatchRecordScreen", {
    extend: 'Ext.panel.Panel',
    xtype: 'catch-record-screen',
    requires:[
        'GeoExt.panel.Map', 
        'GF.view.members.catchrecord.WeatherComponent'
    ],
    itemId: 'catch-record-screen',
    
    layout: {
        type: 'border'
    },
    
    initComponent: function() {
    	var me = this;

    	Ext.apply(this, me, {
    	    
    		items:[
		       {
		           xtype: 'panel',
		           title: 'Single click on map to enter record',
		           region: 'center',
		           layout: 'fit',
		           tools:[
		               { 
		                   xtype: 'combo', 
		                   itemId: 'venueCbo',
		                   fieldLabel: 'Venue',
		                   store: 'lookup.Venue',
		                   valueField: 'venueId',
		                   displayField: 'description',
		                   queryMode: 'local', // because this a lookup we have already created and populated on app loading
		                   labelWidth: 45,
		                   minWidth: 250,
		                   padding: '0 10 0 0'
		               },
		               {
		                   itemId: 'venueDf',
		                   xtype: 'displayfield',
		                   fieldLabel: 'Venue',
		                   labelWidth: 45,
		                   value: 'TBC',
		                   padding: '0 10 0 0'
		               }
		           ],
		           items: [
		               this.createMapPanel()
		           ]
		       },
		       {
		           xtype: 'container',
		           region: 'east',
                   layout: 'border',
                   split: true,
                   width: 500,
                   items: [
                       {
                           xtype: 'panel',
                           title: 'Catch Details',
                           region: 'center',
                           autoScroll: true,
                           tools: [
                               {
                                   itemId: 'dateDp',
                                   xtype: 'datefield',
                                   colspan: 2,
                                   fieldLabel: 'Date',
                                   labelWidth: 40,
                                   name: 'date',
                                   maxValue: new Date(),  // limited to the current date or prior,
                                   value: new Date(),
                                   format: GF.util.AppConfig.DATE_FORMAT
                               }
                           ],
                           items:[
                               {
                                   xtype: 'container',
                                   layout: {
                                       type: 'vbox',
                                       align: 'stretch',
                                       pack: 'center'
                                   },
                                   defaults: {
                                       padding: '5 5 5 5'    
                                   },
                                   items:[
                                      {
                                          xtype: 'gridpanel',
                                          itemId: 'catchRecordGrid',
                                          store: 'members.catchrecord.CatchRecordItem',
                                          plugins: [
                                              new Ext.grid.plugin.CellEditing({
                                                  clicksToEdit: 1
                                              })                                                    
                                          ],
                                          columns: [
                                              {
                                                  menuDisabled: true,
                                                  sortable: false,
                                                  hideable: false,
                                                  xtype: 'actioncolumn',
                                                  width: 30,
                                                  items: [
                                                      {
                                                          itemId: 'deleteCatchRecordBtn',
                                                          iconCls: 'delete',
                                                          tooltip: 'Delete Record',
                                                          handler: function(grid, rowIndex, colIndex) {
                                                              var rec = grid.getStore().getAt(rowIndex);
                                                              this.up('gridpanel').fireEvent('removeCatchRecord', grid.getStore(), rec);
                                                          }
                                                      }
                                                  ]
                                              },
                                              { 
                                                  text: 'Venue',  
                                                  dataIndex: 'venueId', 
                                                  flex: 1,
                                                  renderer: function(value) {
                                                      var venueStore = Ext.data.StoreManager.lookup('lookup.Venue'),
                                                          venue = venueStore.getById(value);
                                                      
                                                      if (venue) {
                                                          return venue.get('description');
                                                      } else {
                                                          return 'Unknown'; // this should never happen
                                                      }
                                                  }
                                              },
                                              { 
                                                  text: 'Time Period',  
                                                  dataIndex: 'timePeriod', 
                                                  flex: 1,
                                                  editor: new Ext.form.field.ComboBox({
                                                      typeAhead: true,
                                                      triggerAction: 'all',
                                                      store: [
                                                          ['Unknown', 'Unknown'],
                                                          ['Morning', 'Morning'],
                                                          ['Afternoon', 'Afternoon'],
                                                          ['Evening', 'Evening'],
                                                          ['Night', 'Night']
                                                      ]
                                                  })
                                              },
                                              { 
                                                  text: 'Weight', 
                                                  dataIndex: 'weight', 
                                                  flex: 1,
                                                  editor: {
                                                      xtype: 'numberfield',
                                                      allowBlank: false,
                                                      minValue: 0,
                                                      maxValue: 1000,
                                                      decimalPrecision: 2,
                                                      step: 0.25
                                                  },
                                                  renderer: function(value){
                                                      if (value === 0) {
                                                          return 'Unknown';
                                                      } else {
                                                          return value;
                                                      }
                                                  }
                                              },
                                              {
                                                  xtype: 'checkcolumn',
                                                  text: 'Killed',
                                                  dataIndex: 'killed',
                                                  editor: {
                                                      xtype: 'checkbox'
                                                  }
                                              }
                                          ],
                                          viewConfig: {
                                              emptyText: 'Single click on map to enter record',
                                              deferEmptyText: false
                                          },
                                          height: 220
                                      },
                                      {
                                          itemId: 'notesTxa',
                                          xtype     : 'textareafield',
                                          fieldLabel: 'Fishing Notes',
                                          labelAlign: 'top',
                                          height: 170
                                      }
                                   ]
                               }
                           ]
                        },
                        {  
                            xtype: 'panel',
                            title: 'Weather',
                            region: 'south',
                            height: 230,
                            items: [
                                {
                                    xtype: 'weathercomponent',
                                    itemId: 'weathercomponent'
                                }
                            ]
                        } 
                   ]
		        }
		     ]
    	});
    	
    	this.callParent(arguments);
    },
    
    createMapPanel: function() {
        
        var map = new OpenLayers.Map('map', {
            attribution: ''
        });
        map.addControl(new OpenLayers.Control.MousePosition({
            prefix: 'You are here: ',
            separator: ' | ',
            numDigits: 5,
            emptyString: 'Mouse is not over map.',
            displayProjection: "EPSG:4326"
        }));  
        var arrayOSM = ["http://otile1.mqcdn.com/tiles/1.0.0/map/${z}/${x}/${y}.jpg",
                    "http://otile2.mqcdn.com/tiles/1.0.0/map/${z}/${x}/${y}.jpg",
                    "http://otile3.mqcdn.com/tiles/1.0.0/map/${z}/${x}/${y}.jpg",
                    "http://otile4.mqcdn.com/tiles/1.0.0/map/${z}/${x}/${y}.jpg"];
        var baseOSM = new OpenLayers.Layer.OSM("MapQuest-OSM Tiles", arrayOSM, {
            attribution: ''
        });
        var markers = new OpenLayers.Layer.Markers("markers");
      
        map.addLayer(baseOSM);
        map.addLayer(markers);
        map.setCenter(new OpenLayers.LonLat(0,0));
        
        var mapPanel = Ext.create('GeoExt.panel.Map', {
            itemId: 'mapPanel',
            map: map
        });
        
        OpenLayers.Control.Click = OpenLayers.Class(OpenLayers.Control, {   
            mapPanel: mapPanel,
            
            defaultHandlerOptions: {
                'single': true,
                'double': false,
                'pixelTolerance': 0,
                'stopSingle': false,
                'stopDouble': false
            },

            initialize: function(options) {
                this.handlerOptions = OpenLayers.Util.extend(
                    {}, this.defaultHandlerOptions
                );
                OpenLayers.Control.prototype.initialize.apply(
                    this, arguments
                ); 
                this.handler = new OpenLayers.Handler.Click(
                    this, {
                        'click': this.trigger
                    }, this.handlerOptions
                );
            }, 

            trigger: function(e) {
                var lonlat = map.getLonLatFromPixel(e.xy);
                var toProjection = new OpenLayers.Projection("EPSG:4326");
                var fromProjection = map.getProjectionObject();
                lonlat.transform(fromProjection, toProjection);
                
                this.mapPanel.fireEvent('mapClicked', lonlat);
            }

        });
        var click = new OpenLayers.Control.Click();
        map.addControl(click);
        click.activate();
        
        return mapPanel;
    }
});


