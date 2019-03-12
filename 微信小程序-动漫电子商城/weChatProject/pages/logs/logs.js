//logs.js
const util = require('../../utils/util.js')
const app=getApp();
Page({
  data: {
    goods:[
      {
        image: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509531953626&di=f286f9a6e3862801528059b6ca596a84&imgtype=0&src=http%3A%2F%2Fimg5.niutuku.com%2Fphone%2F1212%2F0135%2F0135-niutuku.com-13176.jpg'

      },
      {
        image: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509531953629&di=21c4a7decbf6261bf0c7d8dc2c5842a6&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201603%2F11%2F20160311102620_iSPGe.jpeg',

      },
      {
        image: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509531953629&di=16a188b585d77e1da2b6d3cf186293f4&imgtype=0&src=http%3A%2F%2F2t.5068.com%2Fuploads%2Fallimg%2F151113%2F48-151113133T4.jpg'

      }
    ],image2:["_10", "_20", "_50"],
    id:0,
    click:true
  },
  onLoad: function (options) {
    console.log(options.id)
    var self=this;
    this.setData({
      // logs: (wx.getStorageSync('logs') || []).map(log => {
      //   return util.formatTime(new Date(log))
      // })
      id: options.id
    })
    var url=app.globalData.myHttp+'/card?method=all';
    var id = parseInt(this.data.id) + 1;
    var username = app.globalData.userInfo.nickName;
    username = encodeURI(username);
    wx.request({
      url: url,
      success:function(e){
       
        if (e.data.state==1){
          self.setData({
            click:false
          })
        }
      },data:{
        username: username,
        card: id
      }
    })
    
  },
  click:function(){
    wx.showLoading({
      title: '领取中',
    })
    var id=parseInt(this.data.id)+1;
    var username = app.globalData.userInfo.nickName;
    username = encodeURI(username);
    var url = app.globalData.myHttp + '/card?method=add';
    var self=this;
    wx.request({
      url: url,
      success:function(e){
        wx.hideLoading();
        console.log(e.data.state,(e.data.state==1))
        if (e.data.state == 1) {
          self.setData({
            click: false
          })
        }
      },fail:function(){
        wx.hideLoading();
        wx.showModal({
          title: '错误',
          content: '网络错误',
          showCancel:false
        })
      },
      data:{
        username:username,
        card:id
      }
    })
  }
})
