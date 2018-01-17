Ext.define("GF.view.external.MembershipApplication", {
    extend: 'Ext.form.Panel',
    alias: 'widget.external-membership-application',
    requires:['GF.view.common.Map'],
    
    layout: 'fit',
    
    initComponent: function() {
        var me = this;
        
//        var map = new OpenLayers.Map();
//        var layer = new OpenLayers.Layer.WMS(
//            "Global Imagery",
//            "http://maps.opengeo.org/geowebcache/service/wms",
//            {layers: "bluemarble"}
//        );
//        
//        
//        var map2 = new OpenLayers.Map({
//            projection: "EPSG:900913",
//            layers: [
//                new OpenLayers.Layer.XYZ(
//                    "OpenStreetMap", 
//                    [
//                        "http://otile1.mqcdn.com/tiles/1.0.0/map/${z}/${x}/${y}.png",
//                        "http://otile2.mqcdn.com/tiles/1.0.0/map/${z}/${x}/${y}.png",
//                        "http://otile3.mqcdn.com/tiles/1.0.0/map/${z}/${x}/${y}.png",
//                        "http://otile4.mqcdn.com/tiles/1.0.0/map/${z}/${x}/${y}.png"
//                    ],
//                    {
//                        transitionEffect: "resize"
//                    }
//                ),
//                new OpenLayers.Layer.XYZ(
//                    "Imagery",
//                    [
//                        "http://otile1.mqcdn.com/tiles/1.0.0/sat/${z}/${x}/${y}.png",
//                        "http://otile2.mqcdn.com/tiles/1.0.0/sat/${z}/${x}/${y}.png",
//                        "http://otile3.mqcdn.com/tiles/1.0.0/sat/${z}/${x}/${y}.png",
//                        "http://otile4.mqcdn.com/tiles/1.0.0/sat/${z}/${x}/${y}.png"
//                    ],
//                    {
//                        transitionEffect: "resize"
//                    }
//                )
//            ],
//            center: [0, 0],
//            zoom: 1
//        });
//
//        map2.addControl(new OpenLayers.Control.LayerSwitcher());
//        
//        map.addLayer(layer);

//        var mapPanel = new GeoExt.MapPanel({
////            renderTo: 'gxmap',
////            height: 400,
////            width: 600,
//            map: map2,
//            title: 'A Simple GeoExt Map'
//        });
        
        
        
        
        
        var map = new OpenLayers.Map('map');
        var arrayOSM = ["http://otile1.mqcdn.com/tiles/1.0.0/map/${z}/${x}/${y}.jpg",
                    "http://otile2.mqcdn.com/tiles/1.0.0/map/${z}/${x}/${y}.jpg",
                    "http://otile3.mqcdn.com/tiles/1.0.0/map/${z}/${x}/${y}.jpg",
                    "http://otile4.mqcdn.com/tiles/1.0.0/map/${z}/${x}/${y}.jpg"];
        var arrayAerial = ["http://otile1.mqcdn.com/tiles/1.0.0/sat/${z}/${x}/${y}.jpg",
                        "http://otile2.mqcdn.com/tiles/1.0.0/sat/${z}/${x}/${y}.jpg",
                        "http://otile3.mqcdn.com/tiles/1.0.0/sat/${z}/${x}/${y}.jpg",
                        "http://otile4.mqcdn.com/tiles/1.0.0/sat/${z}/${x}/${y}.jpg"];
       
        var baseOSM = new OpenLayers.Layer.OSM("MapQuest-OSM Tiles", arrayOSM);
        var baseAerial = new OpenLayers.Layer.OSM("MapQuest Open Aerial Tiles", arrayAerial);
      
        map.addLayer(baseOSM);
        map.addLayer(baseAerial);
        map.addControl(new OpenLayers.Control.LayerSwitcher());
        map.setCenter(
            new OpenLayers.LonLat(-71.057205,42.362942).transform(
                new OpenLayers.Projection("EPSG:4326"),map.getProjectionObject()), 16
        );   
        
        var mapPanel = new GeoExt.MapPanel({
//          renderTo: 'gxmap',
//          height: 400,
//          width: 600,
          map: map,
          title: 'MapQuest Tiles'
      });
        
        
        
        
//        this.items.push(mapPanel);
        
        Ext.apply(this, me, {
            items: [mapPanel]
//            
////            layout: {
////                type: 'vbox',
////                align: 'center',
////                pack: 'center'
////            },
//            
//            items: [
//                {
//                    xtype: 'panel',
////                    layout: {
////                        type: 'vbox',
////                        align: 'stretch'
////                    },
//                    defaultType: 'textfield',
//                    items: [
////                        {
////                            xtype: 'user-details'
////                        },
//                        {
//                            xtype: 'common-map'
//                        }     
//                    ],
//                    buttons: [
//                        { text:'Submit Application' }
//                    ]
//                }
//            ]
        });
        
        
        this.callParent(arguments);
    }
});