 Ext.define('GF.controller.members.catchrecord.CatchRecordScreen', {
    extend : 'Ext.app.Controller',
    
    views : [
        'members.catchrecord.CatchRecordScreen'
    ],
    
    stores: [
        'members.catchrecord.CatchRecord',
        'members.catchrecord.CatchRecordItem',
        'common.Weather'
    ],
    
    refs : [
        { ref : "venueCbo", selector : '#venueCbo' },
        { ref : "venueDf", selector : '#venueDf' },
        { ref : "dateDp", selector : '#dateDp' },
        { ref : "catchRecordGrid", selector : '#catchRecordGrid' },
        { ref : "mapPanel", selector : '#mapPanel' },
        { ref : "notesTxa", selector : '#notesTxa' },
        { ref : "weatherComponent", selector : '#weathercomponent' }
    ],
    
    currentCatchRecordModel: undefined,
    
    init : function() {
        this.control({
            '#venueCbo': {
                change: this.venueChange
            },
            '#dateDp': {
                change: this.dateChange
            },
            '#mapPanel': {
                mapClicked: this.addCatchRecord
            },
            '#catchRecordGrid': {
                removeCatchRecord: this.removeCatchRecord
            }
        });
    },
    
    /**
     * Life cycle method
     */
    enteringScreen: function() {
        this.initVenueSelection();
        this.getDateDp().setValue(new Date());
    },
    
    /**
     * Life cycle method
     */
    exitingScreen: function() { },
    
    initVenueSelection: function() {
        var venue, venueDesc, 
            venueStore = this.getVenueCbo().getStore();

        if (venueStore.count() === 0) {
            this.getVenueCbo().setVisible(false);
            this.getVenueDf().setVisible(true);
            this.getVenueDf().setValue('TBC'); 
        } else if (venueStore.count() === 1) {
            this.getVenueCbo().setVisible(false);
            this.getVenueDf().setVisible(true);
            venue = venueStore.first();
            this.getVenueCbo().setValue(venue);
            venueDesc = venue.get('description');
            venueDesc.setValue(venueDesc);
        } else {
            this.getVenueCbo().setVisible(true);
            this.getVenueDf().setVisible(false);
            venue = venueStore.first();
            this.getVenueCbo().setValue(venue);
        }
    },
    
    venueChange: function( combo, newValue, oldValue, eOpts ) {
        var venueStore, venue, date, map; 
        venueStore = this.getVenueCbo().getStore();
        venue = venueStore.getById(newValue);
        map = this.getMapPanel().map;
        
        var lonlat = new OpenLayers.LonLat(venue.get('lon'), venue.get('lat'));
        var fromProjection = new OpenLayers.Projection("EPSG:4326");
        var toProjection = map.getProjectionObject();
        lonlat.transform(fromProjection, toProjection);
        
//        debugger;
        
        map.setCenter(lonlat, venue.get('zoom'), false, false);
        
        date = this.getDateDp().getValue();
        this.weatherChange(venue.getId(), date);
    },
    
    weatherChange: function(venueId, date) {
        Ext.Ajax.request({
            url : GF.util.Services.WEATHER_SERVICE + '/singleDayWeather',
            method: 'GET',
            params: {
                'venueId': venueId,
                'date': date 
            },
            success : function(xhr) {
                /* Note ExtJS does not have a way to directly create a model object from JSON, so we load it into the 
                 * store then get the first record which will be the model we want. */
                if (xhr.responseText !== '') {
                    var jsonData = Ext.JSON.decode(xhr.responseText);
                    var store = this.getCommonWeatherStore();
                    store.loadRawData(jsonData, false);
                    
                    var weather = store.first();
                    var weatherHourlyStore = weather.getHourly();
                    var weatherHourly = weatherHourlyStore.first();
                    var weatherIconUrlStore = weatherHourly.getWeatherIconUrl();
                    var weatherIconUrl = weatherIconUrlStore.first();
                    var weatherDescStore = weatherHourly.getWeatherDesc();
                    var weatherDesc = weatherDescStore.first();
                    var weatherData = {
                        tempAndWeatherDescription: weatherHourly.get('tempC') + ' &#x2103; ' + weatherDesc.get('value'),
                        weatherImage: weatherIconUrl.get('value'),
                        windDescription: weatherHourly.get('windspeedMiles') + ' mph from the ' + weatherHourly.get('winddir16Point'),
                        precipMM: weatherHourly.get('precipMM'),
                        cloudcover: weatherHourly.get('cloudcover'),
                        humidity: weatherHourly.get('humidity'),
                        visibility: weatherHourly.get('visibility')
                    };
                    
                    var weatherComponent = this.getWeatherComponent();
                    weatherComponent.update(weatherData);
                } 
            },
            failure : function(xhr) {
                console.log("Error: " + xhr.statusText);
            },
            scope: this
        });
    },
    
    dateChange: function( datefield, newValue, oldValue, eOpts ) {
        Ext.Ajax.request({
            url : GF.util.Services.CATCH_RECORD + '/catch-record-by-date',
            method: 'POST', // because params are being sent
            params: {
                dateParam: newValue //Ext.Date.format(newValue, 'Y-m-d')
            },
            success : function(xhr) {
//                debugger;
                
                /* Note ExtJS does not have a way to directly create a model object from JSON, so we load it into the 
                 * store then get the first record which will be the model we want. */
                if (xhr.responseText !== '') {
                    
                    var jsonData = Ext.JSON.decode(xhr.responseText);
                    var store = this.getMembersCatchrecordCatchRecordStore();
                    store.loadRawData(jsonData, false);
                    var model = store.first();
                    this.populateScreen(model);
                    
                    this.currentCatchRecordModel = model;
                } else {
                    this.currentCatchRecordModel = Ext.create('GF.model.members.catchrecord.CatchRecord');
                    var store = this.getMembersCatchrecordCatchRecordStore();
                    store.removeAll();
                    this.populateScreen(this.currentCatchRecordModel);
                }
            },
            failure : function(xhr) {
                console.log("Error: " + xhr.statusText);
            },
            scope: this
        });
    },
    
    populateScreen: function(catchRecordModel) {
        this.clearScreen();
        
        var associatedStore = catchRecordModel.catchRecordItems();
        associatedStore.each(function(catchRecordItemModel) {
            this.createCatchRecord(catchRecordItemModel);
        }, this);
        
        this.getNotesTxa().setValue(catchRecordModel.get('fishingNotes'));
    },
    
    clearScreen: function() {
        var map = this.getMapPanel().map;
        var markerLayer = map.getLayersByName('markers')[0];
        
        var store = this.getCatchRecordGrid().getStore();
        store.each(function(catchRecordItemModel) {
//            debugger;
            markerLayer.removeMarker(catchRecordItemModel.get('olMarker'));
        }, this);
        
        this.getCatchRecordGrid().getStore().removeAll();
        
        
        this.getNotesTxa().setValue('');
    },

    addCatchRecord: function(lonlat) {
        var catchRecord = Ext.create('GF.model.members.catchrecord.CatchRecordItem', {
            venueId: this.getVenueCbo().getValue(),
            lon: lonlat.lon,
            lat: lonlat.lat,
            timePeriod: 'Unknown',
            weight: 0,
            killed: true
        });
        this.createCatchRecord(catchRecord);
    },
    
    createCatchRecord: function(catchRecordItem) {
        var map, 
            lonLat, 
            markerLayer, 
            store;
        
        // set marker on the map
        map = this.getMapPanel().map;
        markerLayer = map.getLayersByName('markers')[0];
        
        lonLat = new OpenLayers.LonLat(catchRecordItem.get('lon'), catchRecordItem.get('lat'));
        var fromProjection = new OpenLayers.Projection("EPSG:4326");
        var toProjection = map.getProjectionObject();
        lonLat.transform(fromProjection, toProjection);
        
        marker = new OpenLayers.Marker(lonLat);
        markerLayer.addMarker(marker);
        
        // set marker on the catch record item
        catchRecordItem.set('olMarker', marker);
        
        // add the catch record item to the store
        store = this.getCatchRecordGrid().getStore();
        store.add(catchRecordItem);
    },
    
    removeCatchRecord: function(gridStore, catchRecord) {
        var map = this.getMapPanel().map;
        var markerLayer = map.getLayersByName('markers')[0];
        
        gridStore.remove(catchRecord);
        markerLayer.removeMarker(catchRecord.get('olMarker'));
    },
    
    populateModel: function() {
        // init vars
        var catchRecordItems, 
            date, 
            fishingNotes, 
            associatedStore, 
            i;  

        // collect values from screen
        date = this.getDateDp().getValue();
        fishingNotes = this.getNotesTxa().getValue();
        catchRecordItems = this.getCatchRecordGrid().getStore().getRange();
        
        // set values on the current model
        this.currentCatchRecordModel.set('date', date);
        this.currentCatchRecordModel.set('fishingNotes', fishingNotes);
        
        associatedStore = this.currentCatchRecordModel.catchRecordItems();
        associatedStore.removeAll(true);
        
        var map = this.getMapPanel().map;
        var markerLayer = map.getLayersByName('markers')[0];
        
        for (i = 0; i < catchRecordItems.length; i++) {
            markerLayer.removeMarker(catchRecordItems[i].get('olMarker'));
            catchRecordItems[i].set('olMarker', undefined);
            
            associatedStore.add(catchRecordItems[i]);    
        }
    },
    
    /* Common controller methods */
    
    /**
     * Controller method
     */
    save: function() {
        this.populateModel();
        
        var model = this.currentCatchRecordModel;
        var data = model.getData(true);
        var json = Ext.JSON.encode(data);
        
        Ext.Ajax.request({
            url : GF.util.Services.CATCH_RECORD + '/saveCatchRecord',
            method: 'POST', // because params are being sent
            jsonData: json,
            success : function(xhr) {
                /* Note ExtJS does not have a way to directly create a model object from JSON, so we load it into the 
                 * store then get the first record which will be the model we want. */
                if (xhr.responseText !== '') {
                    var jsonData = Ext.JSON.decode(xhr.responseText);
                    var store = this.getMembersCatchrecordCatchRecordStore();
                    store.loadRawData(jsonData, false);
                    var model = store.first();
//                    debugger;
                    this.populateScreen(model);
                   
                    this.currentCatchRecordModel = model;
                } else {
                    this.currentCatchRecordModel = Ext.create('GF.model.members.catchrecord.CatchRecord');
                }
            },
            failure : function(xhr) {
                console.log("Error: " + xhr.statusText);
            },
            scope: this
        });
    },
    
    /**
     * Controller method
     */
    cancel: function() {
        console.log('cancel catch record');
    }
    
});
