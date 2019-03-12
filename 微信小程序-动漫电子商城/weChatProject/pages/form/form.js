const app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    
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
  bindSave:function(e){
    var self=this;
  
    var link=e.detail.value.link;
    var mobile=e.detail.value.mobile;
    var city=e.detail.value.city;
    var address=e.detail.value.address;
    var code=e.detail.value.code;
    if(link==''){
      wx.showModal({
        title: '提示',
        content: '请输入联系人姓名',
        showCancel:false
      })
      return;
    }
    if (mobile == '') {
      wx.showModal({
        title: '提示',
        content: '请输入手机号码',
        showCancel: false
      })
      return;
    }
    if (city == '') {
      wx.showModal({
        title: '提示',
        content: '请输入地区',
        showCancel: false
      })
      return;
    }
    if (address == '') {
      wx.showModal({
        title: '提示',
        content: '请输入详细地址',
        showCancel: false
      })
      return;
    }
    if (code == '') {
      wx.showModal({
        title: '提示',
        content: '请输入邮政编码',
        showCancel: false
      })
      return;
    }
    wx.showLoading({
      title: '加载中',
    })
    var url = app.globalData.myHttp + '/message?method=addMessage';
    var message={};
    var username = app.globalData.userInfo.nickName
    username=encodeURI(username)
    message.link=link;
    message.mobile=mobile;
    message.city=city;
    message.address=address;
    message.code=code;
    message=JSON.stringify(message);
    message=encodeURI(message)
    wx.request({
      url: url,
      data:{
        message:message,
        username:username
      },
      header:{
        'content-type': 'text/html;charset=utf-8'
      },success:function(res){
        console.log(res.data)
        if(res.data.state=="0"){
          wx.showModal({
            title: '错误',
            content: res.data.message,
            showCancel:false
          })
          
        }else{
          
          wx.navigateBack({
            
          })
        }
      }
    })
  }
})