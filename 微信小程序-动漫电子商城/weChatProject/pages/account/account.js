const app= getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
   goods:[],
   message:{},
   price:0,
   url:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if(options.isB!=null){
      
      var goods = options.goods;
      goods=JSON.parse(goods)
      var price=0;
      for(var i in goods){
        console
        price+=goods[i].price*goods[i].sum;
      }
      this.setData({
        goods: goods,
        price:price,
      })
      console.log(goods)
    }else{
      var goods;
      var oid=options.oid;
      
      goods= JSON.parse(options.goods);
      
      
      var message = JSON.parse(options.message);
      var price;
      
      price= options.price;
      console.log(goods)
      this.setData({
        goods: goods,
        message: message,
        price: price
      })
    }
    this.setData({
      url: app.globalData.myHttp
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

  },
  pay:function(){
   
    var oid = this.data.goods[0].oid;
    wx.showModal({
      title: '支付',
      content: '确认支付',
      success:function(res){
        if(res.cancel==true){
          return;
        }
        wx.showLoading({
          title: '正在支付',
        })
        
        //再进行支付
        var url = app.globalData.myHttp + '/pay?method=pay'
        wx.request({
          url: url,
          data:{
            
            oid:oid
          },
          success:function(res){
            if(res.data['state']==1){
              wx.hideLoading();
              wx.showModal({
                title: '支付成功',
                content: '等待卖家发货',
                showCancel:false,
                success:function(){
                  wx.navigateBack({
                    delta: 1,
                  })
                }
              })
            }else{
              wx.hideLoading();
              wx.showModal({
                title: '错误',
                content: '请检查网络',
                showCancel:false
              })
            }
          }
              
          
        })
      },fail:function(){
        
      }
    })
  },
  cancelFK:function(){
    wx.navigateTo({
      url: '../openOrder/openOrder',
    })
  },
  deleteDD:function(){
    var username = app.globalData.userInfo.nickName;
    username = encodeURI(username);
    var url=app.globalData.myHttp+'/order?method=deleteOneOrder'
    var oid=this.data.goods[0].oid;
    wx.request({
      url: url,
      data:{
        username:username,
        oid:oid
      },
      success:function(res){
        if(res.data['state']==1){
          wx.navigateBack({
            
          })
        }else{
          wx.showModal({
            title: '错误',
            content: '服务器或许已关闭',
            showCancel:false
          })
        }
      }
    })
  }
  
})