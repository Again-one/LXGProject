const app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    message:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var self = this;
    var username = app.globalData.userInfo.nickName;
    username = encodeURI(username);
    var url = app.globalData.myHttp + '/message?method=message'
    wx.request({
      url: url,
      data: {
        username: username
      },
      success: function (res) {

        self.setData({
          message: res.data,
          selectId:0
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
  ,selectAddress:function(e){
    var index=e.currentTarget.dataset.index;
    this.setData({
      selectId:index
    })
  },
  ok:function(){
    app.globalData.currentMessage=this.data.selectId;
   wx.navigateBack({
     
   })
  },
  delete2:function(e){
    if(this.data.message.length==0){
      wx.showModal({
        title: '警告',
        content: '没有选择数据',
        
        showCancel:false
      })
      return;
    }
    var self=this;
    var index=this.data.selectId;
    wx.showLoading({
      title: '删除',
    })
    var username = app.globalData.userInfo.nickName;
    var mid=self.data.message[index].mid;
    console.log(username);
    username=encodeURI(username);
    var url = app.globalData.myHttp + '/message?method=deleteMessage'
    wx.request({
      url: url,
      data:{
        username:username,
        mid:mid
      }
      ,
      success:function(res){
        console.log(res.data)
        wx.hideLoading();
        self.data.message.splice(index,1);
        console.log(self.data.message);
        if(res.data.state=="1"){
          
          wx.showToast({
            title: '删除成功',
            success:function(){

              self.setData({
                message:self.data.message
              })
            }
          })
        }else{
          wx.showModal({
            title: '错误',
            content: '请稍后再试',
            showCancel: false
          })
        }
      },
      fail:function(){
        wx.hideLoading();
        wx.showModal({
          title: '错误',
          content: '网络错误',
          showCancel:false
        })
      }
    })
  },
  add:function(){
    wx.redirectTo({
      url: '../form/form',
    })
  }
})