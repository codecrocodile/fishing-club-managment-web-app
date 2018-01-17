Ext.define('GF.controller.users.RegistrationWizard', {
    extend : 'Ext.app.Controller',

    views : [ 'users.RegistrationWizard' ],

    refs : [ 
        { ref : 'registrationWizard', selector : '#registrationWizard' },
        { ref : 'userDetails', selector : '#userDetails' },
        { ref : 'membershipDetails', selector : '#membershipDetails' },
        { ref : 'backBtn', selector : '#backBtn' },
        { ref : 'nextBtn', selector : '#nextBtn' }
    ],

    init : function() {

        this.control({
            '#backBtn': {
                click : function(source, event, eOpts) {
                    this.navigate('back');
                }   
            },
            '#nextBtn' : {
                click : function(source, event, eOpts) {
                    this.navigate('next');
                }  
            },
            '#createMemberBtn' : {
                click : function(source, event, eOpts) {
                    this.createNewMember();
                }  
            }
        });
    },
    
    navigate: function(direction) {
        var cardLayout = this.getRegistrationWizard().getLayout();
        
        if (cardLayout.getActiveItem().getItemId() === 'userDetails') {
            if (this.verifyUserDetails() === true) {
                return;    
            }
        } else if (cardLayout.getActiveItem().getItemId() === 'membershipDetails'){
            if (this.verifyMembershipDetails() === true) {
                return;    
            }
        } 
        
        if (direction === 'next') {
            cardLayout.next();
        } else {
            cardLayout.prev();
        }
        
        if (cardLayout.getActiveItem().getItemId() === 'userDetails') {
            this.getBackBtn().setDisabled(true);
            this.getNextBtn().setDisabled(false);
        } else if (cardLayout.getActiveItem().getItemId() === 'createContainer'){
            this.getBackBtn().setDisabled(false);
            this.getNextBtn().setDisabled(true);
        } else {
            this.getBackBtn().setDisabled(false);
            this.getNextBtn().setDisabled(false);
        }
    },
    
    /*
     * Verify the minimum details required to enter a new member. It might be that 
     * they just want to get them into the system then get the new member to fill
     * in the details.
     */
    verifyUserDetails: function() {
        // minimum required: forename, surname, email
        var userDetailsForm = this.getUserDetails(),
            validators  = Ext.data.validations,
            forenameTxt, surnameTxt, dobDf, emailTxt, 
            validationError = false,
            focusComp = null;
        
        forenameTxt = userDetailsForm.queryById('forenameTxt');
        surnameTxt = userDetailsForm.queryById('surnameTxt');
        dobDf = userDetailsForm.queryById('dobDf');
        emailTxt = userDetailsForm.queryById('emailTxt');
        
        if (validators.email(null, emailTxt.getValue()) === false){
            emailTxt.markInvalid('This field should be an e-mail address in the format "user@example.com"');
            focusComp = emailTxt;
            validationError = true;
        }
        
        if (validators.presence(null, dobDf.getValue()) === false){
            dobDf.markInvalid('This field is required');
            focusComp = dobDf;
            
            validationError = true;
        }
        
        if (validators.presence(null, surnameTxt.getValue()) === false){
            surnameTxt.markInvalid('This field is required');
            focusComp = surnameTxt;
            validationError = true;
        }
        
        if (validators.presence(null, forenameTxt.getValue()) === false){
            forenameTxt.markInvalid('This field is required');
            focusComp = forenameTxt;
            validationError = true;
        }
        
        if (focusComp) {
            focusComp.focus();
        }
        
        return validationError || 
            (emailTxt.validate() === false) ||
            (dobDf.validate() === false) || 
            (surnameTxt.validate() === false) || 
            (forenameTxt.validate() === false) ; // gets any other validation errors on the component;
        
    },
    
    verifyMembershipDetails: function() {
        // minimum required: start date
        var userDetailsForm = this.getMembershipDetails(),
            validators  = Ext.data.validations,
            startDateDf, 
            validationError = false,
            focusComp = null;
        
        startDateDf = userDetailsForm.queryById('startDateDf');
        
        if (validators.presence(null, startDateDf.getValue()) === false){
            startDateDf.markInvalid('This field is required');
            focusComp = startDateDf;
            
            validationError = true;
        } 
        
        if (focusComp) {
            focusComp.focus();
        }
        
        return validationError || (startDateDf.validate() === false); // gets any other validation errors on the component
    },
    
    createNewMember: function() {
        var newMember = Ext.create('GF.model.members.Member');
        this.getUserDetails().updateRecord(newMember);
        this.getMembershipDetails().updateRecord(newMember);
        this.saveNewMember(newMember);
    },
    
    saveNewMember: function(newMember) {
        GF.util.AppMask.show();
        newMember.save({
            scope: this,
            success: function(newMemberFromServer) {
                this.getController('users.UsersDetailsScreen').addNewMember(newMemberFromServer);
                this.getRegistrationWizard().close();
                GF.util.AppMask.hide();
            },
            failure: function (xhr) {
                GF.util.ExceptionHandler.handleException(xhr);
                GF.util.AppMask.hide();
            }
        });
    }

});