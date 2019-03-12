const app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    list:{
      goods: { 1: { selected: true } },
      startX: 0, sum: 0,
      isSelectAll: null,
      price: 0
    },url:''
    
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
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
    this.loadGoods();
    var list=this.data.list;
    list.isSelectAll=false;
    list.price=0;
    this.setData({
      list:list
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
  touchStart:function(e){
   
    var x=e.touches[0].clientX;
    this.data.list.startX=x;
    this.data.list.leftPace=[];
    var list=this.data.list;
    this.setData({
      list:list
    })

  },
  touchMove:function(e){
   
    var movedX=e.touches[0].clientX;
    var distance=this.data.list.startX-movedX;
    var index = e.currentTarget.dataset.index;
    var leftPace=this.data.list.leftPace;
    leftPace[index]=-distance;
    var list = this.data.list
    this.setData({
      list:list
    })
  },
  touchEnd:function(e){
    var index = e.currentTarget.dataset.index;
    var endX=e.changedTouches[0].clientX;
    var distance=this.data.list.startX-endX;
    var buttonWidth=60;
    if(distance<0){
      distance=0;
    }else{
      if(distance>=buttonWidth){
        distance=buttonWidth;
      }else{
        if(distance>=buttonWidth/2){
          distance=buttonWidth;
        }else{
          distance=0;
        }
      }
    }
    var itemArray=this.data.list.leftPace;
    itemArray[index]=-distance;
    var list = this.data.list
    this.setData({
      list:list
    })

  },
  add:function(e){
    var index = e.currentTarget.dataset.index;
    var sum = this.data.list.goods[index].sum +1;
    if (sum > 10) {
      sum = 10;
    }
    this.data.list.goods[index].sum = sum;
    var list = this.data.list
    this.setData({
      list:list
    })
  },
  subtract:function(e){
    var index=e.currentTarget.dataset.index;
    var sum = this.data.list.goods[index].sum- 1;
    if (sum < 0) {
      sum = 0;
    }
    this.data.list.goods[index].sum=sum;
    var list = this.data.list
    this.setData({
      list:list
    })
  },
  deleteGoods:function(e){
    var url = app.globalData.myHttp + '/shopping?method=delete'
    
    var username2 = encodeURI(app.globalData.userInfo.nickName)
    var username = encodeURI(username2);
    var index = e.currentTarget.dataset.index;
    var goods=index;
    
    this.data.list.leftPace[index]=0;
    wx.request({
      url: url,
      data:{
        username:username,
        goods:index
      }
    })
    this.data.list.leftPace=[];
    var list=this.data.list;
    this.setData({
      list:list
    }),
    this.loadGoods();
    
  },
  loadGoods:function(){
    var self=this;
    var url = app.globalData.myHttp + '/shopping?method=loadGoods';
   
    var username = encodeURI(app.globalData.userInfo.nickName)
    
    wx.request({
      url: url,
      success:function(res){
        
        self.data.list.goods = res.data
        var list = self.data.list
        self.setData({
          list:list
          
        })

      },
      data:{
        username:username
      }
    })
  },
  selectGoods:function(e){
    var price = this.data.list.price;
    var index = e.currentTarget.dataset.index;
    if(!this.data.list.goods[index].isSelect){
      
      price += (this.data.list.goods[index].price * this.data.list.goods[index].sum);
      
      this.data.list.goods[index].isSelect = true;
    }else{
      price -= (this.data.list.goods[index].price * this.data.list.goods[index].sum);
      this.data.list.goods[index].isSelect = false;
    }
     if(price<0){
       price=0;
     }
    
    
    
    this.data.list.price=price;
    var list = this.data.list;
    this.setData({
      list:list
    })
  },
  selectAll:function(){
    var price=0;
   
    if(this.data.list.isSelectAll){
      this.data.list.isSelectAll=false;
      for (var i in this.data.list.goods) {
        
        this.data.list.goods[i].isSelect = false;
      }
    }else{
      for (var i in this.data.list.goods) {
        price += (this.data.list.goods[i].price * this.data.list.goods[i].sum);
        this.data.list.goods[i].isSelect=true;
      }
      this.data.list.isSelectAll = true;
    }
    var isSelectAll = this.data.list.isSelectAll;
    this.data.list.price=price;
    
    this.data.list.isSelectAll=isSelectAll;
    console.log("isSelectAll:",isSelectAll);
    var list=this.data.list;
    this.setData({
      list:list
    })
  },
  order:function(){
    var goods=[];
    
    for (var i in this.data.list.goods){
      if(this.data.list.goods[i].isSelect){
        goods.push(this.data.list.goods[i]);
      }
    }
    if(goods.length<=0){
      wx.showModal({
        title: '警告',
        content: '请选择商品',
        showCancel:false
      })
      return;
    }
    goods=JSON.stringify(goods);
    wx.navigateTo({
      url: '../order/order?goods='+goods,
    })
  }
})