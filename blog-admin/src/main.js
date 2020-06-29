import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from './assets/router'
import mavonEditor from 'mavon-editor'  //引入mavon-editor 就是上面所安装的
import 'mavon-editor/dist/css/index.css';
Vue.use(mavonEditor); //让Vue使用mavonEditor
Vue.use(ElementUI);
Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
