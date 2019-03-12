//index.js

const app=getApp();
Page({
  data: {
    banner:[

      
      'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509531953629&di=21c4a7decbf6261bf0c7d8dc2c5842a6&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201603%2F11%2F20160311102620_iSPGe.jpeg',
      '../../image/index.jpg',
      'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509531953629&di=16a188b585d77e1da2b6d3cf186293f4&imgtype=0&src=http%3A%2F%2F2t.5068.com%2Fuploads%2Fallimg%2F151113%2F48-151113133T4.jpg'

    ],
    goods: [{ image: '../../image/panel.png', title: '爆卖wacom 数位板 手绘板 电脑绘画板 影拓5', price: '￥268', sale: '2326人付款' }, { image: '../../image/panel.png', title: '爆卖wacom 数位板 手绘板 电脑绘画板 影拓5', price: '￥268', sale: '2326人付款' }, { image: '../../image/panel.png', title: '爆卖wacom 数位板 手绘板 电脑绘画板 影拓5', price: '￥268', sale: '2326人付款' }, { image: '../../image/panel.png', title: '爆卖wacom 数位板 手绘板 电脑绘画板 影拓5', price: '￥268', sale: '2326人付款' }, { image: '../../image/panel.png', title: '爆卖wacom 数位板 手绘板 电脑绘画板 影拓5', price: '￥268', sale: '2326人付款' }]
    ,
    tab:[],
    clickId:1,
    motto: '商城列表',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    url:''
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    this.setData({
      url:app.globalData.myHttp
    })
    console.log(this.goods)
    this.loadGoods();
    this.loadTab();
    
  },loadGoods:function(){
    var self=this;
    var cid=1;
    var url=app.globalData.myHttp+"/cidGoods?method=cid"
    wx.request({
      url: url,
      header:{
        'content-type':'application/json'
      },data:{
        cid:cid
      },
      success:function(res){
          self.setData({
            goods:res.data
          });
          
      }
    })
  },
  loadTab:function(){
    var url=app.globalData.myHttp+'/cid?method=all'
    var self=this;
    wx.request({
      url: url,
      success:function(res){
        self.setData({

          tab:res.data
        })
      }
    })
  },
  getUserInfo: function(e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
   
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },
  getTrainInfo:function(){

    

  },
  navigationDetail:function(){

    wx.navigateTo({
      url: '../detail/detail',
    })

  },
  navigationRecommend:function(){
    wx.navigateTo({
      url: '../recommend/recommend',
    })
  },
  navigationLogistics:function(){
    wx.navigateTo({
      url: '../logistics/logistics',
    })
  },
  showGoods:function(e){
    console.log(e);
    var $data=e.currentTarget.dataset;
    wx.navigateTo({
      url : '../logs/logs?id='+$data.id,
    })

  },
  showDetail:function(e){
    var data=e.currentTarget.dataset;
    
  
    wx.navigateTo({
      url : '../detail/detail?id='+data.id+'&detail='+data.detail+'&summary='+data.summary+'&picture='+data.picture+"&size="+data.size+"&id="+data.id,
    })

  },
  tabclick:function(e){
    var cid=e.currentTarget.dataset.id;
    this.data.goods=[];
    var self=this;
    var clickId=this.data.clickId;
    if(clickId==cid){
      return;
    }
    var url=app.globalData.myHttp+'/cidGoods?method=cid';
    wx.request({
      url: url,
      data:{
        cid:cid
      },
      success:function(res){
        self.setData({
          goods: res.data,
          clickId:cid
        });
      }
    })
  }

})
