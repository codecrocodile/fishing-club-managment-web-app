
Ext.define('GF.controller.Main', {
	extend : 'Ext.app.Controller',

//	defaultScreen: 'catch-record-screen',
//	defaultController: 'members.catchrecord.CatchRecordScreen',
//	 defaultScreen: 'event-screen',
//	 defaultController: 'admin.events.EventScreen',
//	defaultScreen: 'home-screen',
//	defaultController: 'members.home.HomeScreen', 
	 
//	defaultScreen: 'user-details-screen',
//	defaultController: 'users.UsersDetailsScreen',
	
	defaultScreen: 'settings-screen',
    defaultController: 'admin.settings.SettingsScreen',
	
	
	currentController: null,
	
	views : ['Main'],
	
	refs : [
		{ ref : "screenContainer", selector : '#screen-container' }
	],
	
	init : function() {
		this.control({
	        '#homeScreenBtn' : {
	            click : function(source, event, eOpts){
                    var screenToShow = 'home-screen';
                    var controller = GF.app.getController('members.home.HomeScreen');
                    this.showScreen(screenToShow, controller);
                }
	        },
	        '#fishingLogBtn' : {
                click : function(source, event, eOpts){
                    var screenToShow = 'catch-record-screen';
                    var controller = GF.app.getController('members.catchrecord.CatchRecordScreen');
                    this.showScreen(screenToShow, controller);
                }
	        },   
	        '#profileScreenBtn' : {
	            click : function(source, event, eOpts){
	                var screenToShow = 'profile-screen';
	                var controller = GF.app.getController('members.profile.ProfileScreen');
	                this.showScreen(screenToShow, controller);
	            }
	        },
			'#recordCatchBtn' : {
				click : function(source, event, eOpts){
					var screenToShow = 'catch-record-screen';
					var controller = GF.app.getController('members.catchrecord.CatchRecordScreen');
					this.showScreen(screenToShow, controller);
				}
			},
			'#membersScreenBtn' : {
				click : function(source, event, eOpts){
					var screenToShow = 'user-details-screen';
					var controller = GF.app.getController('users.UsersDetailsScreen');
					this.showScreen(screenToShow, controller);
				}
			},
			'#membershipApplicationsBtn' : {
				click : function(source, event, eOpts){
					var screenToShow = 'incoming-applications-screen';
					var controller = GF.app.getController('admin.applications.IncomingApplicationsScreen');
					this.showScreen(screenToShow, controller);
				}
			},
	        '#eventsBtn' : {
	            click : function(source, event, eOpts){
	                var screenToShow = 'event-screen';
	                var controller = GF.app.getController('admin.events.EventScreen');
	                this.showScreen(screenToShow, controller);
	            }
	        },
	        '#settingsBtn' : {
                click : function(source, event, eOpts){
                    var screenToShow = 'settings-screen';
                    var controller = GF.app.getController('admin.settings.SettingsScreen');
                    this.showScreen(screenToShow, controller);
                }
            },
	        '#screen-container': {
	            beforerender: this.setDefaultScreen
	        },
            '#saveBtn': {
                click: this.save
            },
	        '#cancelBtn': {
	            click: this.cancel
            }
		});
	},
	
	setDefaultScreen: function() {
	    var controller = GF.app.getController(this.defaultController);
        this.showScreen(this.defaultScreen, controller);
	},
	
	/**
	 * Switches screen shown calling appropriate life cycle method on the screen controllers.
	 */
	showScreen: function(screenToShow, controller) {
	    var screen, 
	        screenContainer = this.getScreenContainer();
	    
		console.log('showing screen: ' + screenToShow);

		if (this.currentController !== null && Ext.isFunction(this.currentController.exitingScreen)) {
            this.currentController.exitingScreen();
        }
		
		screen = screenContainer.getLayout().setActiveItem(screenToShow);
		
		this.currentController = controller;
		
		if (Ext.isFunction(this.currentController.enteringScreen)) {
		    // must ensure that screen components are rendered before doing any screen set-up tasks
		    if (screen !== false && !screen.rendered) {
		        screen.on('afterrender', this.currentController.enteringScreen, this.currentController, {single: true});
		    } else if (screen !== false && screen.rendered)  {
		        this.currentController.enteringScreen();
		    }
		}
	},
	
	save: function() {
	    console.log('main.save');
	    this.currentController.save();
	},
	cancel: function() {
	    console.log('main.cancel');
	    this.currentController.cancel();
	}

});
