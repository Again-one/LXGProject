const app=getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    message:{},
    currentMessage:{},
    goods:[],
    price:0,url:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      url: app.globalData.myHttp
    })
    var goods=options.goods
    goods=JSON.parse(goods);
    var price=0;
    for(var i in goods){
      price += parseInt(goods[i].price) * parseInt(goods[i].sum);
    
    }
    this.setData({
      goods:goods,
      price:price
    })
  },
  showMessage:function(){
    var self = this;
    var username = app.globalData.userInfo.nickName;
    var url = app.globalData.myHttp + '/message?method=message'
    username = encodeURI(username);
    wx.request({
      url: url,
      data: {
        username: username
      },
      success: function (res) {
        var currentMessage = '';
        if (app.globalData.currentMessage == null) {
       
          currentMessage = res.data[0]
        } else {
          
          var currentMessage2 = res.data[app.globalData.currentMessage];
          if(currentMessage2==null){
            currentMessage = res.data[0]
          }else{
            currentMessage=currentMessage2;
          }
          
        }
        self.setData({
          message: res.data,
          currentMessage: currentMessage
        })
      }
    })
  }
  ,

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.showMessage();
    
    
    
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
  addAdress:function(){
    wx.navigateTo({
      url: '../form/form',
    })
  },
  selectMessage:function(){
    var self=this;
    var message=JSON.stringify(self.data.message)
    wx.navigateTo({
      url: '../selectOrder/selectOrder?message='+message,
      
    })
  },
  submit:function(e){
    var self=this;
    if(this.data.message.length<=0){
      wx.showModal({
        title: '警告',
        content: '请填写一个送货地址',showCancel:false
      })
      return;
    }
    wx.showLoading({
      title: '订单生成',
    })
    var username = app.globalData.userInfo.nickName;
    username=encodeURI(username);
    var goods=this.data.goods;
    var goodsOid=goods;
    goods=JSON.stringify(goods)
    //跳转购物界面需要的goods
    var goodsAC=goods;
    goods=encodeURI(goods);
    var message = this.data.currentMessage;
    
    message=JSON.stringify(message);
    //挑战购物界面需要的message
    var messageAC=message;
    message=encodeURI(message);
    var url = app.globalData.myHttp + '/order?method=addOrder'
    var price=this.data.price;
    wx.request({
      url: url,
      data:{
        username:username,
        goods:goods,
        message:message,
        price:price
      },
      success:function(res){
        wx.hideLoading();
        var oid=res.data.oid;
        goodsOid[0].oid=oid;
        goodsOid=JSON.stringify(goodsOid)
        
        
       
        wx.redirectTo({
          url: '../account/account?goods='+goodsOid+"&message="+messageAC+"&price="+self.data.price+"&oid="+oid,
        })
      },
      fail:function(){
        wx.hideLoading();
        wx.showToast({
          title: '失败',
        })
      }
    })
  }
})