Ext.define('GF.controller.members.home.HomeScreen', {
    extend : 'Ext.app.Controller',
    
    views: [
        'members.home.HomeScreen', 
        'members.home.CatchLogGrid'
    ],
    stores: [
        'home.OutingFishCatch', 
        'home.MonthFishCatch',
        'members.catchrecord.CatchLog',
        'home.Post',
        'home.PostComment'
    ],

    refs : [
       { ref: 'postEntryTxa', selector: '#postEntryTxa' },
       { ref: 'noticeboard', selector: '#noticeboard' }
    ],
    
    init : function() {
        this.control({
            '#postBtn': {
                click: function() {
                    var me = this;
                    var newPost, 
                        postTxa = this.getPostEntryTxa();
                    
                    if (postTxa.getValue().length === 0) {
                        return;
                    } 
                    
                    GF.util.AppMask.show('Posting...');
                    
                    newPost = Ext.create('GF.model.home.Post', {postId: null, accountId: null, userId: null, userName: null, userImage: null, date: null, text: postTxa.getValue()});
                    newPost.save({
                        scope: me,
                        success: function(newPostFromServer) {
                            this.getHomePostStore().insert(0, newPostFromServer);
                            postTxa.setValue('');
                            GF.util.AppMask.hide();
                        },
                        failure: function (xhr) {
                            GF.util.ExceptionHandler.handleException(xhr);
                            GF.util.AppMask.hide();
                        }
                    });
                }
            },
            '#noticeboard': {
                deletePost: function(postId, viewIndex) {
                    var store = this.getHomePostStore(),
                        post = store.getById(postId);
                    
                    GF.util.AppMask.show('Deleting...');
                    
                    // Must use remove then sync instead of destroying the model directly. I think this is an extjs bug
                    // but it seems to have trouble removing from view when called destroy method on model.
                    store.remove(post);
                    store.sync({
                        scope: this, //controller would be accessible inside load
                        success: function (xhr) {
                            this.getNoticeboard().refreshNode(viewIndex); // refresh row to show new comment
                            this.getNoticeboard().doComponentLayout(); // do layout to tell scroll bars to adjust
                            GF.util.AppMask.hide();
                        },
                        failure: function (xhr) {
                            GF.util.ExceptionHandler.handleException(xhr);
                            GF.util.AppMask.hide();
                        }
                    });
                },
                
                deleteComment: function(postId, postCommentId, viewIndex) {
                    var store = this.getHomePostStore(),
                        post = store.getById(postId),
                        postCommentStore = post.getPostComments(),
                        postComment = postCommentStore.getById(postCommentId);
                    
                    GF.util.AppMask.show('Deleting...');
                    
                    // bug with extjs model destroy(). it doesn't call the callback methods so direct ajax request
                    Ext.Ajax.request({
                        url :  GF.util.Services.DASHBOARD_SERVICE + '/post-comment/' + postComment.getId(),
                        method: 'DELETE', // because params are being sent
                        success : function(xhr) {
                            postCommentStore.remove(postComment);
                            this.getNoticeboard().refreshNode(viewIndex); // refresh row to show new comment
                            this.getNoticeboard().doComponentLayout(); // do layout to tell scroll bars to adjust
                            GF.util.AppMask.hide();
                        },
                        failure : function(xhr) {
                            GF.util.ExceptionHandler.handleException(xhr);
                            GF.util.AppMask.hide();
                        },
                        scope: this
                    });

                },
                
                postComment: function(comment, parentPostId, viewIndex) {
                    var me = this,
                        post = this.getHomePostStore().getById(parentPostId),
                        postCommentStore = post.getPostComments(),
                        newPostComment = Ext.create('GF.model.home.PostComment', {
                            postId: parentPostId,
                            text: comment
                        });
                    
                    if (comment.length === 0) {
                        this.getNoticeboard().refreshNode(viewIndex); // refresh row to show new comment
                        this.getNoticeboard().doComponentLayout(); // do layout to tell scroll bars to adjust
                        return;
                    } 
                    
                    GF.util.AppMask.show('Posting...');
                    
                    newPostComment.save({
                        scope: me,
                        success: function(newPostCommentFromServer) {
                            postCommentStore.insert(0, newPostCommentFromServer);
                            
                            // TODO save to db ad get user details populated in comments so that we can display in comment post
                            this.getNoticeboard().refreshNode(viewIndex); // refresh row to show new comment
                            this.getNoticeboard().doComponentLayout(); // do layout to tell scroll bars to adjust
                            GF.util.AppMask.hide();
                        },
                        failure : function(xhr) {
                            GF.util.ExceptionHandler.handleException(xhr);
                            GF.util.AppMask.hide();
                        }
                    });
                },
                
                editPost: function(comment, postId, viewIndex) {
                    var me = this,
                        post = this.getHomePostStore().getById(postId);
                    
                    GF.util.AppMask.show('Posting...');
                    
                    post.set('text', comment);
                    
                    post.save({
                        scope: me,
                        success: function(xhr) {
                            this.getNoticeboard().refreshNode(viewIndex); // refresh row to show new comment
                            this.getNoticeboard().doComponentLayout(); // do layout to tell scroll bars to adjust
                            GF.util.AppMask.hide();
                        },
                        failure : function(xhr) {
                            GF.util.ExceptionHandler.handleException(xhr);
                            GF.util.AppMask.hide();
                        }
                    });
                    
                },
                
                editPostComment: function(comment, postId, postCommentId, viewIndex) {
                    var me = this,
                        post = this.getHomePostStore().getById(postId),
                        postComment = post.getPostComments().getById(postCommentId);
                    
                    
                    GF.util.AppMask.show('Posting...');
                    
                    postComment.set('text', comment);
                    
                    //TODO due to bbug in extjs (i think fail doesn't give you response object buut this will)
                    postComment.getProxy().on('exception', function(proxy, response, operation) {
                        debugger;
                        var json = Ext.decode(response.responseText);
                        GF.util.ExceptionHandler.handleException(json);
                        GF.util.AppMask.hide();
                        
                    }, this, {single: true});
                    
                    postComment.save({
                        scope: me,
                        success: function(xhr, dataOperation) {
                            debugger;
                            
                            this.getNoticeboard().refreshNode(viewIndex); // refresh row to show new comment
                            this.getNoticeboard().doComponentLayout(); // do layout to tell scroll bars to adjust
                            GF.util.AppMask.hide();
                        },
                        failure : function(xhr, dataOperation) {
                            
                            debugger;
//                            
//                            GF.util.ExceptionHandler.handleException(xhr);
//                            GF.util.AppMask.hide();
                        }
                    });
                    
                }
            }
        });
    },
    
    enteringScreen: function() {
        var homePostStore = this.getHomePostStore(),
            catchLogStore = this.getMembersCatchrecordCatchLogStore();
        homePostStore.loadPage(1);
        catchLogStore.loadPage(1);
    }
    
});