Ext.define('GF.controller.members.profile.PhotoScreen', {
    extend : 'Ext.app.Controller',
    requires: ['GF.view.members.profile.ImageCropWindow'],
    
    views: [
        'members.profile.PhotoScreen'
    ],

    refs : [
       { ref: 'form', selector: '#members-profile-imageForm' },
       { ref: 'memberImg', selector: '#memberImg' },
       { ref: 'editPhotoBtn', selector: '#members-profile-editphoto' }
    ],
    
    cropData: null, // this will be set by the view
        
    init : function() {
        this.control({
            '#members-profile-uploadbtn': {
                click: this.uploadBtnHandler
            },
            '#members-profile-editphoto': {
                click: this.editPhotoHandler
            }
        });
    },
    
    setMember: function(member) {
        this.member = member;
        if (member.get('photo')) {
            var memberImg = this.getMemberImg();
            /* When using the image the image url we must append the timestamp so that the browser does not cache the 
             * image we are working with. This way we can edit and reuse the same image name/url */
            memberImg.setSrc('rest/image/' + member.get('photo') + '?dc=' + new Date().getTime());
        }
    },

    uploadBtnHandler: function() {
        var memberImg = this.getMemberImg();
        
        var form = this.getForm().getForm();
        if(form.isValid()){
            form.submit({
                url: 'rest/file/upload',
                waitMsg: 'Uploading your photo...',
                success: function(fp, o) {
                    Ext.Msg.alert('Success', 'Your photo "' + o.result.imageUrl + '" has been uploaded.');
                    memberImg.setSrc('rest/image/' + o.result.imageUrl + '?dc=' + new Date().getTime());
                    memberImg.doComponentLayout();
                },
                failure: function(form, action) {
                    console.log(action.failureType);
                    console.log(action.result.msg);
                    switch (action.failureType) {
                        case Ext.form.action.Action.CLIENT_INVALID:
                            Ext.Msg.alert('Failure', 'Form fields may not be submitted with invalid values');
                            break;
                        case Ext.form.action.Action.CONNECT_FAILURE:
                            Ext.Msg.alert('Failure', 'Ajax communication failed');
                            break;
                        case Ext.form.action.Action.SERVER_INVALID:
                           Ext.Msg.alert('Failure', action.result.msg);
                   }
                }
                
            });
        }
    },
    
    editPhotoHandler: function() {
        console.log('edit photo...');
        
        var me = this;
        
//        debugger;
        
        
        var memberImg = this.getMemberImg();
        
        var imageURL = 'rest/image/' + this.member.get('photo') + '?dc=' + new Date().getTime(),
        
        cw = new GF.view.members.profile.ImageCropWindow({
          imageUrl: imageURL,
          listeners:{
            save: function(cropWindow){
              // handler if a crop was successfull, and the window was closed

                Ext.Ajax.request({
                  url: 'rest/file/crop',
                  method: 'GET',
//                  waitMsg: 'Cropping your photo...',
                  params: {
                      imageName: this.member.get('photo'), 
                      x: me.cropData.x, 
                      y: me.cropData.y, 
                      width: me.cropData.w, 
                      height: me.cropData.h 
                  },
                  success: function(response){
                      var croppedImageName = response.responseText;
                      // process server response here
                      
                      memberImg.setSrc('rest/image/' + croppedImageName + '?dc=' + new Date().getTime());
                      memberImg.doComponentLayout();
                  },
                  failure : function(xhr){
                      console.log('failure');
                      console.log(xhr);
                  }
              });
            },
            scope: this
          }
        });
        cw.show();
    }
    
});