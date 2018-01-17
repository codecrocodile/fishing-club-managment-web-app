Ext.define("GF.view.members.home.NoticeBoard", {
    extend: 'Ext.view.View',
    alias: 'widget.noticeboard',
    requires: ['GF.view.members.home.CommentComponent', 'Ext.util.Format'],
    
    commentMap: new Ext.util.HashMap(),
    
    itemSelector: 'div.gf-post',

    initComponent: function() {
      var me = this;

      Ext.applyIf(me, {
          tpl: new Ext.XTemplate(
          '<tpl for=".">',
          
              '<div class="gf-post" postId="{postId}">',
              
                  '<div class="gf-single-post">',
                  
                      '<div class="gf-post-main">',
                        '<div class="gf-post-author">',
                            '<div class="edit-post-holder"></div>',
                            '<div class="gf-post-author-edit" style="float: right">',
                                '<img src="./resources/images/pencil-small.png" class="gf-post-edit"/>',
                                '<img src="./resources/images/cross-small.png" class="gf-post-delete"/>',
                            '</div>',
                        
                            '<img src="./resources/images/placeholder-photo-thumb.png"/>',
                            '<span class="userName">{userName:htmlEncode}</span>',
                            '<span class="postTime">{date:date("d-M-y H:i:s")}</span>',
                            '<div class="clear"></div>',
                        '</div>',
                        '<p class="gf-text">{text:htmlEncode}</p>',
                      '</div>',
                      
                      '<div class="gf-post-comment">', //TODO templates expect an array so either we convert associations to array or we use a fffuuunction to create the code for the aaaccosiations
                      
                          '<tpl for="getPostComments">',
                          
                              '<tpl if="xcount &gt; 3 && xindex === 1">', // change 1
                                  '<div class="show-more-message"><span class="click-span">View {[ xcount - 2]} more comment(s)</span></div>',
                              '</tpl>',
                              
                              '<tpl if="xcount &gt; 3 && xindex === 3 && this.isKeepOpen(parent.postId)">', // change 2
                                  '<div class="show-hide">',
                              '<tpl elseif="xcount &gt; 3 && xindex === 3">',
                                  '<div class="show-hide" style="opacity: 1; display: none;">',
                              '</tpl>',
                              
                              '<div class="gf-comment" postCommentId="{postCommentId}">',
                                  '<div class="edit-comment-holder"></div>',
                                  '<div class="gf-comment-post">',
                                      '<div class="gf-comment-image-container">',
                                          '<img src="./resources/images/placeholder-photo-thumb.png" style=""/>',
                                      '</div>',    
                                      '<div class="gf-comment-container">',
                                          '<div class="gf-post-author-edit" style="float: right">',
                                              '<img src="./resources/images/pencil-small.png" class="gf-comment-edit" />',
                                              '<img src="./resources/images/cross-small.png" class="gf-comment-delete" />',
                                          '</div>',
                                          
                                          '<span class="gf-comment-username">{userName:htmlEncode}</span>',
                                          '<span class="gf-comment-text">{text:htmlEncode}</span>',
                                          '<span class="gf-comment-datatime">{date:date("d-M-y H:i:s")}</span>',
                                      '</div>',
                                      '<div class="clear"></div>',
                                  '</div>',
                              '</div>',
                              
                              '<tpl if="xcount &gt; 3 && xcount === xindex">', // change 3
                                  '</div><!-- end hide div -->',
                              '</tpl>',
                              
                          '</tpl>',
                          
                          '<div class="gf-post-comment-bar">',
                              '<span class="comment-button click-span">Comment</span>',
                              '<div class="holder"></div>',
                          '</div>',
                          
                      '</div>', //end gf-post-comment
                      
                  '</div>', // end gf-single-post
                  
              '</div>', // end gf-post
              
              '<tpl if="xcount === xindex">',
                  '<div id="gf-noticeboard-end"><span class="show-more-posts click-span">Show More</span><div>',
              '</tpl>',
              
          '</tpl>',
          {
              isKeepOpen: function(postId) {
                  if (me.commentMap.get(postId) !== undefined && me.commentMap.get(postId) === 'expanded') {
                      return true;
                  }
                  return false;
              }
          }
      )
          
      });

      me.callParent(arguments);
    },
    
    listeners: { 
        'afterrender': function(noticeboard, eOps) {
            
            noticeboard.getEl().addListener('click', function(evt, el, o) {
                var editImage = Ext.fly(el);
                editImage.setVisibilityMode(Ext.Element.VISIBILITY);
                editImage.setVisible(false); 
                
                var postElement = editImage.findParent('.gf-post');
                var viewIndex = postElement.viewIndex;
                var postId = postElement.viewRecordId;
                
                var commentBar = editImage.findParent('.gf-post-author');
                var editPostHolder = Ext.fly(commentBar).query('.edit-post-holder')[0];
                
                var postCommentText = noticeboard.getStore().getById(postId);
                
                var comp = Ext.create('GF.view.members.home.CommentComponent', {
                    renderTo: editPostHolder,
                    postCommentText: postCommentText.get('text')
                });
                
                comp.addListener('cancel', function() {
                    editImage.setVisible(true); 
                    noticeboard.refreshNode(viewIndex); // refresh row to show new comment
                });
                comp.addListener('post', function(commentText) {
                    noticeboard.fireEvent('editPost', commentText, postId, viewIndex);
                    editImage.setVisible(true); 
                });     
                
            }, this, {delegate: '.gf-post-edit'});
            
            
            
            
            
            
            
            
            noticeboard.getEl().addListener('click', function(evt, el, o) {
                var deleteImage = Ext.fly(el);
                var postElement = deleteImage.findParent('.gf-post');
                var viewIndex = postElement.viewIndex;
                var postId = postElement.viewRecordId;
                
                Ext.MessageBox.confirm('Delete Post', 'Are you sure you want delete this post and all comments?', function(buttonId) {
                    if (buttonId === 'yes') {
                        noticeboard.fireEvent('deletePost', postId, viewIndex);
                    }
                });
                
            }, this, {delegate: '.gf-post-delete'});
            
            
            
            
            
            
            
            noticeboard.getEl().addListener('click', function(evt, el, o) {
                
                var editImage = Ext.fly(el);
                editImage.setVisibilityMode(Ext.Element.VISIBILITY);
                editImage.setVisible(false); 
                
                var postElement = editImage.findParent('.gf-post');
                var viewIndex = postElement.viewIndex;
                var postId = postElement.viewRecordId;
                
                var commentElement = editImage.findParent('.gf-comment');
                var commentExtJsElement =  Ext.fly(commentElement);
                var postCommentId = Number(commentExtJsElement.getAttribute('postCommentId')); // convert string to number 
                
                var editPostHolder = Ext.fly(commentElement).query('.edit-comment-holder')[0];
                
                var postComment = noticeboard.getStore().getById(postId).getPostComments().getById(postCommentId);
                
                var comp = Ext.create('GF.view.members.home.CommentComponent', {
                    renderTo: editPostHolder,
                    postCommentText: postComment.get('text')
                });
                
                comp.addListener('cancel', function() {
                    editImage.setVisible(true); 
                    noticeboard.refreshNode(viewIndex); // refresh row to show new comment
                });
                comp.addListener('post', function(commentText) {
                    noticeboard.fireEvent('editPostComment', commentText, postId, postCommentId, viewIndex);
                    editImage.setVisible(true); 
                });     
                
                
            }, this, {delegate: '.gf-comment-edit'});
            
            
            
            
            
            
            noticeboard.getEl().addListener('click', function(evt, el, o) {
                var deleteImage = Ext.fly(el);
                var postElement = deleteImage.findParent('.gf-post');
                var commentElement = deleteImage.findParent('.gf-comment');
                var commentExtJsElement =  Ext.fly(commentElement);
                var postCommentId = Number(commentExtJsElement.getAttribute('postCommentId')); // convert string to number 
                var viewIndex = postElement.viewIndex;
                var postId = postElement.viewRecordId;
                
                Ext.MessageBox.confirm('Delete Post', 'Are you sure you want delete this comment?', function(buttonId) {
                    if (buttonId === 'yes') {
                        noticeboard.fireEvent('deleteComment', postId, postCommentId, viewIndex);
                    }
                });
            }, this, {delegate: '.gf-comment-delete'});
            
            // comment on post
            noticeboard.getEl().addListener('click', function(evt, el, o) {
                var commentLink = Ext.fly(el);
                commentLink.setVisibilityMode(Ext.Element.VISIBILITY);
                commentLink.setVisible(false); 
                
                var postElement = commentLink.findParent('.gf-post');
                var viewIndex = postElement.viewIndex;
                var postId = postElement.viewRecordId;
                
                var commentBar = commentLink.findParent('.gf-post-comment-bar');
                var holder = Ext.fly(commentBar).down('.holder');
                var comp = Ext.create('GF.view.members.home.CommentComponent', {
                    renderTo: holder
                });
                comp.addListener('cancel', function() {
                    commentLink.setVisible(true); 
                    noticeboard.refreshNode(viewIndex); // refresh row to show new comment
                });
                comp.addListener('post', function(commentText) {
                    noticeboard.fireEvent('postComment', commentText, postId, viewIndex);
                    commentLink.setVisible(true); 
                });
                
            }, this, {delegate: '.comment-button'});
            
            // show more comments on the post
            noticeboard.getEl().addListener('click', function(evt, el, o) {
                var commentLink = Ext.fly(el);
                var postElement = commentLink.findParent('.gf-post');
                var postId = postElement.viewRecordId;
                
                var showHideDiv = Ext.fly(el).up('div').up('div').query('.show-hide')[0];
                Ext.fly(showHideDiv).setVisibilityMode(Ext.Element.DISPLAY);

                var showHideText = Ext.fly(el).query('span.click-span')[0];
                
                if (Ext.fly(showHideDiv).isVisible()) {
                    this.commentMap.add(postId, 'collapsed');
                    Ext.fly(showHideDiv).hide(true);
                    Ext.fly(showHideText).update('View more comments');
                } else {
                    this.commentMap.add(postId, 'expanded');
                    Ext.fly(showHideDiv).show(true);
                    Ext.fly(showHideText).update('View less comments');
                }
                
                noticeboard.doComponentLayout();
            }, this, {delegate: '.show-more-message'});
            
            // show more posts
            noticeboard.getEl().addListener('click', function(evt, el, o) {
                var store = noticeboard.getStore();
                if (store.getTotalCount() !== store.getCount()) {
                    store.loadPage(store.currentPage + 1, {
                        addRecords: true
                    });
                } else {
                    Ext.fly(el).setVisible(false);
                }
                
            }, this, {delegate: '.show-more-posts'});
            
            noticeboard.doComponentLayout();
        }
    }
});