// pages/account2/account2.js
const app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    goods: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      url: app.globalData.myHttp
    })
    var oid=options.oid;
    console.log(options)
    wx.showLoading({
      title: '加载',
    })
    var username = app.globalData.userInfo.nickName;
    username = encodeURI(username);
    var self = this;
    var url = app.globalData.myHttp +'/order?method=getOrderInShopping';
    wx.request({
      url: url,
      data:{
        oid:oid,
        username:username
      },success:function(res){
        wx.hideLoading();
        self.setData({

          goods:res.data

        })
      },fail:function(){
        wx.hideLoading();
        wx.showModal({
          title: '错误',
          content: '请检查网络',
          showCancel:false
        })
      }
    })
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
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  }
})