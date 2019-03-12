const app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    order:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log("sdflasdfjasdf");
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
    console.log("sdflasdfjasdf");
    var self=this;
    var url = app.globalData.myHttp + '/order?method=order'
    var username=app.globalData.userInfo.nickName;
    username=encodeURI(username);
    wx.request({
      url: url,
      data:{
        username:username
      },
      success:function(res){
        
        self.setData({

          order:res.data
        })
      }
    })
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
    
  },
  deleteOrder:function(){
    if(this.data.order.length<=0){
      wx.showModal({
        title: '警告',
        content: '暂时没有订单',
        showCancel:false
      })
    }
    var self=this;
    var url = app.globalData.myHttp + '/order?method=deleteOrder'
    var username=app.globalData.userInfo.nickName;
    username=encodeURI(username)
    wx.showLoading({
      title: '删除订单',
    })
    wx.request({
      url: url,
      success:function(res){
        
        wx.hideLoading();
        var state=res.data.state;
        if(state=="1"){
          
          wx.showToast({
            title: '删除成功',
          });
          self.setData({
            order:[]
          })

        }else{
          wx.showModal({
            title: '警告',
            content: '出现网络错误',
          })
        }
      },
      data:{
        username:username
      }
    })
  },
  goShopping:function(){
    wx.switchTab({
      url: '../index/index',
    })
  },
  getOrderInShopping:function(e){
    var id=e.currentTarget.dataset.id;
    var oid=this.data.order[id].oid;
    var url = app.globalData.myHttp + '/order?method=getOrderInShopping'
    var username = app.globalData.userInfo.nickName;
    username = encodeURI(username)
    wx.request({
      url: url,
      data:{
        username:username,
        oid:oid
      },
      success:function(res){
        var goods=res.data;
        goods=JSON.stringify(goods);
        console.log(res)
        wx.navigateTo({
          url: '../account/account?goods='+goods+"&isB=1",
        })
      },fail:function(){
        wx.showToast({
          title: '错误',
        })
      }
    })
    
  }
})