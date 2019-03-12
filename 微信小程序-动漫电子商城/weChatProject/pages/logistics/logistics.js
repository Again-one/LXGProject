const app=getApp();
Page({

  /**
   * 页面的初始数据
   */

  data: {
    logistics:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    
  },

 


  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    wx.showLoading({
      title: '加载',
    })
    var url=app.globalData.myHttp+'/logistics?method=all';
    var username = app.globalData.userInfo.nickName;
    username = encodeURI(username);
    var self=this;
    wx.request({
      url: url,
      data:{
        username:username
      },
      success:function(res){
        wx.hideLoading();
        console.log(res.data)
        self.setData({
          logistics:res.data
        })
      },fail:function(){
        wx.hideLoading();
        wx.showModal({
          title: '错误',
          content: '请检查服务器',
        })
      }
    });
  },

  click:function(e){
    console.log(e)
    var oid = this.data.logistics[e.currentTarget.dataset.id].oid
    
    wx.navigateTo({
      url: '../account2/account2?oid='+oid,
    })
  }


  
})