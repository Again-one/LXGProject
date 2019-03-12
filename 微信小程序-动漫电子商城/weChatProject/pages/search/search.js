const app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    goods:{},
    clear:false,
    searchValue:'',
    url:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      url:app.globalData.myHttp
    })
  },

  
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  focusSearch:function(e){

  },
  searchChangeInput:function(e){
    console.log("搜索")
    var val = e.detail.value;
    this.setData({
      clear:true,
      searchValue:val
    })
  },
  searchClear:function(e){
    console.log("点击")
  },
  searchSubmit:function(e){
    wx.showLoading({
      title: '搜索',
     
    })
    var url=app.globalData.myHttp+"/search?method=search"
    var search=this.data.searchValue;
    search=encodeURI(search);
    var self=this;
    wx.request({
      url: url,
      data:{
        search:search
      },
      success:function(res){
        wx.hideLoading();
        self.setData({
          goods:res.data
        })
      },fail:function(){
        wx.hideLoading();
        wx.showModal({
          title: '错误',
          content: '服务器没打开',
          showCancel:false
        })
      }
    })
  },
  click:function(e){
    var data = e.currentTarget.dataset;
    console.log(data.picture)

    wx.navigateTo({
      url: '../detail/detail?id=' + data.id + '&detail=' + data.detail + '&summary=' + data.summary + '&picture=' + data.picture + "&size=" + data.size + "&id=" + data.id,
    })
  }
})