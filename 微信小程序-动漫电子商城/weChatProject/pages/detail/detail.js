const app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    imageUtils:[

      'https://img.alicdn.com/bao/uploaded/i1/797480344/TB1nP.ZcRDH8KJjy1zeXXXjepXa_!!0-item_pic.jpg_430x430q90.jpg'

    ],
    id:1,
    detail:"",
    summary2:"sdfsd",
    sum:'0',
    picture:0,
    d_image:[0,1,2,3,4,5,6,7,8,9,10],
    size:0,
    url:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

      this.setData({
        id:options.id,
        detail:options.detail,
        summary2:options.summary,
        picture:options.picture,
        size:options.size,
        url:app.globalData.myHttp
      })
      var url3 = app.globalData.myHttp + '/shopping?method=sum';
     
      var self = this;
      var username = encodeURI(app.globalData.userInfo.nickName)
      
      
      wx.request({
        url: url3,
        header: {
          'content-type': 'application/json'
        },
        success: function (res) {
          self.setData({
            sum:res.data.sum
          })
          
        },data:{
          username:username
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
    
  },
  switchCar:function(){

    wx.switchTab({
      url: '../cart/cart',
    })

  },
  addCart:function(){
    var url2 = app.globalData.myHttp + '/shopping?method=addGoods';
    
    var username = encodeURI(app.globalData.userInfo.nickName)
    
   
    var goods=this.data.id;
   
    
    wx.request({
      url: url2,
      data:{
        username:username,
        goods:goods
      }
    })
    var sum2 = parseInt(this.data.sum) +1; 
    this.setData({
      sum: sum2
    })
    
  }
  , imgClick:function(e){
    var that = this;
    var nowImgUrl = e.currentTarget.dataset.url;
  
  
      wx.previewImage({
        current: nowImgUrl, // 当前显示图片的http链接
        urls:[nowImgUrl]
      })
    
  }
})